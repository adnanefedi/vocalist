package vocal.ist;

import java.io.File;
import java.util.zip.Inflater;

import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class VokabelList extends ListActivity {

	private Cursor vokabeln;
	private DB db;
	private LayoutInflater mInflater;
	private boolean delModus;


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DB(this);
		vokabeln = db.findAll();
		
		mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		setAdapter();
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
	}

	private void setAdapter() {
		setListAdapter(new ListAdapter() {

			@Override
			public boolean areAllItemsEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isEnabled(int position) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return vokabeln.getCount();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getItemViewType(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				vokabeln.moveToPosition(position);
				
				final View row = mInflater.inflate(R.layout.list_row, null);
				
				final TextView text = (TextView) row.findViewById(R.id.text);
				text.setText(vokabeln.getString(1));
				text.setTextSize(55);
				text.setTextColor(Color.GREEN);
				text.setFocusable(true);
				if(text.isFocused()){  
				text.setTextColor(Color.YELLOW);}
				final String path = "/sdcard/Dolmetscher/"+vokabeln.getString(1)+".3gp";
				row.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (delModus) {
							new AlertDialog.Builder(VokabelList.this)
											.setTitle("Wirklich!??")
											.setMessage("Bist Du Dir auch wirklich ganz sicher? \n dass Du \""
													+text.getText().toString()+"\" löschen willst?")
											.setPositiveButton("Löschen", new AlertDialog.OnClickListener() {

												@Override
												public void onClick(DialogInterface dialog, int which) {
													db.delete(text.getText().toString());
													delModus = false;
													vokabeln = db.findAll();
													setAdapter();
													new File(path).delete();
												}}).show();
						} else {

							Log.d("OnItemClick", "Hui");
							text.setTextColor(Color.RED);
							Sound.play(path, new OnCompletionListener() {

								@Override
								public void onCompletion(MediaPlayer arg0) {
									row.findViewById(R.id.sound).setVisibility(View.INVISIBLE);
									text.setTextColor(Color.GREEN);
								}});
							row.findViewById(R.id.sound).setVisibility(View.VISIBLE);
						}
					}});
				return row;
			}

			@Override
			public int getViewTypeCount() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void registerDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void unregisterDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Neu");
		menu.add("löschen");
		return super.onCreateOptionsMenu(menu);
	}
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle().equals("Neu"))
			startActivity(new Intent(this, Speak.class));
		else 
			delModus = true;
		return super.onOptionsItemSelected(item);
	}

	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		return super.onContextItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.add(((TextView)v.findViewById(R.id.text)).getText().toString()+" löschen");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		if (vokabeln != null)
			vokabeln.close();
		vokabeln = db.findAll();
		setAdapter();
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		vokabeln.close();
		super.onDestroy();
	}
	
	

}
