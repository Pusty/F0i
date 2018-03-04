package pusty.f0i.parser;

/**
 * A Node that contains Macro Information
 */
public class MacroNode extends Node{

	/**
	 * Creates a macro node
	 * @param c the command
	 * @param d the data array
	 * @param l the label
	 */
	public MacroNode(String c, String[] d, String l) {
		super(c, d, l);
	}
	/**
	 * Parses the command into a readable format
	 */
	public String toString() {
		String ex = "";
		for(String da:this.temp)
			ex = ex+"; "+da;
		return getCommand()+": "+ex;
	}
}
