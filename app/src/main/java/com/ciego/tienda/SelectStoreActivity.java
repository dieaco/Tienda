package com.ciego.tienda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectStoreActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener {

    private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_store);

        initViews();
	}

    private void initViews(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.map);
        SupportMapFragment supportMapFragment = (SupportMapFragment)fragment;
        map = supportMapFragment.getMap();

        moveCamera2();
        addStore("Blind's Store", new LatLng(19.432602, -99.133205));
        addStore("Blind's Store", new LatLng(19.462602, -99.123205));
        addStore("Blind's Store", new LatLng(19.452602, -99.113205));
        addStore("Blind's Store", new LatLng(19.4331448,-99.1406899));

        map.setOnMarkerClickListener(this);
    }

    private void moveCamera(){
        LatLng coors = new LatLng(34.78, -32.98);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(coors);
        map.moveCamera(cameraUpdate);

    }

    private void moveCamera2(){

        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(new LatLng(19.432602,-99.133205));
        builder.zoom(16);

        CameraPosition cameraPosition = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        //map.moveCamera(cameraUpdate);
        map.animateCamera(cameraUpdate, 2000, null);
    }

    private void addStore(String storeName, LatLng latLang){
        /*map.addMarker(new MarkerOptions()
            .position(new LatLng(19.432602,-99.133205))
            .title(storeName)
            .snippet("New Store")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );*/

        map.addMarker(new MarkerOptions()
                        .position(latLang)
                        .title(storeName)
                        .snippet("New Store")
                       .icon(BitmapDescriptorFactory.fromResource(R.drawable.successful_page_heart))
        );
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        String store = marker.getTitle();

        Intent intent = new Intent();
        intent.putExtra("STORE", store);

        setResult(Activity.RESULT_OK, intent);
        finish();

        return true;
    }
}
