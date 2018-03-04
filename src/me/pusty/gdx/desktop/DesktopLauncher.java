package me.pusty.gdx.desktop;


import pusty.f0i.F0iWindowed;
import pusty.f0i.FileChecker;
import pusty.f0i.interpreter.Interpreter;
import pusty.f0i.parser.Parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	/***
	 * Starts a emulation in desktop mode
	 * @param args arguments (argument 1: relative handle to file, argument 2: true or false (whether to save a compiled .pseudo file within the "binary" folder))
	 */
	public static void main (String[] args) {
        if(args.length == 0)
            args = new String[] {"fbinary/minild.pseudo"};
		ResourceFormater.main(null);
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "x86 Emulator";
		config.width = 320*2;
		config.height = 200*2;
		config.resizable = true;
		config.useGL30 = false;
		config.vSyncEnabled = false;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		
		boolean save = false;
		String file = null;
		boolean console = false;
		boolean internal = false;
		
		for(String arg:args) {
			if(arg.startsWith("s"))
				save = Boolean.parseBoolean(arg.substring(1));
			else if(arg.startsWith("c"))
				console = Boolean.parseBoolean(arg.substring(1));
			else if(arg.startsWith("i"))
				internal = Boolean.parseBoolean(arg.substring(1));
			else if(arg.startsWith("e"))
				internal = !Boolean.parseBoolean(arg.substring(1));
			else if(arg.startsWith("f"))
				file = arg.substring(1);
		}
		
		if(!console) {
			try {
				System.out.println(file);
				F0iWindowed f0i = new F0iWindowed(file, save, internal);
				new LwjglApplication(f0i, config);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			config.width = 1;
			config.height = 1;
			config.foregroundFPS = 30;
			config.backgroundFPS = 30;
			LwjglApplication app = new LwjglApplication(new ConsoleApplicationListener (), config);
			if(file != null && file.length() > 0 &&file.charAt(0) == '"')
				file = FileChecker.replaceAll(file, '"'+"", "");
			if(file != null && file.startsWith("'"))
				file = FileChecker.replaceAll(file, "'", "");
			if(file == null || file.equalsIgnoreCase("")|| !Gdx.files.internal(file).exists())
				file = "asm/helloworld.asm";
			try {
				Interpreter.init(Parser.parse(file));
				Interpreter.runAll();
				app.exit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
