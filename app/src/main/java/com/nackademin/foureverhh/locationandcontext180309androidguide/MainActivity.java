package com.nackademin.foureverhh.locationandcontext180309androidguide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION =1;
    private FusedLocationProviderClient mFusedLocationClient;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Log.d("MainActivity","it runs here");
        if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("MainActivity", "it runs here inside if");
          Log.d("MainActivity","No permission");
          //ask for permission
         ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {


                @Override
                public void onSuccess(Location location) {
                    //Got last known location. In some rare situations this can be null
                    Log.d("MainActivity", "it runs here as will");
                    Log.d("Location",String.valueOf(location));
                    if (location != null) {
                        //logic to handle the location
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();
                        Log.d("Location", "Longitude is : " + String.valueOf(longitude) + "Latitude is : " + String.valueOf(latitude));
                    }
                }
            });
        }
    }


}
