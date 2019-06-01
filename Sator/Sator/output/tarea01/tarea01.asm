.model small
.stack 0ffffh
.data
	 N dw 0
	 T dw 0
	 L dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	call legonumerus
	pop N
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	push _lit001
	pop T
tag001:
	push N
	push _lit002
	call more
	pop ax
	cmp ax,0
	jne tag002
	jmp tag003
tag002:
	push N
	push _lit002
	call divide
	pop N
	push T
	call increm
	pop T
	jmp tag001
tag003:
	push N
	push _lit003
	call moreeq
	pop ax
	cmp ax,0
	jne tag004
	jmp tag005
tag004:
	push T
	call increm
	pop T
	jmp tag006
tag005:
tag006:
	push _lit001
	pop N
	push _lit001
	pop L
tag007:
	push T
	push _lit001
	call more
	pop ax
	cmp ax,0
	jne tag008
	jmp tag009
tag008:
	push L
	push _lit002
	call multi
	pop L
	push T
	call decrem
	pop T
	jmp tag007
tag009:
	push L
	call scribonumerus
	mov ax,4c00h
	int 21h
end start