package model;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameMusic {
	
	private Map<String, Music> musics = new HashMap<String, Music>();
	private String[] soundNames = {"menu","intro", "GameOver", "1","2","3"};
	
	public GameMusic()
	{
		for(String name: soundNames)
		{
			try
			{
				musics.put(name, Gdx.audio.newMusic(Gdx.files.internal("Data/"+name+".mp3")));
			}
			catch(NullPointerException e){System.out.println(name);}
		}
	}

	
	public void play(String name){
		
		Music music = musics.get(name);
		if(music != null)
		{
			music.play();
		}
	}
	
	public void setVolume(String name, float volume){
		Music music = musics.get(name);
		if(music != null)
		{
			music.setVolume(volume);;
		}
	}
	
	public boolean isPlaying(String name){
		Music music = musics.get(name);
		if(music != null)
		{
			return music.isPlaying();
		}
		return false;
	}
	
	public void stopSound(String name){
		Music music = musics.get(name);
		if(music != null)
		{
			music.stop();
		}
	}
	
	public void stopAllSounds(){
		for(Map.Entry<String, Music> soundEntry : musics.entrySet()){
			soundEntry.getValue().stop();
		}
	}
	
	public void dispose(){
		for(Map.Entry<String, Music> soundEntry : musics.entrySet()){
			soundEntry.getValue().dispose();
		}
	}
}