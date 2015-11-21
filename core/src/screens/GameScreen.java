package screens;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import main.Game;
import main.Settings;
import model.Anemy;
import model.Bullet;
import model.Explosion;
import model.MySprite;
import model.TextBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.GameController;


public class GameScreen extends Screen 
{	
	private	OrthographicCamera 	guiCam;
	private	SpriteBatch			batcher;
	public	MySprite			player;
	private	float 				stateTime;
	public	List<Bullet>		bullets;
	public	List<Explosion>		explosions;
	private	List<Anemy>			anemys;
	public	int 				lifes;
	public	int 				score;
	public	int 				point;
	public	int 				treck;
	private	TextBox 			pointsBox;
	private	TextBox 			bestPointsBox;
	private	Sprite 				cursur;
	private	Sprite 				heart;
	private	Sprite 				stars[];
	public	boolean 			notAns 	= false;
	private Settings			settings;
	
	public GameScreen(Game game, int lifes, int points)
	{
		super(game);
		
		System.gc();
		settings = new Settings();

		loadSettings();		

		Gdx.input.setCursorCatched(true);
		this.lifes=lifes;
		this.score = points;
		point = 5;
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);		
		
		Gdx.input.setInputProcessor(new GameController(this, game));
						
		batcher = new SpriteBatch();
		stateTime = 0f;
		
		player = new MySprite();
		bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		anemys = new ArrayList<Anemy>();
		Random rnd = new Random();
		treck=rnd.nextInt(3)+1;
		pointsBox = new TextBox(Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight()-20);
		pointsBox.setScale(2);
		bestPointsBox = new TextBox(Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight()-60);
		
