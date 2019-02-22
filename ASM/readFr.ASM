data segment
	var dw 2 dup(0)
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
		@@cicle:
			mov ah,08h
			int 21h
			cmp al,13
		je @@cicleEnd
			cmp al,'|'
			jne @@pipe
			inc bx
			mov di,cx
		@@pipe:
			inc cx
			xor ah,ah
			push ax
			mov ah, 02h
			mov dl,al	
			int 21h
		jmp @@cicle
		
		@@cicleEnd:
			cmp di,0
			je @@errorIInt
			cmp bx,1
			jne @@errorIInt
			cmp cx,3
			jb @@errorIInt
			inc di
			cmp cx,di
			je @@errorIInt
			dec di
		mov bx,1
		xor di,di		
		xor cx,cx
		@@castDen:
			pop ax
			cmp al,'-'
			je @@SignDen
			cmp al,'|'
			je @@next
			cmp al,48
			jb @@errorIInt
			cmp al,57
			ja @@errorIInt
			jmp @@noErrorDen
		@@SignDen:
			pop ax
			cmp al,'|'
			jne @@errorIInt
			;xor di,0ffffh
			;inc di
			inc cx
			mov bx,1
			jmp @@castNum
		@@noErrorDen:	
			sub ax,48
			mul bx
			add di,ax
			mov ax,bx
			mov dx,10
			mul dx
			mov bx,ax
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
		
	
		pop cx
		pop bx
		
		mov word ptr var[0],cx
		mov word ptr var[1],bx
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
	
		push bx
		push cx
		call write
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start