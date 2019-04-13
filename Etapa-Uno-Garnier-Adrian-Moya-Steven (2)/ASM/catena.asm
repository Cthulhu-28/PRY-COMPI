data segment
	varI db 0
	varN dw 0
	varD db 0
	varC db 32 dup(0)
	varF dw 2 dup(1)
	stringExpected db 13,10,"Exceptis: catena expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
    assume cs:code, ds:data,ss:sstack
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
    imago proc near
        locals @@
        mov bp,sp
        mov cx,[bp+2]
        mov bx, cx
        xor ax, ax
        mov di, 4
    @@ciclo:
        add ax, [bp+di]
        add di, 2
        loop @@ciclo
        
        xor dx, dx
        div bx
        mov word ptr [bp+66], ax
        ret 2*32

    imago endp
    
    writei proc near
	locals @@
		mov bp,sp
		mov ax, [bp+2]
		mov ah, 02h
		mov dl, al
		int 21h

		ret 2*1 ;Cleans the parameters
	writei endp

	dualis proc near
    	locals @@
        mov bp,sp
        mov di, 2
        mov ax,[bp+di]
        cmp ax, 9
        jne @@falsem

        add di, 2
        mov ax, [bp+di]
        cmp ax, "v"
        je @@v
        cmp ax, "V"
        jne @@falsem

    @@v:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "e"
        je @@ve
        cmp ax, "E"
        jne @@falsem

    @@ve:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "r"
        je @@ver
        cmp ax, "R"
        jne @@falsem
    @@ver:
        jmp @@falset

    @@falsem:
        jmp @@false

    @@falset:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "i"
        je @@veri
        cmp ax, "I"
        jne @@false
    
    @@veri:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "d"
        je @@verid
        cmp ax, "D"
        jne @@false

    @@verid:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "i"
        je @@veridi
        cmp ax, "I"
        jne @@false

    @@veridi:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "c"
        je @@veridic
        cmp ax, "C"
        jne @@false
       
    @@veridic:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "u"
        je @@veridicu
        cmp ax, "U"
        jne @@false

    @@veridicu:
        add di, 2
        mov ax, [bp+di]
        cmp ax, "s"
        je @@veridicus
        cmp ax, "S"
        jne @@false

    @@veridicus:
		mov word ptr [bp+66],1
		ret 2*32
	@@false:	
		mov word ptr [bp+66],0
		ret 2*32
	dualis endp

	numerus proc near
	locals @@
        mov bp,sp
        xor cx,cx
        xor bx,bx
        xor di,di
        xor si,si
        mov di, 2

        mov cx, [bp+di]
        mov bx, 10

        add di, 2
        mov ax, [bp+di]

        cmp ax, "-"
        je @@negativo
        cmp ax, "0"
        je @@tipo
        cmp ax, "0"
        jb @@errorI
        cmp ax, "9"
        ja @@errorI
		xor ax,ax
        jmp @@base10

    @@tipo:
        dec cx
        add di, 2
        mov ax, [bp+di]
        cmp ax, "r"
        je @@romano
        cmp ax, "R"
        je @@romano
        cmp ax, "x"
        je @@hex
        cmp ax, "X"
        je @@hex
        cmp ax, "0"
        jb @@error
        cmp ax, "9"
        ja @@error
		xor ax,ax
        jmp @@base10


    @@romano:
        jmp @@error
    
    @@errorI:
        jmp @@error

    @@negativo:
        mov si, 1
        add di, 2
        dec cx
		xor ax,ax
        jmp @@base10
    @@hex:
		xor ax,ax
        mov bx, 16
        dec cx
		add di,2

    @@base10:
        cmp word ptr [bp+di], "0"
        jb @@error
        cmp word ptr [bp+di], "9"
        jbe @@N
        cmp bx, 16
        jne @@error
        cmp word ptr [bp+di], "A"
        jb @@error
        cmp word ptr [bp+di], "F"
        jbe @@LU
        cmp word ptr [bp+di], "a"
        jb @@error
        cmp word ptr [bp+di], "f"
        jbe @@LL
        jmp @@error


    @@LL:
        sub word ptr [bp+di], 20h   
    @@LU:
        sub word ptr [bp+di], 7h
    @@N:   
        sub word ptr [bp+di], 30h
        xor dx, dx
        mul bx
		mov dx,[bp+di]
        add ax, [bp+di]

        add di, 2
        
        cmp ax, 32767
        ja @@error

        loop @@base10
		jmp @@fin
    @@error:
        mov word ptr [bp+66], 0
		retf 2*32
    @@fin:
        cmp si, 1
        jne @@pos
        xor ax, 0ffffh
        inc ax
    @@pos:
        mov word ptr [bp+66], ax
		ret 2*32
	numerus endp
    
    fractio proc near
    locals @@
        mov bp,sp
        xor cx,cx
        xor bx,bx
        xor di,di
        xor si,si
        mov di, 2

        mov cx, [bp+di]
        mov bx, 10

        add di, 2
        mov ax, [bp+di]

        cmp ax, "-"
        je @@negativo
        cmp ax, "0"
        je @@tipo
        cmp ax, "0"
        jb @@errori
        cmp ax, "9"
        ja @@errori
		xor ax,ax
        jmp @@base10
    @@errori:
        jmp @@error
    @@tipo:
        dec cx
        add di, 2
        mov ax, [bp+di]
        cmp ax, "r"
        je @@romano
        cmp ax, "R"
        je @@romano
        cmp ax, "x"
        je @@hex
        cmp ax, "X"
        je @@hex
        cmp ax, "0"
        jb @@error
        cmp ax, "9"
        ja @@error
		xor ax,ax
        jmp @@base10

    @@romano:
        jmp @@error


    @@negativo:
        mov si, 1
        add di, 2
        dec cx
        jmp @@base10
    @@hex:
        mov bx, 16
        dec cx
        xor ax, ax
        add di, 2

    @@base10:
        cmp word ptr [bp+di], "|"
        je @@frac
        cmp word ptr [bp+di], "0"
        jb @@error
        cmp word ptr [bp+di], "9"
        jbe @@N
        cmp bx, 16
        jne @@error
        cmp word ptr [bp+di], "A"
        jb @@error
        cmp word ptr [bp+di], "F"
        jbe @@LU
        cmp word ptr [bp+di], "a"
        jb @@error
        cmp word ptr [bp+di], "f"
        jbe @@LL
        jmp @@error


    @@LL:
        sub word ptr [bp+di], 20h
    @@LU:
        sub word ptr [bp+di], 7h
    @@N:   
        sub word ptr [bp+di], 30h

        xor dx, dx
        mul bx
        add ax, [bp+di]

        add di, 2
        
        cmp ax, 32767
        ja @@error

        loop @@base10
        jmp @@fin

    @@error:
        mov word ptr [bp+66], 1
        mov word ptr [bp+68], 0
        ret 2*32
    @@fin:
        cmp si, 1
        jne @@pos
        xor ax, 0ffffh
        inc ax
    @@pos:
        mov word ptr [bp+66], 1
        mov word ptr [bp+68], ax
        ret 2*32

    @@frac:
        cmp bx, 16
        je @@error
        mov word ptr [bp+68], ax
        xor ax, ax
        dec cx
        mov bx, 10
        add di, 2

        @@base10f:
        cmp word ptr [bp+di], "0"
        jb @@error
        cmp word ptr [bp+di], "9"
        ja @@error

        sub word ptr [bp+di], 30h

        xor dx, dx
        mul bx
        add ax, [bp+di]

        add di, 2
        
        cmp ax, 32767
        ja @@error

        loop @@base10f
        mov word ptr [bp+66], ax

        mov di, [bp+68]
        push di
        push ax
        call gcd
        pop bx
        mov ax, [bp+68]
        xor dx,dx
        div bx

        cmp si, 1
        jne @@posf
        xor ax, 0ffffh
        inc ax
        ;Aqui se pone el proceso de simplificacion
    @@posf:
        mov word ptr [bp+68], ax
        mov ax, [bp+66]
        xor dx,dx
        div bx

        mov word ptr [bp+66], ax
        ret 2*32
    fractio endp
    
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
	writeN proc near
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
    writeN endp
    
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
		
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam
		
		
		mov bx,33
		
		
		sendI:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendI
        call imago
        pop ax
        mov varI, al
        xor ax, ax
        mov al, varI
		push ax
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
        call writei

        mov bx,33
		
		sendD:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendD
        call dualis
        pop ax
        mov varD, al

        mov al,varD
		xor ah,ah
		push ax
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		
		call writed
		
		mov bx,33
        sendN:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja sendN
        call numerus
        pop ax
        mov varN, ax
		mov ax,varN
		push ax
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
		call writeN
		
        mov bx,33
        sendF:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
        ja sendF
        mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		int 21h
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












