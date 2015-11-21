package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Parser;

public class Anemy extends Sprite 
{	
	private SpriteBatch spriteBatch;   
	private String 		ans;
    private float 		speed = Gdx.graphics.getWidth() * Gdx.graphics.getDeltaTime()/30;
    public float 		x;
    public TextBox 		answerBox;
    private Parser 		calculator;
    
    public Anemy(String ans, float x, float y) 
    {    	
    	super(new Texture("Data/asteroid.png"));
    	this.x=x;
    	answerBox = new TextBox(x-getWidth()/2, y);
    	answerBox.setStr(ans);
    	
    	calculator = new Parser();
    	try {
			this.ans = calculator.calculate( calculator.negativeValueFix( ans ) );			
		} catch (Exception e) {			
		}
    	
    	spriteBatch = new SpriteBatch();
    	setBounds(x, y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
    	setOrigin(getWidth()/2, 0);
    	setPosition(x, y);
    	answerBox.setPosition(getX()+getWidth()/2-answerBox.getBounds(answerBox.getStr()).width/2, getY()+getHeight()*getScaleY()/2+answerBox.getBounds(answerBox.getStr()).height/2);
	}    
    
    public void update(float stateTime)
    {
    	this.setScale((Gdx.graphics.getHeight()-getY()/1.5f)/Gdx.graphics.getHeight());
    	this.setPosition((float) (x+(Gdx.graphics.getWidth()/2-x-getWidth()/2)*Math.pow(getScaleX(),4)), getY()-speed);
    	answerBox.setScale(1+(Gdx.graphics.getHeight()-getY())/Gdx.graphics.getHeight());
    	answerBox.setPosition(getX()+getWidth()/2-answerBox.getBounds(answerBox.getStr()).width/2, getY()+getHeight()*getScaleY()/2+answerBox.getBounds(answerBox.getStr()).height*answerBox.getScaleY()/3);

    }
    public String getStr()
    {
		return ans;
    } 
	public void draw(float stateTime) 
	{
		spriteBatch.begin();
			draw(spriteBatch);					
		spriteBatch.end();
		answerBox.draw(stateTime);    
}}
