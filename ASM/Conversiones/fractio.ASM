data segment
	varN dw 0
	varI db 0
	varD db 0
	varC db 32 dup(0)
	varF dw 2019,2
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
	imago proc near
		mov bp,sp
		mov ax,[bp+2]
		mov bx,256
		xor dx,dx
		div bx
		mov [bp+4],dx
		ret 2
	imago endp
	catena proc near
		locals @@
		mov bp,sp
		mov ax,[bp+2]
		mov di,4
		mov word ptr [bp+di],0
		mov di,6
		mov bx,ax
		and bx ,8000h
		cmp bx,8000h
		jne @@noSign
		mov word ptr [bp+di],"-"
		inc word ptr [bp+4]
		dec ax
		xor ax,0ffffh
		mov word ptr [bp+2],ax
		add di,2
	@@noSign:
		xor cx,cx
		mov bx,10
	@@remainders:
		xor dx,dx
		div bx
		inc cx
		cmp ax,0
		jne @@remainders
	@@pass:
		mov ax,cx
		mov bl,2
		mul bl
		add di,ax
		sub di,2
		mov ax,[bp+2]
		mov bx,10
		mov si,di
		
	@@remainders2:
		xor dx,dx
		div bx
		add dx,48
		mov word ptr [bp+di],dx
		inc word ptr [bp+4]
		sub di,2
		cmp ax,0
		jne @@remainders2
		
		cmp si,68
		je @@return
		add si,2
		
	@@fill:
		mov word ptr [bp+si],0
		add si,2
		cmp si,68
		jbe @@fill
		
	@@return:
		ret 2
	catena endp
	dualis proc near
		locals @@
		mov bp,sp
		mov ax,[bp+2]
		cmp ax,0
		je @@false
		mov word ptr [bp+4],0
		ret 2
	@@false:	
		mov word ptr [bp+4],1
		ret 2
	dualis endp
	fractio proc near
		mov, bp,sp
		mov ax,[bp+2]
		mov word ptr [bp+4],1
		mov word ptr [bp+6],ax
		ret 2
	fractio endp
	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		push varN
		call imago
		pop ax
		mov varI, al
		
		push varN
		call catena
		
		xor bx,bx
		;Reads every byte returned by the read procedure top-to-bottom
		pam:
			pop ax
			mov varC[bx],al
			inc bx
			cmp bx,32
		jbe pam
		
		push varN
		call dualis
		pop ax
		mov varD,al
		
		push varN
		call fractio
		pop ax
		mov varN[0],ax
		pop ax
		mov varN[1],ax
		mov ax, 4c4dh
		int 21h
		
code ends
end start