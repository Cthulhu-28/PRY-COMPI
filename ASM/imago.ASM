
data segment
	var db 0
	imagoExpected db 13,10,"Exceptis: imago expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	read proc near
	locals @@
		mov bp,sp
		xor cx,cx
	@@cicle:
		mov ah,08h
		int 21h
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key
		xor ah,ah
		push ax
		inc cx
		mov dl,al
		mov ah,02h
		int 21h
	jmp @@cicle	
	@@endCicle:
		cmp cx,1
		ja @@error
		pop ax
		cmp ax,7fh
		ja @@error
		mov [bp+2],ax
		ret
	@@error:
		lea dx,imagoExpected
		mov ah,09h
		int 21h
		mov ax, 4c4dh
		int 21h
	read endp
	write proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call read
		
		;Optional line break, not part of the procedures
		mov ah, 02h
		mov dl, 10
		int 21h
	
		mov ah, 02h
		mov dl, 13
		int 21h
		mov ah, 02h
		mov dl, 7
		int 21h
		

		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		
		pop ax
		
		mov var,al
		
		mov dl,var
		
		push dx
		call write
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start