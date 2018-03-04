package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Decreases cx by one and if it's zero do nothing and if not jump to Parameter 1
 * @see Inst
 */
class Inst082LOOP extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.LOOP;
	}
	@Override
	public String getName() {
		return "LOOP";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m1 == Mem.TYPE_NODE) {
			if(map.readReg(Mem.ADDR_CX)>0)
				map.writeReg(Mem.ADDR_CX, map.readReg(Mem.ADDR_CX)-1);
			if(map.readReg(Mem.ADDR_CX)==0)
				return line;
			return i1-1;
		}
		System.err.println("Something went wrong with node "+getName()+"in line: "+line);
		return -1;
	}
}