org 0x7E00
bits 16
call L8702985
jmp $
L8702985:
   .lL8702985:
   call initGraphics
   .lL31198842:
   mov si, word pal
   call loadPal
   .lL3840954:
   mov ax, word [freeze]
   mov bx, 0 
   cmp bx, ax
   je .lL29744585
   .lL23328673:
   mov di, word screen
   mov ax, word 0
   mov bx, word 0
   call drawImage
   .lL1478827:
   call flip
   .lL24374386:
   jmp .lL27811128
   .lL8406772:
   mov si, word 100
   call sleep
   .lL21830977:
   mov ax, word 0
   call checkKey
   mov [keyPressed], ax 
   .lL29087666:
   mov ax, word 1
   call checkKey
   mov dx, ax
   mov [stack_L8702985+0], dx 
   .lL21886820:
   mov ax, word [stack_L8702985+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL28113457
   .lL22927632:
   mov ax, word 0
   mov [keyState], ax 
   jmp .lL5618579
   .lL28113457:
   mov ax, word 1
   mov [keyState], ax 
   .lL5618579:
   mov bx, word [keyPressed]
   mov ax, word 1
   cmp ax, bx
   je .lL27811128
   .lL9299042:
   mov ax, word 0
   mov [freeze], ax 
   .lL27811128:
   mov ax, word [freeze]
   mov bx, 0 
   cmp bx, ax
   jne .lL8406772
   .lL29744585:
   mov dx, word 0
   mov [stack_L6775863+0], dx 
   call L6775863
   .lL22831804:
   mov ax, word [freeze]
   mov bx, 0 
   cmp bx, ax
   jne .lL5660886
   mov ax, word [loading]
   mov bx, 0 
   cmp bx, ax
   jge .lL15696851
   .lL12039161:
   jmp .lL5660886
   .lL8970080:
   mov ax, word [loading]
   mov bx, 0 
   cmp bx, ax
   jge .lL78219
   .lL6011238:
   mov ax, word [loading]
   mov bx, word 1
   sub ax, bx
   mov [loading], ax 
   .lL32048085:
   mov ax, word [loading]
   mov bx, word 4
   mov dx, 0 
   div bx
   mov bx, dx
   mov ax, word 2
   cmp ax, bx
   jg .lL16426320
   .lL24864323:
   mov di, word loading_0
   mov ax, word 44
   mov bx, word 40
   call drawImage
   jmp .lL7792807
   .lL16426320:
   mov di, word loading_1
   mov ax, word 44
   mov bx, word 40
   call drawImage
   .lL7792807:
   mov di, word loadingImg
   mov ax, word 54
   mov bx, word 42
   call drawImage
   .lL78219:
   call flip
   .lL10769718:
   mov si, word 100
   call sleep
   .lL5660886:
   mov ax, word [loading]
   mov bx, 0 
   cmp bx, ax
   jl .lL8970080
   .lL14050342:
   mov ax, word [freeze]
   mov bx, 0 
   cmp bx, ax
   je .lL15696851
   .lL21565109:
   mov di, word screen
   mov ax, word 0
   mov bx, word 0
   call drawImage
   .lL23376028:
   call flip
   .lL25621063:
   jmp .lL9194103
   .lL7143488:
   mov si, word 100
   call sleep
   .lL2855942:
   mov ax, word 0
   call checkKey
   mov [keyPressed], ax 
   .lL16994425:
   mov ax, word 1
   call checkKey
   mov dx, ax
   mov [stack_L8702985+0], dx 
   .lL9977237:
   mov ax, word [stack_L8702985+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL15926420
   .lL11652231:
   mov ax, word 0
   mov [keyState], ax 
   jmp .lL15397900
   .lL15926420:
   mov ax, word 1
   mov [keyState], ax 
   .lL15397900:
   mov bx, word [keyPressed]
   mov ax, word 1
   cmp ax, bx
   je .lL9194103
   .lL20735553:
   mov ax, word 0
   mov [freeze], ax 
   .lL9194103:
   mov ax, word [freeze]
   mov bx, 0 
   cmp bx, ax
   jne .lL7143488
   .lL15696851:
   mov bx, word [coins]
   mov ax, word 7
   cmp ax, bx
   jg .lL6109469
   .lL5285449:
   mov ax, word 0
   mov [coins], ax 
   .lL13961193:
   mov bx, word [mapIndex]
   mov ax, word 1
   add ax, bx
   mov [mapIndex], ax 
   .lL33189144:
   mov bx, word [mapIndex]
   mov ax, word 2
   cmp ax, bx
   jg .lL1067475
   .lL22992473:
   mov ax, word 0
   mov [mapIndex], ax 
   .lL21658130:
   mov dx, word [mapIndex]
   mov [stack_L6775863+0], dx 
   call L6775863
   .lL9716945:
   mov ax, word 300
   mov [loading], ax 
   jmp .lL3278348
   .lL1067475:
   mov dx, word [mapIndex]
   mov [stack_L6775863+0], dx 
   call L6775863
   .lL3278348:
   mov ax, word 0
   mov [playerX], ax 
   .lL2830910:
   mov ax, word 20
   mov [playerY], ax 
   .lL6109469:
   mov ax, word [jump]
   mov bx, 0 
   cmp bx, ax
   je .lL32486590
   .lL4414010:
   mov ax, word -2
   mov [velY], ax 
   .lL30983464:
   mov bx, word [inJump]
   mov ax, word 5
   cmp ax, bx
   jle .lL7858936
   .lL14900151:
   mov ax, word -1
   mov [velY], ax 
   .lL7858936:
   mov ax, word [inJump]
   mov bx, word 1
   sub ax, bx
   mov [inJump], ax 
   .lL10127976:
   mov ax, word [inJump]
   mov bx, 0 
   cmp bx, ax
   jl .lL32826737
   .lL17010151:
   mov ax, word 0
   mov [jump], ax 
   .lL32826737:
   mov ax, word 0
   mov [onGround], ax 
   jmp .lL5863106
   .lL32486590:
   mov ax, word 1
   mov [velY], ax 
   .lL5863106:
   mov ax, word 0
   call checkKey
   mov [keyPressed], ax 
   .lL25421790:
   mov ax, word 1
   call checkKey
   mov dx, ax
   mov [stack_L8702985+0], dx 
   .lL15453627:
   mov ax, word [stack_L8702985+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL17818297
   .lL32380043:
   mov ax, word 0
   mov [keyState], ax 
   jmp .lL25613444
   .lL17818297:
   mov ax, word 1
   mov [keyState], ax 
   .lL25613444:
   call keyHandle
   .lL15354046:
   mov ax, word [velXP]
   mov [velX], ax 
   .lL21878616:
   call L5612344
   .lL24435002:
   call L21868771
   .lL6400263:
   mov ax, word [velX]
   mov bx, 0 
   cmp bx, ax
   je .lL27271771
   mov ax, word [isMoving]
   mov bx, 0 
   cmp bx, ax
   je .lL27271771
   .lL3115866:
   mov ax, word [velX]
   mov [lastVel], ax 
   .lL27271771:
   mov ax, word [lastVel]
   mov bx, 0 
   cmp bx, ax
   jge .lL23447542
   .lL19589694:
   mov ax, word [onGround]
   mov bx, 0 
   cmp bx, ax
   jne .lL7912507
   .lL9296972:
   mov si, word playerImage
   mov bx, word pRF
   mov [si], bx 
   jmp .lL25567987
   .lL7912507:
   mov ax, word [isMoving]
   mov bx, 0 
   cmp bx, ax
   je .lL23103355
   .lL6206601:
   mov bx, word [inMoving]
   mov ax, word 10
   cmp ax, bx
   jg .lL27182317
   .lL9624795:
   mov si, word playerImage
   mov bx, word pR0
   mov [si], bx 
   jmp .lL25567987
   .lL27182317:
   mov si, word playerImage
   mov bx, word pR1
   mov [si], bx 
   .lL31571602:
   jmp .lL25567987
   .lL23103355:
   mov si, word playerImage
   mov bx, word pR
   mov [si], bx 
   .lL27682895:
   jmp .lL25567987
   .lL23447542:
   mov ax, word [onGround]
   mov bx, 0 
   cmp bx, ax
   jne .lL671035
   .lL3808966:
   mov si, word playerImage
   mov bx, word pLF
   mov [si], bx 
   jmp .lL25567987
   .lL671035:
   mov ax, word [isMoving]
   mov bx, 0 
   cmp bx, ax
   je .lL28991606
   .lL18016247:
   mov bx, word [inMoving]
   mov ax, word 10
   cmp ax, bx
   jg .lL3874616
   .lL25086455:
   mov si, word playerImage
   mov bx, word pL0
   mov [si], bx 
   jmp .lL25567987
   .lL3874616:
   mov si, word playerImage
   mov bx, word pL1
   mov [si], bx 
   .lL16617866:
   jmp .lL25567987
   .lL28991606:
   mov si, word playerImage
   mov bx, word pL
   mov [si], bx 
   .lL25567987:
   call L179514
   .lL22945909:
   mov bx, word [playerY]
   mov ax, word 20
   cmp ax, bx
   jge .lL10822310
   .lL25559096:
   mov si, word playerImage
   mov di, word [si]
   mov ax, word 35
   mov bx, word [playerY]
   call drawImage
   jmp .lL7896086
   .lL10822310:
   mov si, word playerImage
   mov di, word [si]
   mov ax, word 35
   mov bx, word 20
   call drawImage
   .lL7896086:
   call L2272087
   .lL1744155:
   call flip
   .lL21010507:
   call L2272087
   .lL30303902:
   call resetBuffer
   .lL28523022:
   mov bx, word [animTick]
   mov ax, word 1
   add ax, bx
   mov [animTick], ax 
   .lL27817788:
   mov bx, word [animTick]
   mov ax, word 20
   cmp ax, bx
   jge .lL19673895
   .lL13782591:
   mov ax, word 0
   mov [animTick], ax 
   .lL19673895:
   mov si, word 33
   call sleep
   .lL17777129:
   jmp .lL22831804
L21868771:
   .lL21868771:
   mov dx, word 0
   mov [stack_L21868771+0], dx 
   .lL11557581:
   jmp .lL1673653
   .lL10486149:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   cmp ax, bx
   je .lL12829461
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 2
   cmp ax, bx
   jne .lL3972145
   .lL12829461:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   push word [si]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   add ax, bx
   mov cx, ax
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL20089978:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   push word [si]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jge .lL27582163
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   push word [si]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 4
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jg .lL18945918
   .lL27582163:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   neg ax
   mov cx, ax
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL26049230:
   jmp .lL18945918
   .lL3972145:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 3
   cmp ax, bx
   jne .lL18945918
   .lL24745276:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   add ax, bx
   mov cx, ax
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL20237898:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 10
   cmp ax, bx
   jg .lL18945918
   .lL30638546:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L21868771+0]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL18945918:
   add [stack_L21868771+0], word 1
   .lL1673653:
   mov bx, word [stack_L21868771+0]
   mov ax, word 16
   cmp ax, bx
   jg .lL10486149
   .lL15734641:
   ret
L6775863:
   .lL6775863:
   mov ax, word 10
   mov [loading], ax 
   .lL9649099:
   mov ax, word [stack_L6775863+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL3852606
   .lL24257622:
   mov si, word tileWorld1
   mov ax, word 1
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeX], ax 
   .lL11511434:
   mov si, word tileWorld1
   mov ax, word 2
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeY], ax 
   .lL31375837:
   mov dx, word 0
   mov [stack_L6775863+2], dx 
   .lL25804854:
   jmp .lL11150143
   .lL32512553:
   push word tileWorld
   push word [stack_L6775863+2]
   mov si, word tileWorld1
   mov ax, word [stack_L6775863+2]
   mov bx, 2 
   mul bx
   add si, ax
   mov cx, word [si]
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL4558657:
   add [stack_L6775863+2], word 1
   .lL11150143:
   push word [stack_L6775863+2]
   mov bx, word [worldSizeX]
   mov ax, word [worldSizeY]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop bx
   cmp ax, bx
   jg .lL32512553
   .lL12590745:
   jmp .lL18414151
   .lL3852606:
   mov bx, word [stack_L6775863+0]
   mov ax, word 1
   cmp ax, bx
   jne .lL14111765
   .lL13725633:
   mov si, word tileWorld2
   mov ax, word 1
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeX], ax 
   .lL7856:
   mov si, word tileWorld2
   mov ax, word 2
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeY], ax 
   .lL31384576:
   mov dx, word 0
   mov [stack_L6775863+2], dx 
   .lL4899350:
   jmp .lL810652
   .lL1537969:
   push word tileWorld
   push word [stack_L6775863+2]
   mov si, word tileWorld2
   mov ax, word [stack_L6775863+2]
   mov bx, 2 
   mul bx
   add si, ax
   mov cx, word [si]
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL11743647:
   add [stack_L6775863+2], word 1
   .lL810652:
   push word [stack_L6775863+2]
   mov bx, word [worldSizeX]
   mov ax, word [worldSizeY]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop bx
   cmp ax, bx
   jg .lL1537969
   .lL8310136:
   jmp .lL18414151
   .lL14111765:
   mov bx, word [stack_L6775863+0]
   mov ax, word 2
   cmp ax, bx
   jne .lL18414151
   .lL14910620:
   mov si, word tileWorld3
   mov ax, word 1
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeX], ax 
   .lL18306724:
   mov si, word tileWorld3
   mov ax, word 2
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeY], ax 
   .lL20526817:
   mov dx, word 0
   mov [stack_L6775863+2], dx 
   .lL21101238:
   jmp .lL11731442
   .lL4508606:
   push word tileWorld
   push word [stack_L6775863+2]
   mov si, word tileWorld3
   mov ax, word [stack_L6775863+2]
   mov bx, 2 
   mul bx
   add si, ax
   mov cx, word [si]
   pop ax
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL10233621:
   add [stack_L6775863+2], word 1
   .lL11731442:
   push word [stack_L6775863+2]
   mov bx, word [worldSizeX]
   mov ax, word [worldSizeY]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop bx
   cmp ax, bx
   jg .lL4508606
   .lL18414151:
   mov dx, word 0
   mov [stack_L6775863+2], dx 
   .lL30008954:
   jmp .lL3823508
   .lL4973260:
   mov si, word entityData
   mov ax, word [stack_L6775863+2]
   mov cx, word 0
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL1503089:
   add [stack_L6775863+2], word 1
   .lL3823508:
   mov bx, word [stack_L6775863+2]
   mov ax, word 96
   cmp ax, bx
   jg .lL4973260
   .lL29493424:
   call L31983818
   .lL29919449:
   ret
   .lL9472129:
