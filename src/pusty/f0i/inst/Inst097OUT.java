package pusty.f0i.inst;

import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.Bios;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Writes Parameter 2 at {@link Bios#writeOut} with Parameter 1
 * @see Inst
 */
class Inst097OUT extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.OUT;
	}
	@Override
	public String getName() {
		return "OUT";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		Bios.writeOut(map.read(m1, i1, size), map.read(m2, i2, size));
		return line;
	}
}