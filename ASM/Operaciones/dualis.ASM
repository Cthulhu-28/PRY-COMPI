extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far,legoImago:far,scriboImago:far
.model small
.stack 0ffffh 
.data

	varD db 1
	varD2 db 0

	msg1 db "Escriba un valor dualis",10,13,"$"
	boolExpected db 13,10,"Exceptis: dualis expectata",10,13,"$"

	lineBreak db 10,13,"$"
.code
	.386

	read proc near
	locals @@
		mov bp,sp
		xor cx,cx
	@@cicle:
		mov ah,8h
		int 21h	
		cmp al,13
	je @@endCicle ;keeps reading the string until the "enter" key
		
		mov bx,ax
		mov ah,02
		mov dl,al
		int 21h
		
		mov ax,bx
	
		cmp al,61h
		jb @@isUpperCase
		sub al,20h
	@@isUpperCase:
		push ax
		inc cx
	jmp @@cicle	
	@@endCicle:
	cmp cx,10
	je @@false
	cmp cx,9
	je @@true
	jmp @@error
	@@true:
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'U'
		jne @@error
		pop ax
		cmp al,'C'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'D'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'R'
		jne @@error
		pop ax
		cmp al,'E'
		jne @@error
		pop ax
		cmp al,'V'
		jne @@error
		mov [bp+2],1
		jmp @@return
	@@false:
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'U'
		jne @@error
		pop ax
		cmp al,'C'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'D'
		jne @@error
		pop ax
		cmp al,'I'
		jne @@error
		pop ax
		cmp al,'S'
		jne @@error
		pop ax
		cmp al,'L'
		jne @@error
		pop ax
		cmp al,'A'
		jne @@error
		pop ax
		cmp al,'F'
		jne @@error
		mov [bp+2],0
		jmp @@return
	@@return:
		ret
	@@error:
		mov ah,09h
		lea dx, boolExpected
		int 21h
		mov ax, 4c4dh
		int 21h
	read endp
	write proc near
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
	write endp

	non proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		cmp ax, 1
		je @@true
		mov word ptr [bp+2], 1
		ret 
	@@true:
        mov word ptr [bp+2], 0
		ret 
	non endp

	et proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		mov bx,[bp+4]
        and ax, bx
		mov word ptr [bp+4], ax
		ret 1*2
	et endp

    aut proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		mov bx,[bp+4]
        or ax, bx
		mov word ptr [bp+4], ax
		ret 1*2
	aut endp

    xaut proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+2]
		mov bx,[bp+4]
        cmp ax, bx
		je @@True
		mov word ptr [bp+4], 1
		ret 1*2
	@@True:
		mov word ptr [bp+4], 0
		ret 1*2
	
	xaut endp

	start:
		mov ax,@data 
		mov ds,ax 
	
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call read
		pop ax
        mov varD, al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h

		call read
		pop ax
        mov varD2, al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
        xor ax, ax
        mov al, varD
		push ax
		call non
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

        xor ax, ax
        mov al, varD
		push ax
		xor ax, ax
        mov al, varD2
		push ax
		call et
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		xor ax, ax
        mov al, varD
		push ax
		xor ax, ax
        mov al, varD2
		push ax
		call aut
		call scriboDualis

		lea dx,lineBreak
		mov ah,09h
		int 21h

		xor ax, ax
        mov al, varD
		push ax
		xor ax, ax
        mov al, varD2
		push ax
		call xaut
		call scriboDualis

		mov ax,4c00h
		int 21h
		
		
	end start