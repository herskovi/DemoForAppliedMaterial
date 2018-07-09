package com.amat.utils;

import java.io.InputStream;
import java.net.URL;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundUtils 
{
	public final static String filename = "src/main/resources/static/music/ata_totach_short.mp3";
	
	public void playSound() {
	    try {
            MediaLocator mrl = new MediaLocator(getClass().getResource(filename));

            Player player = Manager.createPlayer(mrl);

            player.start();           

	    	
	    	
	    	ClassLoader classLoader = getClass().getClassLoader();
	    	InputStream res = classLoader.getResourceAsStream("static/policesiren.mp3");
	    	URL url = classLoader.getResource("static/policesiren.mp3");
	    	 final Player p= Manager.createRealizedPlayer(url);
			 p.start();
	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(res);
	    	//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Volumes/partition_2/workspace/iotDemo4Amat/src/main/resources/static/policesiren.mp3").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound." + ex.getMessage());
	        ex.printStackTrace();
	    }
	}

}
