package com.fp;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class EmergencyButtonAppActivity extends Activity {
public static DataHelper dh;
public StringBuilder MESSAGE=new StringBuilder();
public String NUMBER=new String();
// Button to trigger sending the SMS
Button aButton;
// PendingIntent to tell the SMS app to notify us
PendingIntent sentPI;
// The intent action we are using
String SENT = "SMS_SENT";
// The BroadcastReceiver that we use to listen for the notification back
BroadcastReceiver br;
private LocationManager locManager;
private LocationListener locListener;
private Location mobileLocation;
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main);
dh=new DataHelper(this);
// Create the Pending Intent
sentPI = PendingIntent.getBroadcast(this, 0,
new Intent(SENT), 0);
final EditText amsg=(EditText)findViewById(R.id.msg);
aButton = (Button) this.findViewById(R.id.Button01);
aButton.setOnClickListener(new OnClickListener() {
public void onClick(View v) {
SmsManager sms = SmsManager.getDefault();
Log.w("*****Ravikanth*****","SMS Manager Started");
// send the message, passing in the pending intent, sentPI
MESSAGE.append(amsg.getText());
GetLocation();
Log.w("*****Ravikanth*****","Location Determined");//Gets the GPS Location of User
//int total=DataHelper.getTotalCount(null);
Cursor Cur = DataHelper.fetchAllNumbers();
Log.w("*****Ravikanth*****","Data Fetched");
Cur.moveToFirst();
Log.w("*****Ravikanth*****","Cursor moved");
while(Cur.isAfterLast()!=true){
NUMBER=Cur.getString(1);
Log.w("*****Ravikanth*****","Fetched number "+NUMBER);
sms.sendTextMessage(NUMBER, null, new String(MESSAGE), sentPI, null);
Log.w("*****Ravikanth*****","Sending SMS "+NUMBER);
Log.w("*****Ravikanth*****","Broadcasr Reciver Set");
Cur.moveToNext();
}
Log.w("*****Ravikanth*****","Exited Loop");
registerReceiver(br, new IntentFilter(SENT));
/*NUMBER=Cur.getString(1);
Log.w("*****Ravikanth*****","Appending Number Outside loop...."+NUMBER);
//sms.sendTextMessage(NUMBER, null, new String(MESSAGE), sentPI, null);
Log.w("*****Ravikanth*****","Sending SMS "+NUMBER);
//registerReceiver(br, new IntentFilter(SENT));*/
}});


// In order to receive the results via the pending intent we need
// to register a new BroadcastReceiver and pay attention to the various
// values we could get back
br = new BroadcastReceiver(){
@Override
public void onReceive(Context ctx, Intent intent) {
switch (getResultCode())
	{
	case Activity.RESULT_OK:
		Toast.makeText(getBaseContext(), "SMS sent "+MESSAGE,
				Toast.LENGTH_LONG).show();
		break;
	case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
		Toast.makeText(getBaseContext(), "SMS: Generic failure",
				Toast.LENGTH_LONG).show();
		break;
	case SmsManager.RESULT_ERROR_NO_SERVICE:
		Toast.makeText(getBaseContext(), "SMS: No service",
				Toast.LENGTH_LONG).show();
		break;
	case SmsManager.RESULT_ERROR_NULL_PDU:
		Toast.makeText(getBaseContext(), "SMS: Null PDU",
				Toast.LENGTH_LONG).show();
		break;
	case SmsManager.RESULT_ERROR_RADIO_OFF:
		Toast.makeText(getBaseContext(), "SMS: Radio off",
				Toast.LENGTH_LONG).show();
		break;
	}
	unregisterReceiver(br);
}
};
}
/** Gets the current location and update the mobileLocation variable*/
private void getCurrentLocation() {
locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
locListener = new LocationListener() {
public void onStatusChanged(String provider, int status,
Bundle extras) {
// TODO Auto-generated method stub
}
public void onProviderEnabled(String provider) {
// TODO Auto-generated method stub
}
public void onProviderDisabled(String provider) {
// TODO Auto-generated method stub
}
public void onLocationChanged(Location location) {
// TODO Auto-generated method stub
mobileLocation = location;
}

};
locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
}
private void GetLocation() {
getCurrentLocation(); // gets the current location and update mobileLocation variable
if (mobileLocation != null) {
locManager.removeUpdates(locListener); // This needs to stop getting the location data and save the battery power.
String londitude = "Londitude: " + mobileLocation.getLongitude();
String latitude = "Latitude: " + mobileLocation.getLatitude();
String altitiude = "Altitiude: " + mobileLocation.getAltitude();
String accuracy = "Accuracy: " + mobileLocation.getAccuracy();
String time = "Time: " + mobileLocation.getTime();
MESSAGE.append(londitude + "\n" + latitude + "\n"
		+ altitiude + "\n" + accuracy + "\n" + time);
} else {
MESSAGE.append("Sorry, location is not determined");
}
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