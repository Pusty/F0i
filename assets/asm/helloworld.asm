
mov si, message
call printString
jmp END

printChar:
	mov ah, 0x0E ;tells bios to print 1 char
	int 0x10 ;do stuff!
	ret
printString:
	pusha ;saves current registers
	mov bh, 0x00 ;page 0
	mov bl, 0x07 ;text attribute
	nextChar:
		lodsb ;moves byte from si into al
		or al, al ;sets flags accordingly for al without changing it
		jz nextChar.end ;if the string ends then end this function
		jmp nextChar.print;print the current char
		.print: ;print a char
			call printChar ;prints the char in al
		.skipChar: ;skip a char
			jmp nextChar ;repeat printing chars
	.end:
	popa ;loads saved registers
	ret
	
message db "Hello World!",0

END: nop