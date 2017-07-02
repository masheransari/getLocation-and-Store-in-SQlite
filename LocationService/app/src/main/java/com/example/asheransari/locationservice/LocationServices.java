package com.example.asheransari.locationservice;

import android.Manifest;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by asher.ansari on 6/30/2017.
 */

public class LocationServices extends Service {

    //    public static final String BROADCAST_ACTION = "Hello World";
//    public static final int TWO_MINUTE = 100 * 60 * 1;
//    private LocationManager locationManager;
//    private MyLocationListner listner;
//    public Location previousBestLocation = null;
//    DBHelper dbHelper;
//
//    Intent intent;
//    int counter = 0;
//    TextView tv_long, tv_lat;
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "Service has been Started..", Toast.LENGTH_SHORT).show();
//
//        return START_STICKY;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        intent = new Intent(BROADCAST_ACTION);
//        dbHelper = new DBHelper(getApplicationContext());
//    }
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//
//        listner = new MyLocationListner();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, listner);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,listner);
//    }
//
//    public boolean isBetterLocation(Location location, Location currentLocation){
//        if(currentLocation == null){
//            return true;
//        }
//        // Check whether the new location fix is newer or older
//        long timeDelta = location.getTime() - currentLocation.getTime();
//        boolean isSignificantlyNewer = timeDelta > TWO_MINUTE;
//        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTE;
//        boolean isNewer = timeDelta > 0;
//
//        // If it's been more than two minutes since the current location, use the new location
//        // because the user has likely moved
//        if (isSignificantlyNewer) {
//            return true;
//            // If the new location is more than two minutes older, it must be worse
//        } else if (isSignificantlyOlder) {
//            return false;
//        }
//        return false;
////
////        // Check whether the new location fix is more or less accurate
////        int accuracyDelta = (int) (location.getAccuracy() - currentLocation.getAccuracy());
////        boolean isLessAccurate = accuracyDelta > 0;
////        boolean isMoreAccurate = accuracyDelta < 0;
////        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
////
////        // Check if the old and new location are from the same provider
////        boolean isFromSameProvider = isSameProvider(location.getProvider(),
////                currentLocation.getProvider());
////
////        // Determine location quality using a combination of timeliness and accuracy
////        if (isMoreAccurate) {
////            return true;
////        } else if (isNewer && !isLessAccurate) {
////            return true;
////        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
////            return true;
////        }
////        return false;
//    }
//
//
//
//    /** Checks whether two providers are the same */
//    private boolean isSameProvider(String provider1, String provider2) {
//        if (provider1 == null) {
//            return provider2 == null;
//        }
//        return provider1.equals(provider2);
//    }
//    public LocationServices() {
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        locationManager.removeUpdates(listner);
//        Toast.makeText(this, "Service has been Destroyed..", Toast.LENGTH_SHORT).show();
//    }
//
////    public static Thread performOnBackgroundThread(final Runnable runnable){
////
////        final Thread thread = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try{
////                    runnable.run();
////                }
////                finally {
////
////                }
////            }
////        });
////        thread.start();
////        return thread;
////    }
//
//    public class MyLocationListner implements LocationListener{
//
//        @Override
//        public void onLocationChanged(Location loc) {
//            Log.i("**************************************", "Location changed");
//            if(isBetterLocation(loc, previousBestLocation)) {
//                loc.getLatitude();
//                loc.getLongitude();
//                SQLiteDatabase database = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(tableColumn.LONTIUDE,loc.getLongitude());
//                values.put(tableColumn.LATITUDE,loc.getLatitude());
//                database.insert(tableColumn.TABLE_NAME,null,values);
//                Log.e("Database ItemCount", (counter++)+"INserted in Database");
//
////                intent.putExtra("Latitude", loc.getLatitude());
////                intent.putExtra("Longitude", loc.getLongitude());
////                intent.putExtra("Provider", loc.getProvider());
//                sendBroadcast(intent);
//
//            }
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//            Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//            Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
//
//        }
//    }
////    private class TimeTaskToGetLocation extends TimerTask{
////
////        @Override
////        public void run() {
//////            fn_location();
////            Handler handle = new Handler();
////            handle.post(new Runnable() {
////                @Override
////                public void run() {
//////                    fn_location();
////                }
////            });
////        }
////    }
//}
//
//
//
//
//
//
//
//
//public class LocationServices extends Service implements LocationListener {
//
//    boolean isGpsEnabled = false;
//    boolean isNetworkEnabled = false;
//    double longitude = 0.0, latitude = 0.0;
//    LocationManager locationManager;
//    private Handler handler = new Handler();
//    Location location;
//    private Timer mTimer = null;
//    long notifiy_interval = 1000;
//    public static String str_service = "asheransari.locationservice.receiver";
//    Intent intent;
//
//    public LocationServices() {
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        mTimer = new Timer();
//            mTimer.schedule(new TimeTaskToGetLocation(), 5, notifiy_interval);
//
//        intent = new Intent(str_service);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//    //Yeha pa ka location Listner ka kam hwa hai ga..
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {
//
//    }
//
//    private void fn_location() {
//        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        if (!isGpsEnabled && !isNetworkEnabled) {
//
//        } else {
//            if (isNetworkEnabled) {
//                location = null;
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
//                if (locationManager != null) {
//                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                    if (location != null) {
//                        longitude = location.getLongitude();
//                        latitude = location.getLatitude();
//                        Log.e("locations", "Longitude: " + longitude + " & Latitude: " + latitude);
//                        fn_update(location);
//                    }
//                }
//            } else if (isGpsEnabled) {
//                location = null;
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
//                if (locationManager != null) {
//                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    if (location != null) {
//                        longitude = location.getLongitude();
//                        latitude = location.getLatitude();
//                        Log.e("locations", "Longitude: " + longitude + " & Latitude: " + latitude);
//                        fn_update(location);
//                    }
//                }
//            }
//            else{
//                Toast.makeText(this, "No Internet Connection Found..!!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
////
//
//    private class TimeTaskToGetLocation extends TimerTask{
//
//        @Override
//        public void run() {
////            fn_location();
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    fn_location();
//                }
//            });
//        }
//    }
//
////
//
//    private void fn_update(Location location){
//        intent.putExtra("latitude",location.getLatitude());
//        intent.putExtra("longtide",location.getLongitude());
//        intent.putExtra("speed",location.getSpeed());
//        sendBroadcast(intent);
//    }
//
//
//



    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class listner implements LocationListener {

        Location mLocation;

        public listner(String provider) {
            this.mLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {

            Log.e(TAG, "onLocationChanged: " + location);
            mLocation.set(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.e(TAG, "onStatusChanged: " + s);
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.e(TAG, "onProviderEnabled: " + s);
        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
//    LocationListener[] mLocationListeners = new LocationListener[]{
//            new LocationListener(LocationManager.GPS_PROVIDER,
//                    new LocationListener(LocationManager.NETWORK_PROVIDER);
//};


    LocationListener[] mLocationListeners = new LocationListener[] {
//            new LocationListener(LocationManager.GPS_PROVIDER),
//            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLocationManager();
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }


    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }
}

