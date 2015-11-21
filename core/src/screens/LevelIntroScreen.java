package screens;

import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelIntroScreen extends Screen
{
	
	private OrthographicCamera 	guiCam;
	private Texture 			levelTexture;
	private Texture 			numTexture;
	private Sprite 				levelSprite;
	private Sprite 				numSprite;
	private TextureRegion[][] 	numRegions;
	private SpriteBatch 		batch;
	private Screen 				nextLevel;
	private float 				scale;
	private float 				alpha;
	private boolean 			isShow;
	public LevelIntroScreen(Game game, int nLevel, GameScreen lastScreen)
	{
		super(game);
		isShow=false;	
		Gdx.input.setCursorCatched(true);	
		
		guiCam = new OrthographicCamera(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height);
		guiCam.position.set(Gdx.graphics.getDesktopDisplayMode().width / 2, Gdx.graphics.getDesktopDisplayMode().height / 2, 0);
		
		levelTexture = new Texture("Data/level.png");
		levelTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		numTexture = new Texture("Data/num.png");
		numRegions = TextureRegion.split(numTexture, numTexture.getWidth() / 9, numTexture.getHeight() / 1);
		numSprite = new Sprite(numRegions[0][nLevel-1]);
		numSprite.setBounds(0, 0, Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/15f);
		levelSprite = new Sprite(levelTexture);
		levelSprite.setBounds(0, 0, Gdx.graphics.getWidth()/5f, Gdx.graphics.getHeight()/15f);
		levelSprite.setPosition(Gdx.graphics.getWidth()/2-levelSprite.getWidth()/2-numSprite.getWidth()/2, Gdx.graphics.getHeight()/2);
		numSprite.setPosition(levelSprite.getX()+levelSprite.getWidth()+Gdx.graphics.getWidth()/40f, Gdx.graphics.getHeight()/2);
		batch = new SpriteBatch();		
		
		switch(nLevel)
		{
		case 1:
			nextLevel = new Level1(game, lastScreen.lifes, lastScreen.score);
			break;
		case 2:
			nextLevel = new Level2(game, lastScreen.lifes, lastScreen.score);
			break;
		case 3:
			nextLevel = new Level3(game, lastScreen.lifes, lastScreen.score);
			break;
		case 4:
			nextLevel = new Level4(game, lastScreen.lifes, lastScreen.score);
			break;
		case 5:
			nextLevel = new Level5(game, lastScreen.lifes, lastScreen.score);
			break;
		}		
	}	
	public void show()
	{
		isShow = true;
		game.music.play("intro");
		game.music.setVolume("intro", 0.7f);
		scale = 0.5f;
		alpha = 0f;
	}
    @Override
    public void update(float deltaTime)
    {
    	if(isShow)
	    {
	    	if(alpha<1.5) alpha+=0.005f;
	    	if(scale<0.99) scale+=0.05f;    	
	    	levelSprite.setScale(scale);
	    	numSprite.setScale(scale);
	    	guiCam.update();
	    	if(Gdx.input.isKeyPressed(Keys.SPACE)||!game.music.isPlaying("intro"))
	    	{ 
	    		if(game.music.isPlaying("intro"))game.sounds.stopSound("intro");
	    		game.setScreen(nextLevel);
	    		this.pause();
	      	   	this.dispose();
	        }
	    }    	
    }

    @Override
    public void present(float deltaTime)
    {
    	if(isShow)
	    {
    		Gdx.gl.glClearColor(0/255f, 2/255f, 10/255f, 0);
	    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
	        batch.setProjectionMatrix(guiCam.combined);
	        batch.begin();
	        	levelSprite.draw(batch, alpha);
	        	numSprite.draw(batch, alpha);
	        batch.end();
	    }

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
