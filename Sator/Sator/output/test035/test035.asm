.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	 X db 2,58,118,29 dup (0)
	 g db 0fa021h
	include "test035_literals.asm"
.code
.386
	include "test035_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
	push N
	push _lit000
	 N db 2 dup (0)
end start