package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Jump to Parameter 1 if less equals
 * @see Inst
 */
class Inst058JLE extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.JLE;
	}
	@Override
	public String getName() {
		return "JLE";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m1 == Mem.TYPE_NODE) {
			if(map.readFlag(Mem.FLAG_SF)!=map.readFlag(Mem.FLAG_OF) || map.readFlag(Mem.FLAG_ZF)==1)
				return i1-1;
			else
				return line;
		}
		System.err.println("Something went wrong with node "+getName()+"in line: "+line);
			return -1;
	}
}