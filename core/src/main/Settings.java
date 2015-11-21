package main;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Settings implements Serializable
{
    public final int[] highscores = new int[] {0, 0, 0, 0, 0};

    public void addScore (int score) 
    {
            for (int i = 0; i < 5; i++) 
            {
                    if (highscores[i] < score) 
                    {
                            for (int j = 4; j > i; j--)
                                    highscores[j] = highscores[j - 1];
                            highscores[i] = score;
                            break;
                    }
            }
    }
}