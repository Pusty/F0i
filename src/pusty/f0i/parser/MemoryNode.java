package pusty.f0i.parser;

import com.badlogic.gdx.files.FileHandle;

import pusty.f0i.FileChecker;
import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;
import pusty.f0i.interpreter.MemoryMap;

/**
 * A Node that contains Memory Information
 * @see pusty.f0i.interpreter.MemoryMap MemoryMap
 */
public class MemoryNode extends Node {

	/**
	 * Creates a memory node
	 * @param cmd the command
	 * @param data the data array
	 * @param label the label
	 */
	public MemoryNode(String cmd, String[] data, String label) {
		super(cmd, data, label);
	}
	/**
	 * Returns the amount of bytes this node stores
	 * @return the amount of bytes this node includes into memory
	 */
	public int length() {
		if(getCommand() == OpCodes.DB) {
			return temp.length;
		}else if(getCommand() == OpCodes.DW) {
			return temp.length*2;
		}else if(getCommand() == OpCodes.DD) {
			return temp.length*4;
		}else if(getCommand() == OpCodes.EQU){
			return 2; //A WORD
		}else if(getCommand() == OpCodes.RESB){
			return Mem.getTypeInt(temp[0]);
		}else if(getCommand() == OpCodes.RESW){
			return Mem.getTypeInt(temp[0])*2;
		}else if(getCommand() == OpCodes.RESD){
			return Mem.getTypeInt(temp[0])*4;
		}else if(getCommand() == OpCodes.INCBIN){
			if(temp != null) {
				String fileName = temp[0];
				fileName = FileChecker.replaceAll(fileName, "'", "");
				fileName = FileChecker.replaceAll(fileName, '"'+"", "");
				FileHandle file = Parser.readFile("asm/"+fileName);
				return (int) file.length();
			}
		}
		return 0;
	}
	/**
	 * Parses the command into a parsed pseudo machine code syntax
	 */
	public String toString() {
		String line = "";
		if(getCommand() == OpCodes.DB) {
			if(getData() != null)
				for(int a=0;a<getData().length;a++)
					line = line + OpCodes.DB + " " + (getData()[a].getAddr()&0x00FF)+((a!=getData().length-1)?"\n":"");
		}else if(getCommand() == OpCodes.DW) {
			if(getData()!= null)
				for(int a=0;a<getData().length;a++) {
					line = line + OpCodes.DB + " " + (getData()[a].getAddr()&0x00FF) + "\n";
					line = line + OpCodes.DB + " " + ((getData()[a].getAddr()&0xFF00)>>8) + ((a!=getData().length-1)?"\n":"");
				}
		}else if(getCommand() == OpCodes.DD) {
			if(getData()!= null)
				for(int a=0;a<getData().length;a++) {
					line = line + OpCodes.DB + " " + (getData()[a].getAddr()&0x000000FF) + "\n";
					line = line + OpCodes.DB + " " + ((getData()[a].getAddr()&0x0000FF00)>>8) + "\n";
					line = line + OpCodes.DB + " " + ((getData()[a].getAddr()&0x00FF0000)>>16) + "\n";
					line = line + OpCodes.DB + " " + ((getData()[a].getAddr()&0xFF000000)>>24) +  ((a!=getData().length-1)?"\n":"");
				}
		}else if(getCommand() == OpCodes.RESB) {
			if(getData()!= null)
				for(int a=0;a<getData()[0].getAddr();a++) 
					line = line + OpCodes.DB + " " + 0+((a!=getData()[0].getAddr()-1)?"\n":"");
		}else if(getCommand() == OpCodes.RESW) {
			if(getData()!= null)
				for(int a=0;a<getData()[0].getAddr()*2;a++) 
					line = line + OpCodes.DB + " " + 0+((a!=getData()[0].getAddr()*2-1)?"\n":"");			
		}else if(getCommand() == OpCodes.RESD) {
			if(getData()!= null)
				for(int a=0;a<getData()[0].getAddr()*4;a++) 
					line = line + OpCodes.DB + " " + 0+((a!=getData()[0].getAddr()*4-1)?"\n":"");			
		}else if(getCommand() == OpCodes.EQU) {
			if(getData()!= null) {
				line = line + OpCodes.DB + " " + (getData()[0].getAddr()&0x0000FF00) + "\n";
				line = line + OpCodes.DB + " " + (getData()[0].getAddr()&0x000000FF);
			}
		}else if(getCommand() == OpCodes.INCBIN) {
			if(temp != null) {
				String fileName = temp[0];
				fileName = FileChecker.replaceAll(fileName, "'", "");
				fileName = FileChecker.replaceAll(fileName, '"'+"", "");
				FileHandle file = Parser.readFile("asm/"+fileName);
				byte[] blist = file.readBytes();
				for(int a=0;a<blist.length;a++) {
					line = line + OpCodes.DB + " " + (MemoryMap.u(blist[a]))+((a!=blist.length-1)?"\n":"");
				}
			}
		}
		return line;
	}
}
