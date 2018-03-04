package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Adds Parameter 1 and Parameter 2 and puts it into Parameter 1 (also updates flags)
 * @see Inst
 */
class Inst005ADD extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.ADD;
	}
	@Override
	public String getName() {
		return "ADD";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int n =  map.add(m1, i1, m2, i2, size);
		map.write(m1, i1, n, size);
		map.updateFlags(n, m1, i1, size);
		return line;
	}
}