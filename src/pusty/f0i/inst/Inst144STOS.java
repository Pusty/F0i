package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Copies the content from AL to EDI and increases EDI by 1 (byte)
 * @see Inst
 */
class Inst144STOS extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.STOS;
	}
	@Override
	public String getName() {
		return "STOS";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(size==Mem.SIZE_BYTE){
			map.writeMem(map.readReg(Mem.ADDR_EDI), map.readReg(Mem.ADDR_AL), size);
			map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+1);
		}else if(size==Mem.SIZE_WORD) {
			map.writeMem(map.readReg(Mem.ADDR_EDI), map.readReg(Mem.ADDR_AX), size);
			map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+2);
		}else {
			map.writeMem(map.readReg(Mem.ADDR_EDI), map.readReg(Mem.ADDR_EAX), size);
			map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+4);
		}

		return line;
	}
}