L31983818:
   .lL31983818:
   mov si, word tileWorld
   mov ax, word 1
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeX], ax 
   .lL15855697:
   mov si, word tileWorld
   mov ax, word 2
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [worldSizeY], ax 
   .lL30969271:
   mov dx, word 0
   mov [stack_L31983818+0], dx 
   .lL4205299:
   jmp .lL12839401
   .lL3273383:
   mov dx, word 0
   mov [stack_L31983818+2], dx 
   .lL20039836:
   jmp .lL23690087
   .lL2409635:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+0]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov dx, word [si]
   mov [stack_L31983818+4], dx 
   .lL32098350:
   mov bx, word [stack_L31983818+4]
   mov ax, word 6
   cmp ax, bx
   jne .lL22367538
   .lL20910958:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+0]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL1352077:
   push word 3
   mov bx, word [stack_L31983818+0]
   mov ax, word 8
   imul bx
   push ax
   mov ax, word [stack_L31983818+2]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   mov bx, word 7
   sub ax, bx
   push ax
   push word 0
   mov dx, word 0
   mov [stack_L20092482+8], dx 
   pop dx
   mov [stack_L20092482+6], dx 
   pop dx
   mov [stack_L20092482+4], dx 
   pop dx
   mov [stack_L20092482+2], dx 
   pop dx
   mov [stack_L20092482+0], dx 
   call L20092482
   .lL22367538:
   mov bx, word [stack_L31983818+4]
   mov ax, word 5
   cmp ax, bx
   jne .lL8012937
   .lL19509473:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+0]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL1812813:
   mov dx, word 0
   mov [stack_L31983818+6], dx 
   .lL534353:
   mov bx, word [stack_L31983818+0]
   mov ax, word 1
   add ax, bx
   mov dx, ax
   mov [stack_L31983818+8], dx 
   .lL21846985:
   jmp .lL29683960
   .lL11024915:
   mov ax, word [stack_L31983818+6]
   mov bx, 0 
   cmp bx, ax
   jne .lL8180602
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+8]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 5
   cmp ax, bx
   jne .lL8180602
   .lL18885993:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+8]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL25515362:
   push word 2
   mov bx, word [stack_L31983818+0]
   mov ax, word 8
   imul bx
   push ax
   mov ax, word [stack_L31983818+2]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   push ax
   mov bx, word [stack_L31983818+0]
   mov ax, word 8
   imul bx
   push ax
   mov ax, word [stack_L31983818+8]
   mov bx, word 1
   sub ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   mov dx, ax
   mov [stack_L20092482+8], dx 
   pop dx
   mov [stack_L20092482+6], dx 
   pop dx
   mov [stack_L20092482+4], dx 
   pop dx
   mov [stack_L20092482+2], dx 
   pop dx
   mov [stack_L20092482+0], dx 
   call L20092482
   .lL11050211:
   mov dx, word 1
   mov [stack_L31983818+6], dx 
   .lL8180602:
   add [stack_L31983818+8], word 1
   .lL29683960:
   mov bx, word [stack_L31983818+8]
   mov ax, word [worldSizeX]
   cmp ax, bx
   jg .lL11024915
   .lL8012937:
   mov bx, word [stack_L31983818+4]
   mov ax, word 7
   cmp ax, bx
   jne .lL30685694
   .lL2850225:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+0]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL21559496:
   mov dx, word 0
   mov [stack_L31983818+6], dx 
   .lL29705835:
   mov bx, word [stack_L31983818+0]
   mov ax, word 1
   add ax, bx
   mov dx, ax
   mov [stack_L31983818+8], dx 
   .lL9578500:
   jmp .lL25252664
   .lL25068634:
   mov ax, word [stack_L31983818+6]
   mov bx, 0 
   cmp bx, ax
   jne .lL19097823
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+8]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 7
   cmp ax, bx
   jne .lL19097823
   .lL28970806:
   push word tileWorld
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L31983818+8]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL3975755:
   push word 1
   mov bx, word [stack_L31983818+0]
   mov ax, word 8
   imul bx
   push ax
   mov ax, word [stack_L31983818+2]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   push ax
   mov bx, word [stack_L31983818+0]
   mov ax, word 8
   imul bx
   push ax
   mov bx, word [stack_L31983818+8]
   mov ax, word 8
   imul bx
   mov dx, ax
   mov [stack_L20092482+8], dx 
   pop dx
   mov [stack_L20092482+6], dx 
   pop dx
   mov [stack_L20092482+4], dx 
   pop dx
   mov [stack_L20092482+2], dx 
   pop dx
   mov [stack_L20092482+0], dx 
   call L20092482
   .lL13640204:
   mov dx, word 1
   mov [stack_L31983818+6], dx 
   .lL19097823:
   add [stack_L31983818+8], word 1
   .lL25252664:
   mov bx, word [stack_L31983818+8]
   mov ax, word [worldSizeX]
   cmp ax, bx
   jg .lL25068634
   .lL30685694:
   add [stack_L31983818+2], word 1
   .lL23690087:
   mov bx, word [stack_L31983818+2]
   mov ax, word [worldSizeY]
   cmp ax, bx
   jg .lL2409635
   .lL7031149:
   add [stack_L31983818+0], word 1
   .lL12839401:
   mov bx, word [stack_L31983818+0]
   mov ax, word [worldSizeX]
   cmp ax, bx
   jg .lL3273383
   .lL27366488:
   ret
