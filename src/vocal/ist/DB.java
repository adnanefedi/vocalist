package vocal.ist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.MotionEvent;
import android.view.View;

public class DB extends SQLiteOpenHelper{

	public DB(Context context) {
		super(context, "wortschatz.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE vokabeln ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "text TEXT" 
				+ ");");
	}
	
	public void create(String text) {
		ContentValues cv = new ContentValues();
		cv.put("text", text);
		getWritableDatabase().insert("vokabeln", null, cv);
	}
	
	public Cursor findAll() {
		return getReadableDatabase().query("vokabeln", new String[]{"_id", "text"}, null, null, null, null, "_id DESC");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void delete(String text) {
		getWritableDatabase().delete("vokabeln", "text = '"+text+"'",	null);
		
	}

}
