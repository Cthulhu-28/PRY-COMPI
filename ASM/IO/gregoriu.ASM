data segment
	var db 3 dup(0)
	gregoriusExpected db 13,10,"Exceptis: gregorius expectata",10,13,"$"
	gregoriusExpected2 db 13,10,"Exceptis: gregorius expectata xd",10,13,"$"
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
			lea dx,gregoriusExpected
			mov ah,09h
			int 21h
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
		lea dx,gregoriusExpected
		mov ah,09h
		int 21h
		mov ax, 4c4dh
		int 21h
	check endp
	write proc near
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
		mov dl, "$"
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
		mov dl, "$"
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
		
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		pop ax
		pop dx
		
		mov byte ptr var[0],dl
		mov byte ptr var[1],ah
		mov byte ptr var[2],al
	
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
		xor dx,dx
		mov dl,byte ptr var[0]
		mov ah,byte ptr var[1]
		mov al,byte ptr var[2]
		
		push ax
		push dx
		call write
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start