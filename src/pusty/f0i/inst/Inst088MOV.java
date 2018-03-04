package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Copies Parameter 2 to Parameter 1
 * @see Inst
 */
class Inst088MOV extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.MOV;
	}
	@Override
	public String getName() {
		return "MOV";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.write(m1, i1, map.readU(m2, i2, size), size);
		return line;
	}
}