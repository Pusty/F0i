%ifndef SLEEP
    %define SLEEP
	
sleep:; input si: delay in 1.024*ms
	xor eax,eax;clear eax for using it as nanosec storeage
	mov ax,si
	shl eax,10
	;eax=2^10*si
	mov dx,ax
	shr eax,16
	mov cx,ax
	;dx,cx=eax
	mov ah, 86h
	int 15h;CX,DX interval in microseconds (1,000,000ths of a second) - CX is high word, DX is low word
	ret

%endif