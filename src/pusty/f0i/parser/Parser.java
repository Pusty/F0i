package pusty.f0i.parser;

import java.util.ArrayList;
import java.util.HashMap;

import pusty.f0i.FileChecker;
import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Parses NASM code into pseudo machine code.
 * Is also able to load and save pseudo machine code binaries.
 */
public class Parser {
	
	/**
	 * Handle of the main <code>NASM File</code>
	 */
	private String mainFile;
	/**
	 * <code>ArrayList</code> which contains all {@link CommandNode CommandNodes} of this <code>Parser</code>
	 */	
	private ArrayList<CommandNode> cmdNode;
	/**
	 * <code>ArrayList</code> which contains all {@link MemoryNode MemoryNodes} of this <code>Parser</code>
	 */	
	private ArrayList<MemoryNode> memNode;
	/**
	 * <code>String[]</code> which contains the NASM file input before parsing and the pseudo machine code after parsing
	 */	
	private String[] lines;
	/**
	 * The current index within {@link #lines}
	 */	
	private int lineIndex;
	/**
	 * The current index within {@link #cmdNode}
	 */	
	private int cmdIndex;
	/**
	 * The current index within {@link #memNode}
	 */	
	private int memIndex;
	/**
	 * <code>true</code> if finished parsing, <code>false</code> if not
	 */	
	private boolean changed;
	/**
	 * <code>true</code> if started parsing, <code>false</code> if not
	 */	
	private boolean started = false;
	
	/** A Parser instance. Used to parse in single steps
	 * @see #tickParser()
	 * 
	 * @param fileHandle main file handle
	 */
	public Parser(String fileHandle) {
		mainFile = fileHandle;
	}
	
	/**
	 * Returns the {@link #mainFile} without unnecessary directories and extensions
	 * @return returns {@link #mainFile} without parent directory and without extension
	 */
	public String getFileName() {
		FileHandle file = readFile(mainFile);
		return file.nameWithoutExtension();
	}
	
	/**
	 * Returns the parsed content
	 * @return parsed lines (null if Parser isn't finished parsing yet)
	 */
	public String[] getParsed() {
		if(changed)
			return lines;
		return null;
	}
	

	/** 
	 * Returns the max value to display {@link #curValue} in percent
	 * @param mode the mode to check
	 * @return maximal value for the given <code>mode</code>; zero if mode not found
	 */
	public int maxValue(int mode) {
		if(mode==0)
			return lines.length;
		if(mode==1)
			return cmdNode.size();
		if(mode==2)
			return memNode.size();
		return 0;
	}
	/**
	 * Returns the current progress for the given <code>mode</code>
	 * @param mode the mode to check
	 * @return current progress for the given <code>mode</code>; zero if mode not found
	 * @see #maxValue
	 */
	public int curValue(int mode) {
		if(mode==0)
			return lineIndex;
		if(mode==1)
			return cmdIndex;
		if(mode==2)
			return memIndex;
		return 0;
	}
	