		heart = new Sprite(new Texture("Data/heart.png"));
		heart.setBounds(10, Gdx.graphics.getHeight()-10, Gdx.graphics.getHeight()/40, Gdx.graphics.getHeight()/40);
		cursur = new Sprite(new Texture("Data/aim.png"));
		cursur.setBounds(0, 0, Gdx.graphics.getHeight()/40, Gdx.graphics.getHeight()/40);
		stars = new Sprite[200];
		for(int i = 0; i<stars.length; i++)
			{
			stars[i]= new Sprite(new Texture("Data/star.png"));
			int r = rnd.nextInt(Gdx.graphics.getWidth()/300);
			stars[i].setBounds(rnd.nextInt(Gdx.graphics.getWidth()), rnd.nextInt(Gdx.graphics.getHeight()), r, r);
			}
	}	

	@Override
    public void update(float deltaTime)
    {		
		
    	stateTime += Gdx.graphics.getDeltaTime();
    	player.update(stateTime);
    	guiCam.update();
    	batcher.setProjectionMatrix(guiCam.combined);
    	
    	pointsBox.setStr("Points: "+score );
    	bestPointsBox.setStr("The best: "+ settings.highscores[0] );
    	
    	cursur.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
    	if(!game.music.isPlaying(""+treck))
    	{
			game.music.play(""+treck);
			game.music.setVolume(""+treck, 0.9f);
    	}
    	
    	Iterator<Bullet> i = bullets.iterator();
    	Iterator<Anemy> j = anemys.iterator();
    	
    	while(i.hasNext()) 
    	{
    		Bullet bullet = i.next();
    		if(bullet.getX()>=0 && bullet.getX()<=Gdx.graphics.getWidth() && bullet.getY()>=0 && bullet.getY()<=Gdx.graphics.getHeight())
    		{
            	bullet.update(stateTime);
    		}
        	else 
        	{
        		i.remove();    				
        	}
    	}
    	
    	while(j.hasNext()) 
    	{
    		Anemy anemy = j.next();
    		if(!player.getBoundingRectangle().overlaps(anemy.getBoundingRectangle()))
    			{
    			anemy.update(stateTime);
    			}
    		
        	else 
        	{        		
        		j.remove();
        		explosions.add(new Explosion("Data/explosion2.gif",5,3,Gdx.graphics.getWidth()/2,0, anemy.getScaleX(),game));
        		for(Explosion explosion : explosions)
                {
                	explosion.setScale(1f, 1.5f);
                	explosion.setRotation((float) Math.atan2(Gdx.graphics.getHeight() , anemy.x - Gdx.graphics.getWidth()/2));
                }
        		lifes--;
        	}
    	}
    	
    	Iterator<Explosion> g = explosions.iterator();
        while(g.hasNext()) 
        {
        	Explosion explosion = g.next();
        	if(explosion.n>12)  g.remove();             
        }
    	CreateAnemys(anemys);
    	testCollision(anemys, bullets);
    	setScore();
    	testStatusOfGame();    	
    	
    }
	Random rnd = new Random();
	@Override
    public void present(float deltaTime)
    {		
		Gdx.gl.glClearColor(0/255f, 5/255f, 20/255f, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	     
        batcher.begin();
        for(Sprite star : stars)
        {
        	star.draw(batcher);
        }
        batcher.end();
        for(Bullet bullet : bullets)
        {
        	bullet.draw(stateTime);
        }        
        for(Anemy anemy : anemys)
    	{
        	anemy.draw(stateTime);
    	}
        for(Explosion explosion : explosions)
        {
        	explosion.draw(stateTime);
        }
        batcher.begin();
    		cursur.draw(batcher);
    		for(int i = 0; i< lifes; i++)
    		{
    			heart.setPosition(Gdx.graphics.getHeight()/60+(heart.getWidth()+Gdx.graphics.getHeight()/60)*i, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
    			heart.draw(batcher);
    		}
    	batcher.end();
        player.draw(stateTime);        
        pointsBox.draw(stateTime);
        bestPointsBox.draw(stateTime);
        
    }

    @Override
    public void pause()
    {
    	game.sounds.stopSound(""+treck);
    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {
    	if(game.music.isPlaying(""+treck))game.music.stopSound(""+treck);
    	Iterator<Anemy> j = anemys.iterator();
    	Iterator<Bullet> g = bullets.iterator();
    	Iterator<Explosion> i = explosions.iterator();
    	batcher.dispose();
    	player=null;
    	pointsBox.dispose();
    	bestPointsBox.dispose();
    	cursur=null;
    	heart=null;
    	while(i.hasNext()) 
    	{    	
    			i.next();
        		i.remove();
    	}
    	while(j.hasNext()) 
    	{    	
    			j.next();
        		j.remove();
    	}
    	while(g.hasNext()) 
    	{   
    			g.next();
        		g.remove();
    	}
    	

    }
    
    public void CreateAnemys(List<Anemy> anemys)
    {
    	Random rnd = new Random();
    	String sign = "";
    	int r = rnd.nextInt(3);
    	switch(r)
    	{
    	case 0:
    		sign = "+";break;
    	case 1:
    		sign = "-";break;
    	case 2:
    		sign = "*";break;    	
    	}
    	if(anemys.size()<5 && rnd.nextInt(50) == 1) anemys.add(new Anemy(""+rnd.nextInt(10)+sign+rnd.nextInt(10), rnd.nextInt(Gdx.graphics.getWidth()-100), Gdx.graphics.getHeight()));
    }
    
    private void testCollision(List<Anemy> anemys, List<Bullet> bullets) {
        Iterator<Bullet> j = bullets.iterator();
        while(j.hasNext()) 
        {
        	Bullet bullet = j.next();
        	Iterator<Anemy> i = anemys.iterator();
         	while(i.hasNext()) 
            {
         		Anemy anemy = i.next();
                  
         		if(bullet.getBoundingRectangle().overlaps(anemy.getBoundingRectangle())
         			&& (anemy.getStr().compareTo(bullet.getStr())==0))
                 {
         			explosions.add(new Explosion("Data/explosion.gif",3,4,anemy.getX(),anemy.getY(), anemy.getScaleX(),game));
         			score+=point;
         			
         			try
         			{
	                    i.remove();
	                    j.remove();
         			}
         			catch(IllegalStateException e){}
                 }
                }
        }
    }
    
    public void testStatusOfGame() 
    {       
      if(lifes <= 0)
      { 
    	settings.addScore(score);
    	settings.highscores[2] = 123;
  		saveSettings();
    	game.GameOver();
      }
    }
    
    public void setScore() 
    {       
      if(lifes <= 0)
      { 
	    	settings.addScore(score);
	    	saveSettings();    	
      }
    }
       
    
	public void loadSettings() 
    {       
    	 try
    	 { 
    		 File file = new File("C:\\best.ldd");
    		 ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
    		 
    		 settings = (Settings) oin.readObject();    		 
    		 
    		 oin.close();
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    }
    
    public void saveSettings() 
    {       
    	OutputStream output = null;
    	ObjectOutputStream out = null;
    	try
    	{
        	File file = new File("C:\\best.ldd");
        	output = new FileOutputStream(file);
    		out = new ObjectOutputStream(output);
    		out.writeObject(settings);
    		out.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	} 
    	
    }    
}

