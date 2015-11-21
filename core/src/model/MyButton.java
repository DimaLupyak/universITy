package model;

import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import controller.Action;

public class MyButton extends Sprite 
{	
	private SpriteBatch spriteBatch;
    private Rectangle 	cursor;
    private boolean 	soundPlay = true;
    private Action 		action;
    private Game 		game;
    private float 		alpha = 0.7f;
    public MyButton(Game game ,String bg, Action action) 
    {    	
    	super(new Texture(bg));
    	this.game=game;
    	this.action=action;
    	setBounds(0, 0, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/15);
    	setOrigin(getWidth()/2, 0);    	    	
    	cursor = new Rectangle(0, 0, 1, 1);    	
    	spriteBatch = new SpriteBatch();
	}    
    
    public void setAlpha(float alpha)
    {
    	this.alpha = alpha;
    }
    public float getAlpha()
    {
    	return alpha;
    }
    
	public void draw(float stateTime) 
	{
		spriteBatch.begin();			
			draw(spriteBatch, alpha);
		spriteBatch.end();
	}
	
	public void update(float stateTime) 
	{
		cursor.x = Gdx.graphics.getWidth()-Gdx.input.getX();
		cursor.y = Gdx.graphics.getHeight()-Gdx.input.getY();
		if(getBoundingRectangle().contains(cursor))
		{
			setScale(1.2f);
			alpha = 1f;
			if(soundPlay){
	    	game.sounds.play("select",1f);
			soundPlay = false;
			}
			if(Gdx.input.isTouched())
			{
		    	game.sounds.play("click",1f);
				action.start(game);
			}
		}
		else 
		{
			soundPlay = true;
			setScale(1f);
			alpha = 0.7f;
		}
	}  
    
}
