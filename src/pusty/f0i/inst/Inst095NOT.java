package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;
/**
 * Logical not Parameter 1 and put it into Parameter 1 (also updates flags)
 * @see Inst
 */
class Inst095NOT extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.NOT;
	}
	@Override
	public String getName() {
		return "NOT";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		int value = 0;
		if(size == Mem.SIZE_BYTE)
			value = ((map.readU(m1, i1,size))^0xFF);
		else if(size == Mem.SIZE_WORD)
			value = ((map.readU(m1, i1,size))^0xFFFF);
		else if(size == Mem.SIZE_DWORD)
			value = (int)((map.readU(m1, i1,size))^0xFFFFFFFFl);
		map.write(m1, i1, value, size);
		map.writeFlag(Mem.FLAG_ZF, value==0?1:0);
		map.writeFlag(Mem.FLAG_CF, 0);
		map.writeFlag(Mem.FLAG_OF, 0);
		map.writeFlag(Mem.FLAG_SF, 0);
		return line;
	}
}