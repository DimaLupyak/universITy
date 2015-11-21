package model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyTable 
{
	private float 			x,y;
	private List<MyButton> 	elements;
	private float 			interval;
	
	public MyTable() 
    {
		this.x = Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/10;
    	this.y = Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/30;
    	interval = Gdx.graphics.getHeight()/40;
    	elements = new ArrayList<MyButton>();
    }
	
	public void setInterval(float interval)
	{
		this.interval=interval;
	}
	public void setPosition(float x, float y)
	{
		this.x = x;
    	this.y = y;
	}
	public void addElement(MyButton el)
	{
		elements.add(el);
		int dy=0;
    	for(Sprite element : elements)
    	{
    		dy += element.getHeight();
    		dy += interval;
    	}
    	setPosition(x, Gdx.graphics.getHeight()/2+dy/2);
    	dy=0;
    	for(Sprite element : elements)
    	{
    		element.setPosition(Gdx.graphics.getWidth()/2-element.getWidth()/2, y - dy);
    		dy += element.getHeight();
    		dy += interval;
    	}
    	for(Sprite element : elements)
    	{
    		dy += element.getHeight();
    		dy += interval;
    	}
	}
	public void draw(float stateTime)
    {
    	for(MyButton element : elements)
    	{
    		element.draw(stateTime);
    	} 
        
    }
	public void update(float stateTime)
    {
		for(MyButton element : elements)
    	{
    		element.update(stateTime);
    	}      
    }
	
}
