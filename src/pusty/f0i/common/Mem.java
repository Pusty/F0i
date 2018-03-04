package pusty.f0i.common;

import java.util.ArrayList;

import pusty.f0i.FileChecker;
import pusty.f0i.parser.CommandNode;
import pusty.f0i.parser.MemoryNode;
import pusty.f0i.parser.Node;

/**
 * Contains all addresses of registers and flags and all common operation sizes.
 * Also used to store <code>Memory</code> references
 */
public class Mem {
	/**
	 * Empty memory reference type (something went wrong)
	 */
	public static final int TYPE_NONE = 0; //Empty Arg
	/**
	 * Register memory reference type
	 */
	public static final int TYPE_REGISTER = 1; //Register (ax,bx,cx)
	/**
	 * {@link CommandNode} memory reference type
	 */
	public static final int TYPE_NODE = 2; //A command (jmp,mov,test)
	/**
	 * {@link MemoryNode} memory reference type with unknown size
	 */
	public static final int TYPE_MEM = 3; //A thing in memory (UNKNOWN) (db 3, incbin stuff)
	/**
	 * direct number memory reference type
	 */
	public static final int TYPE_INT = 4; //A random number
	/**
	 * indirect memory reference type
	 */
	public static final int TYPE_DMEM = 5; //Dynamic Memory reference ([esi],[si],[edi])
	/**
	 *{@link MemoryNode} memory reference type with 8bit size (byte)
	 */
	public static final int TYPE_MEM8 = 6; //A thing in memory 8bit (byte) (db 3, incbin stuff)
	/**
	 * {@link MemoryNode} memory reference type with 16bit size (word)
	 */
	public static final int TYPE_MEM16 = 7; //A thing in memory 16bit (word) (db 3, incbin stuff)
	/**
	 * {@link MemoryNode} memory reference type with 32bit size (dword)
	 */
	public static final int TYPE_MEM32 = 8; //A thing in memory 32bit (dword) (db 3, incbin stuff)
	/**
	 * Size reference for byte (8bit)
	 */
	public static final int SIZE_BYTE = 2;
	/**
	 * Size reference for word (16bit)
	 */
	public static final int SIZE_WORD = 1;
	/**
	 * Size reference for dword (32bit)
	 */
	public static final int SIZE_DWORD = 0;
	/**
	 * Register address for <code>EAX</code>
	 */
	public static final int ADDR_EAX = 0;
	/**
	 * Register address for <code>AX</code>
	 */
	public static final int ADDR_AX = 1;
	/**
	 * Register address for <code>AH</code>
	 */
	public static final int ADDR_AH = 2;
	/**
	 * Register address for <code>AL</code>
	 */
	public static final int ADDR_AL = 3;
	/**
	 * Register address for <code>EBX</code>
	 */
	public static final int ADDR_EBX = 4;
	/**
	 * Register address for <code>BX</code>
	 */
	public static final int ADDR_BX = 5;
	/**
	 * Register address for <code>BH</code>
	 */
	public static final int ADDR_BH = 6;
	/**
	 * Register address for <code>BL</code>
	 */
	public static final int ADDR_BL = 7;
	/**
	 * Register address for <code>ECX</code>
	 */
	public static final int ADDR_ECX = 8;
	/**
	 * Register address for <code>CX</code>
	 */
	public static final int ADDR_CX = 9;
	/**
	 * Register address for <code>CH</code>
	 */
	public static final int ADDR_CH = 10;
	/**
	 * Register address for <code>CL</code>
	 */
	public static final int ADDR_CL = 11;
	/**
	 * Register address for <code>EDX</code>
	 */
	public static final int ADDR_EDX = 12;
	/**
	 * Register address for <code>DX</code>
	 */
	public static final int ADDR_DX = 13;
	/**
	 * Register address for <code>DH</code>
	 */
	public static final int ADDR_DH = 14;
	/**
	 * Register address for <code>DL</code>
	 */
	public static final int ADDR_DL = 15;
	/**
	 * Register address for <code>ESI</code>
	 */
	public static final int ADDR_ESI = 16;
	/**
	 * Register address for <code>SI</code>
	 */
	public static final int ADDR_SI = 17;
	/**
	 * Register address for <code>EDI</code>
	 */
	public static final int ADDR_EDI = 20;
	/**
	 * Register address for <code>DI</code>
	 */
	public static final int ADDR_DI = 21;
	/**
	 * Register address for <code>CS</code> (code segment)
	 */
	public static final int ADDR_CS = 25;
	/**
	 * Register address for <code>DS</code> (data segment)
	 */
	public static final int ADDR_DS = 29;
	/**
	 * Register address for <code>SS</code> (stack segment)
	 */
	public static final int ADDR_SS = 33;
	/**
	 * Flag address for <code>CF</code> (carry flag)
	 */
	public static final int FLAG_CF = 34; //carry flag
//	public static final int FLAG_PF = 35; //parity flag
//	public static final int FLAG_AF = 36; //auxiliary flag
	/**
	 * Flag address for <code>ZF</code> (zero flag)
	 */
	public static final int FLAG_ZF = 37; //zero flag
	/**
	 * Flag address for <code>SF</code> (sign flag)
	 */
	public static final int FLAG_SF = 38; //sign flag
//	public static final int FLAG_TF = 39; //trap flag
//	public static final int FLAG_IF = 40; //interrupt flag
//	public static final int FLAG_DF = 41; //direction flag
	/**
	 * Flag address for <code>OF</code> (overflow flag)
	 */
	public static final int FLAG_OF = 42; //overflow flag
	
