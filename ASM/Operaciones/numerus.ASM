extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far
.model small
.stack 0ffffh 
.data

	varN dw 0
	varN2 dw 0

	msg1 db "Escriba 2 numeros",10,13,"$"
	lineBreak db 10,13,"$"
.code
	.386
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

	start:
		mov ax,@data 
		mov ds,ax 
	
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call legoNumerus
		pop varN

		lea dx,lineBreak
		mov ah,09h
		int 21h

		call legoNumerus
		pop varN2
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		push varN
		call increm
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		call decrem
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call suma
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call resta
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call multi
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call divide
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call modulo
		call scriboNumerus

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call equal
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call less
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call lesseq
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call more
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call moreeq
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		push varN
		push varN2
		call difer
		call scriboDualis

		mov ax,4c00h
		int 21h
		
		
	end start