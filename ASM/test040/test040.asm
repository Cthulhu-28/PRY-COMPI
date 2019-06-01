.model small
.stack 0ffffh
.data
	 C db 32 dup (0)
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	xor bx,bx
tag001:
	pop ax
	mov C[bx],al
	inc bx
	cmp bx,32
jbe tag001
	mov bx, 32
tag002:
	dec bx
	xor ax,ax
	mov al,C[bx]
	push ax
	cmp bx,0
ja tag002
	call scribocatena
	mov ax,4c00h
	int 21h
end start