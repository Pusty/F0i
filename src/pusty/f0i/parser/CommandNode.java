package pusty.f0i.parser;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;

/**
 * A Node that contains a Instruction
 * @see pusty.f0i.inst.Inst Inst
 */
public class CommandNode extends Node {

	/**
	 * Creates a command node
	 * @param cmd the command
	 * @param data the data array
	 * @param label the label
	 */
	public CommandNode(String cmd, String[] data, String label) {
		super(cmd, data, label);
	}

	public int getOperationSize() {
		if(getData()==null) return Mem.SIZE_BYTE;
		if(this.getCommand() == OpCodes.NOP)
			return Mem.SIZE_BYTE;
		if(this.getCommand() == OpCodes.ORG)
			return Mem.SIZE_WORD;
		if(this.getCommand() == OpCodes.JMP)
			return Mem.SIZE_WORD;	
		if(this.getCommand() == OpCodes.CPU)
			return Mem.SIZE_WORD;	
		if(this.getCommand() == OpCodes.INT)
			return Mem.SIZE_BYTE;
		
		if(this.getCommand() == OpCodes.STOS) {
			if(cmd.trim().equalsIgnoreCase("stosb"))
				return Mem.SIZE_BYTE;
			else if(cmd.trim().equalsIgnoreCase("stosw"))
				return Mem.SIZE_WORD;
			else if(cmd.trim().equalsIgnoreCase("stosd"))
				return Mem.SIZE_DWORD;
		}
		if(this.getCommand() == OpCodes.LODS) {
			if(cmd.trim().equalsIgnoreCase("lodsb"))
				return Mem.SIZE_BYTE;
			else if(cmd.trim().equalsIgnoreCase("lodsw"))
				return Mem.SIZE_WORD;
			else if(cmd.trim().equalsIgnoreCase("lodsd"))
				return Mem.SIZE_DWORD;
		}
		if(this.getCommand() == OpCodes.MOVS) {
			if(cmd.trim().equalsIgnoreCase("movsb"))
				return Mem.SIZE_BYTE;
			else if(cmd.trim().equalsIgnoreCase("movsw"))
				return Mem.SIZE_WORD;
			else if(cmd.trim().equalsIgnoreCase("movsd"))
				return Mem.SIZE_DWORD;
		}
		if(this.getCommand() == OpCodes.CMPS) {
			if(cmd.trim().equalsIgnoreCase("cmpsb"))
				return Mem.SIZE_BYTE;
			else if(cmd.trim().equalsIgnoreCase("cmpsw"))
				return Mem.SIZE_WORD;
			else if(cmd.trim().equalsIgnoreCase("cmpsd"))
				return Mem.SIZE_DWORD;
		}
		
		for(int a=0;a<getData().length;a++) {
			if(getData()[a].getType() == Mem.TYPE_REGISTER) {
				if(getData()[a].getAddr()%4==0) 
					return Mem.SIZE_DWORD;
				else if(getData()[a].getAddr()%4==1) 
					return Mem.SIZE_WORD;
				else
					return Mem.SIZE_BYTE;
			}
			if(getData()[a].getType() == Mem.TYPE_NODE) return Mem.SIZE_WORD;
			if(getData()[a].getType() == Mem.TYPE_INT || getData()[a].getType() == Mem.TYPE_DMEM || getData()[a].getType() == Mem.TYPE_MEM) {
				if(getData()[a].getSize() != Mem.TYPE_MEM) {
					if(getData()[a].getSize() == Mem.TYPE_MEM8)
						return Mem.SIZE_BYTE;
					else if(getData()[a].getSize() == Mem.TYPE_MEM16)
						return Mem.SIZE_WORD;
					else if(getData()[a].getSize() == Mem.TYPE_MEM32)
						return Mem.SIZE_DWORD;
				}
			}
		}
		if(this.getCommand() == OpCodes.BITS)
			return Mem.SIZE_WORD;
		return -1;
	}
	/**
	 * Parses the command into a parsed pseudo machine code syntax
	 */
	public String toString() {
		String line = "";
		try {
			line = this.getCommand()+" ";
				for(int a=0;a<2;a++)
					line = line
					+ (getData()!=null&&getData().length>a?getData()[a].getType():0)+" "
					+ (getData()!=null&&getData().length>a?getData()[a].getAddr():0)+" ";
		}catch(Exception e) { e.printStackTrace(); }
		if(getOperationSize()==-1)
			try {
				throw new Exception("Operation Size is not fixed! ("+this.getCommand()+")");
			} catch (Exception e) {
				e.printStackTrace();
			}
		line = line + getOperationSize();
		return line;
	}


}