L179514:
   .lL179514:
   mov ax, word [playerY]
   mov bx, word 20
   sub ax, bx
   mov dx, ax
   mov [stack_L179514+0], dx 
   .lL17975110:
   mov bx, word [playerY]
   mov ax, word 20
   cmp ax, bx
   jge .lL17131806
   .lL3682584:
   mov dx, word 0
   mov [stack_L179514+0], dx 
   .lL17131806:
   mov bx, word [stack_L179514+0]
   mov ax, word 4
   cmp ax, bx
   jle .lL18818021
   .lL23971937:
   mov dx, word 0
   mov [stack_L179514+2], dx 
   .lL8293453:
   jmp .lL2955420
   .lL11200622:
   push word ground
   mov bx, word [stack_L179514+2]
   mov ax, word 4
   imul bx
   push ax
   mov ax, word 48
   mov bx, word [stack_L179514+0]
   sub ax, bx
   mov bx, ax
   pop ax
   pop di
   call drawImage
   .lL8495511:
   add [stack_L179514+2], word 1
   .lL2955420:
   mov bx, word [stack_L179514+2]
   mov ax, word 20
   cmp ax, bx
   jg .lL11200622
   .lL18818021:
   mov dx, word 0
   mov [stack_L179514+2], dx 
   .lL9949222:
   jmp .lL14850080
   .lL7433399:
   mov dx, word 0
   mov [stack_L179514+4], dx 
   .lL9992755:
   jmp .lL8304354
   .lL18403721:
   push word tileWorld
   mov bx, word [stack_L179514+4]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L179514+2]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, 0 
   cmp bx, ax
   je .lL6586390
   .lL1397168:
   push word tileWorld
   mov bx, word [stack_L179514+4]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L179514+2]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov dx, word [si]
   mov [stack_L179514+6], dx 
   .lL27660658:
   mov bx, word [stack_L179514+6]
   mov ax, word 1
   cmp ax, bx
   jne .lL29594051
   .lL9870377:
   mov si, word tile
   mov bx, word tile_0
   mov [si], bx 
   jmp .lL31882858
   .lL29594051:
   mov bx, word [stack_L179514+6]
   mov ax, word 2
   cmp ax, bx
   jne .lL24478976
   .lL7263010:
   mov bx, word [animTick]
   mov ax, word 15
   cmp ax, bx
   jg .lL31952022
   .lL12518719:
   mov si, word tile
   mov bx, word block_0
   mov [si], bx 
   jmp .lL31882858
   .lL31952022:
   mov bx, word [animTick]
   mov ax, word 10
   cmp ax, bx
   jg .lL15831407
   .lL25374911:
   mov si, word tile
   mov bx, word block_1
   mov [si], bx 
   jmp .lL31882858
   .lL15831407:
   mov bx, word [animTick]
   mov ax, word 5
   cmp ax, bx
   jg .lL32853087
   .lL23664622:
   mov si, word tile
   mov bx, word block_2
   mov [si], bx 
   jmp .lL31882858
   .lL32853087:
   mov si, word tile
   mov bx, word block_3
   mov [si], bx 
   .lL10630672:
   jmp .lL31882858
   .lL24478976:
   mov bx, word [stack_L179514+6]
   mov ax, word 3
   cmp ax, bx
   jne .lL25921812
   .lL30708295:
   mov si, word tile
   mov bx, word tile_2
   mov [si], bx 
   jmp .lL31882858
   .lL25921812:
   mov bx, word [stack_L179514+6]
   mov ax, word 4
   cmp ax, bx
   jne .lL13599389
   .lL25709120:
   mov si, word tile
   mov bx, word tile_3
   mov [si], bx 
   jmp .lL31882858
   .lL13599389:
   mov bx, word [stack_L179514+6]
   mov ax, word 5
   cmp ax, bx
   jne .lL31882858
   .lL12774933:
   mov si, word tile
   mov bx, word tile_4
   mov [si], bx 
   .lL31882858:
   mov si, word tile
   push word [si]
   mov bx, word [stack_L179514+2]
   mov ax, word 8
   imul bx
   push ax
   mov ax, word [playerX]
   mov bx, word 35
   sub ax, bx
   mov bx, ax
   pop ax
   sub ax, bx
   push ax
   mov ax, word [stack_L179514+4]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   mov bx, word [stack_L179514+0]
   sub ax, bx
   mov bx, ax
   pop ax
   pop di
   call drawImage
   .lL6586390:
   add [stack_L179514+4], word 1
   .lL8304354:
   mov bx, word [stack_L179514+4]
   mov ax, word [worldSizeY]
   cmp ax, bx
   jg .lL18403721
   .lL27055962:
   add [stack_L179514+2], word 1
   .lL14850080:
   mov bx, word [stack_L179514+2]
   mov ax, word [worldSizeX]
   cmp ax, bx
   jg .lL7433399
   .lL33459432:
   mov dx, word 0
   mov [stack_L179514+2], dx 
   .lL13948523:
   jmp .lL21565531
   .lL30463067:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   cmp ax, bx
   jne .lL19255406
   .lL26670261:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   cmp ax, bx
   jne .lL26977856
   .lL29345020:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, word 5
   mov dx, 0 
   div bx
   mov bx, word 2
   mov dx, 0 
   div bx
   mov ax, dx
   mov bx, 0 
   cmp bx, ax
   jne .lL18724539
   .lL29876954:
   mov si, word tile
   mov bx, word eR0
   mov [si], bx 
   jmp .lL33445663
   .lL18724539:
   mov si, word tile
   mov bx, word eR1
   mov [si], bx 
   .lL17427094:
   jmp .lL33445663
   .lL26977856:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, word 5
   mov dx, 0 
   div bx
   mov bx, word 2
   mov dx, 0 
   div bx
   mov ax, dx
   mov bx, 0 
   cmp bx, ax
   jne .lL539419
   .lL6326112:
   mov si, word tile
   mov bx, word eL0
   mov [si], bx 
   jmp .lL33445663
   .lL539419:
   mov si, word tile
   mov bx, word eL1
   mov [si], bx 
   .lL22538826:
   jmp .lL33445663
   .lL19255406:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 2
   cmp ax, bx
   jne .lL14069849
   .lL13878877:
   mov ax, word [animTick]
   mov bx, word 4
   mov dx, 0 
   div bx
   mov bx, dx
   mov ax, word 2
   cmp ax, bx
   jg .lL25442933
   .lL1708953:
   mov si, word tile
   mov bx, word base_0
   mov [si], bx 
   jmp .lL33445663
   .lL25442933:
   mov si, word tile
   mov bx, word base_1
   mov [si], bx 
   .lL33341602:
   jmp .lL33445663
   .lL14069849:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 3
   cmp ax, bx
   jne .lL33445663
   .lL13301441:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 5
   cmp ax, bx
   jg .lL17708501
   .lL32519825:
   mov si, word tile
   mov bx, word coin_0
   mov [si], bx 
   jmp .lL33445663
   .lL17708501:
   mov si, word tile
   mov bx, word coin_1
   mov [si], bx 
   .lL33445663:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, 0 
   cmp bx, ax
   je .lL26117441
   .lL30311876:
   mov si, word tile
   push word [si]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   push word [si]
   mov ax, word [playerX]
   mov bx, word 35
   sub ax, bx
   mov bx, ax
   pop ax
   sub ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L179514+2]
   imul bx
   mov bx, ax
   mov ax, word 2
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, word [stack_L179514+0]
   sub ax, bx
   mov bx, ax
   pop ax
   pop di
   call drawImage
   .lL26117441:
   add [stack_L179514+2], word 1
   .lL21565531:
   mov bx, word [stack_L179514+2]
   mov ax, word 16
   cmp ax, bx
   jg .lL30463067
   .lL28326938:
   ret
   .lL20570961:
