package screens;

import main.Game;
public abstract class Screen
{
    public Game  game;

    public Screen(Game game)
    {
        this.game = game;
    }
    
    public abstract void update(float deltaTime);
    public abstract void present(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
    public void show(){}
    
    
}