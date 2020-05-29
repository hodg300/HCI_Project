package com.example.hci_project;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private ArrayList<LatLng> locations = new ArrayList<>();
    private ArrayList<Marker> markers = new ArrayList<>();
    private ImageView search_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setId();
        createLocations();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSearchActivity();
            }
        });
    }

    private void goToSearchActivity(){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    private void setId() {
        search_btn = findViewById(R.id.search);
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
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(0)).title("החוג הצפוני").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Segev Expresss
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(1)).title("שגב אקספרס")));
        //Susu and Sons
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(2)).title("סוסו אנד סאן").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Tel Aviv musium
        markers.add(map.addMarker(new MarkerOptions().position(locations.get(3)).title("מוזיאון תל אביב")));


        //Move Camera to Segev EXPRESS
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);
        map.moveCamera(CameraUpdateFactory.newLatLng(locations.get(1)));
        map.animateCamera(zoom);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (markers.get(0).equals(marker)){
            //החוג הצפוני
            openRestaurantDialog(marker.getTitle(),R.drawable.ahug_hatsfoni_img,126,false);
        } else if(markers.get(1).equals(marker)){
            //שגב אקספרס
            openRestaurantDialog(marker.getTitle(),R.drawable.segev_express_img,250,true);

        } else if(markers.get(2).equals(marker)){
            //סוסו אנד סאנס
            openRestaurantDialog(marker.getTitle(),R.drawable.susu_and_sons_img,121,false);

        }else if(markers.get(3).equals(marker)){
            //מוזיאון תל אביב
            openRestaurantDialog(marker.getTitle(),R.drawable.tel_aviv_museum,500,true);

        }

        return true;
    }

    private void openRestaurantDialog(String nameOfRestaurant, int img, int visitors,boolean isFull){

        PlaceDialog dialog = new PlaceDialog(nameOfRestaurant,img,visitors,isFull);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }
}