	/**
	 * memory size for this memory reference
	 */
	private int memSize;
	/**
	 * memory type for this memory reference
	 */
	private int memType;
	/**
	 * memory address for this memory reference
	 */
	private int memAddr;
	/**
	 * A memory reference
	 * @param t memory reference type
	 * @param a memory reference address
	 */
	public Mem(int t,int a) {
		memType = t;
		memAddr = a;
	}
	/**
	 * Returns the size of this memory reference
	 * @return the memory size
	 */
	public int getSize() {
		return memSize;
	}
	/**
	 * Returns the memory reference type
	 * @return the memory reference type
	 */
	public int getType() {
		return memType;
	}

	/**
	 * Returns the memory reference address
	 * @return the memory reference address
	 */
	public int getAddr() {
		return memAddr;
	}
	/**
	 * Returns a char for a given reference type
	 * @param type a memory reference type
	 * @return a char that represents this reference type
	 * @throws Exception can't find this reference type
	 */
	public static char getTypeChar(int type) throws Exception {
		if(type == TYPE_INT) return 'i';
		else if(type == TYPE_MEM) return 'm';
		else if(type == TYPE_REGISTER) return 'r';
		else if(type == TYPE_NODE) return 'f';
		throw new Exception("Don't know type "+type);
	}
	/**
	 * Cast a string into a decimal integer (decimal numbers, octal numbers, hexadecimal numbers and binary numbers)
	 * @param number a number as a <code>String</code>
	 * @return a correctly parsed integer. Returns {@link Integer#MIN_VALUE} if it fails
	 */
	public static int getTypeInt(String number) {
		int b = 0;
		if(number.endsWith("d"))
			try { b = Integer.parseInt(number.substring(0, number.length()-1), 10); }catch(Exception e) {  return Integer.MIN_VALUE; }
		else if(number.endsWith("o"))
			try { b = Integer.parseInt(number.substring(0, number.length()-1), 8); }catch(Exception e) {  return Integer.MIN_VALUE; }
		else if(number.endsWith("h"))
			try { b = Integer.parseInt(number.substring(0, number.length()-1), 16); }catch(Exception e) {  return Integer.MIN_VALUE; }
		else if(number.endsWith("b"))
			try { b = Integer.parseInt(number.substring(0, number.length()-1), 2); }catch(Exception e) {   return Integer.MIN_VALUE; }
		else if(number.startsWith("0x"))
			try { b = Integer.parseInt(number.substring(2, number.length()), 16); }catch(Exception e) { return Integer.MIN_VALUE;}
		else
			try { b = Integer.parseInt(number, 10); }catch(Exception e) {  return Integer.MIN_VALUE; }
		return b;
	}
	/**
	 * Returns the reference size for a given reference mode and address
	 * @param m1 a memory reference mode
	 * @param i1 a memory reference address
	 * @return the memory reference size
	 * @see pusty.f0i.inst.Inst033DIV Inst033DIV
	 * @see pusty.f0i.inst.Inst092MUL Inst092MUL
	 */
	public static int getSize(int m1, int i1) {
		if(m1 == Mem.TYPE_DMEM || m1 == Mem.TYPE_REGISTER) {
			if(i1%4==0) return 32;
			if(i1%4==1) return 16;
			if(i1%4==2 || i1%4 == 3) return 8;
		}
		return 8;
	}
	
