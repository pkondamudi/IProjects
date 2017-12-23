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
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ShowActivity extends ListActivity{
	private DataHelper mDbHelper;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.defaultshow);
		mDbHelper = new DataHelper(this);
        mDbHelper.open();
		fillData();
		registerForContextMenu(getListView());
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
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Delete Entry");
    }
	@Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                DataHelper.deleteNote(info.id);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
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