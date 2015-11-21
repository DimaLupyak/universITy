package screens;

import java.util.Random;

import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScreen extends Screen
{	
	private OrthographicCamera 	guiCam;
	private Sprite 				nameOfTheGame;
	private Sprite 				copyright;
	private SpriteBatch 		batch;
	private float 				scale;
	private float 				alpha;
	private	Sprite 				stars[];	
	
	public IntroScreen(Game game)
	{
		super(game);
		game.music.play("intro");
		game.music.setVolume("intro", 0.7f);
		
		guiCam = new OrthographicCamera(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height);
		guiCam.position.set(Gdx.graphics.getDesktopDisplayMode().width / 2, Gdx.graphics.getDesktopDisplayMode().height / 2, 0);
		
		Random rnd=new Random();
		stars = new Sprite[200];
		for(int i = 0; i<stars.length; i++)
			{
			stars[i]= new Sprite(new Texture("Data/star.png"));
			int r = rnd.nextInt(Gdx.graphics.getWidth()/300);
			stars[i].setBounds(rnd.nextInt(Gdx.graphics.getWidth()), rnd.nextInt(Gdx.graphics.getHeight()), r, r);
			}
		
		copyright = new Sprite(new Texture("Data/copyright.png"));		
		nameOfTheGame = new Sprite(new Texture("Data/rock.png"));
		
		copyright.setBounds(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/8, Gdx.graphics.getWidth()/15, Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/20);
		nameOfTheGame.setBounds(0, 0, Gdx.graphics.getWidth()/5, Gdx.graphics.getWidth()/5);
		
		nameOfTheGame.setOrigin(nameOfTheGame.getWidth()/2, nameOfTheGame.getHeight()/2);
		
		nameOfTheGame.setPosition(Gdx.graphics.getWidth()/2-nameOfTheGame.getOriginX(), Gdx.graphics.getHeight()/2-nameOfTheGame.getOriginY()/2);
		
		batch = new SpriteBatch();
		scale = 0.005f;
		alpha = 0f;
	}	

    @Override
    public void update(float deltaTime)
    {
    	guiCam.update();
    	if(alpha<0.99) alpha+=0.01f ;
    	if( nameOfTheGame.getScaleX()>2.5)scale=-0.003f;
    	if(( nameOfTheGame.getScaleX()<1.5))scale=0.005f;
    	 nameOfTheGame.scale(scale);
    	if(Gdx.input.isKeyPressed(Keys.SPACE)||!game.music.isPlaying("intro"))
        {
    		game.screen.pause();
			game.screen.dispose();
    		game.setScreen(new MenuScreen(game, new LevelIntroScreen(game, 1, new Level1(game, 3, 0))));    		
        }
    }

    @Override
    public void present(float deltaTime)
    {  	
    	Gdx.gl.glClearColor(0, 0, 0, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        
        batch.setProjectionMatrix(guiCam.combined);        
        batch.begin();
        for(Sprite star : stars)
        {
        	star.draw(batch);
        }
        	copyright.draw(batch, alpha);
        	nameOfTheGame.draw(batch, alpha);        	
        batch.end();        
    }

    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose()
    {
    	if(game.music.isPlaying("intro"))game.music.stopSound("intro");
    }
}
