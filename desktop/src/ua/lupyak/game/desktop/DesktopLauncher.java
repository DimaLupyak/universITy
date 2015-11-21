package ua.lupyak.game.desktop;


import java.awt.Dimension;
import java.awt.Toolkit;

import main.Game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*JoglApplicationConfiguration conf = new JoglApplicationConfiguration();
conf.title = "MyGame"; 
conf.width = sSize.width; 
conf.height = sSize.height; 
conf.samples = 4;
conf.useGL20 = false;
conf.vSyncEnabled = true;
conf.fullscreen = true;
System.setProperty("sun.java2d.noddraw", "true");

new JoglApplication(new Game(), conf);*/

public class DesktopLauncher {

	static Dimension sSize;
	public static void main (String[] arg) {
		sSize = Toolkit.getDefaultToolkit().getScreenSize();
		LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
		conf.title = "MyGame"; 
		conf.width = sSize.width; 
		conf.height = sSize.height; 
		conf.samples = 4;
		conf.vSyncEnabled = true;
		conf.fullscreen = true;
		new LwjglApplication(new Game(), conf);
	}
}