	/**
	 * Returns an address reference for a register by their name as a <code>String</code>
	 * @param name a register name
	 * @return a register address
	 * @throws Exception register not found
	 */
	public static int getTypeReg(String name) throws Exception {
		if(name.equalsIgnoreCase("ax")) return ADDR_AX;
		else if(name.equalsIgnoreCase("bx")) return ADDR_BX;
		else if(name.equalsIgnoreCase("cx")) return ADDR_CX;
		else if(name.equalsIgnoreCase("dx")) return ADDR_DX;
		
		else if(name.equalsIgnoreCase("ah")) return ADDR_AH;
		else if(name.equalsIgnoreCase("bh")) return ADDR_BH;
		else if(name.equalsIgnoreCase("ch")) return ADDR_CH;
		else if(name.equalsIgnoreCase("dh")) return ADDR_DH;
		
		else if(name.equalsIgnoreCase("al")) return ADDR_AL;
		else if(name.equalsIgnoreCase("bl")) return ADDR_BL;
		else if(name.equalsIgnoreCase("cl")) return ADDR_CL;
		else if(name.equalsIgnoreCase("dl")) return ADDR_DL;
		
		else if(name.equalsIgnoreCase("eax")) return ADDR_EAX;
		else if(name.equalsIgnoreCase("ebx")) return ADDR_EBX;
		else if(name.equalsIgnoreCase("ecx")) return ADDR_ECX;
		else if(name.equalsIgnoreCase("edx")) return ADDR_EDX;
		
		else if(name.equalsIgnoreCase("esi")) return ADDR_ESI;
		else if(name.equalsIgnoreCase("si")) return ADDR_SI;
		else if(name.equalsIgnoreCase("edi")) return ADDR_EDI;
		else if(name.equalsIgnoreCase("di")) return ADDR_DI;
		
		else if(name.equalsIgnoreCase("cs")) return ADDR_CS;
		else if(name.equalsIgnoreCase("ds")) return ADDR_DS;
		
		throw new Exception("Can't find register "+name);
	}
	/**
	 * Returns if <code>c</code> is the name of a register
	 * @param c the name of the register
	 * @return if there is a register with the name <code>c</code>
	 */
	public static boolean isRegister(String c) {
		return (c.equalsIgnoreCase("ax") || c.equalsIgnoreCase("bx") || c.equalsIgnoreCase("cx") || c.equalsIgnoreCase("dx")
		    || c.equalsIgnoreCase("ah") || c.equalsIgnoreCase("bh") || c.equalsIgnoreCase("ch") || c.equalsIgnoreCase("dh")
		    || c.equalsIgnoreCase("al") || c.equalsIgnoreCase("bl") || c.equalsIgnoreCase("cl") || c.equalsIgnoreCase("dl")
		    || c.equalsIgnoreCase("eax") || c.equalsIgnoreCase("ebx") || c.equalsIgnoreCase("ecx") || c.equalsIgnoreCase("edx")
		    || c.equalsIgnoreCase("esi") || c.equalsIgnoreCase("si") || c.equalsIgnoreCase("edi") || c.equalsIgnoreCase("di")
		    || c.equalsIgnoreCase("cs") || c.equalsIgnoreCase("ds") || c.equalsIgnoreCase("ss"));
	}
	/**
	 * A function to find a label or sublabel within the {@link CommandNode CommandNodes}
	 * @param c the label to search for
	 * @param list the list which contains all <code>command nodes</code>
	 * @param line current position in the list (which only makes sense if this is about a <code>command node</code>)
	 * @return the line in which the label occurs, -1 if not found
	 */
	public static int isFunction(String c, ArrayList<CommandNode> list, int line) {
		if(c.startsWith(".")) { //search for a sublabel near 'line'
			for(int a=line;a>=0;a--) //searching for the first non sublabel
				if(list.get(a).hasLabel() && !list.get(a).getLabel().startsWith(".")) { //if found
					for(int b=a+1;b<list.size();b++) { //check all sublabels of this label
						if(list.get(b).hasLabel() && list.get(b).getLabel().equalsIgnoreCase(c)) //if a sublabel was found and it's the same return the position
							return b;
					}
					return -1;
				}
		}else if(c.contains(".")) {
			String[] s = FileChecker.splitNonRegex(c, ".");
			if(s.length!=2) {
				try {
					throw new Exception("Sublabels format error ("+c+")");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return -1;
			}
			for(int a=0;a<list.size();a++) {
				if(list.get(a).hasLabel() && list.get(a).getLabel().equalsIgnoreCase(s[0])) {
					for(int b=a+1;b<list.size();b++) {
						if(list.get(b).hasLabel() && list.get(b).getLabel().startsWith(".")
						&& s[1].equalsIgnoreCase(list.get(b).getLabel().substring(1, list.get(b).getLabel().length()))) {
							return b;
						}else if(list.get(b).hasLabel() && !list.get(b).getLabel().startsWith(".")){
							try {
								throw new Exception("Can't find sublabel "+c+", "+list.get(b).getLabel()+", "+list.get(a).getLabel());
							} catch (Exception e) {
								e.printStackTrace();
							}
							return -1;
						}
					}
					return -1;
				}
			}
			
		}else
			for(int a=0;a<list.size();a++) { //find the label within the list
				if(list.get(a).hasLabel() && list.get(a).getLabel().equalsIgnoreCase(c))
					return a;
			}
		return -1;
	}
	/**
	 *  A function to find a label within the {@link MemoryNode MemoryNodes}
	 * @param c  the label to search for
	 * @param list the list which contains all <code>memory nodes</code>
	 * @param line current position in the list (which only makes sense if this is about a <code>memory node</code>)
	 * @return the line in which the label occurs, -1 if not found
	 */
	public static int isMemory(String c, ArrayList<MemoryNode> list, int line) {
		if(c.startsWith(".")) {
//			System.err.println(". memory access isn't allowed with this emulator");
			return -1;
		}else {
			int b = 0;
			for(int a=0;a<list.size();a++) { //find the label within the memory nodes
					if(list.get(a).hasLabel() && list.get(a).getLabel().equalsIgnoreCase(c)) {
						return b;
					}
					else if(list.get(a).temp!=null) //adds the right amount of bytes together to calculate the right offset
						b = b + list.get(a).length(); 
			}			
		}
		return -1;
	}
	/**
	 * A function to parse {@link Node} content to memory references within 
	 * the given <code>list</code> and <code>mlist</code> context and in a specific <code>line</code>
	 * 
	 * @param data the data of a <code>Node</code>
	 * @param list an <code>ArrayList</code> which contains all {@link CommandNode CommandNodes}
	 * @param mlist an <code>ArrayList</code> which contains all {@link MemoryNode MemoryNodes}
	 * @param line the line the node is in
	 * @return an array filled with memory references
	 * @throws Exception don't know what to do with the given <code>data</code>
	 */
	public static Mem[] toMem(String[] data, ArrayList<CommandNode> list, ArrayList<MemoryNode> mlist, int line) throws Exception {
		if(data == null) return null;
		Mem[] memory = new Mem[data.length];
		for(int a=0;a<data.length;a++) {
			int byteCall = Mem.TYPE_MEM; //size of the operation later important
			int typeCall = Mem.TYPE_INT;
			int addrCall = -1;			
			String mainpart = data[a].trim(); //removes whitespaces
			mainpart = FileChecker.replaceAll(mainpart, "\t", ""); //remove tabulator
			String testA = "byte";
			if(mainpart.length()>= testA.length()) {
				String test = mainpart.substring(0, testA.length());
				if(test.equalsIgnoreCase(testA)) {
					mainpart = mainpart.substring(testA.length(), mainpart.length());
					byteCall = Mem.TYPE_MEM8;
				}
			}
			testA = "word";
			if(mainpart.length()>= testA.length()) {
				String test = mainpart.substring(0, testA.length());
				if(test.equalsIgnoreCase(testA)) {
					mainpart = mainpart.substring(testA.length(), mainpart.length());
					byteCall = Mem.TYPE_MEM16;
				}
			}
			testA = "dword";
			if(mainpart.length()>= testA.length()) {
				String test = mainpart.substring(0, testA.length());
				if(test.equalsIgnoreCase(testA)) {
					mainpart = mainpart.substring(testA.length(), mainpart.length());
					byteCall = Mem.TYPE_MEM32;
				}
			}
			ArrayList<MathPart> mList = new ArrayList<MathPart>();
			mList.add(new MathPart());
			int cap = 0;
			for(int chI=0;chI<mainpart.length();chI++) {
				char ch = mainpart.charAt(chI);
				if(cap == 0) {
					if(ch == '[') {
						typeCall = Mem.TYPE_MEM;
					}else if(ch == ']') {
						typeCall = Mem.TYPE_MEM;
					}else if(ch == '+') {
						mList.add(new MathPart());
						if(mList.get((mList.size()-1)).getB().length()>0) {
							mList.add(new MathPart());
							mList.get(mList.size()-1).operation = MathPart.OP_ADD;
						}
					}else if(ch == '-') {
						if(mList.get((mList.size()-1)).getB().length()>0) {
							mList.add(new MathPart());
							mList.get(mList.size()-1).operation = MathPart.OP_ADD;
						}
						mList.get(mList.size()-1).negative=!mList.get(mList.size()-1).negative;
					}else if(ch == '*') {
						mList.add(new MathPart());
						mList.get(mList.size()-1).operation = MathPart.OP_MUL;
					}else if(ch == '/') {
						mList.add(new MathPart());
						mList.get(mList.size()-1).operation = MathPart.OP_DIV;					
					}else if(ch == '%') {
						mList.add(new MathPart());
						mList.get(mList.size()-1).operation = MathPart.OP_MOD;	
					}else if(ch == ' ') {
					}else if(ch == "'".charAt(0)) {
						mList.get(mList.size()-1).getB().append(ch);
						cap = 1;
					}else if(ch == '"') {
						mList.get(mList.size()-1).getB().append(ch);
						cap = 2;
					}else
						mList.get(mList.size()-1).getB().append(ch);
				}else {
					if(ch == "'".charAt(0) && cap == 1)
						cap = 0;
					else if(ch == '"' && cap == 2)
						cap = 0;
					mList.get(mList.size()-1).getB().append(ch);
				}
			}
			for(MathPart mPart:mList) {
				if(mPart.getB().length()==0)continue;
				int v = 0;
				String test = mPart.getB().toString();
				if(isRegister(test)) {
					if(typeCall == Mem.TYPE_MEM) {
						typeCall = Mem.TYPE_DMEM;
						addrCall = getTypeReg(test);
					}else if(typeCall != Mem.TYPE_INT && typeCall != Mem.TYPE_REGISTER)
						throw new Exception("Type call exception for register "+test+" :"+typeCall);
					else {
					typeCall = Mem.TYPE_REGISTER;
					addrCall = getTypeReg(test);
					}
				}else {
					int f = -1;
					if(list!=null)
						f = isFunction(test, list, line);
					int m = -1;
					if(mlist!=null)
						m = isMemory(test, mlist, line);
					int i = getTypeInt(test);
					if(f>=0) {
						typeCall = Mem.TYPE_NODE;
						v = v + f;
					}else if(m>=0) {
						v = v + m;
					}else if(i != Integer.MIN_VALUE)
						v = v + i;
					else {
						if(test.startsWith("'") && test.endsWith("'") && test.length()==3) {
							v = v + test.charAt(1);
						}else if(test.equalsIgnoreCase("$")) {
							int c = 0;
							if(list!=null){
								c = c + line;
							}else if(mlist!=null){ //finds current memory offset, only makes sense if instruction is a memory instruction
								if(line<mlist.size())
									for(int d=0;d<line;d++) 
										if(mlist.get(d).temp!=null)
											c = c + mlist.get(d).length(); 
							}else
								throw new Exception("Can't handle $ if mlist is null");
							v = v + c;
						}else if(test.equalsIgnoreCase("$$")) {
							v = v + 0;
						}else if(mlist!= null && line <= mlist.size() && mlist.get(line).getCommand()==OpCodes.INCBIN && (test.charAt(0)=='"' || test.charAt(0)=="'".charAt(0))) {
							v = v + 0;
						}
						else
							throw new Exception("Don't know what to do with "+test+", "+line);
					}			
//					System.err.println(test+", "+line);
				}
				if(typeCall != Mem.TYPE_REGISTER && typeCall != Mem.TYPE_DMEM) {
					mPart.builder = null;
					mPart.number = v;
				}
			}
			for(int c=0;c<mList.size();c++) {
				if(mList.get(c).getO() != MathPart.OP_ADD) {
					if(mList.get(c).getO() == MathPart.OP_MUL) 
						mList.get(c-1).setN(mList.get(c-1).getN()*mList.get(c).getN());
					if(mList.get(c).getO() == MathPart.OP_DIV) 
						mList.get(c-1).setN(mList.get(c-1).getN()/mList.get(c).getN());
					if(mList.get(c).getO() == MathPart.OP_MOD) 
						mList.get(c-1).setN(mList.get(c-1).getN()%mList.get(c).getN());			
						mList.get(c).setN(0);
						mList.get(c).operation = MathPart.OP_ADD;
				}
			}
			int value = 0;
			for(int c=0;c<mList.size();c++) 
				value = value + mList.get(c).getN();
			if(typeCall != Mem.TYPE_REGISTER && typeCall != Mem.TYPE_DMEM) {
				addrCall = value;
			}
			
			memory[a] = new Mem(typeCall, addrCall);
			memory[a].memSize = byteCall;
		}
		return memory;
	}
	
}
/**
 * A part of a calculation
 */
class MathPart {
	/**
	 * Addition
	 */
	public static final int OP_ADD = 0;
	/**
	 * Multiplication
	 */
	public static final int OP_MUL = 2;
	/**
	 * Divide
	 */
	public static final int OP_DIV = 3;
	/**
	 * Mod
	 */
	public static final int OP_MOD = 4;
	/**
	 * Stored Number
	 */
	public int number;
	/**
	 * Is negative?
	 */
	public boolean negative;
	/**
	 * Which operation to perform
	 */
	public int operation;
	/**
	 * <code>StringBuilder</code> that contains content in <code>String</code> format
	 */
	public StringBuilder builder;
	/**
	 * Creates a part of a calculation
	 */	
	public MathPart() {
		number = 0;
		operation = OP_ADD;
		negative = false;
		builder = new StringBuilder("");
	}
	/**
	 * Returns the number in the correct format
	 * @return a number in correct format
	 */
	public int getN() { return number*(negative?-1:1); }
	/**
	 * Sets the number to the correct format
	 * @param n the number to set into this <code>MathPart</code>
	 */
	public void setN(int n) {
		negative = n < 0;		
		number = Math.abs(n);
	}
	/**
	 * Returns if this MathPart contains a negative number
	 * @return is the number from this <code>MathPart</code> negative
	 */
	public boolean getNe() { return negative; }
	/**
	 * Returns the operation type of this <code>MathPart</code>
	 * @return the operation type
	 */
	public int getO() { return operation; }
	/**
	 * Returns the string builder of this <code>MathPart</code>
	 * @return the string
	 */
	public StringBuilder getB() { return builder; }
	/**
	 * Returns the <code>MathPart</code> as a <code>String</code>
	 * @return this <code>MathPart</code> as a <code>String</code>
	 */	
	public String toString() { return (negative?"-":"")+number+" :"+getB();}
}