data segment
	varN dw 0
	varI db 0
	varD db 0
	varC db 32 dup(0)
	varF dw 2 dup(1)
	varG db 3 dup(0)
	intExpected db 13,10,"Exceptis: numerus expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	read proc near
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
			lea dx,intExpected
			mov ah,09h
			int 21h
			mov ax, 4c4dh
			int 21h
	read endp
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
	writeF proc near
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
		
	writeF endp
	writeI proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	writeI endp
	imago proc near
		mov bp,sp
		mov ax,[bp+2]
		mov bx,256
		xor dx,dx
		div bx
		mov [bp+4],dx
		ret 2
	imago endp

	gregoriu proc near
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
	gregoriu endp

	catena proc near
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
	catena endp
	dualis proc near
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
	dualis endp
	fractio proc near
		mov bp,sp
		mov ax,[bp+2]
		mov word ptr [bp+2],1
		mov word ptr [bp+4],ax
		ret
	fractio endp


writeg proc near
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
		
	writeg endp
	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call read
		pop varN
		
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		push varN
		call imago
		pop ax
		mov varI, al
		
		mov al,varI
		xor ah,ah
		push ax
		call writeI
		
		
		push varN
		call catena
		
		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		mov bx,33
		
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send
		call writeC
		
		push varN
		call dualis
		pop ax
		mov varD,al
		
		mov ah, 02h
		mov dl, 10
		int 21h

		mov dl, 13
		
		mov al,varD
		xor ah,ah
		push ax
		call writeD
		
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		push varN
		call fractio
		pop ax
		mov word ptr varF[0],ax
		pop ax
		mov word ptr varF[2],ax
		xor cx,cx
		xor bx,bx
		mov cx,word ptr varF[2]
		mov bx, word ptr varF[0]
		push bx
		push cx
		call writeF

		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h

		xor ax, ax
		mov ax, varN
		push ax
		call gregoriu
		pop ax
		pop dx
		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al

		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call writeG

		
		mov ax, 4c4dh
		int 21h
		
code ends
end start