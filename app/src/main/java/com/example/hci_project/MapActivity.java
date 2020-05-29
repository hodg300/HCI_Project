package com.example.hci_project;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private ArrayList<LatLng> locations = new ArrayList<>();
    private ArrayList<Marker> markers = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        createLocations();
    }

    private void createLocations() {

        locations.add(new LatLng(32.113611, 34.801954)); //aHug Hatzfoni
        locations.add(new LatLng(32.1100635,34.843054)); //Segev Expresss
        locations.add(new LatLng(32.0368665,34.9649833)); //Susu and Sons
        locations.add(new LatLng(32.0938103,34.8110533)); //Tel Aviv musium


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        googleMap.setOnMarkerClickListener(this);

        //aHug Hatzfoni
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(0)).title("החוג הצפוני")));
        //Segev Expresss
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(1)).title("שגב אקספרס")));
        //Susu and Sons
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(1)).title("סוסו אנד סאן")));
        //Tel Aviv musium
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(1)).title("מוזיאון תל אביב")));


        //Move Camera to Segev EXPRESS
        map.moveCamera(CameraUpdateFactory.newLatLng(locations.get(1)));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (markers.get(0).equals(marker)){

        } else if(markers.get(1).equals(marker)){

        } else if(markers.get(2).equals(marker)){

        }else if(markers.get(3).equals(marker)){

        }

        return true;
    }
}
