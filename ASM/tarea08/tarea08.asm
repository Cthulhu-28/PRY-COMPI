.model small
.stack 0ffffh
.data
	 N dw 0
	 C db 1 dup (0)
	 R db 1 dup (0)
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	call legonumerus
	pop N
	xor ax,ax
	mov al,_lit000[0]
	push ax
	pop ax
	mov C,al
	xor ax,ax
	mov al,_lit000[0]
	push ax
	pop ax
	mov R,al
tag000:
	xor ax,ax
	mov al,C[0]
	push ax
	call img_2_num
	xor ax,ax
	mov al,_lit001[0]
	push ax
	call img_2_num
	call lesseq
	pop ax
	cmp ax,0
	jne tag001
	jmp tag002
tag001:
	push N
	call num_2_cat
	xor ax,ax
	mov al,C[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag003
	jmp tag004
tag003:
	xor ax,ax
	mov al,C[0]
	push ax
	pop ax
	mov R,al
	jmp tag005
tag004:
tag005:
	xor ax,ax
	mov al,C[0]
	push ax
	call img_2_num
	call increm
	call num_2_img
	pop ax
	mov C,al
	jmp tag000
tag002:
	mov bx, 32
tag006:
	dec bx
	xor ax,ax
	mov al,_lit002[bx]
	push ax
	cmp bx,0
ja tag006
	call scribocatena
	xor ax,ax
	mov al,R[0]
	push ax
	call scriboimago
	mov ax,4c00h
	int 21h
end start