	/**
	 * Initializes this <code>Parser</code> by starting to read the {@link #mainFile} 
	 * and creating the {@link Node} <code> Lists </code> 
	 * 
	 * @throws Exception something went wrong
	 */
	public void initParsing() throws Exception {
		FileHandle file = readFile(mainFile);
		String content = file.readString();
		lines = FileChecker.splitNonRegex(content, "\n");
		lineIndex = 0;
		cmdIndex = 0;
		memIndex = 0;
		changed = false;
		started = true;
		cmdNode = new ArrayList<CommandNode>();
		memNode = new ArrayList<MemoryNode>();
			
	// while tickParser != -1 tickParser then preCompileAfter!
	//	preCompileAfter(cmdNode, memNode);
		
	}
	/** Returns if the parser is loaded
	 * @return returns if the parser is initiated
	 */
	public boolean isLoaded() {
		return started;
	}
	/*** Ticks the <code>Parser</code> by one.
	 * Either parses a Line ({@link #parseLine})
	 * or preCompiles a {@link Node} ({@link #preCompileNode})
	 * 
	 * @return current mode or -1 if it's done
	 * @throws Exception something went wrong
	 */
	public int tickParser() throws Exception {
		if(lineIndex<lines.length && !changed) {
			parseLine(lines[lineIndex], cmdNode, memNode);
			lineIndex++;
			return 0;
		}else if(cmdIndex<cmdNode.size() && !changed) {
			preCompileNode(cmdNode.get(cmdIndex), cmdNode, memNode, cmdIndex);
			cmdIndex++;
			return 1;
		}else if(memIndex<memNode.size() && !changed) {
			preCompileNode(memNode.get(memIndex), cmdNode, memNode, memIndex);
			memIndex++;
			return 2;
		}else if(!changed) {
			changed = true;
			ArrayList<String> list = preCompileAfter(cmdNode, memNode);
			String text = "";
			for(String s:list) 
				text = text + s + "\n"; 
			lines = FileChecker.splitNonRegex(text, "\n");
			return -1;
		}
		return -1;
	}
	

	/**
	 * Whether to load files internal or external
	 * @see #readFile(String)
	 */
	private static boolean internalMode = false;
	/**
	 * @param b true = load internal files; false = load external files
	 */
	public static void setInternal(boolean b) {
		internalMode = b;
	}
	/** Returns the correct {@link FileHandle} for the given path (in internal or external mode)
	 * @param path the file handle path
	 * @return the correct {@link FileHandle}
	 */
	public static FileHandle readFile(String path) {
		if(internalMode)
			return Gdx.files.internal(path);
		else
			return Gdx.files.local(path);
	}
	/** Reads a binary file
	 * @param bin the binary pseudo machine code file
	 * @return returns a pseudo machine code list from the file
	 */
	public static String[] readBinary(String bin) {
		FileHandle file = readFile(bin);
		return FileChecker.splitNonRegex(file.readString(), "\n");
	}
	/** Writes a binary file
	 * @param fi the file to write to
	 */
	public static void writeBinary(String fi, Parser parser) {
		FileHandle file = readFile(fi);
		String[] p = parser.getParsed();
		String o = "";
		for(int i=0;i<p.length;i++)
			o = o + p[i] + "\n";
		file.writeString(o, false);
	}
	
