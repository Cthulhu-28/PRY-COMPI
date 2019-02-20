data segment
	var db 256 dup(0)
	stringExpected db 13,10,"Exceptis: catena expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	read proc near
		mov bp,sp
		mov di,4
		;Saves memory for the first byte of the string
		mov [bp+2],0
	;keeps reading the string until the "enter" key
	cicle:
		mov ah,08h
		int 21h
		cmp al,13
	je endCicle
		cmp di,514
	je printchar
		inc byte ptr [bp+2]
		mov byte ptr [bp+di],al
		add di,2
	printchar:
		mov dl,al
		mov ah,02h
		int 21h
	jmp cicle	
	endCicle:
		cmp di,514
		je return
	fill:
		mov [bp+di],0
		add di,2
		cmp di,514
		jne fill
	return:
		ret
	read endp
	write proc near
		
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		

		xor bx,bx
		pam:
			pop ax
			mov var[bx],al
			inc bx
			cmp bx,256
		jbe pam
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start