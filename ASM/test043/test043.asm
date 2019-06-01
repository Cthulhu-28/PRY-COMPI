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
tag000:
	push n
	push _lit000
	call less
	pop ax
	cmp ax,0
	jne tag001
	jmp tag002
tag001:
	mov bx, 32
tag003:
	dec bx
	xor ax,ax
	mov al,_lit001[bx]
	push ax
	cmp bx,0
ja tag003
	call scribocatena
	mov bx, 32
tag004:
	dec bx
	xor ax,ax
	mov al,_lit002[bx]
	push ax
	cmp bx,0
ja tag004
	call scribocatena
	push n
	push _lit003
	call suma
	pop n
	jmp tag000
tag002:
	push _lit004
	pop n
	mov ax,4c00h
	int 21h
end start