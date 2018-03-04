package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Logical ANDs Parameter 1 and Parameter 2 and updates flags
 * @see Inst
 */
class Inst147TEST extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.TEST;
	}
	@Override
	public String getName() {
		return "TEST";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int a = map.readU(m1, i1, size);
		int b = map.readU(m2, i2, size);
		int t = a & b;
		map.writeFlag(Mem.FLAG_SF, t<0?1:0);
		map.writeFlag(Mem.FLAG_ZF, t==0?1:0);
		map.writeFlag(Mem.FLAG_CF, 0);
		map.writeFlag(Mem.FLAG_OF, 0);
		return line;
	}
}