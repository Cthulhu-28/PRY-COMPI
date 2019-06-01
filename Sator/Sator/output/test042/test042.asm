.model small
.stack 0ffffh
.data
	 d db 0
	 n dw 4
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	push n
	xor ax,ax
	mov al,d
	push ax
	call dual_2_num
	call suma
	call num_2_dual
	push _lit000
	call num_2_dual
	call et
	call dual_2_num
	pop n
	mov ax,4c00h
	int 21h
end start