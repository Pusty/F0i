package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Removes 1 Argument from stack and puts it into Argument 1
 * @see Inst
 * @see Inst102PUSH
 */
class Inst099POP extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.POP;
	}
	@Override
	public String getName() {
		return "POP";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.write(m1, i1, map.readStack(), size);
		return line;
	}
}