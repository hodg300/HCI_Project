package com.example.hci_project;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hci_project.Utils.Finals;
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

public class VisitorMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private ImageView search_btn;
    public static ArrayList<Place> places = new ArrayList<>();
    public static ArrayList<Invitation> invitations = new ArrayList<>();
    public static User currentUser;
    private Button clock;
    private ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        currentUser = (User) getIntent().getSerializableExtra(Finals.USER);
        mapFragment.getMapAsync(this);
        setIds();
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

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this,SideMenuActivity.class);
        intent.putExtra(Finals.USER, currentUser);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void goToSearchActivity(){
        Intent intent = new Intent(this,SearchActivity.class);
        intent.putExtra(Finals.USER,currentUser);
        startActivity(intent);
    }

    private void setIds() {
        search_btn = findViewById(R.id.search);
        clock = findViewById(R.id.clock_btn);
        menu = findViewById(R.id.menu);
    }

    private void createLocations() {

        //החוג הצפוני
        places.add(new Place("HaHoog Hatzfoni",R.drawable.ahug_hatsfoni_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ",
                new LatLng(32.113611, 34.801954),126,false,200,
                "10:00 - 23:00","Students israeli bar", 0));
        //שגב אקספרס
        places.add(new Place("Segev Express",R.drawable.segev_express_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.1100635,34.843054),250,true,250,
                "18:00 - 04:00","Italian kitchen", 1));
        //סוסו אנד סאנס
        places.add(new Place("Susu and Sons",R.drawable.susu_and_sons_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.0368665,34.9649833),121,false,130,
                "16:00 - 03:00","Texas hamburger", 2));
        //מוזיאון תל אביב
        places.add(new Place("Tel Aviv Museum",R.drawable.tel_aviv_museum,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.0938103,34.8110533),500,true,500,
                "08:00 - 15:00","Legacy of Israel", 3));

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

        PlaceDialog dialog = new PlaceDialog(place,currentUser);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }
}
