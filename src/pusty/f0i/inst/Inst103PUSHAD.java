package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Pushes all dword registers to the stack
 * @see Inst
 * @see Inst100POPAD
 */
class Inst103PUSHAD extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.PUSHAD;
	}
	@Override
	public String getName() {
		return "PUSHAD";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.addStack(map.readReg(Mem.ADDR_EAX));
		map.addStack(map.readReg(Mem.ADDR_EBX));
		map.addStack(map.readReg(Mem.ADDR_ECX));
		map.addStack(map.readReg(Mem.ADDR_EDX));
		map.addStack(map.readReg(Mem.ADDR_ESI));
		map.addStack(map.readReg(Mem.ADDR_EDI));
		map.addStack(map.readReg(Mem.ADDR_DS));
		map.addStack(map.readReg(Mem.ADDR_CS));
		return line;
	}
}