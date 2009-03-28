package vocal.ist;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class Dolmetscher extends Activity {

	private ImageView andforge;
	private ImageView fahrplan;
	private ImageView speak;
	private ImageView vokabelbuch;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dolmetscher);
		
		speak = (ImageView) findViewById(R.id.speak);
		speak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Dolmetscher.this, Speak.class));
				
			}});
		vokabelbuch = (ImageView) findViewById(R.id.vokabelList);
		vokabelbuch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Dolmetscher.this, VokabelList.class));
				
			}});

		andforge = (ImageView) findViewById(R.id.werbung);
		andforge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.andforge.net")));
				
			}});
		fahrplan = (ImageView) findViewById(R.id.werbung2);
		fahrplan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=Fahrplan")));
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://pikmi.net/apps/fahrplan/Fahrplan.apk")));
				
			}});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	
}
