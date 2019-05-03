.model small
.stack 0ffffh 
.data
	varC db 32 dup(0) 
	varN dw 0
    lineBreak db 10,13,"$"


.code
	.386

	cont proc near
		locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov  word ptr [bp+66], ax
		ret 2*32
	cont endp

	lcat proc near
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
		
	lcat endp
    
    snum proc far
		mov bp,sp
		add bp,2
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
	snum endp
	start:
		mov ax,@data 
		mov ds,ax 

		call lcat
		xor bx,bx
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,256
        jbe pam
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
        mov bx,33
        
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
        ja send
        
        call cont
        pop ax

		mov varN, ax
		push ax
		call snum
        

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
        int 21h
        
		mov ax,4c00h
		int 21h
    end start