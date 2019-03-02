
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
		cmp di,514
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
		cmp di,514
		jne @@fill ;In order to return a full string, it fills the left bytes with 0
	@@return:
		ret
		
	read endp
	write proc near
	locals @@
		mov bp,sp
		add bp,2
		mov cx,[bp]
		cmp cx,0
		je @@return
		
	print:
		add bp,2
		mov dx,[bp]
		mov ah,02h
		int 21h
	loop print
	
	@@return:
		ret 2*256 ;Cleans the parameters
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
		mov dl, 13
		

		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam:
			pop ax
			mov var[bx],al
			inc bx
			cmp bx,256
		jbe pam
		
		mov bx,257
		
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,var[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send
		call write
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start