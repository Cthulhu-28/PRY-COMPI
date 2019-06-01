.model small
.stack 0ffffh
.data
	 N db 2 dup (0)
	 A db 2 dup (0)
	 X db 2,58,118,29 dup (0)
	 g db 0fa021h
	include "test037_literals.asm"
.code
.386
	include "test037_runtime.asm"
start:
	mov ax,@data
	mov ds,ax
	push A
	push N
	call equal
	pop ax
	cmp ax,0
	jne tag000
	jmp tag001
tag000:
	push _lit000
	pop A
	jmp tag002
tag001:
	push A
	push _lit001
	call equal
	pop ax
	cmp ax,0
	jne tag003
	jmp tag004
tag003:
	push _lit002
	pop A
	jmp tag005
tag004:
	push _lit003
	pop A
tag005:
tag002:
end start