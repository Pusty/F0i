package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Jump to Parameter 1 if Overflow isn't set
 * @see Inst
 */
class Inst062JNO extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.JNO;
	}
	@Override
	public String getName() {
		return "JNO";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m1 == Mem.TYPE_NODE) {
			if(map.readFlag(Mem.FLAG_OF)==0)
				return i1-1;
			else
				return line;
		}
		System.err.println("Something went wrong with node "+getName()+"in line: "+line);
			return -1;
	}
}