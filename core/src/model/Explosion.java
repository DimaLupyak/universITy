package model;

import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Explosion 
{
	private int 			FRAME_COLS; 
	private int 			FRAME_ROWS;
	private Animation       walkAnimation; 
	private Texture         walkSheet;    
	private TextureRegion[] walkFrames;   
	private SpriteBatch     spriteBatch;   
	private TextureRegion   currentFrame;
	private Sprite			sprite;
    public 	int 			n = 0;
	
	public Explosion(String fileName, int cols, int rows, float x, float y, float scale, Game game) 
    {
    	FRAME_COLS = cols;
    	FRAME_ROWS = rows;
    	walkSheet = new Texture(fileName); 
        TextureRegion tmp[][] = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
 
        for (int i = 0; i < FRAME_ROWS; i++)
            for (int j = 0; j < FRAME_COLS; j++)
                walkFrames[index++] = tmp[i][j];
 
        walkAnimation = new Animation(0.05f, walkFrames); 
        spriteBatch = new SpriteBatch();
        sprite = new Sprite();        
        sprite.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getWidth()/10);
        
        sprite.setOrigin(sprite.getWidth()/2, 0);
        if(fileName=="Data/explosion2.gif") sprite.setPosition(x-sprite.getWidth()/2, y);
        else sprite.setPosition(x, y);
        sprite.setScale(scale);
        if(fileName=="Data/explosion2.gif")
        	game.sounds.play("lose",1f);
        else 
        	game.sounds.play("bang",1f);
	} 
	
	public void draw(float stateTime)
    {
    	currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    	sprite.setRegion(currentFrame);
    	spriteBatch.begin();
    		sprite.draw(spriteBatch);    		
    	spriteBatch.end();    	
    	n++;
    }
		
	public void setScale(float x, float y)
    {
		sprite.setScale(x,y);
    }
	public void setRotation(float angle)
    {
		sprite.setRotation(angle * MathUtils.radiansToDegrees-90);;
    }
}
