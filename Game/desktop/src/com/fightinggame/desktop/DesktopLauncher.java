package com.fightinggame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fightinggame.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 30;
		config.width = Main.SCREENWIDTH;
		config.height = Main.SCREENHEIGHT;
		config.resizable = true;
		new LwjglApplication(new Main(), config);
	}
}
