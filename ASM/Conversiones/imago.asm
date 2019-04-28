data segment
	varI db 0
	varN dw 0
	varD db 0
	varC db 32 dup(0)
	varF dw 2 dup(1)
	varG db 3 dup(0)
	imagoExpected db 13,10,"Exceptis: imago expectata",10,13,"$"
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
	@@cicle:
		mov ah,08h
		int 21h
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key
		xor ah,ah
		push ax
		inc cx
		mov dl,al
		mov ah,02h
		int 21h
	jmp @@cicle	
	@@endCicle:
		cmp cx,1
		ja @@error
		pop ax
		cmp ax,7fh
		ja @@error
		mov [bp+2],ax
		ret
	@@error:
		lea dx,imagoExpected
		mov ah,09h
		int 21h
		mov ax, 4c4dh
		int 21h
	read endp
    catena proc near
		locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov word ptr [bp+4], 1
		mov word ptr [bp+6],ax
		mov si,8
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
		cmp ax,"v"
		je @@false
		cmp ax,"V"
		je @@false
		mov word ptr [bp+4],0
		ret 2
	@@false:	
		mov word ptr [bp+4],1
		ret 2
	dualis endp

	numerus proc near
		mov bp,sp
		xor ax, ax
		mov ax,[bp+2]
		mov [bp+4],ax
		ret 2
	numerus endp

	gregoriu proc near
		mov bp,sp
		xor ax, ax
		mov ax,[bp+2]
		mov di, ax
		;(12*i) + 1583
		mov bl, 12
		mul bl
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

	fractio proc near
		mov bp,sp
		mov ax,[bp+2]
		mov word ptr [bp+4],1
		mov word ptr [bp+6],ax
		ret 2
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

	writed proc near
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
	writed endp

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

	start:
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call read
		pop ax
		mov varI,al
		
		xor ax, ax
		mov al, varI 
		push ax
		call catena

        xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam

		mov bx,33
		
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		call writec

		xor ax, ax
		mov al, varI
		push ax
		call dualis
		pop ax
		mov varD,al

		mov al,varD
		xor ah,ah
		push ax
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		call writed

		xor ax, ax
		mov al, varI
		push ax
		call numerus
		pop ax
		mov varN, ax

		push varN
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		call writen

		xor ax, ax
		mov al, varI
		push ax
		call fractio
		pop ax
		mov word ptr varF[0],ax
		pop ax
		mov word ptr varF[2],ax

		mov bx, varF[0]
		mov cx, varF[2]
		push bx
		push cx
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		call writef

		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h

		xor ax, ax
		mov al, varI
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












