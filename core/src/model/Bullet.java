package model;
import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Bullet extends Sprite 
{	
	private SpriteBatch spriteBatch;   
	private String ans;
	private Vector2 velocity = new Vector2();
    private float speed = Gdx.graphics.getWidth() * Gdx.graphics.getDeltaTime()/1.5f;
    public float angle;
    
    public Bullet(String ans, float angle, float x, float y, Game game) 
    {
    	super(new Texture("Data/bullet.png"));
    	this.angle=angle;
    	this.ans = ans;
    	spriteBatch = new SpriteBatch();
    	setOrigin(getWidth()/2, 0);
    	setPosition((float)(x-getHeight()*Math.cos(angle)), (float) (y+getHeight()*Math.sin(angle)));
    	setRotation(-angle * MathUtils.radiansToDegrees-90);
    	setBounds(x, y, Gdx.graphics.getWidth()/200, Gdx.graphics.getHeight()/30);
    	velocity.set((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
    	game.sounds.play("shot",1f);
	}    
    
    public void update(float stateTime)
    {	    	
    	this.setPosition(getX() + velocity.x, getY() - velocity.y);
    	setScale(0.3f+(Gdx.graphics.getHeight()-getY())/Gdx.graphics.getHeight());
    }
    public String getStr()
    {
		return ans;
    } 
	public void draw(float deltaTime) 
	{
		spriteBatch.begin();			
			draw(spriteBatch);				
		spriteBatch.end();
	}  
    
}
