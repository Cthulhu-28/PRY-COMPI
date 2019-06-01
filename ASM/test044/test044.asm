.model small
.stack 0ffffh
.data
	 n dw 0
	 c db 4,104,111,108,97,27 dup (0)
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	push _lit000
	call num_2_cat
	call len
	call scribonumerus
	mov ax,4c00h
	int 21h
end start