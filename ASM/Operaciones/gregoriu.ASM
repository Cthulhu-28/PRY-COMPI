extrn scriboGregorius:far,legoGregorius:far,legoNumerus:far,scriboNumerus:far,scriboDualis:far,legoFractio:far,scriboFractio:far
.model small
.stack 0ffffh 
.data
	varG db 3 dup(0)
	varG2 db 3 dup(0)
	varN dw 0
	varB db 0
	varF dw 2 dup(1)
	msg1 db "+ y -",10,13,"$"
	msg2 db "d, n y ?",10,13,"$"
	msg3 db "Partes",10,13,"$"
	msg4 db "Hoy",10,13,"$"
	lineBreak db 10,13,"$"
.code
	.386
	toDays proc near
		locals @@
		mov bp,sp
		
		;Año
		mov dx,[bp+2]
		xor eax,eax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov esi,eax
		;Mes
		xor eax,eax
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov edi,eax
		;Dia
		xor eax,eax
		mov ax,[bp+4]
		and ax,1fh
		mov ecx,eax
		
		;m = (m + 9) % 12
		mov eax,edi
		add eax,9
		mov ebx,12
		xor edx,edx
		div ebx
		mov edi,edx
		
		;y = y - m/10
		mov eax,edi
		mov ebx,10
		xor edx,edx
		div ebx
		sub esi,eax
		
		mov dword ptr [bp+2],0
		;365*y + y/4 - y/100 + y/400 + (m*306 + 5)/10 + ( d - 1 )
		
		;365*y
		mov ebx,365
		xor edx,edx
		mov eax,esi
		mul ebx
		add dword ptr [bp+2],eax
		
		;y/4
		xor edx,edx
		mov ebx,4
		mov eax,esi
		div ebx
		add dword ptr [bp+2],eax
		
		;y/100
		xor edx,edx
		mov ebx,100
		mov eax,esi
		div ebx
		sub dword ptr [bp+2],eax
		
		;y/400
		xor edx,edx
		mov ebx,400
		mov eax,esi
		div ebx
		add dword ptr [bp+2],eax
		
		;(m*306 + 5)/10
		xor edx,edx
		mov ebx,306
		mov eax,edi
		mul ebx
		add eax,5
		
		xor edx,edx
		mov ebx,10
		div ebx
		add dword ptr [bp+2],eax
		
		;( d - 1 )
		mov eax,ecx
		dec eax
		add dword ptr [bp+2],eax
		
		ret
	toDays endp
	toDate proc
		locals @@
		mov bp,sp
		;y = (10000*g + 14780)/3652425
		mov eax, dword ptr [bp+2]
		xor edx,edx
		mov ebx,10000
		mul ebx
		add eax,14780
		;xor edx,edx
		mov ebx,3652425
		div ebx
		mov esi,eax
		
		xor ecx,ecx
		mov eax,esi
		xor edx,edx
		mov ebx,365
		
	@@dayCalc:	
		;ddd = g - (365*y + y/4 - y/100 + y/400)
		
		;365*y
		mov ebx,365
		xor edx,edx
		mov eax,esi
		mul ebx
		add ecx,eax
		
		;y/4
		xor edx,edx
		mov ebx,4
		mov eax,esi
		div ebx
		add ecx,eax
		
		;y/100
		xor edx,edx
		mov ebx,100
		mov eax,esi
		div ebx
		sub ecx,eax
		
		;y/400
		xor edx,edx
		mov ebx,400
		mov eax,esi
		div ebx
		add ecx,eax
		
		mov ebx,ecx
		mov ecx,dword ptr [bp+2]
		sub ecx,ebx
		js @@isNegative
		jmp @@next
	@@isNegative:
		dec esi
		jmp @@dayCalc
	@@next:
		;mi = (100*ddd + 52)/3060
		mov eax,ecx
		xor edx,edx
		mov ebx,100
		mul ebx
		add eax,52
		xor edx,edx
		mov ebx,3060
		div ebx
		mov edi,eax
		
		
		;mm = (mi + 2)%12 + 1
		mov dword ptr [bp+2],0
		mov eax,edi
		add eax,2
		xor edx,edx
		mov ebx,12
		div ebx
		inc edx
		mov dword ptr [bp+2],edx
		
		;y = y + (mi + 2)/12
		mov eax,edi
		add eax,2
		xor edx,edx
		mov ebx,12
		div ebx
		add esi,eax
		
		;dd = ddd - (mi*306 + 5)/10 + 1
		mov eax,edi
		xor edx,edx
		mov ebx,306
		mul ebx
		add eax,5
		xor edx,edx
		mov ebx,10
		div ebx
		sub ecx,eax
		inc ecx
		
		mov edi, dword ptr [bp+2]
		mov dword ptr [bp+2],0
		
		;year in si
		;month in di
		;day in cx
		
		;Operaciones sobre bits para retonar con el formato de la fecha
		xor ax,ax
		xor dx,dx
		
		;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
		mov ax,cx
		
		;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
		;Se suma a los bits del día.
		mov bx,di
		shl bx,5
		add ax,bx
		
		;El año ocupa los primeros 7 bits del segundo byte.
		;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
		mov bx,si
		shl bx,9
		add ax,bx
		
		;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
		;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
		mov bx,si
		shr bx,7
		mov dx,bx
		;Se retorna
		mov bp,sp
		mov [bp+2],ax ;2do y 3er byte
		mov [bp+4],dx ;1er byte
		ret
	toDate endp
	
	addDays proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+6]
		mov bx,[bp+4]
		push ax
		push bx
		call toDays
		pop eax
		mov bp,sp
		xor ebx,ebx
		mov bx,[bp+2]
		cmp bx,7FFFh
		jbe @@isPostive
		dec bx
		xor bx,0ffffh
		sub eax,ebx
		jmp @@castToDate
	@@isPostive:
		add eax,ebx
	@@castToDate:
		push eax
		call toDate
		pop ax
		pop dx
		mov bp,sp
		
		mov word ptr [bp+4],ax
		mov word ptr [bp+6],dx
		
		ret 2
	addDays endp
	subDays proc near
		locals @@
		mov bp,sp
		
		mov ax,[bp+6]
		mov bx,[bp+4]
		push ax
		push bx
		call toDays
		pop eax
		mov bp,sp
		xor ebx,ebx
		mov bx,[bp+2]
		cmp bx,7FFFh
		jbe @@isPostive
		dec bx
		xor bx,0ffffh
		add eax,ebx
		jmp @@castToDate
	@@isPostive:
		sub eax,ebx
	@@castToDate:
		push eax
		call toDate
		pop ax
		pop dx
		mov bp,sp
		
		mov word ptr [bp+4],ax
		mov word ptr [bp+6],dx
		
		ret 2
	subDays endp
	weekDay proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+2]
		xor ax,ax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		;Mes
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov di,ax
		;Dia
		mov ax,[bp+4]
		and ax,1fh
		mov cx,ax
		
		xor ax,ax
		xor dx,dx
		xor bx,bx
		
		;y = year - (14 - month) / 12;
		;si = si - (14 - di) / 12;
		mov ax,14
		sub ax,di
		mov bx,12
		div bx
		sub si,ax ;En si queda el nuevo cálculo de y
		
		xor ax,ax
		xor dx,dx
		
		mov word ptr [bp+6],si
		mov ax,si
		mov bx,4
		div bx
		add word ptr [bp+6],ax
		
		xor dx,dx
		mov ax,si
		mov bx,100
		div bx
		sub word ptr [bp+6],ax
		
		xor dx,dx
		mov ax,si
		mov bx,400
		div bx
		add word ptr [bp+6],ax
		
		mov si,[bp+6]
		mov word ptr [bp+6],0
		
		;m = month + 12 * ((14 - month) / 12) - 2;
		mov ax,14
		sub ax,di
		mov bx,12
		xor dx,dx
		div bx
		xor dx,dx
		mul bx
		add ax,di
		sub ax,2
		mov di,ax
		
		mov ax,di
		mov bx,31
		xor dx,dx
		mul bx
		xor dx,dx
		mov bx,12
		div bx
		
		add ax,cx
		add ax,si
		mov bx,7
		xor dx,dx
		div bx
		mov word ptr [bp+6],dx
		ret 2*2
	weekDay endp
	isLeap proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+2]
		xor ax,ax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		
		
		;di será 1 si el año es bisiesto
		mov ax,si
		mov cx,ax
		xor di,di
		
		;Divisibiliad por 400
		mov bx,400
		xor dx,dx
		div bx
		cmp dx,0
		jne @@LeapA
		mov di,1
		jmp @@next
	@@LeapA:
		mov ax,si
		;No divisibiliad por 100
		mov bx,100
		xor dx,dx
		div bx
		cmp dx,0
		je @@next
		mov ax,si
		;Divisibiliad por 4
		mov bx,4
		xor dx,dx
		div bx
		cmp dx,0
		jne @@next
		mov di,1
	@@next:	
		mov word ptr [bp+4],di
		ret 2
	isLeap endp
	
	dayInMonth proc near
		locals @@
		mov bp,sp
		mov ax,[bp+4]
		mov bx,[bp+2]
		push ax
		push bx
		call isLeap
		pop si
		mov bp,sp
		
		;Mes
		xor eax,eax
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov edi,eax
		;Dia
		xor eax,eax
		mov ax,[bp+4]
		and ax,1fh
		mov ecx,eax
		
		;m = (m + 9) % 12
		mov eax,edi
		add eax,9
		mov ebx,12
		xor edx,edx
		div ebx
		mov edi,edx
		
		
		
		;(m*306 + 5)/10
		xor edx,edx
		mov ebx,306
		mov eax,edi
		mul ebx
		add eax,5
		
		xor edx,edx
		mov ebx,10
		div ebx
		mov edi,eax
		
		;( d - 1 )
		mov eax,ecx
		dec eax
		add edi,eax
		
		
		add edi,59
		add edi,esi
		
		mov ebx,365
		add ebx,esi
		xor edx,edx
		mov eax,edi
		div ebx
		inc edx
		
		mov word ptr [bp+2],1
		mov word ptr [bp+4],dx	
		ret
		
	dayInMonth endp
	
	nunc proc near
		mov bp,sp
		mov ah,2ah
		int 21h
		xor ax,ax
		mov al,dh
		mov si,ax
		xor ax,ax
		mov al,dl
		mov di,ax
		
		
		;CX contiene el año
		;DI el día
		;SI el mes
		
		
		;0000 0000  0000 0000  0000 0000
		; |       año      || mes|| día|  
		; |        14      ||  4 ||  5 |
		;El primer bit queda libre
		
		;Operaciones sobre bits para retonar con el formato de la fecha
		xor ax,ax
		xor dx,dx
		
		;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
		mov ax,di
		
		;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
		;Se suma a los bits del día.
		mov bx,si
		shl bx,5
		add ax,bx
		
		;El año ocupa los primeros 7 bits del segundo byte.
		;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
		mov bx,cx
		shl bx,9
		add ax,bx
		
		;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
		;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
		mov bx,cx
		shr bx,7
		mov dx,bx
		;Se retorna
		mov bp,sp
		mov [bp+2],ax ;2do y 3er byte
		mov [bp+4],dx ;1er byte
		
		ret
	nunc endp
	
	getYear proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+2]
		xor ax,ax
		shl dx,7
		mov ax,dx
		mov dx,[bp+4]
		shr dx,9
		add ax,dx
		mov si,ax
		mov word ptr [bp+4],si
		ret 2
	getYear endp
	getMonth proc near
		locals @@
		
		mov bp,sp
		;Mes
		mov ax,[bp+4]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov di,ax
		mov word ptr [bp+4],di
		ret 2
	getMonth endp
	getDay proc near
		locals @@
		
		mov bp,sp
		;Dia
		mov ax,[bp+4]
		and ax,1fh
		mov cx,ax
		mov word ptr [bp+4],cx
		ret 2
	getDay endp
	setDay proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+4]
		xor eax,eax
		shl dx,7
		mov ax,dx
		mov dx,[bp+6]
		shr dx,9
		add ax,dx
		mov cx,ax
		;Mes
		mov ax,[bp+6]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov si,ax
		;Dia
		mov ax,[bp+2]
		;and ax,1fh
		mov di,ax
		
		;CX contiene el año
		;DI el día
		;SI el mes
		
		
		;0000 0000  0000 0000  0000 0000
		; |       año      || mes|| día|  
		; |        14      ||  4 ||  5 |
		;El primer bit queda libre
		
		;Operaciones sobre bits para retonar con el formato de la fecha
		xor ax,ax
		xor dx,dx
		
		;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
		mov ax,di
		
		;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
		;Se suma a los bits del día.
		mov bx,si
		shl bx,5
		add ax,bx
		
		;El año ocupa los primeros 7 bits del segundo byte.
		;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
		mov bx,cx
		shl bx,9
		add ax,bx
		
		;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
		;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
		mov bx,cx
		shr bx,7
		mov dx,bx
		;Se retorna
		mov bp,sp
		mov [bp+4],ax ;2do y 3er byte
		mov [bp+6],dx ;1er byte
		
		ret 2
	setDay endp
	setMonth proc near
		locals @@
		
		mov bp,sp
		;Año
		mov dx,[bp+4]
		xor eax,eax
		shl dx,7
		mov ax,dx
		mov dx,[bp+6]
		shr dx,9
		add ax,dx
		mov cx,ax
		;Mes
		mov ax,[bp+2]
		; shr ax,5
		; xor ah,ah
		; and al,0fh
		mov si,ax
		;Dia
		mov ax,[bp+6]
		and ax,1fh
		mov di,ax
		
		;CX contiene el año
		;DI el día
		;SI el mes
		
		
		;0000 0000  0000 0000  0000 0000
		; |       año      || mes|| día|  
		; |        14      ||  4 ||  5 |
		;El primer bit queda libre
		
		;Operaciones sobre bits para retonar con el formato de la fecha
		xor ax,ax
		xor dx,dx
		
		;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
		mov ax,di
		
		;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
		;Se suma a los bits del día.
		mov bx,si
		shl bx,5
		add ax,bx
		
		;El año ocupa los primeros 7 bits del segundo byte.
		;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
		mov bx,cx
		shl bx,9
		add ax,bx
		
		;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
		;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
		mov bx,cx
		shr bx,7
		mov dx,bx
		;Se retorna
		mov bp,sp
		mov [bp+4],ax ;2do y 3er byte
		mov [bp+6],dx ;1er byte
		
		ret 2
	setMonth endp
	setYear proc near
		locals @@
		
		mov bp,sp
		;Año
		; mov dx,[bp+2]
		; xor eax,eax
		; shl dx,7
		; mov ax,dx
		; mov dx,[bp+4]
		; shr dx,9
		; add ax,dx
		mov cx,[bp+2]
		;Mes
		mov ax,[bp+6]
		shr ax,5
		xor ah,ah
		and al,0fh
		mov si,ax
		;Dia
		mov ax,[bp+6]
		and ax,1fh
		mov di,ax
		
		;CX contiene el año
		;DI el día
		;SI el mes
		
		
		;0000 0000  0000 0000  0000 0000
		; |       año      || mes|| día|  
		; |        14      ||  4 ||  5 |
		;El primer bit queda libre
		
		;Operaciones sobre bits para retonar con el formato de la fecha
		xor ax,ax
		xor dx,dx
		
		;El día es el valor más a la derecha, no ocupa mayor tratamiento, solo se mueve.
		mov ax,di
		
		;El día ocupa 5 bits. Al mes se le hace un corrimiento de 5 bits a la izquierda.
		;Se suma a los bits del día.
		mov bx,si
		shl bx,5
		add ax,bx
		
		;El año ocupa los primeros 7 bits del segundo byte.
		;Se hace un corrrimiento de 9 bits a la izquierda y se suma.
		mov bx,cx
		shl bx,9
		add ax,bx
		
		;Queda el último byte. El año ocupa 7 bits del 1 byte más a la izquierda.
		;Se hace un corrimiento de 7 bits a la derecha para almacenarlo en el tercer byte.
		mov bx,cx
		shr bx,7
		mov dx,bx
		;Se retorna
		mov bp,sp
		mov [bp+4],ax ;2do y 3er byte
		mov [bp+6],dx ;1er byte
		
		ret 2
	setYear endp

	start:
		mov ax,@data 
		mov ds,ax 
	
		
		lea dx,msg1
		mov ah,09h
		int 21h

		call legoGregorius
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		call legoNumerus
		pop varN
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]	
		push ax
		push dx
		push varN
		call addDays
		pop ax
		pop dx
		mov byte ptr varG2[0],dl
		mov byte ptr varG2[1],ah
		mov byte ptr varG2[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG2[0]
		mov ah,byte ptr varG2[1]
		mov al,byte ptr varG2[2]
		push ax
		push dx
		call scriboGregorius
		
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]	
		push ax
		push dx
		push varN
		call subDays
		pop ax
		pop dx
		mov byte ptr varG2[0],dl
		mov byte ptr varG2[1],ah
		mov byte ptr varG2[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG2[0]
		mov ah,byte ptr varG2[1]
		mov al,byte ptr varG2[2]
		push ax
		push dx
		call scriboGregorius
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		lea dx,msg2
		mov ah,09h
		int 21h
		
		call legoGregorius
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		call weekDay
		pop varN
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		push varN
		call scriboNumerus
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		call isLeap
		pop ax
		mov varB,al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		mov al,varB
		xor ah,ah
		push ax
		call scriboDualis
		
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		
		call dayInMonth
		
		pop cx;numerado
		pop ax;denominador
		mov word ptr varF[0],cx
		mov word ptr varF[2],ax
		
		mov ax,varF[2]
		mov cx,varF[0]
		push ax
		push cx
		
		call scriboFractio
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		lea dx,msg4
		mov ah,09h
		int 21h
		
		call nunc
		
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call scriboGregorius
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call getYear
		
		pop varN
		push varN
		
		call scriboNumerus
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call getMonth
		
		pop varN
		push varN
		
		call scriboNumerus
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		push ax
		push dx
		call getDay
		
		pop varN
		push varN
		
		call scriboNumerus
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		lea dx,msg3
		mov ah,09h
		int 21h
		
		call legoNumerus
		pop varN
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		push varN
		call setYear
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		
		
		push ax
		push dx
		call scriboGregorius
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		
		call legoNumerus
		pop varN
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		push varN
		call setMonth
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		call scriboGregorius
		
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		
		call legoNumerus
		pop varN
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		push varN
		call setDay
		pop ax
		pop dx		
		mov byte ptr varG[0],dl
		mov byte ptr varG[1],ah
		mov byte ptr varG[2],al
		
		lea dx,lineBreak
		mov ah,09h
		int 21h
		
		xor dx,dx
		mov dl,byte ptr varG[0]
		mov ah,byte ptr varG[1]
		mov al,byte ptr varG[2]
		push ax
		push dx
		call scriboGregorius
		
		
		mov ax,4c00h
		int 21h
	end start