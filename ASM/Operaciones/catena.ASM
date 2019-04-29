extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far
.model small
.stack 0ffffh 
.data
	varC db 32 dup(0)
	varC db 32 dup(0)
	varN dw 0
	varN dw 0
	varI db 0
	varD db 0
	msg1 db "largo",10,13,"$"
	msg2 db "contiene",10,13,"$"
	msg3 db "indexar",10,13,"$"
	msg4 db "concatenar",10,13,"$"
	msg5 db "cortar y recortar",10,13,"$"
	lineBreak db 10,13,"$"

.code
	.386
	
	len proc near
		mov bp,sp
		mov [bp+64], [bp+2]
		ret 2*31
	len endp

	cont proc near
		locals @@
		mov bp, sp
		mov di, 4
		mov cx, [bp+2];nuemro
		mov bx, [bp+66];char
	@@revisa:
		mov ax, [bp+di]
		cmp ax, bx
		je @@true
		add di, 2
		loop @@revisa
		mov [bp+66], 1
		ret 2*32
	@@true:
		mov [bp+66], 0
		ret 2*32
	cont endp

	index proc near
		locasl@@

		mov bp, sp
		mov di, 4
		mov ax, [bp+66];pos
		cmp ax, 32
		jge @@outR
		add ax, [bp+66]
		add di, ax
		mov ax, [bp+di]
		mov word ptr [bp+66], ax
		ret 2*32

	@@outR:
		;print out of range
		mov word ptr [bp+66], 0
		ret 2*32
	cont endp

	concat proc near
		locals@@

		mov bp, sp
		mov ax, [bp+2]
		add ax, [bp+66];A el segundo se le agrega el primero
		cmp ax, 32
		jge @@outR
		mov cx, [bp+2]
		mov di, 68
		add di, [bp+66]
		add di, [bp+66]
		add [bp+66], cx
		mov si, 4
	@@ciclo:
		mov ax, [bp+si]
		mov word ptr [bp+di]
		add si, 2
		add di, 2
		loop @@ciclo
		ret 2*32


	@@outR:
		mov word ptr [bp+66], 0
		ret 2*32
	concat endp

	cut proc near
		locals@@
		mov bp, sp

		mov ax, [bp+2]
		mov bx, [bp+4]
		cmp ax, 32
		jge @@outR
		cmp bx, 32
		jge @@outR
		cmp ax, bx
		jge @@outR
		sub bx, ax
		inc bx
		mov cx, bx
		mov word ptr [bp+6], cx
		mov di, 6
		mov si, 6
		add ax, ax
		add di, ax
	@@cic:
		mov ax, [bp+di]
		mov word ptr [bp+si], ax
		add di, 2
		add si, 2
		loop @@cic
		mov cx, 31
		sub cx, [bp+6]
		cmp cx, 0
		je @@fin
	@@fill:
		mov word ptr [bp+si], 0
		add si, 2
		loop @@fill
	@@fin
		ret 2*2

	@@outR
		ret 2*2

	cut endp

	recut proc near
		locals@@

		
	recut endp


	start:
		mov ax,@data 
		mov ds,ax 
		
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call legoGregorius
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		call legoNumerus
		pop varN
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]	
		push ax
		push dx
		push varN
		call addDays
		pop ax
		pop dx
		mov byte ptr varG2[0],dl
		mov byte ptr varG2[1],ah
		mov byte ptr varG2[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG2[0]
		mov ah,byte ptr varG2[1]
		mov al,byte ptr varG2[2]
		push ax
		push dx
		call scriboGregorius
		
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]	
		push ax
		push dx
		push varN
		call subDays
		pop ax
		pop dx
		mov byte ptr varG2[0],dl
		mov byte ptr varG2[1],ah
		mov byte ptr varG2[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG2[0]
		mov ah,byte ptr varG2[1]
		mov al,byte ptr varG2[2]
		push ax
		push dx
		call scriboGregorius
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		lea dx,msg2
		mov ah,09h
		int 21h
		
		call legoGregorius
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
		call weekDay
		pop varN
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		push varN
		call scriboNumerus
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		call isLeap
		pop ax
		mov varB,al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		mov al,varB
		xor ah,ah
		push ax
		call scriboDualis
		
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		
		call dayInMonth
		
		pop cx;numerado
		pop ax;denominador
		mov word ptr varF[0],cx
		mov word ptr varF[2],ax
		
		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		
		call scriboFractio
		
		mov ax,4c00h
		int 21h
	end start