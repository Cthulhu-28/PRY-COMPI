.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	 A db 2 dup (0)
	 X db 2,58,118,29 dup (0)
	 g db 0fa021h
	include "test036_literals.asm"
.code
.386
	include "test036_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
	push A
	push N
	pop ax
	cmp ax,0

je tag000:

jmp tag001
tag000:
	push _lit000
	pop A
tag001:
	push _lit001
	pop N
end start