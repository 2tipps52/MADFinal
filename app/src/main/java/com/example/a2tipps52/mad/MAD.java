package com.example.a2tipps52.mad;

import android.content.Intent;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MAD extends AppCompatActivity {
    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_mad);
        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            public boolean onItemLongPress(int i, OverlayItem item) {
                Toast.makeText(MAD.this, item.getTitle() + " " + item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item) {
                Toast.makeText(MAD.this, item.getTitle() + " " + item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.madmaen, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addpoi) {
            Intent intent = new Intent(this, ADDActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if (item.getItemId() == R.id.savepoi){
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/savemaen.txt"));
                for (int i = 0; i < items.size(); i++) {
                    OverlayItem itemmaen = items.getItem(i);
                    writer.println(itemmaen.getTitle() + "," + itemmaen.getSnippet() + "," + itemmaen.getPoint() + "");
                }
                writer.close();

            } catch (IOException e) {
                System.out.println("Could not save a place" + e);
            }
        }
        return false;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = intent.getExtras();
                String name = bundle.getString("com.example.naem");
                String description = bundle.getString("com.example.destxt");

                double longgggg = mv.getMapCenter().getLongitude();
                double latttttt = mv.getMapCenter().getLatitude();

                OverlayItem AnItem = new OverlayItem(name, description, new GeoPoint(latttttt, longgggg));
                items.addItem(AnItem);
            }
        }

    }
}


