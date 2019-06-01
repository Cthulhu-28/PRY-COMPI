legonumerus proc near
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
			;;lea dx,intExpected
			;;mov ah,09h
			;int 21h
			mov ax, 4c4dh
			int 21h
legonumerus endp
scribonumerus proc near
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
scribonumerus endp
legogregorius proc near
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
		ret
	@@errorI:
		; lea dx,gregoriusExpected
		; mov ah,09h
		; int 21h
		mov ax, 4c4dh
		int 21h
legogregorius endp
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
	; lea dx,gregoriusExpected
	; mov ah,09h
	; int 21h
	mov ax, 4c4dh
	int 21h
check endp
scribogregorius proc near
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
scribogregorius endp
legocatena proc near
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
	
legocatena endp
scribocatena proc near
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
scribocatena endp
num_2_imago proc near
		mov bp,sp
		mov ax,[bp+2]
		mov bx,256
		xor dx,dx
		div bx
		mov [bp+4],dx
		ret 2
num_2_imago endp

num_2_greg proc near
	mov bp,sp
	xor ax, ax
	mov ax,[bp+2]
	mov di, ax
	;(i/12) + 1583
	xor dx, dx
	mov bx, 12
	div bx

	add ax, 1583
	mov cx, ax

	xor ax, ax
	;(i%12)+1
	mov ax, di
	mov bx, 12
	xor dx, dx
	div bx

	inc dx
	mov si, dx

	;(i%28)+1
	mov ax, di
	xor dx, dx
	mov bx, 28
	div bx
	inc dx
	mov di, dx


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
	ret 
num_2_greg endp
num_2_catena proc near
	locals @@
	mov bp,sp
	mov ax,[bp+2]
	mov di,4
	mov word ptr [bp+di],0
	mov di,6
	mov bx,ax
	and bx ,8000h
	cmp bx,8000h
	jne @@noSign
	mov word ptr [bp+di],"-"
	inc word ptr [bp+4]
	dec ax
	xor ax,0ffffh
	mov word ptr [bp+2],ax
	add di,2
@@noSign:
	xor cx,cx
	mov bx,10
@@remainders:
	xor dx,dx
	div bx
	inc cx
	cmp ax,0
	jne @@remainders
@@pass:
	mov ax,cx
	mov bl,2
	mul bl
	add di,ax
	sub di,2
	mov ax,[bp+2]
	mov bx,10
	mov si,di
	
@@remainders2:
	xor dx,dx
	div bx
	add dx,48
	mov word ptr [bp+di],dx
	inc word ptr [bp+4]
	sub di,2
	cmp ax,0
	jne @@remainders2
	
	cmp si,68
	je @@return
	add si,2
	
@@fill:
	mov word ptr [bp+si],0
	add si,2
	cmp si,68
	jbe @@fill
	
@@return:
	ret 2
num_2_catena endp
num_2_dual proc near
	locals @@
	mov bp,sp
	mov ax,[bp+2]
	cmp ax,0
	je @@false
	mov word ptr [bp+4],1
	ret 2
@@false:	
	mov word ptr [bp+4],0
	ret 2
num_2_dual endp
num_2_frac proc near
	mov bp,sp
	mov ax,[bp+2]
	mov word ptr [bp+2],1
	mov word ptr [bp+4],ax
	ret
num_2_frac endp
len proc near
		mov bp,sp
		mov ax, [bp+2]
		mov word ptr [bp+64], ax
		ret 2*31
len endp
non proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	cmp ax, 1
	je @@true
	mov word ptr [bp+2], 1
	ret 
@@true:
	mov word ptr [bp+2], 0
	ret 
non endp

et proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	mov bx,[bp+4]
	and ax, bx
	mov word ptr [bp+4], ax
	ret 1*2
et endp

aut proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	mov bx,[bp+4]
	or ax, bx
	mov word ptr [bp+4], ax
	ret 1*2
aut endp

xaut proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	mov bx,[bp+4]
	cmp ax, bx
	je @@True
	mov word ptr [bp+4], 1
	ret 1*2
@@True:
	mov word ptr [bp+4], 0
	ret 1*2

xaut endp
increm proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	inc ax
	mov word ptr [bp+2], ax
	ret
increm endp

decrem proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	dec ax
	mov word ptr [bp+2], ax
	ret
decrem endp

suma proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	mov bx,[bp+4]
	add ax, bx
	mov word ptr [bp+4], ax
	ret 2 *1
suma endp


resta proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	sub ax, bx
	mov word ptr [bp+4], ax
	ret 2 *1
resta endp

multi proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+2]
	mov bx,[bp+4]
	mul bx
	mov word ptr [bp+4], ax
	ret 2 *1
multi endp

divide proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	xor dx, dx
	div bx
	mov word ptr [bp+4], ax
	ret 2 *1
divide endp

modulo proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	xor dx, dx
	div bx
	mov word ptr [bp+4], dx
	ret 2 *1
modulo endp

equal proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	je @@True
	mov word ptr [bp+4], 0
	ret 2 *1
@@True:
	mov word ptr [bp+4], 1
	ret 2 *1
equal endp

less proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	jl @@True
	mov word ptr [bp+4], 0
	ret 2 *1
@@True:
	mov word ptr [bp+4], 1
	ret 2 *1
less endp

lesseq proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	jle @@True
	mov word ptr [bp+4], 0
	ret 2 *1
@@True:
	mov word ptr [bp+4], 1
	ret 2 *1
lesseq endp

more proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	jg @@True
	mov word ptr [bp+4], 0
	ret 2 *1
@@True:
	mov word ptr [bp+4], 1
	ret 2 *1
more endp

moreeq proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	jge @@True
	mov word ptr [bp+4], 0
	ret 2 *1
@@True:
	mov word ptr [bp+4], 1
	ret 2 *1
moreeq endp

difer proc near
	locals @@
	mov bp,sp
	
	mov ax,[bp+4]
	mov bx,[bp+2]
	cmp ax, bx
	je @@True
	mov word ptr [bp+4], 1
	ret 2 *1
@@True:
	mov word ptr [bp+4], 0
	ret 2 *1
difer endp