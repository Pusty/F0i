package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Pushes Argument 1 to the stack
 * @see Inst
 * @see Inst099POP
 */
class Inst102PUSH extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.PUSH;
	}
	@Override
	public String getName() {
		return "PUSH";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.addStack(map.read(m1, i1, size));
		return line;
	}
}