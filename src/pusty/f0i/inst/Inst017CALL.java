package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Jumps to Parameter 1 and adds the current line to the stack
 * @see Inst
 */
class Inst017CALL extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.CALL;
	}
	@Override
	public String getName() {
		return "CALL";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.addStack(line);
		return i1-1;
	}
}