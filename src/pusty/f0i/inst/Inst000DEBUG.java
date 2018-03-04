package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Prints out content from parameter 1
 * @see Inst
 */
class Inst000DEBUG extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.DEBUG;
	}
	@Override
	public String getName() {
		return "DEBUG";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		System.out.println("DEBUG: "+map.read(m1, i1, size));
		return line;
	}
}