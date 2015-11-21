package screens;
import java.util.List;
import java.util.Random;

import main.Game;
import model.Anemy;

import com.badlogic.gdx.Gdx;

public class Level1 extends GameScreen
{
	private Screen nextLevel;
	
	public Level1(Game game, int lifes, int points) 
	{
		super(game, lifes, points);
		point = 10;
	}
	
	public void CreateAnemys(List<Anemy> anemys)
    {
    	Random rnd = new Random();
    	if(anemys.size()<6 
    	   &&rnd.nextInt((int)(Gdx.graphics.getWidth()*Gdx.graphics.getDeltaTime()/6))==1)
    	anemys.add
    	(    			
    		new Anemy
    		(
    			""+(rnd.nextInt(10)+1)+"+"+(rnd.nextInt(10)+1),
    			rnd.nextInt(Gdx.graphics.getWidth()-100),
    			Gdx.graphics.getHeight()
    		)
    	);
    }
	public void testStatusOfGame() 
    {
       if(lifes <= 0){game.GameOver();}
       if(score > 300)
       {
    	   nextLevel = new LevelIntroScreen(game, 2, this);    	   
    	   nextLevel.show();
    	   game.screen = nextLevel;
    	   this.pause();
    	   this.dispose();
       }
    }
}
