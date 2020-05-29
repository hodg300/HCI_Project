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
    private ImageView search_btn;
    public static ArrayList<Place> places = new ArrayList<>();


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

        //החוג הצפוני
        places.add(new Place("HaHoog Hatzfoni",R.drawable.ahug_hatsfoni_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ",
                new LatLng(32.113611, 34.801954),126,false));
        //שגב אקספרס
        places.add(new Place("Segev Express",R.drawable.segev_express_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.1100635,34.843054),250,true));
        //סוסו אנד סאנס
        places.add(new Place("Susu and Sons",R.drawable.susu_and_sons_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.0368665,34.9649833),121,false));
        //מוזיאון תל אביב
        places.add(new Place("Tel Aviv Museum",R.drawable.tel_aviv_museum,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.0938103,34.8110533),500,true));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        googleMap.setOnMarkerClickListener(this);

        //aHug Hatzfoni
        places.get(0).setMarker(map.addMarker(new MarkerOptions().position(places.get(0).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Segev Expresss
        places.get(1).setMarker(map.addMarker(new MarkerOptions().position(places.get(1).getLatlng())));
        //Susu and Sons
        places.get(2).setMarker(map.addMarker(new MarkerOptions().position(places.get(2).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Tel Aviv musium
        places.get(3).setMarker(map.addMarker(new MarkerOptions().position(places.get(3).getLatlng())));


        //Move Camera to Segev EXPRESS
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(14);
        map.moveCamera(CameraUpdateFactory.newLatLng(places.get(1).getLatlng()));
        map.animateCamera(zoom);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (places.get(0).getMarker().equals(marker)){
            //החוג הצפוני
            openPlaceDialog(places.get(0));
        } else if(places.get(1).getMarker().equals(marker)){
            //שגב אקספרס
            openPlaceDialog(places.get(1));
        } else if(places.get(2).getMarker().equals(marker)){
            //סוסו אנד סאנס
            openPlaceDialog(places.get(2));

        }else if(places.get(3).getMarker().equals(marker)){
            //מוזיאון תל אביב
            openPlaceDialog(places.get(3));
        }

        return true;
    }

    private void openPlaceDialog(Place place){

        PlaceDialog dialog = new PlaceDialog(place);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }
}
