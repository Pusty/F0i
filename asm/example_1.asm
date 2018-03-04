
mov ah, 0 ;set display mode
mov al, 13h ;13h = 320x200
int  0x10 ;tell bios to change to 320x200

mov bx, 25

mov al, 15 ;c
mov cx, 0+60 ;x
mov dx, 0+60 ;y
call drawSquare
mov al, 14 ;c
mov cx, 0+60 ;x
mov dx, 25+60 ;y
call drawSquare
mov al, 13 ;c
mov cx, 0+60 ;x
mov dx, 50+60 ;y
call drawSquare
mov al, 12 ;c
mov cx, 25+60 ;x
mov dx, 25+60 ;y
call drawSquare
mov al, 11 ;c
mov cx, 50+60 ;x
mov dx, 0+60 ;y
call drawSquare
mov al, 10 ;c
mov cx, 50+60 ;x
mov dx, 25+60 ;y
call drawSquare
mov al, 9 ;c
mov cx, 50+60 ;x
mov dx, 50+60 ;y
call drawSquare



mov al, 4 ;c
mov cx, 100+60 ;x
mov dx, 0+60 ;y
call drawSquare
mov al, 5 ;c
mov cx, 100+60 ;x
mov dx, 25+60 ;y
call drawSquare
mov al, 6 ;c
mov cx, 100+60 ;x
mov dx, 50+60 ;y
call drawSquare

;mov ah, 0xC ;draw pixel
;int 0x10 ;draw pixel at x,y with color c

hlt	


;bx = size, cx = xpos, dx = ypos, al = color
drawSquare:
	pusha
		mov ah, 0xC ;sets ah to trigger draw pixel on interrupt
		mov si, cx  ;saves x position parameter into si to use cx as a counter for the loops
		mov cx, bx ;sets counter for size on y-axis
		.ysize:
			push cx ;saves current counter for y-axis
			mov cx, bx ;reset counter for size on x-axis
			.xsize:
				push cx ;saves current x-axis counter
				mov cx, si ;set counter register to the drawing x position
				int 0x10 ;draws a pixel at the given position (cx=xpos, dx=ypos, ah=0xC, al=color)
				pop cx ;resets current x-axis counter
			add si, 1 ;increases y position by 1
			loop .xsize ;if x-axis counter is done end if not repeat and decrease counter by 1
			pop cx ;resets counter for y-axis
			add dx, 1 ;increases y position by 1
			sub si, bx ;resets x position
		loop .ysize ;if y-axis counter is done end if not repeat and decrease counter by 1
	popa
	ret