L20092482:
   .lL20092482:
   mov dx, word 0
   mov [stack_L20092482+10], dx 
   .lL24833256:
   jmp .lL22514347
   .lL5313146:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, 0 
   cmp bx, ax
   je .lL9708927
   jmp .lL2737550
   .lL9708927:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   mov cx, word [stack_L20092482+0]
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL6888942:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   mov cx, word [stack_L20092482+2]
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL19658898:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 2
   add ax, bx
   mov cx, word [stack_L20092482+4]
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL30167145:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word [stack_L20092482+6]
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL11742932:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 4
   add ax, bx
   mov cx, word [stack_L20092482+8]
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL29857804:
   push word entityData
   mov bx, word [stack_L20092482+10]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   mov cx, word 1
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL13594894:
   ret
   .lL2737550:
   add [stack_L20092482+10], word 1
   .lL22514347:
   mov bx, word [stack_L20092482+10]
   mov ax, word 16
   cmp ax, bx
   jg .lL5313146
   .lL17268681:
   ret
   .lL22450101:
L28154095:
   .lL28154095:
   push word entityData
   mov bx, word [stack_L28154095+0]
   mov ax, word 6
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   mov cx, word 0
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL32816375:
   ret
   .lL10625551:
L23459640:
   .lL23459640:
   mov dx, word 0
   mov [stack_L23459640+4], dx 
   .lL6961504:
   mov dx, word 0
   mov [stack_L23459640+6], dx 
   .lL31248093:
   jmp .lL26285376
   .lL672904:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   cmp ax, bx
   jne .lL1668655
   .lL27140443:
   mov bx, word [stack_L23459640+0]
   mov ax, word 9
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jg .lL10481832
   push word [stack_L23459640+0]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 7
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL10481832
   mov bx, word [playerY]
   mov ax, word 16
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 2
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jne .lL10481832
   mov ax, word [inJump]
   mov bx, 0 
   cmp bx, ax
   jl .lL10481832
   .lL7388808:
   mov ax, word 1
   mov [jump], ax 
   .lL32308743:
   mov ax, word 15
   mov [inJump], ax 
   .lL1677625:
   mov dx, word 1
   mov [stack_L23459640+4], dx 
   .lL10132325:
   jmp .lL10481832
   .lL1668655:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 2
   cmp ax, bx
   jne .lL5253145
   .lL7907968:
   mov bx, word [stack_L23459640+0]
   mov ax, word 9
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jg .lL10481832
   push word [stack_L23459640+0]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 15
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL10481832
   mov bx, word [playerY]
   mov ax, word 16
   add ax, bx
   push ax
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 2
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   cmp ax, bx
   jne .lL10481832
   mov ax, word [inJump]
   mov bx, 0 
   cmp bx, ax
   jl .lL10481832
   .lL121226:
   mov ax, word [velX]
   mov bx, 0 
   cmp bx, ax
   jne .lL24192593
   .lL25865024:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov [velX], ax 
   jmp .lL15794899
   .lL24192593:
   push word [velX]
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   pop bx
   add ax, bx
   mov bx, 0 
   cmp bx, ax
   je .lL15794899
   .lL15758165:
   mov ax, word [velXP]
   mov bx, 0 
   cmp bx, ax
   jge .lL2355643
   .lL30620267:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 1
   add ax, bx
   mov [velX], ax 
   jmp .lL15794899
   .lL2355643:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 5
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, word 1
   sub ax, bx
   mov [velX], ax 
   .lL15794899:
   mov dx, word 1
   mov [stack_L23459640+4], dx 
   .lL10520143:
   jmp .lL10481832
   .lL5253145:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 0
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 3
   cmp ax, bx
   jne .lL10481832
   .lL13754931:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 1
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov dx, word [si]
   mov [stack_L23459640+8], dx 
   .lL22643639:
   push word entityData
   mov bx, word 6
   mov ax, word [stack_L23459640+6]
   imul bx
   mov bx, ax
   mov ax, word 2
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov dx, word [si]
   mov [stack_L23459640+10], dx 
   .lL30831739:
   mov bx, word [stack_L23459640+0]
   mov ax, word 9
   add ax, bx
   mov bx, ax
   mov ax, word [stack_L23459640+8]
   cmp ax, bx
   jg .lL10481832
   push word [stack_L23459640+0]
   mov bx, word [stack_L23459640+8]
   mov ax, word 7
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL10481832
   mov bx, word [stack_L23459640+2]
   mov ax, word 15
   add ax, bx
   push ax
   mov bx, word [stack_L23459640+10]
   mov ax, word 6
   add ax, bx
   pop bx
   cmp ax, bx
   jg .lL10481832
   push word [stack_L23459640+2]
   mov bx, word [stack_L23459640+10]
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 7
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL10481832
   .lL7960257:
   mov dx, word [stack_L23459640+6]
   mov [stack_L28154095+0], dx 
   call L28154095
   .lL6526955:
   mov bx, word [coins]
   mov ax, word 1
   add ax, bx
   mov [coins], ax 
   .lL10481832:
   add [stack_L23459640+6], word 1
   .lL26285376:
   mov bx, word [stack_L23459640+6]
   mov ax, word 16
   cmp ax, bx
   jg .lL672904
   .lL9104244:
   mov dx, word [stack_L23459640+4]
   mov [stack_L23459640+0], dx 
   ret
   .lL6829563:
