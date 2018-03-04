package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Converts AL to AX
 * @see Inst
 */
class Inst018CBW extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.CBW;
	}
	@Override
	public String getName() {
		return "CBW";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {	
		map.writeReg(Mem.ADDR_AH, 0);
		// al + ah => ax (ah = 0 means that ax = al)
		return line;
	}
}