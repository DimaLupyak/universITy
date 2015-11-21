package screens;
import java.util.List;
import java.util.Random;

import main.Game;
import model.Anemy;

import com.badlogic.gdx.Gdx;

import controller.GameController;


public class Level3 extends GameScreen
{
	private Screen nextLevel;
	private int startPoints; 
	
	public Level3(Game game, int lifes, int points) 
	{
		super(game, lifes, points);
		startPoints = points; 
		this.lifes=lifes;
		point = 20;
		super.lifes++;
		Gdx.input.setInputProcessor(new GameController(this, game));
	}
	
	public void CreateAnemys(List<Anemy> anemys)
    {
    	Random rnd = new Random();
    	
    	if(anemys.size()<4 && rnd.nextInt((int)(Gdx.graphics.getWidth() * Gdx.graphics.getDeltaTime()/5)) == 1)
    		anemys.add
    		(
    				new Anemy
    				(
    						""+(rnd.nextInt(10))+"*"+(rnd.nextInt(10)),
    						rnd.nextInt(Gdx.graphics.getWidth()-100),
    						Gdx.graphics.getHeight()
    				)
    		);
    }
	public void testStatusOfGame() 
    {
       if(lifes <= 0){game.GameOver();}
       if(score > startPoints + 500)
       {
    	   nextLevel = new LevelIntroScreen(game, 4, this);
    	   nextLevel.show();
    	   game.screen = nextLevel;
    	   this.pause();
    	   this.dispose();
       }
    }
}
