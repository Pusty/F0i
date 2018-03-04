package pusty.f0i;

import java.util.Map.Entry;

import pusty.f0i.interpreter.Bios;
import pusty.f0i.parser.Parser;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A Windowed emulator object. Loads fonts and batches and parses file.
 */
public class F0iWindowed  extends Game {

	OrthographicCamera camera = null;
	SpriteBatch batch = null;
	BitmapFont font = null;
	TextureLoader pictureloader;
	boolean running = true;
	
	Parser parser;
	String mainFile;
	boolean save;
	boolean intern;
	
	/**
	 * @param string file handle
	 * @param s saves file to binary/FILENAME.pseudo if true
	 * @param i true = internal file (with jarfile or executable); false = external file (raw file within a directory)
	 */
	public F0iWindowed(String string, boolean s, boolean i) {
		mainFile = string;
		save = s;
		intern = i;
		Parser.setInternal(intern);
	}
	/**
	 * @return is a internal file? (if this returns false it is a external file)
	 */
	public boolean isInternal() {
		return intern;
	}
	/**
	 * @return true if this file should be saved
	 */
	public boolean isSave() {
		return save;
	}
	/**
	 * @return is F0iWindowed running?
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * @return returns a parser
	 */
	public Parser getParser() {
		return parser;
	}
	/**
	 * @return the main file handle
	 */
	public String getFile() {
		return mainFile;
	}
	
	@Override
	public void create () {
		pictureloader=new TextureLoader();
		startInit();
		initStartScreen();
	}
	
	public void initStartScreen() {
		F0iWindowedTick tick = new F0iWindowedTick(this);
		this.setScreen(tick);
	    Gdx.input.setInputProcessor(tick);
	}
	public void startInit() {
		this.setBatch(new SpriteBatch());
		this.setFont(new BitmapFont(Gdx.files.internal("resources/arial-15.fnt"), false));
		this.getFont().getData().scale(1);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		try{
			for(Entry<String,FileHandle> entry:FileChecker.checkFolderToHashMap("", "png").entrySet()) {
				String name = entry.getValue().nameWithoutExtension();
				FileHandle file = entry.getValue();
				if(name.equalsIgnoreCase("chars")) {
					
					char[] smallletters = { ' ', 'A','B','C','D','E','F','G','H','I',
											'J', 'K','L','M','N','O','P','Q','R','S',
											'T', 'U','V','W','X','Y','Z','a','b','c',
											'd', 'e','f','g','h','i','j','k','l','m',
											'n', 'o','p','q','r','s','t','u','v','w',
											'x', 'y','z','0','1','2','3','4','5','6',
											'7', '8','9','!','"','%','&','/','(',')',
											'=', '?','[',']','{','}','\\','|','<','>',
											'*', '+','~',"'".toCharArray()[0],'#','-','_','.',':',',',
											';'};
					Texture tex = new Texture(file);
					TextureRegion[][]  tmp = TextureRegion.split(tex, tex.getWidth()/10, tex.getHeight()/10);
			        int index = 0;
			        for (int i = 0; i < tmp.length; i++) {
			            for (int j = 0; j < tmp[i].length; j++) {
			            	getImageHandler().addImage("small_" + smallletters[index], tmp[i][j]);
			                index++;
			                if(index >= smallletters.length)
			                	break;
			            }
		                if(index >= smallletters.length)
		                	break;
			        }
				}else {
					getImageHandler().addImage(name, new TextureRegion(new Texture(file)));
				}
			}
			
			Bios.setWindowed(true);
			
//			Gdx.app.setLogLevel(Application.LOG_DEBUG);
	    	
//			Gdx.app.log("wanted file", mainFile);
			
			if(mainFile != null && mainFile.length() > 0 && mainFile.charAt(0) == '"')
				mainFile = FileChecker.replaceAll(mainFile, '"'+"", "");
			if(mainFile != null && mainFile.startsWith("'"))
				mainFile = FileChecker.replaceAll(mainFile, "'", "");
			if(mainFile == null || mainFile.equalsIgnoreCase("")|| !Gdx.files.internal(mainFile).exists())
				mainFile = "asm/helloworld.asm";
			
			parser = new Parser(mainFile);
			
			if(mainFile.endsWith(".asm")) 
				parser.initParsing();
			
//			Gdx.app.log("loaded file", mainFile);
		}catch(Exception e){e.printStackTrace();}

	}
	
	
	
	@Override
	public void resize(int w,int h) {
		
	}

	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        
        
		if (isRunning()) {
			batch.begin();
			super.render();
			batch.end();
		}
	}
	@Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
	
	public TextureLoader getImageHandler(){return pictureloader;}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
	
}
