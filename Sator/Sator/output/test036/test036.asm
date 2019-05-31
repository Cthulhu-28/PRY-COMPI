.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	 X db 2,58,118,29 dup (0)
	 g db 0fa021h
	include "test036_literals.asm"
.code
.386
	include "test036_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
	push _lit000
	push _lit001
	push _lit002
	push N
	call divide
	call multi
	call suma
	pop N
	mov bx, 32
tag000:
	dec bx
	mov al,_lit003[bx]
	push ax
	cmp bx,0
ja tag000
	xor bx,bx
tag001:
	pop ax
	mov X[bx],al
	inc bx
	cmp bx,32
jbe tag001
end start