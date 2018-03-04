package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Increases Parameter 1 by 1 and puts it into Parameter 1
 * @see Inst
 */
class Inst041INC extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.INC;
	}
	@Override
	public String getName() {
		return "INC";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int n =  map.add(m1, i1, Mem.TYPE_INT,1, size);
		map.write(m1, i1, n, size);
		map.updateFlags(n, m1, i1, size);
		return line;
	}
}