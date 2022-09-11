package com.virusfighter.game.desktop;

import Virus_fighter.VirusFighterGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	private static boolean rebuildAtlas = true;
	
	public static void main(String[] arg) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.height = 1920;
			config.width= 1080;
			config.resizable = false;
			new LwjglApplication(new VirusFighterGame(), config);
	}
}
