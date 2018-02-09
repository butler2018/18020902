package com.example.student.a18020902;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    public static String add;
    public static String nowLocation;
    LocationManager lm;
   // TextView tv;
   // MyListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
       // listener = new MyListener();  //於頁面顯示現在地址
        Nowlocation();
    }

//    public void click1(View v)
//    {
//
//
//    }

    public void Nowlocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[] {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 321
            );
            return;
        } else {
            startLoc();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                startLoc();
            } else {

            }
        }
    }

    private void startLoc() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyListener());
    }
    class MyListener implements android.location.LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.d("LOC", "Change!! " + location.getLatitude() + "," + location.getLongitude());
            Location loc101 = new Location("LOC");
            loc101.setLatitude(25.0336);
            loc101.setLongitude(121.5646);
            nowLocation = (location.getLatitude() + "," + location.getLongitude());
            float dist = location.distanceTo(loc101);
            Log.d("LOC", "Dist:" + dist);

            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.TRADITIONAL_CHINESE);//設定語言
            //lm.removeUpdates(listener); //停止更新
            //Geocoder gc = new Geocoder(getActivity(), Locale.TRADITIONAL_CHINESE);
            try {
                List<Address> mylist = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                Address addr = mylist.get(0);
                add = addr.getAddressLine(0);
                Log.d("LOC", addr.getAddressLine(0));
               // tv.setText(String.valueOf(add)); // 地址放入
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
