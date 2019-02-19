data segment
	var dw 0
	intExpected db 13,10,"ERROR LOL",10,13,"$"
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
	write proc near
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
	ciclo1PAX: xor dx, dx
		div bx
		push dx
		inc cx
		cmp ax, 0
		jne ciclo1PAX
		mov ah, 02h
	ciclo2PAX: pop DX
		add dl, 30h
		int 21h
		loop ciclo2PAX

		pop DX
		pop CX
		pop BX
		pop AX
		
		ret 2
	write endp

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		pop var
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		push var
		call write
		int 21h
	
		
		
		mov ax, 4c4dh
		int 21h
code ends
end start