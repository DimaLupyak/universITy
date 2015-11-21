package controller;

import main.Game;
import model.Bullet;
import screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class GameController implements InputProcessor 
{
	private Game game;
	private GameScreen screen;

	public GameController(GameScreen screen, Game game) 
	{
		this.game = game;
		this.screen = screen;;
	}
	@Override
	public boolean keyDown(int keyCode) 
	{
		try {
			switch (keyCode) 
			{
			case Keys.NUM_0:
				screen.player.answerBox.add("0".charAt(0));
				play();
				break;
			case Keys.NUM_1:
				screen.player.answerBox.add("1".charAt(0));
				play();
				break;
			case Keys.NUM_2:
				screen.player.answerBox.add("2".charAt(0));
				play();
				break;
			case Keys.NUM_3:
				screen.player.answerBox.add("3".charAt(0));
				play();
				break;
			case Keys.NUM_4:
				screen.player.answerBox.add("4".charAt(0));
				play();
				break;
			case Keys.NUM_5:
				screen.player.answerBox.add("5".charAt(0));
				play();
				break;
			case Keys.NUM_6:
				screen.player.answerBox.add("6".charAt(0));
				play();
				break;
			case Keys.NUM_7:
				screen.player.answerBox.add("7".charAt(0));
				play();
				break;
			case Keys.NUM_8:
				screen.player.answerBox.add("8".charAt(0));
				play();
				break;
			case Keys.NUM_9:
				screen.player.answerBox.add("9".charAt(0));
				play();
				break;
			case Keys.MINUS:
				if (screen.player.answerBox.getStr().length() == 0)
				{
					screen.player.answerBox.add("-".charAt(0));
					play();
				}
				break;
			case 0:
				if (screen.player.answerBox.getStr().length() == 0)
				{
					screen.player.answerBox.add("-".charAt(0));
					play();
				}
				break;
			case Keys.BACKSPACE:
				screen.player.answerBox.back();
				play();
				break;
			case Keys.ESCAPE:
				game.Pause(screen);
				break;
			case Keys.SPACE:
				if (screen.player.answerBox.getStr() != "" || screen.notAns) 
				{
					screen.bullets.add(new Bullet(
							screen.player.answerBox.getStr(),
							screen.player.getAngle(),
							screen.player.getW(),
							screen.player.getH(),
							game));
					screen.player.answerBox.clear();
				}
				break;
			case Keys.LEFT:
				if (screen.player.angle > -Math.PI) screen.player.angle -= 0.1;
				Gdx.input.setCursorPosition(
					(int)(Gdx.graphics.getWidth()/2+Gdx.graphics.getHeight()/2*Math.cos(-screen.player.angle)),
					(int)(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2*Math.sin(-screen.player.angle)));
				break;
			case Keys.RIGHT:
				if (screen.player.angle < 0) screen.player.angle += 0.1;
				Gdx.input.setCursorPosition(
					(int)(Gdx.graphics.getWidth()/2+Gdx.graphics.getHeight()/2*Math.cos(-screen.player.angle)),
					(int)(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2*Math.sin(-screen.player.angle)));
				break;
			}
		} 
		catch (NullPointerException e){}		
		return false;
	}
	@Override
	public boolean keyTyped(char arg0){return false;}
	@Override
	public boolean keyUp(int keyCode){return false;}
	@Override
	public boolean scrolled(int arg0){return false;}
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int button) 
	{
		try {
			if ((screen.player.answerBox.getStr() != "" || screen.notAns) && button == Buttons.LEFT) 
			{
				screen.bullets.add(new Bullet(screen.player.answerBox.getStr(),
						screen.player.getAngle(),
						screen.player.getW(),
						screen.player.getH(),
						game));
				screen.player.answerBox.clear();
			}
			if (button == Buttons.RIGHT) 
			{
				screen.player.answerBox.back();
				play();
			}
		} catch (NullPointerException e){}		
		return false;
	}
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2){return false;}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3){return false;}
	
	public void play(){	game.sounds.play("key",0.7f);}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		try {
			screen.player.angle = (float) Math.atan2(Gdx.input.getY()
					- Gdx.graphics.getHeight(),
					Gdx.input.getX() - Gdx.graphics.getWidth() / 2);
		} catch (NullPointerException e){}
		return false;
	}
}
