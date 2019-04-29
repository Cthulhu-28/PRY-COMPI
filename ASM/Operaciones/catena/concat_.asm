extrn legoCatena:far, scriboCatena:far
data segment
	varC1 db 4,"hola" , 27 dup(0)
	varC2 db 4,"cama" , 27 dup(0)
	
	
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	concat proc near
		locals @@


		mov bp, sp
		
		mov ax, [bp+2]
		add ax, [bp+66];A el segundo se le agrega el primero
		cmp ax,32
		jae @@range
		xor bx,bx
		mov word ptr [bp+130],ax
		mov di,4
		mov si,68
		add bx,2
	@@ciclo:
		mov si,bx
		mov dx,[bp+di]
		mov word ptr [bp+si],dx
		add bx,2
		cmp di,[bp+2]
		jbe @@ciclo
		mov si,68
		mov di,bx
	@@ciclo2:
		mov di,bx
		mov dx ,[bp+si]
		mov word ptr [bp+di], dx
		add bx,2
		cmp di,[bp+2]
		jbe @@ciclo
	@@fill:
		mov [bp+di],0
		add di,2
		cmp di,192
		jne @@fill
	@@range:
		ret 4*32
	concat endp
	write proc near
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
	write endp
	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		; call legoCatena
		
		; mov ah, 02h
		; mov dl, 10
		; int 21h
		; mov dl, 13
		

		; xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		; pam:
			; pop ax
			; mov varC1[bx],al
			; inc bx
			; cmp bx,32
		; jbe pam
		
		
		; call legoCatena
		
		; mov ah, 02h
		; mov dl, 10
		; int 21h
		; mov dl, 13
		

		; xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		; pam1:
			; pop ax
			; mov varC2[bx],al
			; inc bx
			; cmp bx,32
		; jbe pam1
		
		
		
		
		
		mov bx,32
		
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC2[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send
		
		
		mov bx,32
		
		;Moves into the stack the string var, bottom-to-top
		send1:
			dec bx
			mov al,varC1[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send1
		
		
		
		
		call concat
		
		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam2:
			pop ax
			mov varC1[bx],al
			inc bx
			cmp bx,32
		jbe pam2
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
	
	
		mov bx,32
		
		;Moves into the stack the string var, bottom-to-top
		send2:
			dec bx
			mov al,varC1[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send2
	
		call write
		
		mov ax, 4c4dh
		int 21h
		
		
code ends
end start