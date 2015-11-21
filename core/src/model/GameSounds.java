package model;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GameSounds {
	
	private Map<String, Sound> sounds = new HashMap<String, Sound>();
	private final String[] soundNames = {"bang", "click", "key","lose", "select", "shot"};
	private Sound sound;
	
	public GameSounds()
	{
		for(String name: soundNames)
		{
			try
			{
				sounds.put(name, Gdx.audio.newSound(Gdx.files.internal("Data/"+name+".mp3")));
			}
			catch(NullPointerException e){System.out.println(name);}
		}
	}

	
	public void play(String name){
		
		sound = sounds.get(name);
		if(sound != null)
		{
			sound.play();
		}
	}
	
	public void play(String name, float vol){
		
		sound = sounds.get(name);
		if(sound != null)
		{
			sound.play(vol);
		}
	}		
	
	public void stopSound(String name){
		sound = sounds.get(name);
		if(sound != null)
		{
			sound.stop();
		}
	}
	
	public void stopAllSounds(){
		for(Map.Entry<String, Sound> soundEntry : sounds.entrySet()){
			soundEntry.getValue().stop();
		}
	}
	
	public void dispose(){
		for(Map.Entry<String, Sound> soundEntry : sounds.entrySet()){
			soundEntry.getValue().dispose();
		}
	}
}