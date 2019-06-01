.model small
.stack 0ffffh
.data
	 date db 3 dup (0)
	 today db 3 dup (0)
	 diff dw 0
	include literals.asm
.code
.386
	include runtime.asm
start:
	mov ax,@data
	mov ds,ax
	mov bx, 32
tag000:
	dec bx
	xor ax,ax
	mov al,_lit000[bx]
	push ax
	cmp bx,0
ja tag000
	call scribocatena
	call legogregorius
	pop ax
	pop dx
	mov byte ptr date[0],dl
	mov byte ptr date[1],ah
	mov byte ptr date[2],al
	xor dx,dx
	mov dl,byte ptr today[0]
	mov ah,byte ptr today[1]
	mov al,byte ptr today[2]
	push ax
	push dx
	call nunc
	pop ax
	pop dx
	mov byte ptr today[0],dl
	mov byte ptr today[1],ah
	mov byte ptr today[2],al
	xor dx,dx
	mov dl,byte ptr today[0]
	mov ah,byte ptr today[1]
	mov al,byte ptr today[2]
	push ax
	push dx
	call getYear
	xor dx,dx
	mov dl,byte ptr date[0]
	mov ah,byte ptr date[1]
	mov al,byte ptr date[2]
	push ax
	push dx
	call getYear
	call resta
	pop diff
	mov bx, 32
tag001:
	dec bx
	xor ax,ax
	mov al,_lit001[bx]
	push ax
	cmp bx,0
ja tag001
	call scribocatena
	push diff
	call scribonumerus
	mov ax,4c00h
	int 21h
end start