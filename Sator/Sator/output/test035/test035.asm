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
	call increm
	push N
	call decrem
	xor ax,ax
	mov al,byte ptr g[0]
	push ax
	mov ah,byte ptr g[1]
	mov al,byte ptr g[2]
	push ax
	call getYear
	xor ax,ax
	mov al,_lit000
	push ax
	call non
	push _lit001
	push _lit002
	call suma
	push _lit003
	push _lit004
	call increm
	call multi
	call resta
end start