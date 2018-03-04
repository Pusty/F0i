package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Ands Logical and Parameter 1 and Parameter 2 and updates Zero Flag (resets other flags)
 * @see Inst
 */
class Inst007AND extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.AND;
	}
	@Override
	public String getName() {
		return "AND";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int value = (map.readU(m1, i1,size))&(map.readU(m2, i2,size));
		map.write(m1, i1, value, size);
		map.writeFlag(Mem.FLAG_ZF, value==0?1:0);
		map.writeFlag(Mem.FLAG_CF, 0);
		map.writeFlag(Mem.FLAG_OF, 0);
		map.writeFlag(Mem.FLAG_SF, 0);
		return line;
	}
}