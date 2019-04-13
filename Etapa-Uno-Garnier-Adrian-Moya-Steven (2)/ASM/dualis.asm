data segment
	varI db 0
	varN dw 0
	varD db 0
	varC db 32 dup(0)
    varF dw 2 dup(1)
    boolExpected db 13,10,"Exceptis: dualis expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
    assume cs:code, ds:data,ss:sstack
    catena proc near
		locals @@
        mov bp,sp
        mov di, 4
        mov ax, [bp+2]
        cmp ax, 0
        je @@false
        mov word ptr [bp+di], 09h
        add di,2
        mov word ptr [bp+di], "v"
        add di,2
        mov word ptr [bp+di], "e"
        add di,2
        mov word ptr [bp+di], "r"
        jmp @@rest

    @@false:
        mov word ptr [bp+di], 0Ah
        add di, 2
        mov word ptr [bp+di], "f"
        add di, 2
        mov word ptr [bp+di], "a"
        add di, 2
        mov word ptr [bp+di], "l"
        add di, 2
        mov word ptr [bp+di], "s"

    @@rest:
        add di, 2
        mov word ptr [bp+di], "i"
        add di, 2
        mov word ptr [bp+di], "d"
        add di, 2
        mov word ptr [bp+di], "i"
        add di, 2
        mov word ptr [bp+di], "c"
        add di, 2
        mov word ptr [bp+di], "u"
        add di, 2
        mov word ptr [bp+di], "s"
        add di, 2

	@@fill:
		mov word ptr [bp+di],0
		add di,2
		cmp di,68
		jbe @@fill
		
	@@return:
		ret 2
	catena endp


	imago proc near
		locals @@
		mov bp,sp
		mov ax,[bp+2]
		cmp ax,0
		je @@false
		mov word ptr [bp+4], "V"
		ret 2
	@@false:	    
		mov word ptr [bp+4], "F"
		ret 2
	imago endp

	numerus proc near
        mov bp,sp
        mov ax,[bp+2]
        cmp ax,0
        je @@false
        mov word ptr [bp+4],1
        ret 2
    @@false:	    
        mov word ptr [bp+4],0
        ret 2
    numerus endp

	fractio proc near
        mov bp,sp
        mov ax,[bp+2]
        cmp ax,0
        je @@false
        mov word ptr [bp+4],1
		mov word ptr [bp+6],1
        ret 2
    @@false:	    
        mov word ptr [bp+4],1
        mov word ptr [bp+6],0
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

	writei proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	writei endp

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
		mov word ptr [bp+2],1
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
		mov word ptr [bp+2],0
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

    start:
    

		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax

        call read
        pop ax
        mov varD,al
        
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
		xor ax, ax
		mov al, varD 
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
        call writec
        
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h

		xor ax, ax
		mov al, varD
		push ax
		call imago
		pop ax
        mov varI, al
        
        mov al, varI
        xor ah, ah
        push ax
        call writei

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h

		xor ax, ax
		mov al, varD
		push ax
		call numerus
		pop ax
        mov varN, ax
        
        push varN
        call writen

        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h

		xor ax, ax
		mov al, varD
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
		call writef

        mov ax, 4c4dh
		int 21h
		
code ends
end start












