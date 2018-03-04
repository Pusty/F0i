package pusty.f0i.interpreter;

import java.util.ArrayList;
import pusty.f0i.FileChecker;
import pusty.f0i.common.Mem;
import pusty.f0i.inst.Inst;

/**
 * The Interpreter of Pseudo Machine Code parsed by the {@link pusty.f0i.parser.Parser Parser}.
 * The emulation code of a x86 machine runs mainly through this class.
 * @see Inst
 * @see MemoryMap
 * @see pusty.f0i.F0iWindowedTick F0iWindowedTick
 * @see Bios
 */
public class Interpreter {
	/**
	 * The Interpreter currently has no non-static methods or variables and thus isn't used as an object
	 */
	public Interpreter() {}
	/** the emulated {@link MemoryMap} which contains the used memory and all used registers and flags
	 * @see pusty.f0i.common.Mem
	 */
	public static MemoryMap memoryMap = null;
	/** an array filled with commands (Command Syntax: OPCODE MODE1 INT1 MODE2 INT2 SIZE)
	 * @see pusty.f0i.common.OpCodes
	 */
	public static Integer[] commandsArray = null;
	/** current position for {@link #runOne}
	 */
	private static int tempPos = 0;
	/** Initializes the {@link Interpreter} and Instruction List.
	 * 	This function splits <code>args</code> into command and memory {@link pusty.f0i.parser.Node nodes}. It then puts the
	 *  parsed commands into {@link #commandsArray} and uses parsed memory to initiate {@link #memoryMap}. After that, it initializes
	 *  the {@link Inst Instruction Array} with <code>commandsArray</code>
	 * @param args an array filled with parsed {@link pusty.f0i.parser.CommandNode command} and {@link pusty.f0i.parser.MemoryNode memory} nodes
	 */
	public static void init(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		ArrayList<Byte> byteList = new ArrayList<Byte>();
		boolean mem = false;
		for(int a=0;a<args.length;a++) {
			if(args[a].trim().equalsIgnoreCase("#cmd:")) { mem = false; continue; }
			if(args[a].trim().equalsIgnoreCase("#mem:")) { mem = true; continue; }
			args[a] = args[a].trim();
			if(args[a].equalsIgnoreCase("")) continue;
			String[] cmd = FileChecker.splitNonRegex(args[a], " ");
			int[] nums = new int[cmd.length];
			if(!mem) {
				for(int b=0;b<cmd.length;b++)
					nums[b] = Integer.parseInt(cmd[b]);
				for(int b=0;b<6;b++)
					arrayList.add(nums.length<=b?0:nums[b]);
			}else {
					byteList.add((byte) Integer.parseInt(cmd[1]));
			}
		}
		Integer[] commands = arrayList.toArray(new Integer[arrayList.size()]);
		Byte[] 	  memory = byteList.toArray(new Byte[byteList.size()]);
		MemoryMap map = new MemoryMap(toByteArray(memory));
		
		memoryMap = map;
		commandsArray = commands;
		Inst.initInstructions(commands);
	}
	/** 
	 * Runs all instructions until it finished or reaches a {@link pusty.f0i.inst.Inst037HLT halt} or {@link pusty.f0i.inst.Inst059JMP jmp $} instruction.
	 * It prints out the content of <code>AX</code>, <code>BX</code>, <code>CX</code> and <code>DX</code> after that.
	 */
	public static void runAll() {	
		for(int i=0;i<commandsArray.length/6;i++) {			
			i = tick(i);
			if(i==-1)break;
		}
		System.out.println();
		System.out.println("ax "+memoryMap.readReg(Mem.ADDR_AX));
		System.out.println("bx "+memoryMap.readReg(Mem.ADDR_BX));
		System.out.println("cx "+memoryMap.readReg(Mem.ADDR_CX));
		System.out.println("dx "+memoryMap.readReg(Mem.ADDR_DX));
	}
	/** 
	 * Runs one instruction and increase {@link #tempPos}.
	 * If tempPos is -1 (because of a {@link pusty.f0i.inst.Inst037HLT halt} or {@link pusty.f0i.inst.Inst059JMP jmp $} instruction)
	 * return without doing anything
	 */
	public static void runOne() {
		if(tempPos==-1)return;
		if(tempPos<commandsArray.length/6) {
			tempPos = tick(tempPos);
		}
		if(tempPos==-1)return;
		tempPos++;
	}
	/** 
	 * Executes the command in line <code>line</code>
	 * @param line the line to execute
	 * @return the line to continue from
	 */
	public static int tick(int line) {
		return exec(memoryMap, commandsArray[line*6], commandsArray[line*6+1], commandsArray[line*6+2], commandsArray[line*6+3], commandsArray[line*6+4], commandsArray[line*6+5], line);
	}
	/** Utility Function which converts Byte[] to byte[] so it can be used in general functions.
	 * @param byteArray <code>Byte</code> <code>Array</code>
	 * @return outputs <code>byte</code> <code>Array</code>
	 */
	private static byte[] toByteArray(Byte[] byteArray) {
		byte[] array = new byte[byteArray.length];
		for (int i=0;i<byteArray.length;i++) 
			array[i] = byteArray[i].byteValue();
		return array;
	}
	/** Runs an instruction with the given arguments
	 * @param map The emulated {@link MemoryMap} 
	 * @param cmd command integer
	 * @param m1 mode for i1
	 * @param i1 number parameter 1
	 * @param m2 mode for i2
	 * @param i2 number parameter 2
	 * @param size operation size for this instruction (byte,word,dword)
	 * @param line the current IP
	 * @return the new IP
	 * @throws Exception something went wrong
	 * @see Inst#run
	 */
	public static int exec(MemoryMap map, int cmd, int m1, int i1, int m2, int i2, int size,int line) {
		try {
			return Inst.runInst(map, cmd, m1, i1, m2, i2, size, line);
		} catch (Exception e) {
			e.printStackTrace();
			return line;
		}
	}

}
