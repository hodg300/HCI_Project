package com.example.hci_project;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
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
    private ImageView searchBtn;
    public static ArrayList<Invitation> invitations = new ArrayList<>();
    public static User currentUser;
    private ImageView menu;
    public static Activity activity;
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
        activity = this;
        mapFragment.getMapAsync(this);
        setIds();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




    private void setOnClickListeners() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = null;
        if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
             intent = new Intent(this, VisitorSideMenuActivity.class);
        } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)) {
            intent = new Intent(this, PoliceSideMenuActivity.class);
        }
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
        searchBtn = findViewById(R.id.search);
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
        //Moses
        WaitingActivity.places.get(4).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(4).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));

        //Beer Garden
        WaitingActivity.places.get(5).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(5).getLatlng())));

        WaitingActivity.places.get(6).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(6).getLatlng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));

        WaitingActivity.places.get(7).setMarker(map.addMarker(new MarkerOptions().position(WaitingActivity.places.get(7).getLatlng())));


        //Move Camera to Segev EXPRESS
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
        map.moveCamera(CameraUpdateFactory.newLatLng(WaitingActivity.places.get(1).getLatlng()));
        map.animateCamera(zoom);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (WaitingActivity.places.get(0).getMarker().equals(marker)){
            //החוג הצפוני
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(0));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(0));
            }
        } else if(WaitingActivity.places.get(1).getMarker().equals(marker)){
            //שגב אקספרס
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(1));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(1));
            }
        } else if(WaitingActivity.places.get(2).getMarker().equals(marker)){
            //סוסו אנד סאנס
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(2));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(2));
            }

        }else if(WaitingActivity.places.get(3).getMarker().equals(marker)){
            //מוזיאון תל אביב
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(3));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(3));
            }

            //מוזס
        }else if(WaitingActivity.places.get(4).getMarker().equals(marker)){
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(4));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(4));
            }
        }

        //ביר גארדן
        else if(WaitingActivity.places.get(5).getMarker().equals(marker)){
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(5));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(5));
            }
        }

        else if(WaitingActivity.places.get(6).getMarker().equals(marker)){
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(6));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(6));
            }
        }

        else if(WaitingActivity.places.get(7).getMarker().equals(marker)){
            if(currentUser.getRole().toLowerCase().equals(Finals.VISITOR)) {
                openPlaceDialog(WaitingActivity.places.get(7));
            } else if(currentUser.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                openPolicePlaceDialog(WaitingActivity.places.get(7));
            }
        }

        return true;
    }

    private void openPolicePlaceDialog(Place place) {

        PoliceAboutPlaceDialog dialog = new PoliceAboutPlaceDialog(place,currentUser);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");

    }

    private void openPlaceDialog(Place place){

        PlaceDialog dialog = new PlaceDialog(place,currentUser);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }
}
