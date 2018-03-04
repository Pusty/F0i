package pusty.f0i.inst;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * Divides AX/Parameter 1 and puts puts AX/Parameter 1 into AL and AX%Parameter 1 into AL (for size = byte)
 * @see Inst
 */
class Inst033DIV extends Inst {
	@Override
	public int getOpCode() {
		return OpCodes.DIV;
	}
	@Override
	public String getName() {
		return "DIV";
	}
	@Override
	public int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2,
			int size, int line) {
		if(Mem.getSize(m1, i1) == 8) {
			int e =  map.readReg(Mem.ADDR_AX) / map.read(m1, i1, size);
			if(e > 0xFF) System.err.println("Divide Error");
			map.writeReg(Mem.ADDR_AH, map.readReg(Mem.ADDR_AX) % map.read(m1, i1, size));
			map.writeReg(Mem.ADDR_AL, e);
		}else if(Mem.getSize(m1, i1) == 16) {
			int o = ((map.readReg(Mem.ADDR_DX)&MemoryMap.maxValueU(Mem.SIZE_WORD)) << 8) + map.readReg(Mem.ADDR_AX)&MemoryMap.maxValueU(Mem.SIZE_WORD);
			int e =  o / map.read(m1, i1, size);
			if(e > 0xFFFF) System.err.println("Divide Error");
			map.writeReg(Mem.ADDR_DX, o % map.read(m1, i1, size));
			map.writeReg(Mem.ADDR_AX, e);
		}else {
			int o = ((map.readReg(Mem.ADDR_EDX)) << 16) + map.readReg(Mem.ADDR_EAX);
			int e =  o / map.read(m1, i1, size);
			if(e > 0xFFFFFFFF) System.err.println("Divide Error");
			map.writeReg(Mem.ADDR_EDX, o % map.read(m1, i1, size));
			map.writeReg(Mem.ADDR_EAX, e);
		}
		return line;
	}
}