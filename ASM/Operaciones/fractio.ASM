extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far,legoImago:far,scriboImago:far
.model small
.stack 0ffffh 
.data

	varF dw 2 dup (1)
	varF2 dw 2 dup (1)
	fractioExpected db 13,10,"Exceptis: fractio expectata",10,13,"$"
	overflow db 13,10,"Exceptis: superfundo",10,13,"$"
	zero db 13,10,"Exceptis: nullus numerus dividatur",10,13,"$"


	msg1 db "Escriba un caracter",10,13,"$"
	lineBreak db 10,13,"$"
.code
	.386
	write proc near
		locals @@
		mov bp,sp
		mov ax,[bp+2]
		
		mov bx,ax
		and bx ,8000h
		cmp bx,8000h
		jne @@noSignNum
		push ax
		mov ah,02
		mov dl,"-"
		int 21h
		pop ax
		dec ax
		xor ax,0ffffh	
	@@noSignNum:	
		xor cx, cx
		mov bx, 10
	@@ciclo1Num: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1Num
		mov ah, 02h
	@@ciclo2Num: 
		pop DX
		add dl, 30h
		int 21h
		loop @@ciclo2Num
		mov ah,02
		mov dl,"|"
		int 21h
	
		mov ax,[bp+4]
		
		mov bx,ax
		and bx ,8000h
		cmp bx,8000h
		jne @@noSignDen
		push ax
		mov ah,02
		mov dl,"-"
		int 21h
		pop ax
		dec ax
		xor ax,0ffffh	
	@@noSignDen:	
		xor cx, cx
		mov bx, 10
	@@ciclo1Den: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1Den
		mov ah, 02h
	@@ciclo2Den: 
		pop DX
		add dl, 30h
		int 21h
		loop @@ciclo2Den	
	
		ret 2*2
		
	write endp
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

	pent proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+2]
		mov bx, [bp+4]
		div bx

		mov [bp+4], ax

		ret 1*2
	pent endp

	round proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+2]
		mov bx, [bp+4]
		div bx
		
		mov [bp+4], ax

		ret 1*2
	round endp

	addit proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+4]
		mov bx, [bp+6]
		mul bx
		mov di, ax

		xor dx, dx
		mov ax, [bp+8]
		mov bx, [bp+2]
		mul bx
		add ax, di

		mov [bp+6], ax

		mov ax, [bp+4]
		mov bx, [bp+8]
		xor dx, dx
		mul bx
		mov [bp+8], ax
		
		ret 2*2
	addit endp

	subs proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+4]
		mov bx, [bp+6]
		mul bx
		mov di, ax

		xor dx, dx
		mov ax, [bp+8]
		mov bx, [bp+2]
		mul bx
		sub ax, di

		mov [bp+6], ax

		mov ax, [bp+4]
		mov bx, [bp+8]
		xor dx, dx
		mul bx
		mov [bp+8], ax
		
		ret 2*2
	subs endp

	divit proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+4]
		mov bx, [bp+6]
		mul bx
		mov di, ax

		xor dx, dx
		mov ax, [bp+8]
		mov bx, [bp+2]
		mul bx
		mov [bp+8], ax
		mov [bp+6], di

		ret 2*2
	divit endp

	mult proc near
		locals @@
		mov bp,sp

		xor dx, dx
		mov ax, [bp+4]
		mov bx, [bp+8]
		mul bx
		mov di, ax
		

		xor dx, dx
		mov ax, [bp+2]
		mov bx, [bp+6]
		mul bx
		mov [bp+6], ax
		mov [bp+8], di

		ret 2*2
	mult endp

    

	start:
		mov ax,@data 
		mov ds,ax 
	
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call read
		
		pop cx;numerado
		pop ax;denominador
		
		mov word ptr varF[0],cx
		mov word ptr varF[2],ax
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		call read
		
		pop cx;numerado
		pop ax;denominador
		
		mov word ptr varF2[0],cx
		mov word ptr varF2[2],ax

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		call pent
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		call round
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		mov ax,varF2[2]
		mov cx,varF2[0]
		push ax
		push cx
		call addit
		call write

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF2[2]
		mov cx,varF2[0]
		push ax
		push cx
		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		call subs
		call write

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		mov ax,varF2[2]
		mov cx,varF2[0]
		push ax
		push cx
		call divit
		call write

		lea dx,lineBreak
		mov ah,09h
		int 21h

		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		mov ax,varF2[2]
		mov cx,varF2[0]
		push ax
		push cx
		call mult
		call write


       

		mov ax,4c00h
		int 21h
		
		
	end start