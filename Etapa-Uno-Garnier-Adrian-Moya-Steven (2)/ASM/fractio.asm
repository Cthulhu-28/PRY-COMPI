

    data segment
	varN dw 0
	varI db 0
	varD db 0
	varC db 32 dup(0)
	varF dw 2 dup (1)
	fractioExpected db 13,10,"Exceptis: fractio expectata",10,13,"$"
	overflow db 13,10,"Exceptis: superfundo",10,13,"$"
	zero db 13,10,"Exceptis: nullus numerus dividatur",10,13,"$"
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
		xor bx,bx
		xor di,di
		xor si, si
		;lee tecla por tecla la entrada. Al dar enter termina el cliclo
		;En cx guarda la cantidad de caracteres leídos
		;En di almacena la posición del último | leído
		;En bx almacena la cantidad de | leídos
		@@cicle:
			mov ah,08h
			int 21h
			;Comparación con el enter
			cmp al,13
		je @@cicleEnd
			cmp al,'|'
			jne @@pipe
			inc bx
			mov di,cx
		@@pipe:
			inc cx
			xor ah,ah
			;Cada tecla digitada se almacena en la pila
			push ax
			mov ah, 02h
			mov dl,al	
			int 21h
		jmp @@cicle
		
		@@cicleEnd:
		;Se intenta detectar errores simples en la entrada:
	
			;A. Comienza con |
			cmp di,0
			je @@errorIInt
			;B. Que haya más de un |
			cmp bx,1
			jne @@errorIInt
			;C. Que lo ingresado no sea al menos 3 caracteres
			cmp cx,3
			jb @@errorIInt
			inc di
			;D. Que el | sea el último caracter introducido
			cmp cx,di
			je @@errorIInt
			dec di
			
		;En di se guardará el denominador
		;En cx se cuenta si hay un número negativo
		;Em bx se trabajará las potencias de 10. Se comienza con 10^0
		mov bx,1
		xor di,di		
		xor cx,cx
		
		@@castDen:
		;Se saca la tecla de la pila y se compara con - y |.
			pop ax
			cmp al,'-'
			je @@SignDen
			cmp al,'|'
			je @@next
			;Se verifica que la tecla esté en el rango de los números
			cmp al,48
			jb @@errorIInt
			cmp al,57
			ja @@errorIInt
			jmp @@noErrorDen
		@@SignDen:
		;Si hay un menos, luego debe seguir un |, sino es error.
			pop ax
			cmp al,'|'
			jne @@errorIInt
			;xor di,0ffffh
			;inc di
			;Se cuenta que hay un menos
			inc cx
			mov bx,1
			jmp @@castNum
		@@noErrorDen:	
			;Se obtiene el valor real
			sub ax,48
			;Se multiplica el número por la potencia de 10 respectiva según su valor posicional.
			mul bx
			;Se incrementa el di con el nuevo valor.
			add di,ax
			mov ax,bx
			;Se pasa a la siguiente potencia de 10.
			mov dx,10
			mul dx
			mov bx,ax
			;Se pregunta en caso de overflow.
			cmp di,8000h
			ja @@errorOInt
		jmp @@castDen
		
		@@errorIInt:
			jmp @@errorI
		@@next:
			mov bx,1
			cmp di,7fffh
			ja @@errorOInt
		@@castNum:
			pop ax
			cmp al,'-'
			je @@SignNum
			cmp al,48
			jb @@errorI
			cmp al,57
			ja @@errorI
			jmp @@noErrorNum
		@@SignNum:
			;xor si,0ffffh
			;inc si
			inc cx
			cmp bp,sp
			je @@gcd
			jmp @@errorI
		@@noErrorNum:	
			sub ax,48
			mul bx
			add si,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp si,8000h
			ja @@errorO
			cmp bp,sp
			je @@nextCheck
		jmp @@castNum
		@@nextCheck:
			cmp si,7fffh
			ja @@errorO
		
		@@gcd:
			mov bp,sp
			cmp si,0
			jne @@notZero
			mov [bp+2],0
			mov [bp+4],1
			ret
		@@errorOint: jmp @@errorO
		@@notZero:
			cmp di,0
			je @@errorZ
			push si
			push di
			call gcd
			pop bx
			mov bp,sp
			xor bh,bh
			mov ax,si
			xor dx,dx
			div bx
			mov [bp+2],ax
			mov ax,di
			div bx
			mov [bp+4],ax
			cmp cx,1
			jne return
		@@signed:
			mov si,[bp+2]
			xor si,0ffffh
			inc si
			mov [bp+2],si
		return:
			ret
		@@errorI:
			lea dx,fractioExpected
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
		@@errorO:
			lea dx,overflow
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
		@@errorZ:
			lea dx,zero
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	read endp
	gcd proc
		locals @@
		mov bp,sp
		mov ax,[bp+2];denominador
		mov dx,[bp+4];numerador
	@@cicle:
		cmp ax,dx
		je @@return
		cmp dx,ax
		jbe @@add
		sub dx,ax
		jmp @@cicle
	@@add:
		sub ax,dx
	jmp @@cicle
	@@return:
		mov [bp+4],dx
		ret 2
	gcd endp
	writeI proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	writeI endp
	writeD proc near
	locals @@
		mov bp,sp
		mov dx,[bp+2]
		mov ah,02h
		cmp dx,0
		je @@false
	@@true:
		mov dl,'v'
		int 21h
		mov dl,'e'
		int 21h
		mov dl,'r'
		int 21h
		jmp @@return
	@@false:
		mov dl,'f'
		int 21h
		mov dl,'a'
		int 21h
		mov dl,'l'
		int 21h
		mov dl,'s'
		int 21h
	@@return:
		mov dl,'i'
		int 21h
		mov dl,'d'
		int 21h
		mov dl,'i'
		int 21h
		mov dl,'c'
		int 21h
		mov dl,'u'
		int 21h
		mov dl,'s'
		int 21h
		ret 2
	writeD endp
	imago proc near
		mov bp,sp
		mov ax,[bp+2]
		mov bx,[bp+4]
		xor dx,dx
        mov cx, 8000h
        and cx, ax
        cmp cx, 8000h
        jne @@sig
        mov cx, 0ffffh
        xor ax, cx
        inc ax
    @@sig:
		div bx
		mov [bp+6],ax
		ret 2*2
	imago endp
	;catena proc near
	;	locals @@
	;	mov bp,sp
	;	mov ax,[bp+2]
	;	mov di,4
	;	mov word ptr [bp+di],0
	;	mov di,6
	;	mov bx,ax
	;	and bx ,8000h
	;	cmp bx,8000h
	;	jne @@noSign
	;	mov word ptr [bp+di],"-"
	;	inc word ptr [bp+4]
	;	dec ax
	;	xor ax,0ffffh
	;	mov word ptr [bp+2],ax
	;	add di,2
	;@@noSign:
	;	xor cx,cx
	;	mov bx,10
	;@@remainders:
	;	xor dx,dx
	;	div bx
	;	inc cx
	;	cmp ax,0
	;	jne @@remainders
	;@@pass:
	;	mov ax,cx
	;	mov bl,2
	;	mul bl
	;	add di,ax
	;	sub di,2
	;	mov ax,[bp+2]
	;	mov bx,10
	;	mov si,di
	;	
	;@@remainders2:
	;	xor dx,dx
	;	div bx
	;	add dx,48
	;	mov word ptr [bp+di],dx
	;	inc word ptr [bp+4]
	;	sub di,2
	;	cmp ax,0
	;	jne @@remainders2
	;	
	;	cmp si,68
	;	je @@return
	;	add si,2
	;	
	;@@fill:
	;	mov word ptr [bp+si],0
	;	add si,2
	;	cmp si,68
	;	jbe @@fill
		
	;@@return:
	;	ret 2
	;catena endp
	dualis proc near
		locals @@
		mov bp,sp
		mov ax,[bp+2]
		mov bx,[bp+4]
        xor dx,dx

        mov cx, 8000h
        and cx, ax
        cmp cx, 8000h
        jne @@sig
        mov cx, 0ffffh
        xor ax, cx
        inc ax
    @@sig:
        idiv bx
		cmp ax,0
		je @@false
		mov word ptr [bp+6],1
		ret 2
	@@false:	
		mov word ptr [bp+6],0
		ret 2*2
	dualis endp
	fractio proc near
		mov bp,sp
		mov ax,[bp+2]
		mov word ptr [bp+4],1
		mov word ptr [bp+6],ax
		ret 2
	fractio endp
	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call read
		
		pop cx;numerado
		pop ax;denominador
		
		mov word ptr varF[0],cx
		mov word ptr varF[2],ax
		
		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		call imago
		push ax
		mov varI,al
	
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
		mov al,varI
		xor ah,ah
		call writeI
		
		
		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		call dualis
		pop ax
		mov varD,al
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
		mov al,varD
		xor ah,ah
		push ax
		call writeD
		
		
		
		; push varN
		; call catena
		
		; xor bx,bx
	
		; pam:
			; pop ax
			; mov varC[bx],al
			; inc bx
			; cmp bx,32
		; jbe pam
		
		; push varN
		; call dualis
		; pop ax
		; mov varD,al
		
		; push varN
		; call fractio
		; pop ax
		; mov varN[0],ax
		; pop ax
		; mov varN[1],ax
		mov ax, 4c4dh
		int 21h
		
code ends
end start