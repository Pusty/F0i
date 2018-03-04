package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Removes all word registers from stack and puts them accordingly
 * @see Inst
 * @see Inst103PUSHA
 */
class Inst100POPA extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.POPA;
	}
	@Override
	public String getName() {
		return "POPA";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.writeReg(Mem.ADDR_CS, map.readStack());
		map.writeReg(Mem.ADDR_DS, map.readStack());
		map.writeReg(Mem.ADDR_DI, map.readStack());
		map.writeReg(Mem.ADDR_SI, map.readStack());
		map.writeReg(Mem.ADDR_DX, map.readStack());
		map.writeReg(Mem.ADDR_CX, map.readStack());
		map.writeReg(Mem.ADDR_BX, map.readStack());
		map.writeReg(Mem.ADDR_AX, map.readStack());
		return line;
	}
}