.model small
.stack 0ffffh
.data
	 d db 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	xor ax,ax
	mov al,d
	push ax
	pop ax
	cmp ax,0
	jne tag000
	jmp tag001
tag000:
	mov bx, 32
tag002:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag002
	call scribocatena
	jmp tag003
tag001:
	mov bx, 32
tag004:
	dec bx
	xor ax,ax
	mov al,_lit001[bx]
	push ax
	cmp bx,0
ja tag004
	call scribocatena
tag003:
	mov ax,4c00h
	int 21h
end start