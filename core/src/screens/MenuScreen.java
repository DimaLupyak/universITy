package screens;

import java.util.Random;
import main.Game;
import model.MyButton;
import model.MyTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Action;

public class MenuScreen extends Screen
{	
	private OrthographicCamera 	guiCam;	
	private SpriteBatch 		batch;
	private MyTable 			table;
	private Screen 				lastScreen;
	private	Sprite 				stars[];
	private Sprite 				nameOfTheGame;
	private float 				scale;
	private float 				dx=1 ;
	private float 				dy=1;
		
	public MenuScreen(Game game, Screen screen)
	{
		super(game);
		Gdx.input.setCursorCatched(false);
		lastScreen = screen;
		table = new MyTable();
		table.addElement(new MyButton(game, "Data/playButton.png", new Action(){

			@Override
			public void start(Game game) 
			{				
				game.screen.pause();
				game.screen.dispose();
				lastScreen.show();
	    		game.screen = lastScreen;
	    		Gdx.input.setCursorCatched(true);
			}}));
		table.addElement(new MyButton(game, "Data/exitButton.png", new Action(){

			@Override
			public void start(Game game) 
			{				
				Gdx.app.exit();
			}}));
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
		
		nameOfTheGame = new Sprite(new Texture("Data/rock.png"));
		nameOfTheGame.setBounds(0, 0, Gdx.graphics.getWidth()/15, Gdx.graphics.getWidth()/15);
		nameOfTheGame.setOrigin(nameOfTheGame.getWidth()/2, nameOfTheGame.getHeight()/2);
		nameOfTheGame.setPosition(rnd.nextInt(Gdx.graphics.getWidth()), rnd.nextInt(Gdx.graphics.getHeight()));
		
		
		batch = new SpriteBatch();
		game.music.play("menu");
		game.music.setVolume("menu", 0.7f);
	}	

    @Override
    public void update(float deltaTime)
    {
    	
    	if(Gdx.input.isKeyPressed(Keys.ENTER))
        {
    		this.pause();
    		this.dispose();
    		lastScreen.
    		game.screen = lastScreen;
    		
        }
    	if(Gdx.input.isKeyPressed(Keys.F1))
        {
    		Gdx.app.exit();
        }
    	
    	if( nameOfTheGame.getScaleX()>2.5)scale=-0.003f;
    	if(( nameOfTheGame.getScaleX()<1.5))scale=0.005f;
    	nameOfTheGame.scale(scale);
    	nameOfTheGame.setPosition(nameOfTheGame.getX()+dx, nameOfTheGame.getY()+dy);
    	nameOfTheGame.rotate(dx/2);
    	if(nameOfTheGame.getX()<=0 || nameOfTheGame.getX()>=Gdx.graphics.getWidth()) 
    	{
    		 dx=-dx;
    		 nameOfTheGame.setPosition(nameOfTheGame.getX()+dx, nameOfTheGame.getY());
    	}
    	if(nameOfTheGame.getY()<=0 || nameOfTheGame.getY()>=Gdx.graphics.getHeight()) 
    	{
    		 dy=-dy;
    		 nameOfTheGame.setPosition(nameOfTheGame.getX(), nameOfTheGame.getY()+dy);
    	}
    	table.update(deltaTime);
    	guiCam.update();
        batch.setProjectionMatrix(guiCam.combined);
    }

    @Override
    public void present(float deltaTime)
    {
    	Gdx.gl.glClearColor(0, 0, 0, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        nameOfTheGame.draw(batch);
        for(Sprite star : stars)
        {
        	star.draw(batch);
        }    		
        	table.draw(deltaTime);
        batch.end();
    }
    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose()
    {
    	if(game.music.isPlaying("menu"))game.music.stopSound("menu");
    }
}
