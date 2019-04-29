code segment
	public legoNumerus,scriboNumerus,legoGregorius,scriboGregorius,legoDualis,scriboDualis,legoFractio,scriboFractio,legoCatena,scriboImago
	assume cs:code
	intExpected db 13,10,"Exceptis: numerus expectata",10,13,"$"
	gregoriusExpected db 13,10,"Exceptis: gregorius expectata",10,13,"$"
	boolExpected db 13,10,"Exceptis: dualis expectata",10,13,"$"
	fractioExpected db 13,10,"Exceptis: fractio expectata",10,13,"$"
	overflow db 13,10,"Exceptis: superfundo",10,13,"$"
	zero db 13,10,"Exceptis: nullus numerus dividatur",10,13,"$"
	stringExpected db 13,10,"Exceptis: catena expectata",10,13,"$"
	legoNumerus proc far
		mov bp,sp
		add bp,2
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
			retf
		errorI:
			mov ax,code
			mov ds,ax
			lea dx,intExpected
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	legoNumerus endp
	scriboNumerus proc far
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
		
		retf 2
	scriboNumerus endp
	legoGregorius proc far
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
			add bp,2
			mov [bp+2],ax ;2do y 3er byte
			mov [bp+4],dx ;1er byte
			retf
		@@errorI:
			mov ax,code
			mov ds,ax
			lea dx,gregoriusExpected
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	legoGregorius endp
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
		mov ax,code
		mov ds,ax
		lea dx,gregoriusExpected
		mov ah,09h
		int 21h
		mov ax, 4c4dh
		int 21h
	check endp
	scriboGregorius proc far
		locals @@
		mov bp,sp
		add bp,2
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
	
		retf 2*2	
	scriboGregorius endp
	legoDualis proc far
	locals @@
		mov bp,sp
		add bp,2
		xor cx,cx
	@@cicle:
		mov ah,8h
		int 21h
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key
		
		mov bx,ax
		mov ah,02
		mov dl,al
		int 21h
		
		mov ax,bx
	
		cmp al,61h
		jb @@isUpperCase
		sub al,20h
	@@isUpperCase:
		push ax
		inc cx
	jmp @@cicle	
	@@endCicle:
	cmp cx,10
	je @@false
	cmp cx,9
	je @@true
	jmp @@error
	@@true:
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'U'
		jne @@error
		pop ax
		cmp al,'C'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'D'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'R'
		jne @@error
		pop ax
		cmp al,'E'
		jne @@error
		pop ax
		cmp al,'V'
		jne @@error
		mov word ptr [bp+2],1
		jmp @@return
	@@false:
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'U'
		jne @@error
		pop ax
		cmp al,'C'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'D'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'L'
		jne @@error
		pop ax
		cmp al,'A'
		jne @@error
		pop ax
		cmp al,'F'
		jne @@error
		mov word ptr [bp+2],0
		jmp @@return
	@@return:
		retf
	@@error:
		mov ax,code
		mov ds,ax
		mov ah,09h
		lea dx, boolExpected
		int 21h
		mov ax, 4c4dh
		int 21h
	legoDualis endp
	scriboDualis proc far
	locals @@
		mov bp,sp
		add bp,2
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
		retf 2
	scriboDualis endp
	legoFractio proc far
		locals @@
		mov bp,sp
		add bp,2
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
			mov word ptr [bp+2],0
			mov word ptr [bp+4],1
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
			retf
		@@signed:
			mov si,[bp+2]
			xor si,0ffffh
			inc si
			mov [bp+2],si
		@@return:
			retf
		@@errorI:
			mov ax,code
			mov ds,ax
			lea dx,fractioExpected
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
		@@errorO:
			mov ax,code
			mov ds,ax
			lea dx,overflow
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
		@@errorZ:
			mov ax,code
			mov ds,ax
			lea dx,zero
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	legoFractio endp
	gcd proc near
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
	scriboFractio proc near
		locals @@
		mov bp,sp
		add bp,2
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
	
		retf 2*2
		
	scriboFractio endp
	legoCatena proc near
	locals @@
		mov bp,sp
		add bp,2
		mov di,6
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
		cmp di,516
		jne @@fill ;In order to return a full string, it fills the left bytes with 0
	@@return:
		retf
		
	legoCatena endp
	scriboImago proc near
	locals @@
		mov bp,sp
		add bp,2
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		retf 2*1 ;Cleans the parameters
	scriboImago endp
code ends
end