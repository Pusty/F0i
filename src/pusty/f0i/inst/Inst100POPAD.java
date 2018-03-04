package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Removes all dword registers from stack and puts them accordingly
 * @see Inst
 * @see Inst103PUSHAD
 */
class Inst100POPAD extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.POPAD;
	}
	@Override
	public String getName() {
		return "POPAD";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		map.writeReg(Mem.ADDR_CS, map.readStack());
		map.writeReg(Mem.ADDR_DS, map.readStack());
		map.writeReg(Mem.ADDR_EDI, map.readStack());
		map.writeReg(Mem.ADDR_ESI, map.readStack());
		map.writeReg(Mem.ADDR_EDX, map.readStack());
		map.writeReg(Mem.ADDR_ECX, map.readStack());
		map.writeReg(Mem.ADDR_EBX, map.readStack());
		map.writeReg(Mem.ADDR_EAX, map.readStack());
		return line;
	}
}