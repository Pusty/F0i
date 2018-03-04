package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Pops one byte from stack and jumps to it
 * @see Inst
 */
class Inst110RET extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.RET;
	}
	@Override
	public String getName() {
		return "RET";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		return map.readStack();
	}
}