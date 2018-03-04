org 0x7E00
bits 16
cpu 8086

mov cx, [valueLen]
mov ax, cx
mov dx, 0
mov bx, 4
idiv bx
mov [valueLen], ax

mov cx, [valueLen]
printValue:
push cx
mov si, value
mov ax, 4
mov bx, [valueLen]
sub bx, cx
imul bx
mov dx, 0
add si, ax
call printFBin
call newLine
pop cx
loop printValue

mov si, value
call printFloat
jmp $
	
value dd 15
valueLen dw 4

printFloat:
	mov dl, [si+3]
	call printSign
	
	
	ret
;dl = byte with sign at bit 8
printSign:
	and dl, 10000000b
	mov cl, 1
	shl dl, cl
	jnc .nomin
	mov al, '-'
	call printChar
	jmp .endmin
	.nomin:
	mov al, '+'
	call printChar
	.endmin:
	ret
;single precision real in bits
printFBin:
	mov ah, 0x0E ;tells bios to print 1 char
	mov bh, 0x00 ;page 0
	mov bl, 0x07 ;text attribute
	add si, 4
	
	mov dh, 0
	mov cx, 4
	.byte:
		sub si, 1
		push cx
		mov dl, [si]
		mov cx, 8 ;loop bit 8 times as a byte has 8 bits
		.bit:
			add dh, 1
			.n0:
				cmp dh, 2
				jne .n1
				mov al, ' '
				int 0x10
			.n1:
				cmp dh, 3
				jne .n2
				mov al, ' '
				int 0x10
			.n2:
				cmp dh, 1+8+1
				jne .n3
				mov al, ' '
				int 0x10
			.n3:
			mov ch, cl
			mov cl, 1
			shl dl, cl ;sets carry flag to the bit that's "outshifted"
			jc .one
			mov al, '0'
			jmp .end
			.one:
			mov al, '1'
			.end:
			int 0x10 ;print character
			mov cl, ch
			mov ch, 0 ;resets cx to the counter
			loop .bit
		pop cx
		loop .byte
	
	ret

%include "include/printInt.asm"
%include "include/printBin.asm"
%include "include/newLine.asm"

%assign WASTED 512*16 - ($-$$)
%assign MAX 512*16
%warning Waste: WASTED / MAX Bytes unused [Kernel]
times  512*16 - ($-$$) db 10
