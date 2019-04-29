.model small
.stack 0ffffh 
.data
	varC db 04,"hola",27 dup(0)
    varN dw 2
    varN2 dw 4

.code
	.386
	
    cut proc near
        locals @@
        mov bp,sp
        mov cx, 1
        mov bx, [bp+2]
		mov di, 8
		mov si, 8
		inc bx
    @@ciclo1:
        cmp bx, cx
        je @@fin1
		inc cx
		mov byte ptr [bp+di],0
        add di, 2
        jmp @@ciclo1
    @@fin1:
        mov bx, [bp+4]
        mov dx, [bp+6]
        mov ax, [bp+6]
        mov si, di
    @@ciclo2:
        cmp bx, cx
        je @@fin2
        inc cx
        dec dx
		mov ax, [bp+di]
        mov word ptr [bp+si], ax
        add di, 2
        add si, 2
        jmp @@ciclo2
    @@fin2:
        mov word ptr [bp+6], dx
        sub ax, cx
        mov cx, ax
        inc cx
    @@ciclo3:

        loop @@ciclo3
        mov cx, 31
        sub cx, dx
    @@fill:
        mov word ptr [bp+si], 0
        add si, 2
        loop @@fill
        ret 2*2

    cut endp

    lnum proc near
		mov bp,sp
		xor cx,cx
		xor bx,bx
		xor di,di
		xor si, si
		;lee tecla por tecla la entrada. Al dar enter termina el cliclo
		cicle:
			mov ah,08h
			int 21h
			cmp al,13
		je cicleEnd
			cmp al, "-"
			jne notSigned
			cmp si,1
		je sumErrors
			cmp cx,0
			jne notSigned
		signed:
			mov si,1
			jmp printChar
		notSigned:
			cmp al,48
		jb sumErrors
			cmp al,57
		jbe notSum
		sumErrors:
			inc bx
		notSum:
			xor ah,ah
			push ax
			inc cx
		printChar:
			mov ah, 02h
			mov dl,al
			int 21h
		jmp cicle
		
		cicleEnd:
			cmp bx,0
		jne errorI
		jcxz errorI
			mov bx,1
		
		;Convierte el número
		cast:
			pop ax
			;Restar para obtener el número real
			sub ax,48
			mul bx
			add di,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			dec cx
			cmp cx,0
		jne cast
			cmp si,0
		je return
			xor di,0ffffh
			inc di
		return:
			mov [bp+2],di
			ret
		errorI:
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	lnum endp
    

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
    
    writeC proc near
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
		ret 2*32 ;Cleans the parameters
	writeC endp
    
    start:
		mov ax,@data 
		mov ds,ax 
		


		;call lcat
		;xor bx,bx
		;pam:
		;	pop ax
		;	mov varC[bx],al
		;	inc bx
		;	cmp bx,256
        ;jbe pam

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
        int 21h
        
        ;call lnum
        ;pop ax
        ;mov varN, ax

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
        int 21h
        
        ;call lnum
        ;pop ax
        ;mov varN2, ax

        mov bx, 33
        
        
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
        ja send
        
        push varN2
        push varN
		
		
        call cut

        
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
        int 21h
        
        xor bx, bx
        
        pam2:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam2
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		mov bx,33
		
		;Moves into the stack the string var, bottom-to-top
		send2:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send2
		call writeC

		mov ax,4c00h
		int 21h
    end start