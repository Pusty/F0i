package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Subtracts EDI and ESI and updates flags and then increases EDI and ESI
 * @see Inst
 */
class Inst026CMPS extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.CMPS;
	}
	@Override
	public String getName() {
		return "CMPS";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int n =  map.sub(Mem.TYPE_MEM,  map.readReg(Mem.ADDR_EDI), Mem.TYPE_MEM,  map.readReg(Mem.ADDR_ESI), size);
		map.updateFlags(n, Mem.TYPE_MEM, map.readReg(Mem.ADDR_EDI), size);		
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