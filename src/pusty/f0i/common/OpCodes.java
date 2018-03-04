package pusty.f0i.common;

import pusty.f0i.parser.CommandNode;
import pusty.f0i.parser.MacroNode;
import pusty.f0i.parser.MemoryNode;

/**
 * Contains all pseudo machine codes and utility functions for them.
 */
public class OpCodes {
	/**
	 * @see pusty.f0i.inst.Inst005ADD Inst005ADD
	 */
	public static final int ADD = 1;
	/**
	 * @see pusty.f0i.inst.Inst007AND Inst007AND
	 */
	public static final int AND = 2;
	/**
	 * @see pusty.f0i.inst.Inst017CALL Inst017CALL
	 */
	public static final int CALL = 3;
	/**
	 * @see pusty.f0i.inst.Inst025CMP Inst025CMP
	 */
	public static final int CMP = 4;
	/**
	 * @see pusty.f0i.inst.Inst032DEC Inst032DEC
	 */
	public static final int DEC = 5;
	/**
	 * @see pusty.f0i.inst.Inst033DIV Inst033DIV
	 */
	public static final int DIV = 6;
	/**
	 * @see pusty.f0i.inst.Inst037HLT Inst037HLT
	 */
	public static final int HLT = 7;
	/**
	 * @see pusty.f0i.inst.Inst040IN Inst040IN
	 */
	public static final int IN = 10;
	/**
	 * @see pusty.f0i.inst.Inst047IRET Inst040IRET
	 */
	public static final int IRET = 11;
	/**
	 * @see pusty.f0i.inst.Inst048JA Inst048JA
	 */
	public static final int JA = 12;
	/**
	 * @see pusty.f0i.inst.Inst049JAE Inst049JAE
	 */
	public static final int JAE = 13;
	/**
	 * @see pusty.f0i.inst.Inst050JB Inst050JB
	 */
	public static final int JB = 14;
	/**
	 * @see pusty.f0i.inst.Inst051JBE Inst051JBE
	 */
	public static final int JBE = 15;
	/**
	 * @see pusty.f0i.inst.Inst054JE Inst054JE
	 */
	public static final int JE = 16;
	/**
	 * @see pusty.f0i.inst.Inst055JG Inst055JG
	 */
	public static final int JG = 17;
	/**
	 * @see pusty.f0i.inst.Inst056JGE Inst056JGE
	 */
	public static final int JGE = 18;
	/**
	 * @see pusty.f0i.inst.Inst057JL Inst057JL
	 */
	public static final int JL = 19;
	/**
	 * @see pusty.f0i.inst.Inst058JLE Inst058JLE
	 */
	public static final int JLE = 20;
	/**
	 * @see pusty.f0i.inst.Inst059JMP Inst059JMP
	 */
	public static final int JMP = 21;
	/**
	 * @see pusty.f0i.inst.Inst061JNE Inst061JNE
	 */
	public static final int JNE = 22;
//	public static final int LEA = 23;
	/**
	 * @see pusty.f0i.inst.Inst088MOV Inst088MOV
	 */
	public static final int MOV = 24;
	/**
	 * @see pusty.f0i.inst.Inst092MUL Inst092MUL
	 */
	public static final int MUL = 25;
	/**
	 * @see pusty.f0i.inst.Inst093NEG Inst093NEG
	 */	
	public static final int NEG = 26;
	/**
	 * @see pusty.f0i.inst.Inst094NOP Inst094NOP
	 */	
	public static final int NOP = 27;
	/**
	 * @see pusty.f0i.inst.Inst095NOT Inst095NOT
	 */	
	public static final int NOT = 28;
	/**
	 * @see pusty.f0i.inst.Inst096OR Inst096OR
	 */	
	public static final int OR = 29;
	/**
	 * @see pusty.f0i.inst.Inst097OUT Inst097OUT
	 */	
	public static final int OUT = 30;
	/**
	 * @see pusty.f0i.inst.Inst099POP Inst099POP
	 */	
	public static final int POP = 31;
	/**
	 * @see pusty.f0i.inst.Inst100POPA Inst100POPA
	 */	
	public static final int POPA = 32;
	/**
	 * @see pusty.f0i.inst.Inst102PUSH Inst102PUSH
	 */	
	public static final int PUSH = 33;
	/**
	 * @see pusty.f0i.inst.Inst103PUSHA Inst103PUSHA
	 */	
	public static final int PUSHA = 34;
	/**
	 * @see pusty.f0i.inst.Inst110RET Inst110RET
	 */	
	public static final int RET = 35;
	/**
	 * @see pusty.f0i.inst.Inst146SUB Inst146SUB
	 */	
	public static final int SUB = 36;
	/**
	 * @see pusty.f0i.inst.Inst154XOR Inst154XOR
	 */	
	public static final int XOR = 37;
	/**
	 * @see pusty.f0i.inst.Inst081LODS Inst081LODS
	 */	
	public static final int LODS = 38; //!
	/**
	 * @see pusty.f0i.inst.Inst043INT Inst043INT
	 */	
	public static final int INT = 39; //!
	/**
	 * @see pusty.f0i.inst.Inst147TEST Inst147TEST
	 */	
	public static final int TEST = 40; //!
	/**
	 * @see pusty.f0i.inst.Inst094NOP Inst094NOP
	 */	
	public static final int ORG = 42; //!
	/**
	 * @see pusty.f0i.inst.Inst094NOP Inst094NOP
	 */	
	public static final int BITS = 43; //!
	/**
	 * @see pusty.f0i.inst.Inst137SHR Inst137SHR
	 */	
	public static final int SHR = 44; //!
	/**
	 * @see pusty.f0i.inst.Inst114SHL Inst114SHL
	 */	
	public static final int SHL = 45; //!
	/**
	 * @see pusty.f0i.inst.Inst041INC Inst041INC
	 */	
	public static final int INC = 46;
	/**
	 * @see pusty.f0i.inst.Inst052JC Inst052JC
	 */	
	public static final int JC = 47;
	/**
	 * @see pusty.f0i.inst.Inst060JNC Inst060JNC
	 */	
	public static final int JNC = 48;
	/**
	 * @see pusty.f0i.inst.Inst053JCXZ Inst053JCXZ
	 */	
	public static final int JCXZ = 49;
	/**
	 * @see pusty.f0i.inst.Inst065JO Inst065JO
	 */	
	public static final int JO = 50;
	/**
	 * @see pusty.f0i.inst.Inst062JNO Inst062JNO
	 */	
	public static final int JNO = 51;
	/**
	 * @see pusty.f0i.inst.Inst067JS Inst067JS
	 */	
	public static final int JS = 52;
	/**
	 * @see pusty.f0i.inst.Inst063JNS Inst063JNS
	 */	
	public static final int JNS = 53;
	/**
	 * @see pusty.f0i.inst.Inst103PUSHAD Inst103PUSHAD
	 */	
	public static final int PUSHAD = 54;
	/**
	 * @see pusty.f0i.inst.Inst100POPAD Inst100POPAD
	 */	
	public static final int POPAD = 55;
	/**
	 * @see pusty.f0i.inst.Inst053JECXZ Inst053JECXZ
	 */	
	public static final int JECXZ = 56;
	/**
	 * @see pusty.f0i.inst.Inst094NOP Inst094NOP
	 */	
	public static final int CPU = 57;
	/**
	 * @see pusty.f0i.inst.Inst082LOOP Inst082LOOP
	 */
	public static final int LOOP = 58;
	/**
	 * @see pusty.f0i.inst.Inst144STOS Inst144STOS
	 */
	public static final int STOS = 59;
	/**
	 * @see pusty.f0i.inst.Inst089MOVS Inst089MOVS
	 */
	public static final int MOVS = 60;
	/**
	 * @see pusty.f0i.inst.Inst026CMPS Inst026CMPS
	 */
	public static final int CMPS = 61;
	/**
	 * @see pusty.f0i.inst.Inst018CBW Inst018CBW
	 */
	public static final int CBW = 62;
	/**
	 * @see pusty.f0i.inst.Inst000DEBUG Inst000DEBUG
	 */
	public static final int DEBUG = 63;
	/**
	 * @see pusty.f0i.inst.Inst071LEA Inst071LEA
	 */
	public static final int LEA = 64;
	
	
	//MEMORY NODES
	/** store byte */
	public static final int DB = 200;
	/** store word */
	public static final int DW = 201;
	/** include binary */
	public static final int INCBIN = 202;
	/** store dword */
	public static final int DD = 203;
	/** reserve words */
	public static final int RESW = 204;
	/** reserve bytes */
	public static final int RESB = 205;
	/** reserve dwords */
	public static final int RESD = 206;
	/** store constant */
	public static final int EQU = 207;
	
