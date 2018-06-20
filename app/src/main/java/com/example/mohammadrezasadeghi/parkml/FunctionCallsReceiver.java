package com.example.mohammadrezasadeghi.parkml;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import org.json.JSONObject;

import java.sql.Timestamp;

import hrituc.studenti.uniroma1.it.generocityframework.Constants;
import hrituc.studenti.uniroma1.it.generocityframework.ParkingLocation;
import hrituc.studenti.uniroma1.it.generocityframework.TripPoint;

public class FunctionCallsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constants.LOGS) Log.e("FunctionCallsReceiver", "onReceive called");
        String action = intent.getAction();
        if (Constants.DID_ENTER_CAR.equals(action)){

            //get the bluetooth device that the user is connected to. BluetoothDevice.EXTRA_DEVICE is
            //an android constant, so since it was there I decided to use it.
            BluetoothDevice connectedDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"isInCar");
            builder.setContentText("Did enter car");
            builder.setSmallIcon( hrituc.studenti.uniroma1.it.generocityframework.R.mipmap.ic_launcher );
            builder.setContentTitle( "FunctionCallsReceiver" );
            NotificationManagerCompat.from(context).notify(7, builder.build());
        } else if (Constants.DID_EXIT_CAR.equals(action)){
            //extract the parking position
            ParkingLocation parkingLocation = ParkingLocation.fromIntent(context,intent);

            //get the device that the user was connected to
            BluetoothDevice previouslyConnectedDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            //The parking location may be null in some rare cases, like if the user manually disables
            //the position setting on his phone
            double latitude = -1;
            double longitude = -1;
            if (parkingLocation != null) {
                latitude = parkingLocation.getLatitude();
                longitude = parkingLocation.getLongitude();
                if (Constants.LOGS) Log.e("FunctionCallsReceiver", "parking location extracted");
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"isInCar");
            builder.setContentText("Did exit car on lat: "+latitude+" - lon: "+longitude);
            builder.setSmallIcon( hrituc.studenti.uniroma1.it.generocityframework.R.mipmap.ic_launcher );
            builder.setContentTitle( "FunctionCallsReceiver" );
            NotificationManagerCompat.from(context).notify(8, builder.build());
        } else if (Constants.WILL_EXIT_CAR.equals(action)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"willExit");
            builder.setContentText("Will exit car");
            builder.setSmallIcon( hrituc.studenti.uniroma1.it.generocityframework.R.mipmap.ic_launcher );
            builder.setContentTitle( "FunctionCallsReceiver" );
            NotificationManagerCompat.from(context).notify(9, builder.build());
        } else if (Constants.TRIP_POINT.equals(action)) {
            //extract the data from the intent
            TripPoint tripPoint = TripPoint.fromIntent(intent);
            double lat = tripPoint.getLatitude();
            double lon = tripPoint.getLongitude();
            float speed = tripPoint.getSpeed();
            Timestamp timestamp = tripPoint.getTimestamp();
            //do stuff with these

            JSONObject jArrayTripPointData = new JSONObject();
            JSONObject jObjectType = new JSONObject();

            // put elements into the object as a key-value pair
            jObjectType.put("type", "facebook_login");

            jArrayFacebookData.put("system", jObjectType);

            // 2nd array for user information
            JSONObject jObjectData = new JSONObject();


            // Create Json Object using Facebook Data
            jObjectData.put("facebook_user_id", id);
            jObjectData.put("first_name", first_name);
            jObjectData.put("last_name", last_name);
            jObjectData.put("email", email);
            jObjectData.put("username", username);
            jObjectData.put("birthday", birthday);
            jObjectData.put("gender", gender);
            jObjectData.put("location", place);
            jObjectData.put("display_photo", display_photo_url);

            jArrayFacebookData.put("data", jObjectData);




            if (Constants.LOGS) Log.e("FunctionCallsReceiver", "trip-point extracted");
        } else if(Constants.WILL_ENTER_CAR.equals(action)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"willEnter");
            builder.setContentText("Will enter car");
            builder.setSmallIcon( hrituc.studenti.uniroma1.it.generocityframework.R.mipmap.ic_launcher );
            builder.setContentTitle( "FunctionCallsReceiver" );
            NotificationManagerCompat.from(context).notify(10, builder.build());
        } else if (action.equals("UPDATE_GEOFENCE_LIST")) {
            String id = intent.getStringExtra("id");
            double latitude = intent.getDoubleExtra("latitude",-1);
            double longitude = intent.getDoubleExtra("longitude",-1);
           // MainActivity.updateGefenceMap(id,latitude,longitude);
        }
    }
}





/*public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("RECEIVER", "------------------RECEIVED NULL--------------");
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals( Constants.TRIP_POINT)) {
                Log.e("RECEIVER", "------------------RECEIVED TRIPPOINT--------------");
                StringBuilder sb = new StringBuilder();
                sb.append("Action: " + intent.getAction() + "\n");
                sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
                String log = sb.toString();
                Log.d(TAG, log);
                Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            }
        }
    }
}
   */