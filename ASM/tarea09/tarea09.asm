.model small
.stack 0ffffh
.data
	 F dw 1,1
	 D dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	call legofractio
	pop ax
	mov word ptr F[0],ax
	pop ax
	mov word ptr F[2],ax
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	push F[2]
	push F[0]
	call pent
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
	push D
	call scribonumerus
	mov ax,4c00h
	int 21h
end start