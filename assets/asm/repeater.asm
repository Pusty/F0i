mov si, msg
loop:
mov ah, 0
int 0x16
mov [si], al
mov ah, 0x0E
int 0x10
add [counter], byte 1
add si, 1
;cmp [counter], word [len] or even , len as len is a constant and has a fixed size
cmp [counter], byte [len+1]
jl loop

mov si, back
call print_string
mov si, msg
call print_string

hlt

print_string:
    mov ah, 0x0E      ; VGA BIOS fnct. 0x0E: teletype
.loop:
    lodsb             ; grab a byte from SI
    test al, al       ; NUL?
    jz .done          ; if the result is zero, get out
    int 0x10          ; otherwise, print out the character!
    jmp .loop
.done:
    ret

counter db 0
back db 13,10,0
;msg db 0,0,0,0,0,0,0,0,0,0,0,0 ;works
;msg times 10 db 0 ;works
msg resb 10
zero db 0
len equ zero-msg
