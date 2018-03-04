package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Does nothing
 * @see Inst
 */
class Inst094NOP extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.NOP;
	}
	@Override
	public String getName() {
		return "NOP";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		return line;
	}
}