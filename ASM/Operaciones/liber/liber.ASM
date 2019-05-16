data segment
	var db 165 dup (0) ;total 164
	errorMsg db "Exceptis: liber non apertum",13,10,"$"
	varC db 32 dup(0)
	varN dw 0
	line db 10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	ligo proc near
	locals @@
		mov bp,sp
		mov bx,[bp+2]
		mov si,6
	@@ciclo:
		mov ax, [bp+si]
		mov byte ptr [bx], al
		inc bx
		add si,2
		cmp si,70
		jb @@ciclo
	ret	2*34
	ligo endp
	openWrite proc near
		mov bp,sp
		mov ah,3dh
		mov al,01
		mov dx, offset [bp+2]
		int 21h
		mov bx,[bp+2]
		add bx,128
		mov word ptr [bx], ax
	ret 2
	openWrite endp
	openRead proc near
		mov bp,sp
		mov ah,3dh
		mov al,00
		mov dx, offset [bp+2]
		int 21h
		mov bx,[bp+2]
		add bx,128
		mov word ptr [bx], ax
	ret 2
	openRead endp
	scripturam proc near
	locals @@
		mov bp,sp
		mov di,[bp+2]
		mov bx,di
		add bx,131
		mov si,6
	@@ciclo:
		mov ax, [bp+si]
		mov byte ptr [bx], al
		inc bx
		add si,2
		cmp si,70
		jb @@ciclo
		mov bx,128
		
		mov bx,di
		add bx,128
		mov ax,4202h
		mov bx, word ptr [bx]
		xor cx,cx
		xor dx,dx
		int 21h
		
		; mov dx,ax
		; xor cx,cx
		; mov ax,4200h
		; int 21h

		
		mov ah,40h
		mov cx,[bp+4]
		mov bx,di
		add bx,131
		mov dx,bx
		mov bx,di
		add bx,128
		mov bx, word ptr [bx]
		int 21h
	ret 2*34
	scripturam endp
	lectio proc near
		locals @@
		mov bp, sp
		mov di,[bp+2]
		
		mov bx,di

		
		mov ah,3fh
		mov bx,di
		add bx,131
		mov dx,bx
		mov cx,31
		mov bx,di
		add bx,128
		mov bx, word ptr [bx]
		int 21h
		mov si,6
		mov bx,di
		add bx,131
		mov word ptr [bp+4],ax
	@@ciclo:
		mov dl, byte ptr [bx]
		xor dh,dh
		mov word ptr [bp+si],dx
		add si,2
		inc bx
		dec ax
		cmp ax,0
	jne @@ciclo
		ret 2
	lectio endp
	prope proc near
		mov bp,sp
		
		add bx,128
		mov ax,4200h
		mov bx, word ptr [bx]
		xor cx,cx
		xor dx,dx
		int 21h
		
		mov bx,[bp+2]
		add bx,128
		mov bx, word ptr [bx]
		mov ah,3eh
		int 21h
		ret 2
	prope endp
	
	read proc near
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
		cmp di,68
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
		cmp di,68
		jne @@fill ;In order to return a full string, it fills the left bytes with 0
	@@return:
		ret
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

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		call read
		xor bx,bx
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,31
		jbe pam
		mov bx,31
		sendI:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI
		lea dx, var
		push dx
		call ligo
		
		lea dx, var
		push dx
		call openWrite
		
		lea dx,line
		mov ah,09h
		int 21h
		
		call read
		xor bx,bx
		pam1:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,31
		jbe pam1
		
		
		mov bx,31
		sendI2:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI2
		lea dx,var
		push dx
		call scripturam
		
		lea dx,var
		push dx
		call prope
		
		lea dx,var
		push dx
		call openRead
		
		lea dx,line
		mov ah,09h
		int 21h
		
		lea dx,var
		push dx
		call lectio
		xor bx,bx
		pam2:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,31
		jbe pam2
		
		mov bx,31
		sendI3:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI3
		call writeC
		mov ax, 4c4dh
		int 21h
code ends
end start