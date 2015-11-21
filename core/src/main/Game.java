package main;
import model.GameMusic;
import model.GameSounds;
import screens.GameOverScreen;
import screens.IntroScreen;
import screens.MenuScreen;
import screens.Screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Game  implements ApplicationListener
{
	public Screen screen;
	public GameSounds sounds;
	public GameMusic music;
	
    public Screen getStartScreen()
    {
        return new IntroScreen(this);
    }

    public void create()
    {
    	sounds = new GameSounds();
    	music = new GameMusic();
    	screen = getStartScreen();    	
    }

    public void resume(){}

    public void render()
    {
    	screen.update(Gdx.graphics.getDeltaTime());
    	screen.present(Gdx.graphics.getDeltaTime());
    }

    public void resize(int i, int i1){}

    public void pause(){}

    public void dispose(){}

    public void setScreen(Screen screen)
    {
        this.screen.pause();
        this.screen.dispose();
        this.screen = screen;
    }
    public void Pause(Screen scr)
    {
    	scr.pause();
        screen = new MenuScreen(this, scr);
    }

	public void GameOver() 
	{
		this.screen.pause();
        this.screen.dispose();
        this.screen = new GameOverScreen(this);
	}
}
