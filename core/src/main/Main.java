package main;
import java.awt.Dimension;
import java.awt.Toolkit;

//import com.badlogic.gdx.backends.jogl.JoglApplication;
//import com.badlogic.gdx.backends.jogl.JoglApplicationConfiguration;


public class Main  
{
	static Dimension sSize;
	public static void main(String[] args)
	{
  		sSize = Toolkit.getDefaultToolkit().getScreenSize();
		
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
	};
}