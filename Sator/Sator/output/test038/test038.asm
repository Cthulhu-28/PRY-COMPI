.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	 A db 2 dup (0)
	 X db 2,58,118,29 dup (0)
	 g db 0fa021h
	include "test038_literals.asm"
.code
.386
	include "test038_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
tag000:
	push A
	push _lit000
	call equal
	pop ax
	cmp ax,0
	jne tag001
	jmp tag002
tag001:
	push _lit001
	pop A
	jmp tag000
tag002:
	push _lit001
	pop A
end start