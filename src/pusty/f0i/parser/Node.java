package pusty.f0i.parser;

import java.util.ArrayList;

import pusty.f0i.common.Mem;
import pusty.f0i.common.OpCodes;

/**
 * A Node that contains a command, a label and the corresponding 
 */
public class Node {
//	static final int memoryStart = 0xFFFF;
//	static int memoryPosition = memoryStart;
	/**
	 * The pseudo machine code for the command in this <code>Node</code>
	 */
	private int command;
	/**
	 * The temporary <code>String</code> data
	 */
	public String[] temp;
	/**
	 * The compiled memory data
	 */
	private Mem[] data;
	/**
	 * The label of this <code>Node</code>
	 */
	private String label;
	/**
	 * The command of this <code>Node</code> as a <code>String</code>
	 */
	protected String cmd;
	/**
	 * Creates a node
	 * @param c the command
	 * @param d the data array
	 * @param l the label
	 */
	public Node(String c, String[] d, String l) {
		try {
			command = OpCodes.getOpCode(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		label = l;
		temp = d;
		cmd = c;
	}
	/**
	 * Returns the compiled memory references of this <code>Node</code>
	 * @return the compiled memory references of this <code>Node</code>
	 */
	public Mem[] getData() {
		return data;
	}
	/**
	 * Returns the pseudo machine code
	 * @return the pseudo machine code
	 */
	public int getCommand() {
		return command;
	}
	/**
	 * Returns the label
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * Sets the label
	 * @param l new label
	 */
	public void setLabel(String l) {
		label = l;
	}
	/**
	 * Has this <code>Node</code> a label
	 * @return if this label exists
	 */
	public boolean hasLabel() {
		return label!=null;
	}
	/**
	 * Returns the operation size of this node
	 * @return the operation size of this node
	 */
	public int getOperationSize() {
		return Mem.SIZE_BYTE;
	}
	/**
	 * Precompiles this node and puts the results into data
	 * @param list the {@link CommandNode} {@link ArrayList}
	 * @param mlist the {@link MemoryNode} <code>ArrayList</code>
	 * @param line the current line within the correct node list (depends on this node type)
	 */
	public void preCompile(ArrayList<CommandNode> list,ArrayList<MemoryNode> mlist, int line) {
		try {
			data = Mem.toMem(temp, list, mlist, line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
