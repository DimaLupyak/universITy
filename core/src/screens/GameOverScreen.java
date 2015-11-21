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

public class GameOverScreen extends Screen {

	OrthographicCamera guiCam;
	private Texture splashTexture;
	private Sprite splashSprite;
	private SpriteBatch batch;
	float scale = 0.02f;
	float alpha = 0f;
	public GameOverScreen(Game game)
	{
		super(game);		
		game.music.play("GameOver");
		game.music.setVolume("GameOver",1f);
		
		guiCam = new OrthographicCamera(Gdx.graphics.getDesktopDisplayMode().width,
									    Gdx.graphics.getDesktopDisplayMode().height);
		guiCam.position.set(Gdx.graphics.getDesktopDisplayMode().width/2,
							Gdx.graphics.getDesktopDisplayMode().height/2,0);
		
		splashTexture = new Texture("Data/gameover.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setOrigin(splashTexture.getWidth()/2, splashTexture.getHeight()/2);
		splashSprite.setPosition(Gdx.graphics.getWidth()/2-splashSprite.getOriginX(),
								 Gdx.graphics.getHeight()/2-splashSprite.getOriginY());		
		batch = new SpriteBatch();
	}	

    @Override
    public void update(float deltaTime)
    {
    	if(alpha<0.99) alpha+=0.005f ;    	
    	if(splashSprite.getScaleX()>5)scale=-0.05f;
    	if((splashSprite.getScaleX()<0.5))scale=0.02f;
    	splashSprite.scale(scale);
    	
    	if(Gdx.input.isKeyPressed(Keys.SPACE)||!game.music.isPlaying("GameOver"))
        {
    		game.setScreen(new MenuScreen(game, new Level1(game, 2, 0)));
        }
    }

    @Override
    public void present(float deltaTime)
    {
    	Gdx.gl.glClearColor(0, 0, 0, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        batch.setProjectionMatrix(guiCam.combined);
        batch.begin();
        	splashSprite.draw(batch, alpha);
        batch.end();
    }
    @Override
    public void pause(){}
    @Override
    public void resume(){}

    @Override
    public void dispose()
    {    	
    	splashTexture.dispose();
    	batch.dispose();
    	if(game.music.isPlaying("intro"))game.music.stopSound("GameOver");
    }
}
