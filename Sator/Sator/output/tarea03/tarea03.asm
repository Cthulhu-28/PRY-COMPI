.model small
.stack 0ffffh
.data
	 N dw 0
	 C dw 0
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
	pop C
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit002[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag001
	jmp tag002
tag001:
	push C
	call increm
	pop C
	jmp tag003
tag002:
tag003:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit003[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag004
	jmp tag005
tag004:
	push C
	call increm
	pop C
	jmp tag006
tag005:
tag006:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit004[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag007
	jmp tag008
tag007:
	push C
	call increm
	pop C
	jmp tag009
tag008:
tag009:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit005[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag010
	jmp tag011
tag010:
	push C
	call increm
	pop C
	jmp tag012
tag011:
tag012:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit006[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag013
	jmp tag014
tag013:
	push C
	call increm
	pop C
	jmp tag015
tag014:
tag015:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit007[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag016
	jmp tag017
tag016:
	push C
	call increm
	pop C
	jmp tag018
tag017:
tag018:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit008[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag019
	jmp tag020
tag019:
	push C
	call increm
	pop C
	jmp tag021
tag020:
tag021:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit009[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag022
	jmp tag023
tag022:
	push C
	call increm
	pop C
	jmp tag024
tag023:
tag024:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit010[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag025
	jmp tag026
tag025:
	push C
	call increm
	pop C
	jmp tag027
tag026:
tag027:
	push N
	call num_2_cat
	xor ax,ax
	mov al,_lit011[0]
	push ax
	call index
	call num_2_dual
	pop ax
	cmp ax,0
	jne tag028
	jmp tag029
tag028:
	push C
	call increm
	pop C
	jmp tag030
tag029:
tag030:
	push C
	call scribonumerus
	mov ax,4c00h
	int 21h
end start