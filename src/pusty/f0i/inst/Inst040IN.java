package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.Bios;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Inputs {@link Bios#readIn} for Parameter 2 and then puts it into Parameter 1
 * @see Inst
 */
class Inst040IN extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.IN;
	}
	@Override
	public String getName() {
		return "IN";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.write(m1, i1, Bios.readIn(map.read(m2, i2, size)), size);
		return line;
	}
}