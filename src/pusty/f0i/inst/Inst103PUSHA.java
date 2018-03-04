package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Pushes all word registers to the stack
 * @see Inst
 * @see Inst100POPA
 */
class Inst103PUSHA extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.PUSHA;
	}
	@Override
	public String getName() {
		return "PUSHA";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.addStack(map.readReg(Mem.ADDR_AX));
		map.addStack(map.readReg(Mem.ADDR_BX));
		map.addStack(map.readReg(Mem.ADDR_CX));
		map.addStack(map.readReg(Mem.ADDR_DX));
		map.addStack(map.readReg(Mem.ADDR_SI));
		map.addStack(map.readReg(Mem.ADDR_DI));
		map.addStack(map.readReg(Mem.ADDR_DS));
		map.addStack(map.readReg(Mem.ADDR_CS));
		return line;
	}
}