package com.example.nicolas.test_foursquare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private static final String LOG_TAG = "MainActivity";
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private ListView mListView;

    private void buildGoogleClient(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleClient();
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        List<PointOfInterest> interestPoint = loadInterestPoint();

        PointOfInterestAdapter adapter = new PointOfInterestAdapter(MainActivity.this, 0, interestPoint);
        mListView.setAdapter(adapter);
    }

    private List<PointOfInterest> loadInterestPoint()  {
        FourSquareSearch fss = new FourSquareSearch( "0","0" );
        JsonReader jsonReader = null;
        List<PointOfInterest> interestPoints = null;
        Log.d(LOG_TAG, "Test1");

        try {
            jsonReader = new JsonReader( new FileReader( "Request.json" ) );
            interestPoints = fss.readMessagesArray( jsonReader );

        }
        catch(IOException e){
            Log.d(LOG_TAG, "IOException  - " + e);

        }
        for(PointOfInterest i : interestPoints){
            Log.d(LOG_TAG, "Print  - " + i);
        }
        //List<PointOfInterest> interestPoints = new ArrayList<PointOfInterest>();
        /*interestPoints.add(new PointOfInterest("1","Café", null));
        interestPoints.add(new PointOfInterest("2","Café", null));
        interestPoints.add(new PointOfInterest("3","Café", null));
        interestPoints.add(new PointOfInterest("4","Café", null));
        */
        return interestPoints;

    }

    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "Start Application");
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.connect();
            Log.d(LOG_TAG, "Connection established :  - " + this.mGoogleApiClient.isConnected());
        }
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.nicolas.test_foursquare/http/host/path")
        );
    }

    @Override
    protected void onStop() {
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
        }
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.nicolas.test_foursquare/http/host/path")
        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(LOG_TAG, "Permission ACCESS_FINE_LOCATION is not granted");
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_FINE_LOCATION );

        }

        Log.d(LOG_TAG, "Permission ACCESS_FINE_LOCATION is granted.");
        this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
        if (this.mLastLocation != null) {
            Log.d("Latitude", String.valueOf(this.mLastLocation.getLatitude()));
            Log.d("Longitude", String.valueOf(this.mLastLocation.getLongitude()));
        }
        else
            Log.d(LOG_TAG, "Location Not Found");

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(LOG_TAG, "Connection to Google Play Services suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Connection to Google Play Services failed." +
                "ERROR : " + connectionResult.getErrorCode());
        /**
         * TODO: treat the connection failure on the ConnectionResult
         * https://developers.google.com/android/guides/api-client#Starting : Handle connection failures
         * */

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            Log.d(LOG_TAG, "GooglePlay available");
            return true;
        } else {
            Log.d(LOG_TAG, "GooglePlay not available");
            return false;
        }
    }

    public void runListPoint(View view){

    }
}

