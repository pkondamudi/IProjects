package com.fp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SearchActivity extends ListActivity {
	EditText edt;
	DataHelper mDbHelper;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.w("Ravikanth","***** Started Search Activity ****  "+R.id.searchbtn);
	        setContentView(R.layout.search_layout);
	        mDbHelper = new DataHelper(this);
	        mDbHelper.open();
	        Button searchbtn=(Button)findViewById(R.id.searchbtn);
	        Log.w("Ravikanth","***** Setting Search Layout ****");
	        edt=(EditText)findViewById(R.id.searchedittext);
	        fillData();
	        searchbtn.setOnClickListener(new Button.OnClickListener(){
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					searchWord();
				}});
	        registerForContextMenu(getListView());
	 }
	 private void searchWord() {
			// TODO Auto-generated method stub
		 try{
				// TODO Auto-generated method stub
			 	mDbHelper.open();
				Cursor cur=DataHelper.Search(edt.getText().toString());
				Log.w("ravikanth****","Managing Cursor");
				startManagingCursor(cur);
				String[] from = new String[]{DataHelper.KEY_TITLE};
		        int[] to = new int[]{R.id.list};
		        Log.w("ravikanth****","Simple CA......");
				SimpleCursorAdapter number = 
		            new SimpleCursorAdapter(this, R.layout.show, cur, from, to);
				Log.w("ravikanth****","Cursor Established");
		        setListAdapter(number);
		        //fillData();
			/*cur=Dictionary.dh.Search(ed.getText().toString());
			
			List<String> list=new ArrayList<String>();
			if (cur.moveToFirst()) {  
				do {  
					list.add(cur.getString(0));
				} 
				while (cur.moveToNext());  
			}  
			
			if (cur != null && !cur.isClosed()) {  
				cur.close();  
			}  
			Log.w("Ravikanth","***** Setting SEARCH Adapter  ****");
			setListAdapter(new ArrayAdapter<String>(this,R.layout.show,list));
			Log.w("Ravikanth","***** Reached END OF THE SEARCH ACTIVITY ****");*/
			
		 }

			catch(NullPointerException e){
				Toast.makeText(getBaseContext(), "No Such Word Found", Toast.LENGTH_LONG).show();
		 }
	 }
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v,
	            ContextMenuInfo menuInfo) {
	        super.onCreateContextMenu(menu, v, menuInfo);
	        menu.add(0, 1, 0, "Delete Entry");
	    }
		@Override
	    public boolean onContextItemSelected(MenuItem item) {
	        switch(item.getItemId()) {
	            case 1:
	            	Log.w("*****RaviKnath*****","Setting Adapter");
	                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	                Log.w("*****RaviKnath*****","Deleting Note");
	                DataHelper.deleteNote(info.id);
	                Log.w("*****RaviKnath*****","Note Deleted");
	                fillData();
	                return true;
	        }
	        return super.onContextItemSelected(item);
	    }
		private  void fillData() {
			// TODO Auto-generated method stub
			Cursor cur=DataHelper.fetchAllNumbers();
			startManagingCursor(cur);
			String[] from = new String[]{DataHelper.KEY_TITLE};
	        int[] to = new int[]{R.id.list};
			SimpleCursorAdapter number = 
	            new SimpleCursorAdapter(this, R.layout.show, cur, from, to);
			Log.w("ravikanth****","Cursor Established");
	        setListAdapter(number);
			
		}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    super.onCreateOptionsMenu(menu);
		    MenuItem add=menu.add(0, 0, 0, "Add");
		    add.setIcon(R.drawable.ic_menu_edit);
		    MenuItem show=menu.add(0, 1, 0, "Show");
		    show.setIcon(R.drawable.search);
		    MenuItem search=menu.add(0, 2, 0, "Search");
		    search.setIcon(R.drawable.search);
		    return true;
		}

		@Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
		    switch(item.getItemId()) {
		        case 0:
		            Intent a = new Intent(this, AddActivity.class);
		            startActivityForResult(a,0);break;
		            //createNote();
		        case 1:
		        	Intent s = new Intent(this, ShowActivity.class);
		            startActivityForResult(s,0);break;
		        case 2:
		        	Intent se=new Intent(this,SearchActivity.class);
		        	startActivityForResult(se,0);break;
		    }

		    return super.onMenuItemSelected(featureId, item);
		}
}
