package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Copy content from esi to edi and increase both
 * @see Inst
 */
class Inst089MOVS extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.MOVS;
	}
	@Override
	public String getName() {
		return "MOVS";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.writeMem(map.readReg(Mem.ADDR_EDI),map.readMem(map.readReg(Mem.ADDR_ESI), size), size);
		if(size==Mem.SIZE_BYTE) {
		map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+1);
		map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+1);
		}else if(size==Mem.SIZE_WORD) {
			map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+2);
			map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+2);
		}else if(size==Mem.SIZE_DWORD) {
			map.writeReg(Mem.ADDR_EDI, map.readReg(Mem.ADDR_EDI)+4);
			map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+4);
		}
		return line;
	}
}
