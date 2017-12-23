package com.fp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity{
	static String num=new String();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		Button aButton=(Button)findViewById(R.id.submit);
		final EditText et=(EditText)findViewById(R.id.editText);
		aButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				num=et.getText().toString();
				insertNum();
				et.setText("");
				num="";
				
				
			}});
		//Toast.makeText(null, "Add Successful!!!", Toast.LENGTH_LONG);
	}
	public  void insertNum(){
		DataHelper.createNum(new String(num));
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