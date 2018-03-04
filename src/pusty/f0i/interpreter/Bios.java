package pusty.f0i.interpreter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import pusty.f0i.F0iWindowedTick;
import pusty.f0i.common.Mem;

/**
 * The emulated BIOS
 */
public class Bios {
	/**
	 * Whether to output into a canvas or the console
	 */
	private static boolean windowed = false;
	/**
	 * Returns if the output should be a canvas or the console
	 * @return should output to canvas?
	 */
	public static boolean isWindowed() {
		return windowed;
	}
	/**
	 * Sets the windowed mode to <code>b</code>
	 * @param b the mode to set windowed to
	 */
	public static void setWindowed(boolean b) {
		windowed = b;
	}
	
	/***
	 *  Emulates a real BIOS interrupt
	 *  <p>
	 *  VERY BUGGED AT THE MOMENT
	 *  
	 * @param mem the memory map
	 * @param interrupt the interrupt that got called
	 */
	public static void emulateBios(MemoryMap mem, int interrupt) {
		if(interrupt == 0x10) { //Video Services
			int ah = mem.readReg(Mem.ADDR_AH);
			if(ah == 0x00) { //set video mode
				int al = mem.readReg(Mem.ADDR_AL);
				if(Bios.isWindowed()) {
					if(al == 0x13) {
						graphic = true;					
						FileHandle pal = Gdx.files.internal("resources/tmp.png");
						pallete = new Pixmap(pal);
					}
				}else
					System.err.println("Setting Video Mode isn't implemented for CommandlineMode");
			}else if(ah == 0x02) { //set cursor position
				System.err.println("Setting Cursor Position");
			}else if(ah == 0x0A) { //write character at cursor position
				System.err.println("Writing at cursor position is not implemented yet");
			}else if(ah == 0x0C) { //write pixel at position
				int x = mem.readReg(Mem.ADDR_CX);
				int y = mem.readReg(Mem.ADDR_DX);
				int c = mem.readReg(Mem.ADDR_AL);
				F0iWindowedTick.map.drawPixel(x, y, pallete.getPixel(c%16, c/16));
//				System.err.println("Writing Pixel is not implemented yet");
			}else if(ah == 0x0D) { //read pixel at position
//				int x = mem.readReg(Mem.ADDR_CX);
//				int y = mem.readReg(Mem.ADDR_DX);
//				int c = mem.readReg(Mem.ADDR_AL);
//				F0iWindowedTick.map.getPixel(x, y)
//				F0iWindowedTick.map.drawPixel(x, y, pallete.getPixel(c%16, c/16));
				System.err.println("Reading Pixel is not implemented yet");
			}else if(ah == 0x0E) { //write character in TTY mode and move cursor by 1
//				  AH=0Eh   Schreiben und Cursor weiterbewegen
//				  AL     zu schreibendes Zeichen im ASCII-Code
//				  BH     Seitennummer
//				  BL     Farbe (nur Grafikmodi)
				if(F0iWindowedTick.str != null)
					F0iWindowedTick.str = F0iWindowedTick.str + (char)mem.readReg(Mem.ADDR_AL);
				if(!Bios.isWindowed())
				System.out.print((char)mem.readReg(Mem.ADDR_AL));
			}
			int ax = mem.readReg(Mem.ADDR_AX);
			if(ax == 0x1010) { //change pallete entry
				int index = mem.readReg(Mem.ADDR_BX);
				int red = mem.readReg(Mem.ADDR_DH); //0-63
				int green = mem.readReg(Mem.ADDR_CH);
				int blue = mem.readReg(Mem.ADDR_CL);
				Color c = new Color(red/63f,green/63f,blue/63f,1f);
				pallete.setColor(c);
				pallete.drawPixel(index%16,index/16);
				pallete.setColor(Color.BLACK);
//				System.out.println(index+", "+red+":"+green+":"+blue);
			}
		}else if(interrupt == 0x16) { //Keyboard functions
			int ah = mem.readReg(Mem.ADDR_AH);
			if(ah == 00) { //read key 
				if(!Bios.isWindowed()) { //meh code
					System.err.println("Keyboard functions not supported in console mode");
				}else {
					F0iWindowedTick.waitForKey = true;
				}
			}
		}else if(interrupt == 0x15) { //?
			int ah = mem.readReg(Mem.ADDR_AH);
			if(ah == 0x86) { //sleep
				if(!Bios.isWindowed()) { //meh code
					try {
						Thread.sleep(mem.readReg(Mem.ADDR_DX));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
			
				}
			}
		}else
			System.err.println("Error executing interrupt "+interrupt);
	}

 //	static final Color[] colors = {
//		new Color(0,0,0,1),new Color(0,0,168,1),new Color(0,168,0,1),new Color(0,168,168,1),
//		new Color(168/255f,0,0,1),new Color(168,0,168,1),new Color(168,87,0,1),new Color(168,168,168,1),
//		new Color(87/255f,87,87,1),new Color(87,87,255,1),new Color(87,255,87,1),new Color(87,255,255,1),
//		new Color(255/255f,87,87,1),new Color(255,87,255,1),new Color(255,255,87,1),new Color(255,255,255,1)
//		
//	};
	/**
	 * The current mode of emulation (text or graphic mode)
	 */
	private static boolean graphic = false;
	/**
	 * The {@link Pixmap} which contains the pallete
	 */
	private static Pixmap pallete = null;
	/**
	 * The last pressed or released scancode
	 */	
	public static int scancode = 0; //last key pressed/realeased
	

	/*** 
	 * Draws a pixel on {@link F0iWindowedTick#map}
	 * 
	 * @param i index of pixel. (must be less than 320*200)
	 * @param v index of color to draw. Colors are defined in {@link #pallete}
	 */
	public static void draw(int i, byte v) {
		if(!graphic) {
			System.err.println("Can't draw in textmode");
		}
		if(!Bios.isWindowed()) {
		}else {
			//320*200 256 color resolution
//			colors[v&0xFF].toIntBits()
//			int pos = v&0xFF;
			int pos = v;
			F0iWindowedTick.map.drawPixel(i%320, i/320, pallete.getPixel(pos%16, pos/16));
//			if(pallete.getPixel(pos%16, pos/16)!=0)
//			System.out.println(pos%16+": "+pos/16+", "+pallete.getPixel(pos%16, pos/16));
		}
	}
	/**
	 * Read IN emulation
	 * @param read the address to read
	 * @return the value this in returns
	 */
	public static int readIn(int read) {
		if(read==0x60) {
//			System.err.println(scancode);
			return scancode;
		}else
			System.err.println("ERROR READING AT "+read);
		return 0;
	}
	/**
	 * Write OUT emulation
	 * @param read the address to write to
	 * @param read2 the value to write
	 */
	public static void writeOut(int read, int read2) {
		System.err.println("Output "+read2+" at "+read);
	}
}
