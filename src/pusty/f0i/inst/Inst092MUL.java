package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Multiplies Parameter 1 by AL and puts it into AX (byte)
 * @see Inst
 */
class Inst092MUL extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.MUL;
	}
	@Override
	public String getName() {
		return "MUL";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(m2 != Mem.TYPE_NONE) {
			if(Mem.getSize(m1, i1) == Mem.SIZE_BYTE) 
				map.write(m1, i1, ((map.read(m1, i1, size)) * (map.read(m2, i2, size)))&0xFF, size);
			else if(Mem.getSize(m1, i1) == Mem.SIZE_WORD) 
				map.write(m1, i1, ((map.read(m1, i1, size)) * (map.read(m2, i2, size)))&0xFFFF, size);
			else 
				map.write(m1, i1, (map.read(m1, i1, size)) * (map.read(m2, i2, size)), size);			
		}else {
			if(Mem.getSize(m1, i1) == Mem.SIZE_BYTE) {
				map.writeReg(Mem.ADDR_AX,(map.readReg(Mem.ADDR_AL)) * (map.read(m1, i1, size)));
			}else if(Mem.getSize(m1, i1) == Mem.SIZE_WORD) {
				int e = (map.readReg(Mem.ADDR_AX)) * (map.read(m1, i1, size));
				map.writeReg(Mem.ADDR_DX, e&0xFFFF0000);
				map.writeReg(Mem.ADDR_AX, e&0x0000FFFF);
			}else {
				int e = map.readReg(Mem.ADDR_EAX) * (map.read(m1, i1, size));
				map.writeReg(Mem.ADDR_EDX, 0);
				map.writeReg(Mem.ADDR_EAX, e);
			}
		}
		return line;
	}
}