L23738549:
   .lL23738549:
   mov dx, word 0
   mov [stack_L23738549+4], dx 
   .lL28318458:
   jmp .lL12273995
   .lL29945809:
   mov dx, word 0
   mov [stack_L23738549+6], dx 
   .lL16290468:
   jmp .lL23293518
   .lL14615608:
   push word tileWorld
   mov bx, word [stack_L23738549+6]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L23738549+4]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov ax, word [si]
   mov bx, 0 
   cmp bx, ax
   jne .lL26093085
   jmp .lL23611142
   .lL26093085:
   push word tileWorld
   mov bx, word [stack_L23738549+6]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L23738549+4]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov bx, word [si]
   mov ax, word 5
   cmp ax, bx
   jne .lL17759612
   jmp .lL23611142
   .lL17759612:
   mov bx, word [stack_L23738549+0]
   mov ax, word 9
   add ax, bx
   push ax
   mov bx, word [stack_L23738549+4]
   mov ax, word 8
   imul bx
   pop bx
   cmp ax, bx
   jg .lL23611142
   push word [stack_L23738549+0]
   mov bx, word [stack_L23738549+4]
   mov ax, word 8
   imul bx
   mov bx, ax
   mov ax, word 7
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL23611142
   mov bx, word [stack_L23738549+2]
   mov ax, word 15
   add ax, bx
   push ax
   mov ax, word [stack_L23738549+6]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   pop bx
   cmp ax, bx
   jg .lL23611142
   push word [stack_L23738549+2]
   mov ax, word [stack_L23738549+6]
   mov bx, word [worldSizeY]
   sub ax, bx
   mov bx, ax
   mov ax, word 6
   add ax, bx
   mov bx, ax
   mov ax, word 8
   imul bx
   mov bx, ax
   mov ax, word 7
   add ax, bx
   pop bx
   cmp ax, bx
   jl .lL23611142
   .lL19141351:
   push word tileWorld
   mov bx, word [stack_L23738549+6]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L23738549+4]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov dx, word [si]
   mov [stack_L23738549+8], dx 
   .lL22358902:
   mov bx, word [stack_L23738549+8]
   mov ax, word 4
   cmp ax, bx
   jne .lL9996039
   .lL29945686:
   push word tileWorld
   mov bx, word [stack_L23738549+6]
   mov ax, word [worldSizeX]
   imul bx
   mov bx, ax
   mov ax, word [stack_L23738549+4]
   add ax, bx
   mov bx, ax
   mov ax, word 3
   add ax, bx
   mov cx, word 5
   pop si
   mov bx, 2 
   mul bx
   add si, ax
   mov [si], cx 
   .lL9996039:
   mov dx, word 1
   mov [stack_L23738549+0], dx 
   ret
   .lL23611142:
   add [stack_L23738549+6], word 1
   .lL23293518:
   mov bx, word [stack_L23738549+6]
   mov ax, word [worldSizeY]
   cmp ax, bx
   jg .lL14615608
   .lL14222419:
   add [stack_L23738549+4], word 1
   .lL12273995:
   mov bx, word [stack_L23738549+4]
   mov ax, word [worldSizeX]
   cmp ax, bx
   jg .lL29945809
   .lL27988400:
   mov dx, word 0
   mov [stack_L23738549+0], dx 
   ret
   .lL1465214:
