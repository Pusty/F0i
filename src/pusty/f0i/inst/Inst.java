package pusty.f0i.inst;

import pusty.f0i.interpreter.MemoryMap;
import static pusty.f0i.common.OpCodes.*;

/**
 * Instruction class, all instructions extend from this
 */
public abstract class Inst {
	/** 
	 * Map for all Instructions. Uses for skipping 100 compares each times by directly 
	 *  calling the instruction for the given operation code.
	 */
	static Inst[] instArray = new Inst[100];
	/** 
	 * Empty Constructor as {@link Inst} is only used to call instructions faster by
	 * putting them into a array and calling them by operation code
	 */	
	public Inst() { }
	/**
	 * Returns the pseudo operation code for this instruction. Pseudo Codes are listed in {@link pusty.f0i.common.OpCodes OpCodes}
	 * @return the pseudo operation code for this instruction
	 */
	public abstract int getOpCode();
	/**
	 * Returns the instruction name of this instruction
	 * @return the instruction name
	 */
	public abstract String getName();
	/** 
	 * Runs a instruction with the given arguments.
	 * m1 and i1 together are Parameter 1
	 * m2 and i2 together are Parameter 2
	 * size defines how this instruction is handled
	 * 
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
	 */
	public abstract int run(MemoryMap map, int cmd, int m1, int i1, int m2, int i2, int size,int line);
	/** 
	 * Runs a instruction with the given arguments.
	 * m1 and i1 together are Parameter 1
	 * m2 and i2 together are Parameter 2
	 * size defines how this instruction is handled
	 * 
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
	 */
	public static int runInst(MemoryMap map, int cmd, int m1, int i1, int m2, int i2, int size,int line) throws Exception {
		Inst in = instArray[cmd];
		if(in==null) {
			throw new Exception("Can't handle opcode "+cmd+" "+m1+" /"+i1+" "+m2+" /"+i2+" ("+size+") in line "+line);
		}else
			return in.run(map, cmd, m1, i1, m2, i2, size, line);
	}
	/** 
	 * Initializes instructions which appear within the given commands set
	 * @param commands array with parsed {@link pusty.f0i.parser.CommandNode CommandNodes}
	 */
	public static void initInstructions(Integer[] commands) {
		for(int a=0;a<commands.length/6;a++) {
			int i = commands[a*6];
			switch(i) {
				case ORG: if(instArray[i]==null) instArray[ORG] = new Inst094NOP(); break;
				case BITS: if(instArray[i]==null) instArray[BITS] = new Inst094NOP(); break;
				case CPU: if(instArray[i]==null) instArray[CPU] = new Inst094NOP(); break;
				case DEBUG: if(instArray[i]==null) instArray[DEBUG] = new Inst000DEBUG(); break;
				
				case ADD: if(instArray[i]==null) instArray[ADD] = new Inst005ADD(); break;
				case AND: if(instArray[i]==null) instArray[AND] = new Inst007AND(); break;
				case CALL: if(instArray[i]==null) instArray[CALL] = new Inst017CALL(); break;
				case CBW: if(instArray[i]==null) instArray[CBW] = new Inst018CBW(); break;
				case CMP: if(instArray[i]==null) instArray[CMP] = new Inst025CMP(); break;
				case CMPS: if(instArray[i]==null) instArray[CMPS] = new Inst026CMPS(); break;
				case DEC: if(instArray[i]==null) instArray[DEC] = new Inst032DEC(); break;
				case DIV: if(instArray[i]==null) instArray[DIV] = new Inst033DIV(); break;
				case HLT: if(instArray[i]==null) instArray[HLT] = new Inst037HLT(); break;
				case IN: if(instArray[i]==null) instArray[IN] = new Inst040IN(); break;
				case INC: if(instArray[i]==null) instArray[INC] = new Inst041INC(); break;
				case INT: if(instArray[i]==null) instArray[INT] = new Inst043INT(); break;
				case IRET: if(instArray[i]==null) instArray[IRET] = new Inst047IRET(); break;
				case JA: if(instArray[i]==null) instArray[JA] = new Inst048JA(); break;
				case JAE: if(instArray[i]==null) instArray[JAE] = new Inst049JAE(); break;
				case JB: if(instArray[i]==null) instArray[JB] = new Inst050JB(); break;
				case JBE: if(instArray[i]==null) instArray[JBE] = new Inst051JBE(); break;
				case JC: if(instArray[i]==null) instArray[JC] = new Inst052JC(); break;
				case JCXZ: if(instArray[i]==null) instArray[JCXZ] = new Inst053JCXZ(); break;
				case JECXZ: if(instArray[i]==null) instArray[JECXZ] = new Inst053JECXZ(); break;
				case JE: if(instArray[i]==null) instArray[JE] = new Inst054JE(); break;
				case JG: if(instArray[i]==null) instArray[JG] = new Inst055JG(); break;
				case JGE: if(instArray[i]==null) instArray[JGE] = new Inst056JGE(); break;
				case JL: if(instArray[i]==null) instArray[JL] = new Inst057JL(); break;
				case JLE: if(instArray[i]==null) instArray[JLE] = new Inst058JLE(); break;
				case JMP: if(instArray[i]==null) instArray[JMP] = new Inst059JMP(); break;
				case JNC: if(instArray[i]==null) instArray[JNC] = new Inst060JNC(); break;
				case JNE: if(instArray[i]==null) instArray[JNE] = new Inst061JNE(); break;
				case JNO: if(instArray[i]==null) instArray[JNO] = new Inst062JNO(); break;
				case JNS: if(instArray[i]==null) instArray[JNS] = new Inst063JNS(); break;
				case JO: if(instArray[i]==null) instArray[JO] = new Inst065JO(); break;
				case JS: if(instArray[i]==null) instArray[JS] = new Inst067JS(); break;
				case LEA: if(instArray[i]==null) instArray[LEA] = new Inst071LEA(); break;
				case LODS: if(instArray[i]==null) instArray[LODS] = new Inst081LODS(); break;
				case LOOP: if(instArray[i]==null) instArray[LOOP] = new Inst082LOOP(); break;
				case MOV: if(instArray[i]==null) instArray[MOV] = new Inst088MOV(); break;
				case MOVS: if(instArray[i]==null) instArray[MOVS] = new Inst089MOVS(); break;
				case MUL: if(instArray[i]==null) instArray[MUL] = new Inst092MUL(); break;
				case NEG: if(instArray[i]==null) instArray[NEG] = new Inst093NEG(); break;
				case NOP: if(instArray[i]==null) instArray[NOP] = new Inst094NOP(); break;
				case NOT: if(instArray[i]==null) instArray[NOT] = new Inst095NOT(); break;
				case OR: if(instArray[i]==null) instArray[OR] = new Inst096OR(); break;
				case OUT: if(instArray[i]==null) instArray[OUT] = new Inst097OUT(); break;
				case POP: if(instArray[i]==null) instArray[POP] = new Inst099POP(); break;
				case POPA: if(instArray[i]==null) instArray[POPA] = new Inst100POPA(); break;
				case POPAD: if(instArray[i]==null) instArray[POPAD] = new Inst100POPAD(); break;
				case PUSH: if(instArray[i]==null) instArray[PUSH] = new Inst102PUSH(); break;
				case PUSHA: if(instArray[i]==null) instArray[PUSHA] = new Inst103PUSHA(); break;
				case PUSHAD: if(instArray[i]==null) instArray[PUSHAD] = new Inst103PUSHAD(); break;
				case RET: if(instArray[i]==null) instArray[RET] = new Inst110RET(); break;
				case SHL: if(instArray[i]==null) instArray[SHL] = new Inst114SHL(); break;
				case SHR: if(instArray[i]==null) instArray[SHR] = new Inst137SHR(); break;
				case STOS: if(instArray[i]==null) instArray[STOS] = new Inst144STOS(); break;
				case SUB: if(instArray[i]==null) instArray[SUB] = new Inst146SUB(); break;
				case TEST: if(instArray[i]==null) instArray[TEST] = new Inst147TEST(); break;
				case XOR: if(instArray[i]==null) instArray[XOR] = new Inst154XOR(); break;
			}
		}
	}
}

