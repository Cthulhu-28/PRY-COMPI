data segment
	var db 165 dup (0) ;total 164
	errorMsg db "Exceptis: liber non apertum",13,10,"$"
	varC db 32 dup(0)
	varN dw 0
	line db 10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	ligo proc near
	locals @@
		mov bp,sp
		mov bx,[bp+2]
		mov si,6
	@@ciclo:
		mov ax, [bp+si]
		mov byte ptr [bx], al
		inc bx
		add si,2
		cmp si,70
		jb @@ciclo
	ret	2*34
	ligo endp
	create proc near
		mov bp,sp
		mov cx,5
		mov ah,3ch
		mov dx, offset [bp+2]
		int 21h
		mov bx,128
		mov word ptr [bx], ax
	ret 2
	create endp
	scripturam proc near
	locals @@
		mov bp,sp
		mov di,[bp+2]
		mov bx,di
		add bx,131
		mov si,6
	@@ciclo:
		mov ax, [bp+si]
		mov byte ptr [bx], al
		inc bx
		add si,2
		cmp si,70
		jb @@ciclo
		mov bx,128
		
		mov ah,40h
		mov cx,[bp+4]
		mov bx,di
		add bx,131
		mov dx,bx
		mov bx,di
		add bx,128
		mov bx, word ptr [bx]
		int 21h
	ret 2*34
	scripturam endp
	prope proc near
		mov bp,sp
		mov bx,[bp+2]
		mov ah,3eh
		int 21h
		ret 2
	prope endp
	write proc near
		mov bp,sp
		mov ax,[bp+2]
		
		push bx
		
		mov bx,ax
		and bx ,8000h
		cmp bx,8000h
		jne noSign
		push ax
		mov ah,02
		mov dl,"-"
		int 21h
		pop ax
		dec ax
		xor ax,0ffffh
		
		
	noSign:	
		pop bx
		push AX
		push BX
		push CX
		push DX

		xor cx, cx
		mov bx, 10
	ciclo1: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne ciclo1
		mov ah, 02h
	ciclo2: 
		pop DX
		add dl, 30h
		int 21h
		loop ciclo2

		pop DX
		pop CX
		pop BX
		pop AX
		
		ret 2
	write endp
	read proc near
	locals @@
		mov bp,sp
		mov di,4
	;Saves memory for the first byte of the string
		mov [bp+2],0
	@@cicle:
		mov ah,08h
		int 21h
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key
		cmp di,68
	je @@printchar ;If the number of characters entered is bigger than the string capacity, it does not save it, but print it.
		inc byte ptr [bp+2]
		mov byte ptr [bp+di],al
		add di,2
	@@printchar:
		mov dl,al
		mov ah,02h
		int 21h
	jmp @@cicle	
	@@endCicle:
		cmp di,514
	je @@return ;If the strinf is full, returns.
	@@fill:
		mov [bp+di],0
		add di,2
		cmp di,68
		jne @@fill ;In order to return a full string, it fills the left bytes with 0
	@@return:
		ret
	read endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		xor bx,bx
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,31
		jbe pam
		mov bx,31
		sendI:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI
		lea dx, var
		push dx
		call ligo
		
		lea dx, var
		push dx
		call create
		
		lea dx,line
		mov ah,09h
		int 21h
		
		call read
		xor bx,bx
		pam1:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,31
		jbe pam1
		
		
		mov bx,31
		sendI2:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI2
		lea dx,var
		push dx
		call scripturam
		
		lea dx,var
		push dx
		call prope
		
		mov ax, 4c4dh
		int 21h
code ends
end start