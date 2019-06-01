.model small
.stack 0ffffh
.data
	 n dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	push _lit000
	pop n
	push _lit001
	push _lit002
	push n
	call scribonumerus
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit003[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	mov bx, 32
tag001:
	dec bx
	xor ax,ax
	mov al,_lit004[bx]
	push ax
	cmp bx,0
ja tag001
	call scribocatena
	mov ax,4c00h
	int 21h
end start