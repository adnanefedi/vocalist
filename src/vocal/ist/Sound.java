package vocal.ist;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class Sound {
	
	static  MediaRecorder recorder;
	static  MediaPlayer player;
	static  String path = "/sdcard/Dolmetscher/test1.3gp";

	public static void rec(String path) {
		String state = android.os.Environment.getExternalStorageState();
	    if(!state.equals(android.os.Environment.MEDIA_MOUNTED))  {
	        Log.d("Sound", "sdCard not mounted :(");
	    }

		Sound.path = path;
	    // make sure the directory we plan to store the recording in exists
	    File directory = new File(path).getParentFile();
	    if (!directory.exists() && !directory.mkdirs()) {
	      Log.d("Sound", "Ohhhh :(");
	    }

	    recorder = new MediaRecorder();
	    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	    recorder.setOutputFile(path);
	    recorder.prepare();
	    recorder.start();
	}
	
	public static void pause() {
		recorder.stop();
		recorder.release();
		player = new MediaPlayer();
		try {
			player.setDataSource(path);
			player.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void play(String path, OnCompletionListener cpl) {
		try {
			player = new MediaPlayer();
			player.setDataSource(path);
			player.prepare();
			if (cpl != null)
				player.setOnCompletionListener(cpl);
			play();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void play() {
		try {
			player.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	
}
