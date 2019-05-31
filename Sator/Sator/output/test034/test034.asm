.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	include "test034_literals.asm"
.code
.386
	include "test034_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
	push N
	push _lit000
end start