.model small
.stack 0ffffh
.data
	 N dw 0
	 N2 dw 0
	 N3 dw 0
	 C db 1 dup (0)
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
	call legoimago
	pop ax
	mov C,al
	push N
	call num_2_cat
	call len
	pop N2
	push N
	call num_2_cat
	xor ax,ax
	mov al,C[0]
	push ax
	call index
	pop N3
	push N2
	push N3
	call resta
	pop D
	push D
	call increm
	pop D
	mov bx, 32
tag001:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag001
	call scribocatena
	push N
	call num_2_cat
	call len
	push N
	call num_2_cat
	xor ax,ax
	mov al,C[0]
	push ax
	call index
	call resta
	call increm
	call scribonumerus
	mov ax,4c00h
	int 21h
end start