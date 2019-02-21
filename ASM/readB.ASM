
data segment
	var db 0
	boolExpected db 13,10,"Exceptis: dualis expectata",10,13,"$"
data ends

sstack segment stack 'stack'
    dw 256 dup (?)
sstack ends

code segment
	assume cs:code, ds:data,ss:sstack
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

	start:	
		mov ax, data
        mov ds, ax

        mov ax, sstack
        mov ss, ax
		
		call read
		
		;Optional line break, not part of the procedures
		mov ah, 02h
		mov dl, 10
		int 21h
		mov dl, 13
		
		pop ax
		mov var,al
		
		mov al,var
		xor ah,ah
		push ax
		call write


		
		
		mov ax, 4c4dh
		int 21h
code ends
end start