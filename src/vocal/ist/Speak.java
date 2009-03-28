package vocal.ist;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class Speak extends Activity {
    private EditText text;
	private Button rec;
	protected boolean isRecording;
	private Button play;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        text = (EditText)findViewById(R.id.text);
        text.selectAll();
        
        rec = (Button) findViewById(R.id.rec);
        rec.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d("onTouch", event.toString());
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!isRecording) {
						Sound.rec("/sdcard/Dolmetscher/tmp.3gp");
						rec.setBackgroundResource(R.drawable.stop2);
						isRecording = true;
					}
					else {
						Sound.pause();
						rec.setBackgroundResource(R.drawable.rec);
						isRecording = false;
					}
				}	
				return true;
			}});
        
        play = (Button) findViewById(R.id.play);
        play.setOnTouchListener(new OnTouchListener() {
        	
        	@Override
        	public boolean onTouch(View v, MotionEvent event) {
        		if (event.getAction() == MotionEvent.ACTION_DOWN && !isRecording) {
        			Sound.play();
        		}
        		return true;
        	}});
    }

	@Override
	protected void onPause() {
		super.onResume();
		(new DB(this)).create(text.getText().toString());
		new File("/sdcard/Dolmetscher/tmp.3gp").renameTo(new File("/sdcard/Dolmetscher/"+text.getText().toString()+".3gp"));
	}
    
    
}