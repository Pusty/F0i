package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Shifts Parameter 1 Parameter 2 bits to the right (and Carry to the last outshifted bit)
 * @see Inst
 */
class Inst137SHR extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.SHR;
	}
	@Override
	public String getName() {
		return "SHR";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int n =  map.readU(m1, i1, size);
		n = n>>(map.readU(m2, i2, size)-1);
		map.writeFlag(Mem.FLAG_CF, (n&0x1));
		n = n>>1;
		map.write(m1, i1, n, size);
		return line;
	}
}