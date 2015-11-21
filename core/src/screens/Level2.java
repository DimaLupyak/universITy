package screens;
import java.util.List;
import java.util.Random;

import main.Game;
import model.Anemy;

import com.badlogic.gdx.Gdx;

import controller.GameController;


public class Level2 extends GameScreen
{
	private Screen nextLevel;
	private int startPoints;
	
	public Level2(Game game, int lifes, int points) 
	{		
		super(game, lifes, points);
		startPoints = points; 
		nextLevel = new Level3(game, lifes, points);
		point = 15;
		super.lifes++;
		Gdx.input.setInputProcessor(new GameController(this, game));
	}
	
	public void CreateAnemys(List<Anemy> anemys)
    {
    	Random rnd = new Random();
    	String sign = "";
    	int r = rnd.nextInt(2);
    	switch(r)
    	{
    	case 0:
    		sign = "+";break;
    	case 1:
    		sign = "-";break;	
    	}
    	if(anemys.size()<5 && rnd.nextInt((int)(Gdx.graphics.getWidth() * Gdx.graphics.getDeltaTime()/5)) == 1)
    		anemys.add
    		(
    				new Anemy
    				(
    						""+(rnd.nextInt(15)+1)+sign+(rnd.nextInt(15)+1),
    						rnd.nextInt(Gdx.graphics.getWidth()-100),
    						Gdx.graphics.getHeight()
    				)
    		);
    }
	public void testStatusOfGame() 
    {
       if(lifes <= 0){game.GameOver();}
       if(score > startPoints + 350)
       {
    	   nextLevel = new LevelIntroScreen(game, 3, this);
    	   nextLevel.show();
    	   game.screen = nextLevel;
    	   this.pause();
    	   this.dispose();
       }
    }
}
