extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far,legoImago:far,scriboImago:far
.model small
.stack 0ffffh 
.data

	varI db 0

	msg1 db "Escriba un caracter",10,13,"$"
	lineBreak db 10,13,"$"
.code
	.386
	uperc proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		cmp al, 'a'
        jl @@fin
        cmp al, 'z'
        jg @@fin
        sub al, 20h 
    @@fin: 
        mov word ptr [bp+2], ax
		ret
	uperc endp

    lowc proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		cmp al, 'A'
        jl @@fin
        cmp al, 'Z'
        jg @@fin
        add al, 20h  
    @@fin:
        mov word ptr [bp+2], ax
		ret
	lowc endp

    letter proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		cmp al, 'A'
        jl @@fin
        cmp al, 'z'
        jg @@fin
        cmp al, 'a'
        jge @@char
        cmp al, 'Z'
        jle @@char
        jmp @@fin
    @@char:
        mov word ptr [bp+2], 1
		ret
    @@fin:
        mov word ptr [bp+2], 0
		ret
	letter endp

    numero proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		cmp ax, '0'
        jl @@fin
        cmp ax, '9'
        jg @@fin
        mov word ptr [bp+2], 1
		ret
    @@fin:
        mov word ptr [bp+2], 0
		ret
	numero endp


	start:
		mov ax,@data 
		mov ds,ax 
	
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call legoImago
		pop ax
        mov varI, al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
        xor ax, ax
        mov al, varI
		push ax
		call uperc
		call scriboImago

		lea dx,lineBreak
		mov ah,09h
		int 21h

        xor ax, ax
        mov al, varI
		push ax
		call lowc
		call scriboImago

		lea dx,lineBreak
		mov ah,09h
		int 21h

		xor ax, ax
        mov al, varI
		push ax
		call numero
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		xor ax, ax
        mov al, varI
		push ax
		call letter
		call scriboDualis

		mov ax,4c00h
		int 21h
		
		
	end start