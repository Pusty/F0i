package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Load content from esi to al and increase esi by 1 (byte)
 * @see Inst
 */
class Inst081LODS extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.LODS;
	}
	@Override
	public String getName() {
		return "LODS";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(size==Mem.SIZE_BYTE) {
			map.writeReg(Mem.ADDR_AL, map.readMem(map.readReg(Mem.ADDR_ESI),size));
			map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+1);
		}else if(size==Mem.SIZE_WORD) {
			map.writeReg(Mem.ADDR_AX, map.readMem(map.readReg(Mem.ADDR_ESI),size));
			map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+2);
		}else {
			map.writeReg(Mem.ADDR_EAX, map.readMem(map.readReg(Mem.ADDR_ESI),size));
			map.writeReg(Mem.ADDR_ESI, map.readReg(Mem.ADDR_ESI)+4);
		}
		
		return line;
	}
}