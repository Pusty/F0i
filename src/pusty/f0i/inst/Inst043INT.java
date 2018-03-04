package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.Bios;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Calls Interrupt Parameter 1 at the {@link Bios#emulateBios}
 * @see Inst
 */
class Inst043INT extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.INT;
	}
	@Override
	public String getName() {
		return "INT";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		//BIOS EMULATION
		if(m1 == Mem.TYPE_INT)
			Bios.emulateBios(map, i1);
		else
			System.err.println("Using memory mode "+m1+" to access BIOS doesn't make sense");
		return line;
	}
}