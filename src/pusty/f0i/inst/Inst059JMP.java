package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Jump to Parameter 1 if Parameter 1 is a NODE type. Halts if not (jmp $)
 * @see Inst
 */
class Inst059JMP extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.JMP;
	}
	@Override
	public String getName() {
		return "JMP";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m1 == Mem.TYPE_NODE) {
			return i1-1;
		}else
			return -1;
	}
}