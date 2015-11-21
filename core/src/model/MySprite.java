package model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class MySprite extends Sprite 
{	
	private SpriteBatch spriteBatch;
	private Sprite 		body;
	private Sprite 		cannon;
	public 	float 		angle;
	public 	TextBox 	answerBox;
	
    
    public MySprite() 
    {    		
    	body = new Sprite(new Texture("Data/body.png"));
    	body.setBounds(0, 0, Gdx.graphics.getWidth()/10, Gdx.graphics.getWidth()/20);    	
    	body.setOrigin(body.getWidth()/2, 0);
    	body.setPosition(Gdx.graphics.getWidth()/2-body.getWidth()/2, 0);
    	
    	cannon = new Sprite(new Texture("Data/cannon.png"));
    	cannon.setBounds(0, 0, Gdx.graphics.getWidth()/100, Gdx.graphics.getWidth()/12);
    	cannon.setOrigin(cannon.getWidth()/2, 0);
    	cannon.setPosition(Gdx.graphics.getWidth()/2-cannon.getWidth()/2, 0);   	
    	
    	answerBox = new TextBox(0,0);
    	answerBox.setScale(1.5f);
        spriteBatch = new SpriteBatch();
        setBounds(Gdx.graphics.getWidth()/2-body.getWidth()/2, 0, Gdx.graphics.getWidth()/10, Gdx.graphics.getWidth()/20);
	}
    
    public void draw(float stateTime)
    {
    	spriteBatch.begin();
    		cannon.draw(spriteBatch);
    		body.draw(spriteBatch);    		
    	spriteBatch.end();
    	answerBox.draw(stateTime);
        
    }
    public void update(float deltaTime)
    {
    	answerBox.setPosition(body.getX()+body.getOriginX()-answerBox.getBounds(answerBox.getStr()).width/2, body.getHeight()/3);
    	
    	cannon.setRotation(-angle * MathUtils.radiansToDegrees-90);
    } 
    public float getAngle()
    {
		return angle;	    	
    	
    }
    public float getW()
    {
		return (float)(cannon.getX() + cannon.getHeight()*Math.cos(angle));	
    } 
    public float getH()
    {
    	return (float)-(cannon.getY() + cannon.getHeight()*Math.sin(angle));	    	
    	
    } 
    
}
