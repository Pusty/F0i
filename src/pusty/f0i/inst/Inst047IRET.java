package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Pops one byte from stack and jumps to it
 * @see Inst110RET
 */
class Inst047IRET extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.IRET;
	}
	@Override
	public String getName() {
		return "IRET";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		return map.readStack();
	}
}