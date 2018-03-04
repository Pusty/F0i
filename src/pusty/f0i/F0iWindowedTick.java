package pusty.f0i;


import pusty.f0i.common.Mem;
import pusty.f0i.interpreter.Bios;
import pusty.f0i.interpreter.Interpreter;
import pusty.f0i.parser.Parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/** Render and Action Class for Windowed F0i Emulation
 */
public class F0iWindowedTick implements Screen, InputProcessor  {
	private F0iWindowed f0;
	public F0iWindowedTick(F0iWindowed f0){
		this.f0 = f0;
	}	
	/** Converts libGDX scancodes to x86 BIOS scancodes
	 * @param old libGDX scancode
	 * @return x86 BIOS scancode
	 */
	public static int newKeycode(int old) {
		switch(old) {
		case 131:
			return 1;
		case 8:
			return 2;
		case 9:
			return 3;
		case 10:
			return 4;
		case 11:
			return 5;
		case 12:
			return 6;
		case 13:
			return 7;
		case 14:
			return 8;
		case 15:
			return 9;
		case 16:
			return 10;
		case 7:
			return 11;
		case 45:
			return 16;
		case 51:
			return 17;
		case 33:
			return 18;
		case 46:
			return 19;
		case 48:
			return 20;
		case 54:
			return 21;
		case 49:
			return 22;
		case 37:
			return 23;
		case 43:
			return 24;
		case 44:
			return 25;
			
		case 29:
			return 30;
		case 47:
			return 31;
		case 32:
			return 32;
		case 34:
			return 33;
		case 35:
			return 34;
		case 36:
			return 35;
		case 38:
			return 36;
		case 39:
			return 37;
		case 40:
			return 38;
			
		case 53:
			return 44;
		case 52:
			return 45;
		case 31:
			return 46;
		case 50:
			return 47;
		case 30:
			return 48;
		case 41:
			return 49;
		case 42:
			return 50;
			
		case 62:
			return 57;
		case 129:
			return 29;
		}
		return 0;	
	}
	@Override
	public boolean keyDown(int keycode) {
//		Interpreter.memoryMap.writeReg(Mem.ADDR_AH, keycode);
		Bios.scancode = newKeycode(keycode);
//		System.out.println(Integer.toBinaryString(keycode));
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		Bios.scancode = newKeycode(keycode)|Integer.parseInt("10000000", 2);
//		System.out.println(Integer.toBinaryString(keycode));
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		if(waitForKey && ((int)character)>31 && ((int)character)<127) {
			Interpreter.memoryMap.writeReg(Mem.ADDR_AL, (int)character);
			waitForKey = false;
		}
		return true;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return true;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return true;
	}
	
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public static Pixmap map = null;
	Texture text;
	public static String str = null;
	public static boolean waitForKey = false;
	/** Initializes the {@link Pixmap} to draw on
	 */
	private void initPixmap() {
		map = new Pixmap(320, 200, Format.RGB888);
		text = new Texture(map);
		str = "";
	}
	
	int lastmode = 0;
	boolean init = false;
	/** Renders the screen and executes precompiling, compiling and executing
	 * @see Screen#render
	 */
	@Override
	public void render(float delta) {
		if(f0.getParser().isLoaded() && lastmode >= 0) {
			try {
				for(int i=0;i<(1000*delta);i++) //parse 1000 instructions per second
					lastmode = f0.getParser().tickParser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String text = "";
			if(f0.getParser().maxValue(lastmode)>0)
			text = lastmode+"/"+"2: ["+((f0.getParser().curValue(lastmode)*100)/f0.getParser().maxValue(lastmode))+"%]";
			f0.getFont().draw(f0.getBatch(), text,  80, 50);
			
			return;
		}else 
			if(!init) {
			if(f0.getParser().isLoaded())
			{
				Interpreter.init(f0.getParser().getParsed());
				if(f0.isSave())
					Parser.writeBinary("binary/"+f0.getParser().getFileName()+".pseudo", f0.getParser());
			}else
				Interpreter.init(Parser.readBinary(f0.getFile()));
			initPixmap();
			init = true;
			return;
			}
		
//		long c = System.currentTimeMillis();
//		System.out.println(delta);
		int i = 200000;
		while(!waitForKey && i>=0) {
			Interpreter.runOne();
			i--;
		}
//		c = System.currentTimeMillis() - c;
//		System.err.println(c+"ms for "+100000+" instructions"+"");
		if(f0.isRunning()) {
			text.draw(map, 0, 0);
			f0.getBatch().draw(text,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			f0.getFont().draw(f0.getBatch(), str, 10, Gdx.graphics.getHeight()-10);
		}
	}
	
	/** Draws text in a custom font at loc
	 * @param en Source for images
	 * @param g Place to draw on
	 * @param loc Start location for drawing
	 * @param txt Text to draw
	 */
	public static void renderSmallText(F0iWindowed en,SpriteBatch g,Vector2 loc,String txt){
		final int charSize = 16;
		if(txt == null || txt == "")return;
		for(int a=0;a<txt.length();a++) {
			TextureRegion image = en.getImageHandler().getImage("small_"+txt.toUpperCase().toCharArray()[a]);
			if(image==null) continue;
			g.draw(image, (int)loc.x + a*4*charSize, (int)loc.y ,image.getRegionWidth()*charSize,image.getRegionHeight()*charSize);
		}
	}
	
	@Override
	public void resize(int width, int height) {
	}
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
	}
	@Override
	public void hide() {
	}
	@Override
	public void dispose() {
	}


	@Override
	public void show() {
	}
}
