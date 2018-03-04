package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Copies the address of Parameter 2 to Parameter 1
 * @see Inst
 */
class Inst071LEA extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.LEA;
	}
	@Override
	public String getName() {
		return "LEA";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.write(m1, i1, i2, size);
		return line;
	}
}