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
	
			;A. Comienza con |
			cmp si,0
			je @@errorIInt
			;B. Que haya más de un |
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
			jb @@errorI
			cmp al,57
			ja @@errorI
			
			sub ax,48
			mul bx
			add di,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp di,31
			ja @@errorI
		jmp @@castDay
		@@errorIInt: jmp @@errorI
		@@next:
			mov bx,1
		@@castMonth:
			pop ax
			cmp al,'$'
			je @@nextB
			cmp al,48
			jb @@errorI
			cmp al,57
			ja @@errorI
			
			sub ax,48
			mul bx
			add si,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
			cmp si,12
			ja @@errorI
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
			
			call check
			
			pop di
			pop si
			pop cx
		
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
		
		mov ax,[bp+2*3]
		mov cx,ax
		xor di,di
	
		mov bx,400
		xor dx,dx
		div bx
		cmp dx,0
		jne @@LeapA
		mov di,1
		jmp @@next
	@@LeapA:
		mov ax,[bp+2*3]
		mov bx,100
		xor dx,dx
		div bx
		cmp dx,0
		je @@next
		mov ax,[bp+2*3]
		mov bx,4
		xor dx,dx
		div bx
		cmp dx,0
		jne @@next
		mov di,1
	@@next:
		cmp [bp+2*2],2
		jne @@nextB
		cmp di,1
		jne @@checkFeb
		cmp [bp+2],29
		ja @@error
		jmp @@nextC
	@@checkFeb:
		cmp [bp+2],28
		ja @@error
	@@nextB:	
		cmp [bp+2*2],4
		je @@checkDay
		cmp [bp+2*2],6
		je @@checkDay
		cmp [bp+2*2],9
		je @@checkDay
		cmp [bp+2*2],11
		je @@checkDay
		jmp @@nextC
	@@checkDay:
		cmp [bp+2],30
		ja @@error
	@@nextC:
		cmp [bp+2*3],1582
		jb @@error
		cmp [bp+2*3],1582
		jne @@return
		cmp [bp+2*2],10
		jb @@error
		cmp [bp+2*2],10
		jne @@return
		cmp [bp+2],15
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
	@@ciclo1PAXNum: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1PAXNum
		mov ah, 02h
	@@ciclo2PAXNum: 
		pop DX
		add dl, 30h
		int 21h
		loop @@ciclo2PAXNum
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
	@@ciclo1PAXDen: 
		xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne @@ciclo1PAXDen
		mov ah, 02h
	@@ciclo2PAXDen: 
		pop DX
		add dl, 30h
		int 21h
		loop @@ciclo2PAXDen	
	
		ret 2*2
		
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		
	
		; pop cx
		; pop bx
		
		; mov word ptr var[0],cx
		; mov word ptr var[1],bx
		
		; mov ah, 02h
		; mov dl, 10
		; int 21h
		; mov dl, 13
		; int 21h
	
		; push bx
		; push cx
		; call write
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start