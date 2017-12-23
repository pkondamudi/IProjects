package com.fp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper {
	private OpenHelper mDbHelper;
	 public static final String KEY_TITLE = "number";
	    //public static final String KEY_BODY = "msg";
	    public static final String KEY_ROWID = "_id";

	    //private static final String TAG = "NotesDbAdapter";
	    //private DatabaseHelper mDbHelper;
	    private static SQLiteDatabase mDb;
	    private final Context mctx;

	    /**
	     * Database creation sql statement
	     */
	    private static final String DATABASE_CREATE =
	        "create table contacts (_id integer primary key autoincrement, "
	        + "number text not null);";

	    private static final String DATABASE_NAME = "EmergencyButton.db";
	    private static final String DATABASE_TABLE = "contacts";
	    private static final int DATABASE_VERSION = 1;

	    private static class OpenHelper extends SQLiteOpenHelper{
			public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.w("Ravikanth","**** OpenHelper() Invoked ****");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		Log.w("Ravikanth","**** Table Created ****");
	}

	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Ravikanth", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }
}


	    /**
	     * Constructor - takes the context to allow the database to be
	     * opened/created
	     * 
	     * @param ctx the Context within which to work
	     */
	    public DataHelper(Context con){
			 Log.w("Ravikanth","**** DataHelper() Invoked ****");
			this.mctx=con;
			Log.w("Ravikanth","**** Context Assigned Invoked ****");
			OpenHelper openHelper=new OpenHelper(this.mctx);
			  mDb= openHelper.getWritableDatabase();
		}




	    /**
	     * Create a new note using the title and body provided. If the note is
	     * successfully created return the new rowId for that note, otherwise return
	     * a -1 to indicate failure.
	     * 
	     * @param title the title of the note
	     * @param body the body of the note
	     * @return rowId or -1 if failed
	     */
	    public static void createNum(String number) {
	    	ContentValues values=new ContentValues();
			values.put("number",number);
	        //initialValues.put(KEY_TITLE, number);
	        //initialValues.put(KEY_BODY, body);
			mDb.insert(DATABASE_TABLE, null, values);
			Log.w("Ravikanth","**** Insertion Successful ****");
	    }

	    /**
	     * Delete the note with the given rowId
	     * 
	     * @param rowId id of note to delete
	     * @return true if deleted, false otherwise
	     */
	    public static boolean deleteNote(long rowId) {
	    	Log.w("*****RaviKnath*****","deleteNode Invoked");

	        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	    }

	    /**
	     * Return a Cursor over the list of all notes in the database
	     * 
	     * @return Cursor over all notes
	     */
	    public static Cursor fetchAllNumbers() {

	        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
	                }, null, null, null, null, null);
	    }

	    /**
	     * Return a Cursor positioned at the note that matches the given rowId
	     * 
	     * @param rowId id of note to retrieve
	     * @return Cursor positioned to matching note, if found
	     * @throws SQLException if note could not be found/retrieved
	     */
	    public Cursor fetchNumber(long rowId) throws SQLException {

	        Cursor mCursor =

	            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID}, KEY_ROWID + "=" + rowId, null,
	                    null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;

	    }

	    /**
	     * Update the note using the details provided. The note to be updated is
	     * specified using the rowId, and it is altered to use the title and body
	     * values passed in
	     * 
	     * @param rowId id of note to update
	     * @param title value to set note title to
	     * @param body value to set note body to
	     * @return true if the note was successfully updated, false otherwise
	     */
	    public boolean updateNote(long rowId, String title, String body) {
	        ContentValues args = new ContentValues();
	        args.put(KEY_TITLE, title);
	        //args.put(KEY_BODY, body);

	        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	    }
	    public static int getTotalCount(Context _context)
	    {
	    Cursor c = null;
	    try
	    {
	        c = /*getInstance(_context).*/mDb.rawQuery("SELECT COUNT(number) FROM  "
	            + DATABASE_TABLE, null);

	        return c.getCount();

	    } catch (Exception e)
	    {
	        Log.e("getTotalCount", e.toString());
	    }
		return 0;
    }
	    public DataHelper open() throws SQLException {
	        mDbHelper = new OpenHelper(mctx);
	        mDb = mDbHelper.getWritableDatabase();
	        return this;
	    }

	    public void close() {
	        mDbHelper.close();
	    }
	    
	    
		public static Cursor Search(String string) {
			// TODO Auto-generated method stub
			Log.w("Ravikanth","***** SearchWord() Invoked  ****");
			//return mDb.rawQuery("select number from "+DATABASE_TABLE+" where number like '"+string+"%'",new String[]{KEY_TITLE});
			return mDb.query(DATABASE_TABLE, new String[]{KEY_ROWID,KEY_TITLE}, "number like '"+string+"%'", null, null, null, null);
		}
}