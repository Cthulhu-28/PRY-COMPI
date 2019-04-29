extrn legoCatena:far, scriboImago:far, legoNumerus:far
data segment
	varN dw 0
	varC db 32 dup(0)
	varI db 0
	indexOutOfRange db 13,10,"Excepcion: index fuera de rango",13,10,"$"
	
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	index proc near
		locals @@

		mov bp, sp
		mov ax, [bp+2];pos
		cmp ax, 32
		jge @@outR
		xor dx,dx
		mov bx,2
		mul bx
		mov di,ax
		add di,6
		mov ax, [bp+di]
		mov word ptr [bp+66], ax
		ret 2*32

	@@outR:
		lea dx, indexOutOfRange
		mov ah,09h
		int 21h
		mov ax, 4c4dh
		int 21h
	index endp
	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call legoCatena
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		

		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam
		
		call legoNumerus
		pop varN
		
		mov bx,257
		
		;Moves into the stack the string var, bottom-to-top
		send:
			dec bx
			mov al,varC[bx]
			xor ah,ah
			push ax
			cmp bx,0
		ja send
		
		push varN
		
		call index
		pop ax
		mov varI,al
		
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		xor ax,ax
		mov al,varI
		push ax
		call scriboImago
		
		mov ax, 4c4dh
		int 21h
		
		
code ends
end start