package com.example.hci_project;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private ImageView search_btn;
    public static ArrayList<Invitation> invitations = new ArrayList<>();
    public static User currentUser;
    private Button clock;
    private ImageView menu;
    private int hours;
    private int minutes;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        currentUser = (User) getIntent().getSerializableExtra(Finals.USER);
        mapFragment.getMapAsync(this);
        setIds();
        setOnClickListeners();
        changeTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleTime();
    }

    private void handleTime() {
        new Handler().postDelayed(new Runnable() {
            public void run() {

              changeTime();
              handleTime();

            }
        }, 5000);

    }

    private void changeTime(){

        Log.d("TIME", "im here on changeTime: ");
        // current time

       hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        clock.setText(hours +":" + minutes);
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
        Intent intent = new Intent(this, VisitorSideMenuActivity.class);
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


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        googleMap.setOnMarkerClickListener(this);

        //aHug Hatzfoni
        WaitingActivity.places.get(0).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(0).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Segev Expresss
        WaitingActivity.places.get(1).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(1).getLatlng())));
        //Susu and Sons
        WaitingActivity.places.get(2).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(2).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        //Tel Aviv musium
        WaitingActivity.places.get(3).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(3).getLatlng())));


        //Move Camera to Segev EXPRESS
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(14);
        map.moveCamera(CameraUpdateFactory.newLatLng(WaitingActivity.places.get(1).getLatlng()));
        map.animateCamera(zoom);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (WaitingActivity.places.get(0).getMarker().equals(marker)){
            //החוג הצפוני
            openPlaceDialog(WaitingActivity.places.get(0));
        } else if(WaitingActivity.places.get(1).getMarker().equals(marker)){
            //שגב אקספרס
            openPlaceDialog(WaitingActivity.places.get(1));
        } else if(WaitingActivity.places.get(2).getMarker().equals(marker)){
            //סוסו אנד סאנס
            openPlaceDialog(WaitingActivity.places.get(2));

        }else if(WaitingActivity.places.get(3).getMarker().equals(marker)){
            //מוזיאון תל אביב
            openPlaceDialog(WaitingActivity.places.get(3));
        }

        return true;
    }

    private void openPlaceDialog(Place place){

        PlaceDialog dialog = new PlaceDialog(place,currentUser);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }
}
