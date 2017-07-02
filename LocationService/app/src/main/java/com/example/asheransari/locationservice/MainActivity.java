package com.example.asheransari.locationservice;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_stop;
    TextView tv_long, tv_lat;
    boolean permission;
    private static final int REQUEST_PERMISSIONS = 100;
    SharedPreferences mPref;
    SharedPreferences.Editor mEditor;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        tv_long = (TextView) findViewById(R.id.longitudeTxt);
        tv_lat = (TextView) findViewById(R.id.latitudeTxt);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPref.edit();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (permission) {
                    if (mPref.getString("service", "").matches("")) {
                        mEditor.putString("service", "found").commit();
                        Intent i = new Intent(getApplicationContext(), tempService.class);
                        startService(i);
                        Toast.makeText(MainActivity.this, "Service has been Started..", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Service is already running", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enable the gps", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        btn_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(getApplicationContext(), "Disabled Services..", Toast.LENGTH_SHORT).show();
//                if (mPref.getString("service", "found").matches("found")) {
////                    Toast.makeText(MainActivity.this, "Match", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(getApplicationContext(), tempService.class);
//                    getApplicationContext().stopService(i);
//                } else {
//                    Toast.makeText(MainActivity.this, "Services Not Found..!!", Toast.LENGTH_SHORT).show();
//                }

//                if (isMyServiceRunning(tempService.class)) {
//                    stopService(new Intent(MainActivity.this, tempService.class));
//                    mEditor.putString("service", "").commit();
//                }
//            }
//        });

        check_permission();
        readDatabase(this);
    }

    public void stopService(View view) {
        if (mPref.getString("service","found").matches("found")) {
            getApplicationContext().stopService(new Intent(getApplicationContext(), tempService.class));
            mEditor.putString("service", "").commit();
        }
         else {
//            Log.e("problem", "in Else condition");
            Toast.makeText(this, "Service Already Finished...!!", Toast.LENGTH_SHORT).show();
         }
    }

    private void readDatabase(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {tableColumn.LONTIUDE, tableColumn.LATITUDE};
        Cursor cursor = database.query(tableColumn.TABLE_NAME, projection, null, null, null, null, null);
        int longIndex = cursor.getColumnIndex(tableColumn.LONTIUDE);
        int latIndex = cursor.getColumnIndex(tableColumn.LATITUDE);
        while (cursor.moveToNext()) {
//            Log.e("Long","Long:"+cursor.getString(longIndex)+" & Latitude:"+cursor.getString(latIndex));
            tv_long.append("\n\n" + (counter++) + ": " + cursor.getString(longIndex));
            tv_lat.append("\n\n" + cursor.getString(latIndex));
        }
    }

    private void check_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);

            }
        } else {
            permission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permission = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
