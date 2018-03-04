package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Switches sign from Parameter 1 and puts it into Parameter 1
 * @see Inst
 */
class Inst093NEG extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.NEG;
	}
	@Override
	public String getName() {
		return "NEG";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.write(m1, i1, -map.read(m1, i1, size), size);
		return line;
	}
}