	/** Parse a file filled with NASM code into pseudo opcodes
	 * @param file Path to file (relative)
	 * @return a correctly parsed {@link String String[]}
	 */
	public static String[] parse(String file) {
		String[] ar = null;
		try {
		ArrayList<String> list = parseFile(file);
		String text = "";
		for(String s:list) 
			text = text + s + "\n"; 
		ar = FileChecker.splitNonRegex(text, "\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ar;
	}
	
	
	/**
	 * the {@link HashMap} which contains all <code>Macros</code> and their <code>Values</code>
	 */
	private static HashMap<String, Integer> macroHash = new HashMap<String, Integer>();
	/**
	 * stops reading {@link Node Nodes} when hitting specific {@link MacroNode MacroNodes} and starts reading again
	 * at <code>%endif</code>
	 * @see #parseLine
	 */
	private static boolean macroPause = false;
	/*** Parses a file and its includes into a arraylist
	 * 
	 * @param f relative file name
	 * @return the array list to parse to
	 * @throws Exception something went wrong
	 */
	public static ArrayList<String> parseFile(String f) throws Exception {
		ArrayList<CommandNode> cmdNode = new ArrayList<CommandNode>();
		ArrayList<MemoryNode> memNode = new ArrayList<MemoryNode>();
		parseFile(f,cmdNode,memNode);
		return preCompile(cmdNode, memNode);
	}
	/** Precompiles command and memory nodes
	 * 
	 * @param cmdNode command nodes to precompile
	 * @param memNode memory nodes to precompile
	 * @return the finished parsed pseudo machine code
	 */
	private static ArrayList<String> preCompile(ArrayList<CommandNode> cmdNode, ArrayList<MemoryNode> memNode) {
		for(int b=0;b<cmdNode.size();b++)
			preCompileNode(cmdNode.get(b), cmdNode, memNode, b);
		for(int b=0;b<memNode.size();b++)
			preCompileNode(memNode.get(b), cmdNode, memNode, b);		
		return preCompileAfter(cmdNode, memNode);
	}
	/** Forms precompiled nodes into a string
	 * 
	 * @param cmdNode command nodes to convert to string
	 * @param memNode memory nodes to convert to string
	 * @return the finished parsed pseudo machine code
	 */
	private static ArrayList<String> preCompileAfter(ArrayList<CommandNode> cmdNode, ArrayList<MemoryNode> memNode) {
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("#cmd:");
		for(CommandNode c:cmdNode)
			stringList.add(c.toString());
		stringList.add("#mem:");
		for(MemoryNode m:memNode)
			stringList.add(m.toString());	
		return stringList;
	}
	/** Precompiles a specific node
	 * 
	 * @param n Node to precompile
	 * @param cmdNode command node list
	 * @param memNode memory node list
	 * @param b current index
	 */
	private static void preCompileNode(Node n, ArrayList<CommandNode> cmdNode, ArrayList<MemoryNode> memNode, int b) {
		n.preCompile(cmdNode, memNode, b);
	}	
	/*** Parses a file into all its {@link Node Nodes}
	 * 
	 * @param fileName Path to the file that should get parsed
	 * @param cmdNode the list to add the {@link CommandNode CommandNodes} to
	 * @param memNode the list to add the {@link MemoryNode MemoryNodes} to
	 * @throws Exception stuff went wrong
	 */
	public static void parseFile(String fileName, ArrayList<CommandNode> cmdNode, ArrayList<MemoryNode> memNode) throws Exception {
		FileHandle file = readFile(fileName);
		String content = file.readString();
		String[] lines = FileChecker.splitNonRegex(content, "\n");
		if(cmdNode==null)
			cmdNode = new ArrayList<CommandNode>();
		if(memNode==null)
			memNode = new ArrayList<MemoryNode>();
		for(int a=0;a<lines.length;a++) {
			parseLine(lines[a], cmdNode, memNode);
		}
	}
	/*** Parses a line into a {@link Node} and adds it to the correct {@link ArrayList}
	 * 
	 * @param cmdNode the list to add the {@link CommandNode CommandNodes} to
	 * @param memNode the list to add the {@link MemoryNode MemoryNodes} to
	 * @param line line to get parsed
	 * @throws Exception stuff went wrong
	 */
	private static void parseLine(String line, ArrayList<CommandNode> cmdNode, ArrayList<MemoryNode> memNode) throws Exception {
		String mLabel = mainLabel;
		if(macroPause && !line.trim().startsWith("%endif")) return;
		macroPause = false;
		Node n = parseCommand(line);
		if(mainLabel != null && mLabel != null && !mLabel.equalsIgnoreCase(mainLabel))
			n = new CommandNode("nop", new String[0], mLabel);
		if(n==null) return;
		if(n instanceof CommandNode) cmdNode.add((CommandNode) n);
		if(n instanceof MemoryNode) memNode.add((MemoryNode) n);
		if(n instanceof MacroNode) {
			String l = n.temp[0];
			if(n.getCommand() == OpCodes.TIMES) {
				String[] vList = new String[1];
				vList[0] = l;
				try {
					String cmd = getCommand(l);
					if(cmd != null) {
						vList[0] = vList[0].substring(0, vList[0].indexOf(cmd));
						Mem[] mems = Mem.toMem(vList, null, null, -1);
						int im = mems[0].getAddr();
						try {
						for(int i=0;i<im;i++) {
							Node n2 = parseCommand(l.substring(l.indexOf(cmd), l.length()));
							if(i==0)
								n2.setLabel(n.getLabel());
							if(n2==null) continue;
							if(n2 instanceof CommandNode) cmdNode.add((CommandNode) n2);
							if(n2 instanceof MemoryNode) memNode.add((MemoryNode) n2);
						}
						}catch(Exception e){
							e.printStackTrace();
						}
							
					}
				}catch(Exception e) { }
			}else if(n.getCommand() == OpCodes.INCLUDE) {
				l = FileChecker.replaceAll(l, '"'+"", "");
				l = FileChecker.replaceAll(l, "'", "");
				parseFile("asm/"+l, cmdNode, memNode);
			}else if(n.getCommand() == OpCodes.IFNDEF) {
				if(macroHash.containsKey(l))
					macroPause = true;
			}else if(n.getCommand() == OpCodes.IFDEF) {
				if(!macroHash.containsKey(l))
					macroPause = true;
			}else if(n.getCommand() == OpCodes.DEFINE) {
				macroHash.put(l, 1);
			}else if(n.getCommand() == OpCodes.ENDIF) {
				macroPause = false;
			}else if(n.getCommand() == OpCodes.ASSIGN) {
			}else if(n.getCommand() == OpCodes.WARNING) {
			}else {
				throw new Exception("Don't know what to do with maco node "+l+", "+n);
			}
		}
	}
	
	/***Trims a command line to a usable format. It removes unnecessary tabs, whitespace, comments and makes strings to single chars (also optimized for macro parsing)
	 * @param line the line to trim
	 * @return the trimmed line
	 */
	public static String trimString(String line) {
		line = line.trim();
		StringBuilder tline = new StringBuilder("");
		int textMode = 0;
		boolean trim = false;
		String temp = "";
		if(line.equalsIgnoreCase("")) return "";
		boolean macro = line.charAt(0)=='%';
		for(int c=0;c<line.length();c++) {
			if((textMode == 0 || textMode == 2) && line.charAt(c)=='"') {
				if(macro)
					tline.append('"');
				else {
					textMode = textMode==0?2:0;
					if(textMode == 0)
						tline.deleteCharAt(tline.length()-1);
				}
			}else if((textMode == 0 || textMode == 1) && line.charAt(c) == "'".charAt(0)) {
				if(macro)
					tline.append('"');
				else {
					textMode = textMode==0?1:0;
					if(textMode == 0)
						tline.deleteCharAt(tline.length()-1);
				}
			}else if(textMode != 0) {
				tline.append("'"+line.charAt(c)+"',");
			}else if(textMode == 0 && line.charAt(c) == ';')
				break;
			else if(textMode == 0 && (line.charAt(c) == ' ' || line.charAt(c) == '\t')) {
				if(trim == false) {
					tline.append(" ");
					temp = "";
					trim = true;
				}
			}else {
				trim = false;
				tline.append(line.charAt(c));
				temp = temp + line.charAt(c);
				if(temp.equalsIgnoreCase("incbin"))
					macro = true;
			}
			
		}
		return tline.toString();
	}
	
	/*** Gets the label of the given <code>line</code> (if it contains one) by trimming it and removing the <code>Command</code>
	 *  and the <code>Data</code> from the <code>String</code>
	 * 
	 * @param line the line which to check
	 * @return the label. <code>null</code> if no <code>Label</code> was found
	 * @throws Exception throws when more than one <code>Label</code> was found
	 */
	public static String getLabel(String line) throws Exception {
		if(line.equalsIgnoreCase("")) return null;
		String[] st = FileChecker.splitNonRegex(line, " ");
		String labelFound = null;
//		if(line.charAt(0) == '%') return null;
		for(String s:st) {
			if(s.equalsIgnoreCase("")) continue;
			try {
				OpCodes.getOpCode(s);
				return labelFound;
			}catch(Exception e) {
				s = FileChecker.replaceAll(s, ":", "");
				if(Mem.getTypeInt(s)!= Integer.MIN_VALUE)
					throw new Exception("Expected label not number: "+s+" (previous label="+labelFound+") L= "+line);
				if(labelFound != null)
					throw new Exception("Expected a command: "+s+" (previous label="+labelFound+")");
				labelFound = s;
			}
		}
		return labelFound;
	}
	
	/*** Gets the command of the given <code>line</code> (if it contains one) by trimming it and removing the <code>Label</code>
	 *  and the <code>Data</code> from the <code>String</code>
	 * 
	 * @param line the line which to check
	 * @return the command. <code>null</code> if no <code>Command</code> was found
	 * @throws Exception throws when more than one <code>Command</code> was found
	 */
	public static String getCommand(String line) throws Exception {
		if(line.equalsIgnoreCase("")) return null;
		String[] st = FileChecker.splitNonRegex(line, " ");
		String cmdFound = null;
		for(String s:st) {
			if(s.equalsIgnoreCase("")) continue;
			try {
				OpCodes.getOpCode(s);
				if(cmdFound != null)
					throw new Exception("Expected arguments not another command: "+s+" (previous command="+cmdFound+")");
				cmdFound = s;
			}catch(Exception e) { }
		}
		return cmdFound;
	}
	/*** Gets the data of the given <code>line</code> (if it contains <code>data</code>) by trimming it and removing the <code>Label</code>
	 *  and the <code>Command</code> from the <code>String</code>
	 * 
	 * @param line the line which to check
	 * @return an {@link ArrayList} with the <code>data</code> of the <code>line</code>. Returns a <code>ArrayList</code> with the size zero when no data is found.
	 * @throws Exception if more than one command or more than one label was found
	 */
	public static ArrayList<String> getData(String line) throws Exception {
		ArrayList<String> lb = new ArrayList<String>();
		if(line.equalsIgnoreCase("")) return null;
 		String label = getLabel(line);
 		String command = getCommand(line);
 		if(label != null) {
	 		line = line.substring(label.length(), line.length());
	 		if(line.charAt(0) == ':')
	 			line = line.substring(1, line.length());
	 		line = line.trim();
 		}
 		if(command != null) {
	 		line = line.substring(command.length(), line.length());
	 		line = line.trim();
 		}
 		if(line.equalsIgnoreCase("")) return null;
 		String[] data = FileChecker.splitNonRegex(line, ",");
 		for(String d:data)
 			lb.add(d.trim());
		return lb;
	}
	/**
	 * the last label that appeared in {@link #parseCommand}
	 */
	private static String mainLabel = null;
	/***
	 * Parses a line into a {@link Node} by {@link #trimString trimming} and splitting it 
	 * into a {@link #getLabel label} (if it contains one), a {@link #getCommand command} (if it contains one}
	 * and the necessary {@link #getData data}
	 * 
	 * @param line command line to parse
	 * @return either a {@link CommandNode}, a {@link MemoryNode},
	 *  a {@link MacroNode} or zero if the <code>Node</code> couldn't be parsed
	 * @throws Exception couldn't handle the command in <code>line</code>
	 */
	public static Node parseCommand(String line) throws Exception {
		line = trimString(line);
		if(line.equalsIgnoreCase("")) return null;
		Node node = null;
		String label = getLabel(line);
		if(label != null) {
			mainLabel = label;
		}
		String cmd = getCommand(line);
		String[] data = null;
		ArrayList<String> d = getData(line);
		if(d != null)
		data = d.toArray(new String[d.size()]);

		if(cmd != null) {
//			System.out.println(mainLabel+" "+cmd+" "+d);
			if(OpCodes.isMemoryNode(cmd)) {
				node = new MemoryNode(cmd, data, mainLabel);
			}else if(OpCodes.isCommandNode(cmd))
				node = new CommandNode(cmd, data, mainLabel);
			else if(OpCodes.isMacroNode(cmd)) {
				String[] str = new String[1];
				if(getLabel(line)!=null) {
					line = line.substring(mainLabel.length(), line.length());
					line = line.trim();
				}
				line = line.substring(cmd.length(), line.length());
				line = line.trim();
				str[0] = line;
				node = new MacroNode(cmd, str, mainLabel);
			}
			else
				throw new Exception("Can't handle command "+cmd);
			mainLabel = null;
		}
		return node;
	}
}
