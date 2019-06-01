.model small
.stack 0ffffh
.data
	 N dw 0
	 L dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	call legonumerus
	pop N
	push N
	call num_2_cat
	call len
	pop L
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	push L
	call scribonumerus
	mov ax,4c00h
	int 21h
end start