L5612344:
   .lL5612344:
   mov bx, word [playerY]
   mov ax, word [velY]
   add ax, bx
   mov dx, ax
   mov [stack_L5612344+0], dx 
   .lL30462244:
   push word [playerX]
   mov dx, word [stack_L5612344+0]
   mov [stack_L23459640+2], dx 
   pop dx
   mov [stack_L23459640+0], dx 
   call L23459640
   mov ax, word [stack_L23459640+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL5424820
   mov bx, word [stack_L5612344+0]
   mov ax, word 15
   add ax, bx
   mov bx, ax
   mov ax, word 48
   cmp ax, bx
   jle .lL5424820
   push word [playerX]
   mov dx, word [stack_L5612344+0]
   mov [stack_L23738549+2], dx 
   pop dx
   mov [stack_L23738549+0], dx 
   call L23738549
   mov ax, word [stack_L23738549+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL5424820
   .lL7572744:
   mov ax, word [stack_L5612344+0]
   mov [playerY], ax 
   .lL3157607:
   mov ax, word 0
   mov [onGround], ax 
   jmp .lL20357537
   .lL5424820:
   mov ax, word [velY]
   mov bx, 0 
   cmp bx, ax
   jge .lL20357537
   .lL28142411:
   mov ax, word 1
   mov [onGround], ax 
   .lL20357537:
   mov bx, word [playerX]
   mov ax, word [velX]
   add ax, bx
   mov dx, ax
   mov [stack_L5612344+2], dx 
   .lL6237616:
   mov ax, word [stack_L5612344+2]
   mov bx, 0 
   cmp bx, ax
   jg .lL11576309
   push word [stack_L5612344+2]
   mov dx, word [playerY]
   mov [stack_L23738549+2], dx 
   pop dx
   mov [stack_L23738549+0], dx 
   call L23738549
   mov ax, word [stack_L23738549+0]
   mov bx, 0 
   cmp bx, ax
   jne .lL11576309
   .lL14434757:
   mov ax, word [stack_L5612344+2]
   mov [playerX], ax 
   .lL6427893:
   mov bx, word [inMoving]
   mov ax, word 1
   add ax, bx
   mov [inMoving], ax 
   .lL11576309:
   mov bx, word [inMoving]
   mov ax, word 20
   cmp ax, bx
   jg .lL21893435
   .lL5076660:
   mov ax, word 0
   mov [inMoving], ax 
   .lL21893435:
   ret
   .lL28073747:
L2272087:
   .lL2272087:
   ret
keyHandle:
   .lL26598747:
   mov ax, word 0
   mov [freeze], ax 
   .lL33000296:
   mov bx, word [keyPressed]
   mov ax, word 30
   cmp ax, bx
   jne .lL14779369
   .lL26953436:
   mov ax, word [keyState]
   mov bx, 0 
   cmp bx, ax
   je .lL21573890
   .lL3190337:
   mov ax, word -1
   mov [velXP], ax 
   .lL16900:
   mov ax, word 1
   mov [isMoving], ax 
   jmp .lL15605470
   .lL21573890:
   mov ax, word 0
   mov [velXP], ax 
   .lL19697576:
   mov ax, word 0
   mov [isMoving], ax 
   .lL9144903:
   jmp .lL15605470
   .lL14779369:
   mov bx, word [keyPressed]
   mov ax, word 32
   cmp ax, bx
   jne .lL19098837
   .lL12470752:
   mov ax, word [keyState]
   mov bx, 0 
   cmp bx, ax
   je .lL14949315
   .lL31116492:
   mov ax, word 1
   mov [velXP], ax 
   .lL29071960:
   mov ax, word 1
   mov [isMoving], ax 
   jmp .lL15605470
   .lL14949315:
   mov ax, word 0
   mov [velXP], ax 
   .lL26357574:
   mov ax, word 0
   mov [isMoving], ax 
   .lL6146452:
   jmp .lL15605470
   .lL19098837:
   mov bx, word [keyPressed]
   mov ax, word 57
   cmp ax, bx
   jne .lL22885256
   .lL31614731:
   mov ax, word [keyState]
   mov bx, 0 
   cmp bx, ax
   je .lL15605470
   mov ax, word [onGround]
   mov bx, 0 
   cmp bx, ax
   je .lL15605470
   .lL14361585:
   mov ax, word 1
   mov [jump], ax 
   .lL18135083:
   mov ax, word 0
   mov [onGround], ax 
   .lL21443983:
   mov ax, word 15
   mov [inJump], ax 
   .lL876215:
   jmp .lL15605470
   .lL22885256:
   mov bx, word [keyPressed]
   mov ax, word 1
   cmp ax, bx
   jne .lL15605470
   .lL29706134:
   mov ax, word 1
   mov [freeze], ax 
   .lL15605470:
   ret
jmp $
VARIABLES:
velY dw 0
yOffset dw 0
onGround dw 0
eR1 incbin 'minild/eR1.bimg'
eR0 incbin 'minild/eR0.bimg'
velX dw 0
pRF incbin 'minild/pR3.bimg'
playerImage dw 0
worldSizeX dw 0
animTick dw 0
lastVel dw 0
worldSizeY dw 0
loadingImg incbin 'minild/loading.bimg'
stack_L23459640 dw 0, 0, 0, 0, 0, 0
pL incbin 'minild/pL0.bimg'
tile_0 incbin 'minild/block_e.bimg'
stack_L179514 dw 0, 0, 0, 0
pR0 incbin 'minild/pR1.bimg'
tile_2 incbin 'minild/tile2.bimg'
pR1 incbin 'minild/pR2.bimg'
screen incbin 'minild/screen.bimg'
tile_3 incbin 'minild/tile3.bimg'
tile_4 incbin 'minild/tile4.bimg'
tileWorld3 incbin 'minild/map3.bmap'
loading dw 0
stack_L5612344 dw 0, 0
keyPressed dw 0
pR incbin 'minild/pR0.bimg'
loading_1 incbin 'minild/loading_1.bimg'
loading_0 incbin 'minild/loading_0.bimg'
stack_L23738549 dw 0, 0, 0, 0, 0
stack_L21868771 dw 0
;set tileWorld1 to pusty.f0xC.util.Null@1fc9f88
;set tileWorld2 to pusty.f0xC.util.Null@4dd014
jump dw 0
;set tileWorld3 to pusty.f0xC.util.Null@97f621
xOffset dw 0
coins dw 0
block_3 incbin 'minild/block_3.bimg'
block_2 incbin 'minild/block_2.bimg'
stack_L31983818 dw 0, 0, 0, 0, 0
stack_L20092482 dw 0, 0, 0, 0, 0, 0
inJump dw 0
;set tileWorld to pusty.f0xC.util.Null@7ed081
inMoving dw 0
entityAmount dw 0
pL1 incbin 'minild/pL2.bimg'
ground incbin 'minild/ground.bimg'
pL0 incbin 'minild/pL1.bimg'
b2 dw ' Chipsets Level: ', 0
playerX dw 0
coin_0 incbin 'minild/coin0.bimg'
eL0 incbin 'minild/eL0.bimg'
coin_1 incbin 'minild/coin1.bimg'
eL1 incbin 'minild/eL1.bimg'
entityLength dw 0
stack_L6775863 dw 0, 0
tileWorldOffset dw 0
b1 dw ' ', 0
stack_L28154095 dw 0
stack_L8702985 dw 0
tileWorld2 incbin 'minild/map2.bmap'
block_1 incbin 'minild/block_1.bimg'
block_0 incbin 'minild/block_0.bimg'
tileWorld incbin 'minild/map.bmap'
keyState dw 0
playerY dw 20
entityData dw 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
b dw '/', 0
isMoving dw 0
pal incbin 'minild/tmp.bpal'
velXP dw 0
base_0 incbin 'minild/base_0.bimg'
base_1 incbin 'minild/base_1.bimg'
pLF incbin 'minild/pL3.bimg'
freeze dw 0
tile dw tile_0
tileWorld1 incbin 'minild/map.bmap'
mapIndex dw 0
resw 0
INCLUDES:
%include 'initGraphics.asm'
%include 'loadPal.asm'
%include 'buffer.asm'
%include 'sleep.asm'
%include 'checkKey.asm'
times 512*40- ($-$$) db 0
