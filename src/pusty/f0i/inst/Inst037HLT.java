package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Halts the system
 * @see Inst
 */
class Inst037HLT extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.HLT;
	}
	@Override
	public String getName() {
		return "HLT";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		return -1;
	}
}