	//MACRO NODES
	/** include source file */
	public static final int INCLUDE = 400;
	/** repeat command */
	public static final int TIMES = 401;
	/** if not defined */
	public static final int IFNDEF = 402;
	/** if defined */
	public static final int IFDEF = 403;
	/** end if */
	public static final int ENDIF = 404;
	/** define macro */
	public static final int DEFINE = 405;
	/** print out compiler warning */
	public static final int WARNING = 406;
	/** assign constant */
	public static final int ASSIGN = 407;
	
	
	/** 
	 * Returns the corresponding pseudo machine code operation code for a given NASM Command
	 * @param c a NASM command
	 * @return a pseudo machine code operation code
	 * @throws Exception Unknown Operation Code
	 */
	public static int getOpCode(String c) throws Exception {
		switch(c.toLowerCase()) {
			case "%include":
				return OpCodes.INCLUDE;
			case "%ifndef":
				return OpCodes.IFNDEF;
			case "%ifdef":
				return OpCodes.IFDEF;
			case "%define":
				return OpCodes.DEFINE;
			case "%endif":
				return OpCodes.ENDIF;
			case "%assign":
				return OpCodes.ASSIGN;
			case "%warning":
				return OpCodes.WARNING;
			case "times":
				return OpCodes.TIMES;
			case "org":
				return OpCodes.ORG;
			case "cpu":
				return OpCodes.CPU;
			case "debug":
				return OpCodes.DEBUG;
			case "bits":
				return OpCodes.BITS;
			case "loop":
				return OpCodes.LOOP;
			case "jc":
				return OpCodes.JC;	
			case "jcxz":
				return OpCodes.JCXZ;
			case "cbw":
				return OpCodes.CBW;
			case "jecxz":
				return OpCodes.JECXZ;
			case "jnc":
				return OpCodes.JNC;	
			case "js":
				return OpCodes.JS;	
			case "jns":
				return OpCodes.JNS;
			case "jo":
				return OpCodes.JO;
			case "jno":
				return OpCodes.JNO;	
			case "pushad":
				return OpCodes.PUSHAD;	
			case "popad":
				return OpCodes.POPAD;	
			case "lodsb":
				return OpCodes.LODS;
			case "lodsw":
				return OpCodes.LODS;
			case "lodsd":
				return OpCodes.LODS;
			case "stosb":
				return OpCodes.STOS;
			case "stosw":
				return OpCodes.STOS;
			case "stosd":
				return OpCodes.STOS;
			case "movsb":
				return OpCodes.MOVS;
			case "movsw":
				return OpCodes.MOVS;
			case "movsd":
				return OpCodes.MOVS;
			case "cmpsb":
				return OpCodes.CMPS;
			case "cmpsw":
				return OpCodes.CMPS;
			case "cmpsd":
				return OpCodes.CMPS;
			case "int":
				return OpCodes.INT;
			case "shr":
				return OpCodes.SHR;
			case "shl":
				return OpCodes.SHL;
			case "test":
				return OpCodes.TEST;
			case "resw":
				return OpCodes.RESW;
			case "resb":
				return OpCodes.RESB;
			case "resd":
				return OpCodes.RESD;
			case "equ":
				return OpCodes.EQU;
			case "add":
				return OpCodes.ADD;
			case "and":
				return OpCodes.AND;
			case "call":
				return OpCodes.CALL;
			case "cmp":
				return OpCodes.CMP;
			case "inc":
				return OpCodes.INC;
			case "dec":
				return OpCodes.DEC;
			case "div":
				return OpCodes.DIV;
			case "hlt":
				return OpCodes.HLT;
			case "idiv":
				return OpCodes.DIV;
			case "in":
				return OpCodes.IN;
			case "imul":
				return OpCodes.MUL;
			case "iret":
				return OpCodes.IRET;
			case "lea":
				return OpCodes.LEA;
			case "mov":
				return OpCodes.MOV;
			case "mul":
				return OpCodes.MUL;
			case "neg":
				return OpCodes.NEG;
			case "nop":
				return OpCodes.NOP;
			case "not":
				return OpCodes.NOT;
			case "or":
				return OpCodes.OR;
			case "out":
				return OpCodes.OUT;
			case "pop":
				return OpCodes.POP;
			case "popa":
				return OpCodes.POPA;
			case "push":
				return OpCodes.PUSH;
			case "pusha":
				return OpCodes.PUSHA;
			case "ret":
				return OpCodes.RET;
			case "sub":
				return OpCodes.SUB;
			case "xor":
				return OpCodes.XOR;
			case "jmp":
				return OpCodes.JMP;
			case "ja":
				return OpCodes.JA;
			case "jnbe":
				return OpCodes.JA;
			case "jae":
				return OpCodes.JAE;
			case "jnb":
				return OpCodes.JAE;
			case "jb":
				return OpCodes.JB;
			case "jnae":
				return OpCodes.JB;
			case "jbe":
				return OpCodes.JBE;
			case "jna":
				return OpCodes.JBE;
			case "je":
				return OpCodes.JE;
			case "jz":
				return OpCodes.JE;
			case "jg":
				return OpCodes.JG;
			case "jnle":
				return OpCodes.JG;
			case "jge":
				return OpCodes.JGE;
			case "jnl":
				return OpCodes.JGE;
			case "jl":
				return OpCodes.JL;
			case "jnge":
				return OpCodes.JL;
			case "jle":
				return OpCodes.JLE;
			case "jng":
				return OpCodes.JLE;
			case "jne":
				return OpCodes.JNE;
			case "jnz":
				return OpCodes.JNE;
				
			case "db":
				return DB;
			case "dw":
				return DW;
			case "dd":
				return DD;
			case "incbin":
				return INCBIN;
			default:
				throw new Exception("Unknown Opcode for "+c);
		}
	}
	/**
	 * Checks if the pseudo operation code for a given NASM command is a command code
	 * @param cmd a NASM command
	 * @return whether the pseudo operation code for this NASM command is a command code
	 * @see CommandNode
	 */
	public static boolean isCommandNode(String cmd) {
		try {
			return getOpCode(cmd) < 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Checks if the pseudo operation code for a given NASM command is a memory code
	 * @param cmd a NASM command
	 * @return whether the pseudo operation code for this NASM command is a memory code
	 * @see MemoryNode
	 */
	public static boolean isMemoryNode(String cmd) {
		try {
			return getOpCode(cmd) >= 200 && getOpCode(cmd) < 400;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Checks if the pseudo operation code for a given NASM command is a macro code
	 * @param cmd a NASM command
	 * @return whether the pseudo operation code for this NASM command is a macro code
	 * @see MacroNode
	 */
	public static boolean isMacroNode(String cmd) {
		try {
			return getOpCode(cmd) >= 400;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
