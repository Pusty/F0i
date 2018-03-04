package pusty.f0i.interpreter;

import java.util.ArrayList;
import java.util.List;

import pusty.f0i.common.Mem;

/**
 * A emulated memory map which contains stored data, registers, flags and the stack
 * @see Mem
 */
public class MemoryMap {
	/**
	 * the array containing the {@link pusty.f0i.parser.MemoryNode MemoryNode} information
	 */
	private byte[] memory;
	/**
	 * the array containing the {@link pusty.f0i.common.Mem Register} information
	 */
	private byte[] register;
	/**
	 * a list containing the stack
	 */
	private List<Integer> stack;
	/**
	 * Creates a memory map with 48 bytes reserved for registers
	 * @param m the memory to import into the <code>MemoryMap</code>
	 */
	public MemoryMap(byte[] m) {
		memory = m;
		register = new byte[48];
		stack = new ArrayList<Integer>();
	}
	/**
	 * Returns the stack
	 * @return the stack
	 */
	public List<Integer> getStack() {
		return stack;
	}
	/** 
	 * Adds a word (max 0xFFFF) to the stack
	 * @param value the number to add to the stack
	 */
	public void addStack(int value) {
		if(value>0xFFFF || value<-32768)
			System.err.println("Value "+(value)+" out of bounds for the stack");
		stack.add(value);
	}
	/**
	 * Reads a word (max 0xFFFF) from the stack and then removes it from the list
	 * @return highest value on the stack
	 */
	public int readStack() {
		int read = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return read;
	}
	/**
	 * Returns the memory
	 * @return the memory
	 */
	public byte[] getMemory() {
		return memory;
	}
	/**
	 * Performs a addition of Parameter 1 and Parameter 2 and returns the result. Also sets the Carry Flag
	 * @param m1 mode for Parameter 1
	 * @param i1 value for Parameter 1
	 * @param m2 mode for Parameter 2
	 * @param i2 value for Parameter 2
	 * @param size the size of the Parameter
	 * @return the result of addition
	 */
	public int add(int m1, int i1, int m2, int i2, int size) {
		int a = read(m1, i1, size);
		int b = read(m2, i2, size);
		int c = a + b;
		register[Mem.FLAG_CF] = (byte)((c > maxValue(m1, i1) || ((a&0xffffffffl) + (b&0xffffffffl)) < 0)?1:0); //?
		return c;
	}
	/**
	 * Performs a subtraction of Parameter 1 and Parameter 2 and returns the result. Also sets the Carry Flag
	 * @param m1 mode for Parameter 1
	 * @param i1 value for Parameter 1
	 * @param m2 mode for Parameter 2
	 * @param i2 value for Parameter 2
	 * @param size the size of the Parameter
	 * @return the result of subtraction
	 */
	public int sub(int m1, int i1, int m2, int i2, int size) {
		int a = read(m1, i1, size);
		int b = read(m2, i2, size);
		int c = a - b;
		register[Mem.FLAG_CF] = (byte)((c > maxValue(m1, i1) || ((a&0xffffffffl) - (b&0xffffffffl)) < 0)?1:0);
		return c;
	}
	/**
	 * Updates flags corresponding to Parameter 1 and the number to find there
	 * @param n the number in Parameter 1
	 * @param m1 mode of Parameter 1
	 * @param i1 value of Parameter 1
	 * @param size size of Parameter 1
	 */
	public void updateFlags(int n, int m1, int i1,int size) {
		register[Mem.FLAG_ZF] = (byte) (n==0?1:0); 
		register[Mem.FLAG_SF] = (byte) (n<0?1:0); //set in all execpt mul and div, in test, in cmp, logical operators
		//carry = w1 > UINT_MAX-w2;
//		System.out.println((n&0xffffffffl) > maxValue(m1, i1));
//		System.out.println((n&0xffffffffl) +" > "+ maxValue(m1, i1));
//		System.out.println(Integer.toBinaryString(n&maxValue(m1, i1))+", "+Integer.toBinaryString(maxValue(m1, i1))+": "+Integer.toBinaryString((n|maxValue(m1, i1))-maxValue(m1, i1)));
//		register[Mem.FLAG_CF] = (byte) (((n&0xffffffffl) > 0)?1:0); //n in reg isn't the expected n thus set a carry
		int temp = read(m1, i1, size);
		write(m1, i1, n, size);
		register[Mem.FLAG_OF] = (byte) (((n>0 && read(m1,i1,size)<0) || (n<0 && read(m1,i1,size)>0))?1:0); //n is negative now, this isn't suppost to happen so a overflow happend
		write(m1, i1, temp, size);
//		System.out.println("("+n+") ZF: "+register[Mem.FLAG_ZF]+", SF:"+register[Mem.FLAG_SF]+", CF:"+register[Mem.FLAG_CF]+", OF: "+register[Mem.FLAG_OF]);
	}
	/**
	 * Returns the maxValue for a given size without sign
	 * @param size size mode from {@link Mem}
	 * @return the maxValue with sign (0x7F for byte)
	 */
	public static int maxValue(int size) {
		if(size == Mem.SIZE_BYTE) return 0x7F;
		if(size == Mem.SIZE_WORD) return 0x7FFF;
		if(size == Mem.SIZE_DWORD) return 0x7FFFFFFF;
		else
			try {
				throw new Exception("Can't find operation size "+size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return 0;
	}
	/**
	 * Returns the maxValue for a given size with sign
	 * @param size size mode from {@link Mem}
	 * @return the maxValue without sign (0xFF for byte)
	 */
	public static int maxValueU(int size) {
		if(size == Mem.SIZE_BYTE) return 0xFF;
		if(size == Mem.SIZE_WORD) return 0xFFFF;
		if(size == Mem.SIZE_DWORD) return 0xFFFFFFFF;
		else
			try {
				throw new Exception("Can't find operation size "+size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return 0;
	}
	/**
	 * Returns the maxValue for a given Parameter without sign
	 * @param m1 Parameter 1 Mode
	 * @param i1 Parameter 1 Value
	 * @return the maxValue without sign (0xFF for byte)
	 */
	public static int maxValueU(int m1, int i1) {
		if(m1 == Mem.TYPE_MEM) return 0xFF;
		else if(m1 == Mem.TYPE_REGISTER) {
			if(i1%4==0) 
				return 0xFFFFFFFF;
			else if((i1-1)%4==0) 
				return 0xFFFF;
			else if((i1-2)%4==0) 
				return 0xFF;
			else if((i1-3)%4==0) 
				return 0xFF;	
			else
				try {
					throw new Exception("Don't know reg "+i1);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(m1 == Mem.TYPE_INT) {
			return 0xFFFFFFFF;
		}else
			return 0xFFFF;
		return 0xFFFF;
	}
	/**
	 * Returns the maxValue for a given Parameter with sign
	 * @param m1 Parameter 1 Mode
	 * @param i1 Parameter 1 Value
	 * @return the maxValue with sign (0x7F for byte)
	 */
	public static int maxValue(int m1, int i1) {
		if(m1 == Mem.TYPE_MEM) return 0x7F;
		else if(m1 == Mem.TYPE_REGISTER) {
			if(i1%4==0) 
				return 0x7FFFFFFF;
			else if((i1-1)%4==0) 
				return 0x7FFF;
			else if((i1-2)%4==0) 
				return 0x7F;
			else if((i1-3)%4==0) 
				return 0x7F;	
			else
				try {
					throw new Exception("Don't know reg "+i1);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(m1 == Mem.TYPE_INT) {
			return Integer.MAX_VALUE;
		}else
			try {
				throw new Exception("Don't know max value of "+m1+"("+i1+")");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		return -1;
	}
	/**
	 * Read the unsigned value from Parameter 1
	 * @param m1 Parameter 1 Mode
	 * @param i1 Parameter 1 Value
	 * @param size Size of Parameter 1
	 * @return the unsigned value from Parameter 1
	 */
	public int readU(int m1, int i1, int size) {
		return read(m1, i1, size)&maxValueU(size);
	}
	/**
	 * Read the signed value from Parameter 1
	 * @param m1 Parameter 1 Mode
	 * @param i1 Parameter 1 Value
	 * @param size Size of Parameter 1
	 * @return the signed value from Parameter 1
	 */
	public int read(int m1, int i1, int size) {
		if(m1 == Mem.TYPE_REGISTER)
			return readReg(i1);
		else if(m1 == Mem.TYPE_MEM)
			return readMem(i1, size);
		else if(m1 == Mem.TYPE_NODE)
			return i1;
		else if(m1 == Mem.TYPE_INT)
			return i1;
		else if(m1 == Mem.TYPE_DMEM)
			return this.readMem(this.readReg(i1), size);
		else
			try {
				throw new Exception("Can't read modifier "+m1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1;
	}
	/**
	 * Writes <code>v</code> into Parameter 1
	 * @param m1 Parameter 1 Mode
	 * @param i1 Parameter 1 Value
	 * @param v Value to put into Parameter 1
	 * @param size Size of Operation
	 */
	public void write(int m1, int i1, int v, int size) {
		if(m1 == Mem.TYPE_REGISTER)
			writeReg(i1, v);
		else if(m1 == Mem.TYPE_MEM){
			writeMem(i1, v, size);
		}else if(m1 == Mem.TYPE_DMEM) {
//			System.out.println(readReg(Mem.ADDR_SI));
			writeMem(readReg(i1), v, size);
		}	else
			try {
				throw new Exception("Can't write modifier "+m1);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * Reads <code>addr</code> from memory with the given <code>size</code>
	 * @param addr the address
	 * @param size the operation size
	 * @return the read value
	 */
	public int readMem(int addr,int size) {
		if(readReg(Mem.ADDR_DS) != 0) {
			int pos = (readReg(Mem.ADDR_DS)&0xFFFF) +addr;
			if(pos >= 0x000A000 && pos <= 0x000A000+320*200) {
				return 0;
			}else
				return 0;
		}else {
			if(size==Mem.SIZE_BYTE)
				return memory[addr];
			else if(size==Mem.SIZE_WORD)  {
				return ((((memory[addr+1]&0xFF)<<8)) +  (memory[addr+0]&0xFF));
			}else if(size==Mem.SIZE_DWORD)
				return (((memory[addr+3])<<24)+ (u(memory[addr+2])<<16) +(u(memory[addr+1])<<8) +  u(memory[addr+0]));	
			else
				return 0;
		}
	}
	/**
	 * Writes <code>v</code> into memory at <code>addr</code> with the given <code>size</code>
	 * @param addr the address
	 * @param v the value to write into addr
	 * @param size the operation size
	 */
	public void writeMem(int addr, int v, int size) {
		if(readReg(Mem.ADDR_DS) != 0) {
			int pos = (readReg(Mem.ADDR_DS)&0xFFFF)+(addr&0xFFFF);
			if(pos >= 0x000A000 && pos <= 0x000A000+320*200) {
				Bios.draw(pos-0x000A000, (byte)v);
			}else {
				System.err.println("DS: "+(readReg(Mem.ADDR_DS)&0xFFFF));
				System.err.println("Addr: "+pos+","+(addr&0xFFFF));
				System.err.println("ERROR: "+Integer.toHexString(pos));
			}
		}else {
			if(size==Mem.SIZE_BYTE) {
				memory[addr] = (byte) (v&0xFF);
			} else if(size==Mem.SIZE_WORD)  {
				memory[addr+1]	= (byte) (((v&0xFF00)>>8));
				memory[addr+0]	= (byte) (v&0x00FF);
			}else if(size==Mem.SIZE_DWORD) {
				memory[addr+3]	= (byte) ((v>>24)&0xFF);
				memory[addr+2]	= (byte) ((v>>16)&0xFF);
				memory[addr+1]	= (byte) ((v>>8)&0xFF);
				memory[addr+0]	= (byte) ((v)&0xFF);	
			}
		}
	}
	/*** Writes either a 0 or a 1 into a flag
	 * 
	 * @param flag the address of the flag
	 * @param v the value to put into the flag
	 */
	public void writeFlag(int flag, int v) {
		register[flag]	= (byte) (v&0x1);
	}
	/**
	 * Reads the flat at address <code>flag</code>
	 * @param flag the flag address
	 * @return the unsigned value read from the flag
	 */
	public int readFlag(int flag) {
		return u(register[flag]);
	}
	/**
	 * Unsignes Byte and casts it into int
	 * @param b byte value
	 * @return casted into unsigned int
	 */
	public static int u(byte b) { 
		return b&0xFF;
	}
	/**
	 * Writes <code>v</code> into {@link #register} at <code>reg</code>
	 * @param reg the address of the register
	 * @param v the value to write into the register at <code>reg</code>
	 */
	public void writeReg(int reg, int v) {
		if(reg%4==0) {
			register[reg]	= (byte) ((v>>24)&0xFF);
			register[reg+1]	= (byte) ((v>>16)&0xFF);
			register[reg+2]	= (byte) ((v>>8)&0xFF);
			register[reg+3]	= (byte) ((v>>0)&0xFF);
		}else if((reg-1)%4==0) {
			register[(reg-1)+2]	= (byte) ((v>>8)&0xFF);
			register[(reg-1)+3]	= (byte) ((v>>0)&0xFF);
		}else if((reg-2)%4==0) {
			register[(reg-2)+2]	= (byte) ((v>>0)&0xFF);
		}else if((reg-3)%4==0) {
			register[(reg-3)+3]	= (byte) ((v>>0)&0xFF);			
		} else
			try {
				throw new Exception("Don't know reg "+reg);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * Read the register with the address <code>reg</code>
	 * @param reg address of the register to write to
	 * @return the value read from the register
	 */
	public int readReg(int reg) {
		if(reg%4==0) {
			return (((register[reg])<<24)+(u(register[reg+1])<<16)+(u(register[reg+2])<<8)+u(register[reg+3]));
		}else if((reg-1)%4==0) {
			return (((register[(reg-1)+2])<<8) +  u(register[(reg-1)+3]));					
		}else if((reg-2)%4==0) {
			return ((register[(reg-2)+2]));					
		}else if((reg-3)%4==0) {
			return (register[(reg-3)+3]);		
		} else
			try {
				throw new Exception("Don't know reg "+reg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return 0;
	}
}
