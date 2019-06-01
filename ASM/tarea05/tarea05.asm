.model small
.stack 0ffffh
.data
	 N dw 0
	 C dw 0
	 D dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	call legonumerus
	pop N
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	call legonumerus
	pop C
tag001:
	push N
	push C
	call moreeq
	pop ax
	cmp ax,0
	jne tag002
	jmp tag003
tag002:
	push N
	push C
	call resta
	pop N
	push D
	call increm
	pop D
	jmp tag001
tag003:
	mov bx, 32
tag004:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag004
	call scribocatena
	push D
	call scribonumerus
	mov ax,4c00h
	int 21h
end start