.model small
.stack 0ffffh 
.data
	varI db 0
	varN dw 0
	varD db 0
	varC db 32 dup(0)
    varF dw 2 dup(1)
    varG db 3 dup(0)
    boolExpected db 13,10,"Exceptis: gregoriu expectata",10,13,"$"

.code
	.386
    catena proc near
		locals @@
		mov bp,sp
		mov dx,[bp+2]
		xor ax,ax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		
	
		xor cx, cx
		mov bx, 10
	@@ciclo1Year: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1Year
		mov ah, 02h
	@@ciclo2Year: 
		pop DX
		add dl, 30h
		int 21h
	loop @@ciclo2Year
	
		mov ah, 02h
		mov dl, "/"
		int 21h
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
	
		xor cx, cx
		mov bx, 10
	@@ciclo1Month: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1Month
		mov ah, 02h
	@@ciclo2Month: 
		pop DX
		add dl, 30h
		int 21h
	loop @@ciclo2Month
		mov ah, 02h
		mov dl, "/"
		int 21h
		
		mov ax,[bp+4]
		and ax,1fh
		
		xor cx, cx
		mov bx, 10
	@@ciclo1Day: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1Day
		mov ah, 02h
	@@ciclo2Day: 
		pop DX
		add dl, 30h
		int 21h
	loop @@ciclo2Day
	
		ret 2*2
		
	catena endp


	imago proc near
		locals @@
		;Año
		mov dx,[bp+2]
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		;Mes
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov di,ax
		;Dia
		mov ax,[bp+4]
		and ax,1fh

		add ax, si
		add ax, di

		xor dx, dx   
		mov bx, 128
		div bx   

		mov [bp+4], dx
		ret 2

	imago endp

	numerus proc near
		locals @@
		mov bp,sp
		
		xor ax,ax
		;Año
		mov dx,[bp+2]
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		;Mes
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov di,ax
		;Dia
		mov ax,[bp+4]
		and ax,1fh


		add ax, si
		add ax, di

		mov [bp+4], ax
		ret 2
    numerus endp

	fractio proc near
		
	locals @@
		mov bp,sp
		mov ax,[bp+4]
		mov bx,[bp+2]
		push ax
		push bx
		call dualis
		pop si
		mov bp,sp
		
		;Mes
		xor eax,eax
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov edi,eax
		;Dia
		xor eax,eax
		mov ax,[bp+4]
		and ax,1fh
		mov ecx,eax
		
		;m = (m + 9) % 12
		mov eax,edi
		add eax,9
		mov ebx,12
		xor edx,edx
		div ebx
		mov edi,edx
		
		
		
		;(m*306 + 5)/10
		xor edx,edx
		mov ebx,306
		mov eax,edi
		mul ebx
		add eax,5
		
		xor edx,edx
		mov ebx,10
		div ebx
		mov edi,eax
		
		;( d - 1 )
		mov eax,ecx
		dec eax
		add edi,eax
		
		
		add edi,59
		add edi,esi
		
		mov ebx,365
		add ebx,esi
		xor edx,edx
		mov eax,edi
		div ebx
		inc edx
		
		mov word ptr [bp+2],1
		mov word ptr [bp+4],dx	
		ret

	fractio endp
    
    writec proc near
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
	writec endp

	writei proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	writei endp

	writen proc near
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
	writen endp

	writef proc near
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
		
	writef endp

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

    read proc near
		locals @@
		mov bp,sp
		xor cx,cx
		xor bx,bx
		xor di,di
		xor si, si
		mov si,1
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
			cmp al,'$'
			jne @@dollar
			inc bx
			mov di,cx
			cmp cx,0
			jne @@dollar
			mov si,0
		@@dollar:
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
	
			;A. Comienza con $
			cmp si,0
			je @@errorIInt
			;B. Que haya más de dos $
			cmp bx,2
			jne @@errorIInt
			;C. Que lo ingresado no sea al menos 9 caracteres
			cmp cx,8
			jb @@errorIInt
			inc di
			;D. Que el $ sea el último caracter introducido
			cmp cx,di
			je @@errorIInt
			dec di
			
		;En di se guardará el dia
		;En cx se cuenta si hay un número negativo
		;Em bx se trabajará las potencias de 10. Se comienza con 10^0
		mov bx,1
		xor di,di		
		xor cx,cx
		xor si,si
	
		
		@@castDay:
			pop ax
			cmp al,'$'
			je @@next
			;Se verifica que la tecla esté en el rango de los números
			cmp al,48
			jb @@errorIInt
			cmp al,57
			ja @@errorIInt
			
			sub ax,48
			mul bx
			add di,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp di,31
			ja @@errorIInt
		jmp @@castDay
		@@errorIInt: jmp @@errorI
		@@next:
			mov bx,1
		@@castMonth:
			pop ax
			cmp al,'$'
			je @@nextB
			cmp al,48
			jb @@errorIInt
			cmp al,57
			ja @@errorIInt
			
			sub ax,48
			mul bx
			add si,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp si,12
			ja @@errorIInt
		jmp @@castMonth
		@@nextB:
			mov bx,1
		@@castYear:
			pop ax
			cmp al,48
			jb @@errorI
			cmp al,57
			ja @@errorI
			
			sub ax,48
			mul bx
			add cx,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp cx,9999;270f
			ja @@errorI
			cmp bp,sp
			je @@nextC
		jmp @@castYear
		
		@@nextC: 
		
			push cx
			push si
			push di
			
			push cx
			push si
			push di
			
			;CX contiene el año
			;DI el día
			;SI el mes
			call check
			
			pop di
			pop si
			pop cx
			
			;0000 0000  0000 0000  0000 0000
			; |       año      || mes|| día|  
			; |        14      ||  4 ||  5 |
			;El primer bit queda libre
			
			;Operaciones sobre bits para retonar con el formato de la fecha
			xor ax,ax
			xor dx,dx
			
			;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
			mov ax,di
			
			;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
			;Se suma a los bits del día.
			mov bx,si
			shl bx,5
			add ax,bx
			
			;El año ocupa los primeros 7 bits del segundo byte.
			;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
			mov bx,cx
			shl bx,9
			add ax,bx
			
			;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
			;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
			mov bx,cx
			shr bx,7
			mov dx,bx
			;Se retorna
			mov bp,sp
			mov [bp+2],ax ;2do y 3er byte
			mov [bp+4],dx ;1er byte
		return:
			ret
		@@errorI:

			mov ax, 4c4dh
			int 21h
	read endp
	check proc
		locals @@
		mov bp,sp
		
		;di será 1 si el año es bisiesto
		mov ax,word ptr [bp+2*3]
		mov cx,ax
		xor di,di
		
		;Divisibiliad por 400
		mov bx,400
		xor dx,dx
		div bx
		cmp dx,0
		jne @@LeapA
		mov di,1
		jmp @@next
	@@LeapA:
		mov ax,word ptr [bp+2*3]
		;No divisibiliad por 100
		mov bx,100
		xor dx,dx
		div bx
		cmp dx,0
		je @@next
		mov ax,word ptr [bp+2*3]
		;Divisibiliad por 4
		mov bx,4
		xor dx,dx
		div bx
		cmp dx,0
		jne @@next
		mov di,1
	@@next:
		;Comparación de que febrero tenga max 29 días, o 29 si es bisiesto
		cmp word ptr [bp+2*2],2
		jne @@nextB
		cmp di,1
		jne @@checkFeb
		cmp word ptr [bp+2],29
		ja @@error
		jmp @@nextC
	@@checkFeb:
		cmp word ptr [bp+2],28
		ja @@error
	@@nextB:	
		;Se buscan los meses que tengan 30 días.
		cmp word ptr [bp+2*2],4
		je @@checkDay
		cmp word ptr [bp+2*2],6
		je @@checkDay
		cmp word ptr [bp+2*2],9
		je @@checkDay
		cmp word ptr [bp+2*2],11
		je @@checkDay
		jmp @@nextC
	@@checkDay:
		;Se compara la regla de los 30 días.
		cmp word ptr [bp+2],30
		ja @@error
	@@nextC:
		;Se pregunta por mes y día distintos de cero
		cmp word ptr [bp+2*2],0
		je @@error
		cmp word ptr [bp+2],0
		je @@error
		;Se compara con el incio del calendario gregoriano, 1582/10/15
		cmp word ptr [bp+2*3],1582
		jb @@error
		cmp word ptr [bp+2*3],1582
		jne @@return
		cmp word ptr [bp+2*2],10
		jb @@error
		cmp word ptr [bp+2*2],10
		jne @@return
		cmp word ptr [bp+2],15
		jb @@error
	@@return:
		ret 2*3
	@@error:

		mov ax, 4c4dh
		int 21h
	check endp

	dualis proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+2]
		xor ax,ax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		
		
		;di será 1 si el año es bisiesto
		mov ax,si
		mov cx,ax
		xor di,di
		
		;Divisibiliad por 400
		mov bx,400
		xor dx,dx
		div bx
		cmp dx,0
		jne @@LeapA
		mov di,1
		jmp @@next
	@@LeapA:
		mov ax,si
		;No divisibiliad por 100
		mov bx,100
		xor dx,dx
		div bx
		cmp dx,0
		je @@next
		mov ax,si
		;Divisibiliad por 4
		mov bx,4
		xor dx,dx
		div bx
		cmp dx,0
		jne @@next
		mov di,1
	@@next:	
		mov word ptr [bp+4],di
		ret 2
	dualis endp
	
	start:
	
		mov ax,@data 
		mov ds,ax 

        call read
		pop ax
		pop dx
		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
        
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		

		; xor dx,dx
		; mov dl,byte ptr varG[0]
		; mov ah,byte ptr varG[1]
		; mov al,byte ptr varG[2]
		
		; push ax
		; push dx
		; call catena

        ; xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		; pam:
			; pop ax
			; mov varC[bx],al
			; inc bx
			; cmp bx,32
        ; jbe pam
        
        ; mov bx,33
		
		;Moves into the stack the string var, bottom-to-top
		; send:
			; dec bx
			; mov al,varC[bx]
			; xor ah,ah
			; push ax
			; cmp bx,0
		; ja send
        ; call writec
        
        ; mov ah, 02h
		; mov dl, 10
		; int 21h
		; mov dl, 13
		; int 21h
		

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call imago
		pop ax
        mov varI, al
        
        mov al, varI
        xor ah, ah
        push ax
        call writei

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call numerus
		pop ax
        mov varN, ax
        
        push varN
        call writen

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call fractio
		pop ax
		mov word ptr varF[0],ax
		pop ax
        mov word ptr varF[2],ax
        
        mov bx, varF[0]
		mov cx, varF[2]
		push bx
		push cx
		call writef

		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call dualis
		pop ax
		mov varD,al

		mov al,varD
		xor ah,ah
		push ax
		call writeD

		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call catena

        mov ax, 4c4dh
		int 21h
	end start

