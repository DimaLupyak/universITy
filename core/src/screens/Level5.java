package screens;
import java.util.List;
import java.util.Random;

import main.Game;
import model.Anemy;

import com.badlogic.gdx.Gdx;

import controller.GameController;


public class Level5 extends GameScreen
{
	private Screen nextLevel;
	private int startPoints;
	
	public Level5(Game game, int lifes, int points) 
	{		
		super(game, lifes, points);
		startPoints = points; 
		nextLevel = new Level3(game, lifes, points);
		point = 30;
		super.lifes++;
		Gdx.input.setInputProcessor(new GameController(this, game));
	}
	
	public void CreateAnemys(List<Anemy> anemys)
    {
    	Random rnd = new Random();
    	String sign1 = "";
    	String sign2 = "";
    	int r1 = rnd.nextInt(3);
    	int r2 = rnd.nextInt(3);
    	switch(r1)
    	{
    	case 0:
    		sign1 = "+";break;
    	case 1:
    		sign1 = "-";break;
    	case 2:
    		sign1 = "*";break;
    	}
    	switch(r2)
    	{
    	case 0:
    		sign2 = "+";break;
    	case 1:
    		sign2 = "-";break;
    	case 2:
    		sign2 = "*";break;
    	}
    	if(anemys.size()<3 && rnd.nextInt((int)(Gdx.graphics.getWidth() * Gdx.graphics.getDeltaTime()/5)) == 1)
    		anemys.add
    		(
    				new Anemy
    				(
    						""+(rnd.nextInt(5)+1)+sign1+(rnd.nextInt(5)+1)+sign2+(rnd.nextInt(5)+1),
    						rnd.nextInt(Gdx.graphics.getWidth()-100),
    						Gdx.graphics.getHeight()
    				)
    		);
    }
	public void testStatusOfGame() 
    {
       if(lifes <= 0){game.GameOver();}
       if(score > startPoints + 600)
       {
    	   nextLevel = new Level5(game, lifes, score);
    	   nextLevel.show();
    	   game.screen = nextLevel;
    	   this.pause();
    	   this.dispose();
       }
    }
}
