
data segment
	var db "liber.txt", 155 dup(0),"$" ;total 164
	key db 0
	errorMsg db "Exceptis: liber non apertum",13,10,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	read proc near
	locals @@
		mov bp,sp
		mov ah,3ch
		mov al,02h
		xor cx,cx
		mov dx, offset [bp+2]
		int 21h
		jc @@error
		jmp @@create
	@@create:
		mov bx,ax
	@@cicle:
		mov ah,08h
		int 21h
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key

	@@printchar:
		mov dl,al
		mov ah,02h
		int 21h
		mov ah,40h
		mov cx,1
		mov key,dl
		lea dx,key
		int 21h
	jmp @@cicle	
	@@endCicle:
		mov ah,3eh
		int 21h
		ret 2
	@@error:
		mov ah,09h
		lea dx, errorMsg
		int 21h
		mov ax, 4c4dh
		int 21h
		
	read endp
	write proc near
		mov bp,sp
		;ABRE EL ARCHIVO
		mov ah,3dh
		mov al,02h
		mov dx,offset [bp+2] ;DIRECCIÓN DEL ARCHIVO
		int 21h
		mov bx,ax ;EL HANDLE ESTÁ EN AX
		jc @@error
		jmp @@read
		
	@@read:
		mov ah,3fh
		mov cx,64;El buffer el profe dijo que era de 64 bytes
		mov dx, offset [bp+4]
		int 21h
		mov si,ax
		mov ah,09h
		int 21h
		cmp cx,si
		jne @@return
		push bx
		mov bx,dx
		dec cx
	@@clean:
		mov [bx],0 ;Se limpia el buffer
		inc bx
	loop @@clean
		pop bx
	jmp @@read
	@@return:
		mov ah,3eh
		int 21h
		ret 2*2
	@@error:
		mov ah,09h
		lea dx, errorMsg
		int 21h
		mov ax, 4c4dh
		int 21h
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		lea dx,var
		push dx
		call read
		
		mov ah, 02h
		mov dl, 10
		int 21h
		
		lea dx,var[100];direccion del buffer
		push dx
		lea dx,var
		push dx
		call write
		
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start