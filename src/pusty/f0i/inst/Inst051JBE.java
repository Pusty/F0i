package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Jump to Parameter 1 if above or equals
 * @see Inst
 */
class Inst051JBE extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.JBE;
	}
	@Override
	public String getName() {
		return "JBE";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m1 == Mem.TYPE_NODE) {
			if(map.readFlag(Mem.FLAG_CF)==1 || map.readFlag(Mem.FLAG_ZF)==1)
				return i1-1;
			else
				return line;
		}
		System.err.println("Something went wrong with node "+getName()+"in line: "+line);
			return -1;
	}
}