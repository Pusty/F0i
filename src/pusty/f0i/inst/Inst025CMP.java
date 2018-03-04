package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Subtracts Parameter 1 and Parameter 2 and updates flags
 * @see Inst
 */
class Inst025CMP extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.CMP;
	}
	@Override
	public String getName() {
		return "CMP";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int n =  map.sub(m1, i1, m2, i2, size);
		map.updateFlags(n, m1, i1, size);
		return line;
	}
}