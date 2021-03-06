package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;
import com.example.hci_project.Views.PlaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends Activity {

    private EditText searchEditText;
    private ArrayList<Place> searchedPlaces = new ArrayList<>();
    private ArrayList<PlaceView> placesView = new ArrayList<>();

    private LinearLayout placesHolder;
    private DynamicXML dynamicXML = new DynamicXML();
    private Map<LinearLayout,Place> linearToPlaceMap;
    private ImageView menu;
    private User user;
    private RelativeLayout searchWindow;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setContentView(R.layout.activity_search);
        linearToPlaceMap = new HashMap<>();
        activity = this;
        setIds();
        setOnClickListeners();
        searchPlaces("");
        showPlaces();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(searchWindow);
    }



    private void setOnClickListeners() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // fill here
               searchPlaces(charSequence);
               showPlaces();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });
    }

    private void showPlaces() {

        placesHolder.removeAllViews();

        if(searchedPlaces.size() == 0){
            TextView noResultTV = dynamicXML.createTextView(this,"We didn't find any result.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            placesHolder.addView(noResultTV);
            return;
        }
        for(Place place : searchedPlaces){
            PlaceView placeView = null;
            if(user.getRole().toLowerCase().equals(Finals.VISITOR)) {
                 placeView = new PlaceView(this,
                        dynamicXML.createTextView(this, place.getName(), "sans-serif-condensed", 20, Color.BLACK, Gravity.TOP, 0, 0, 0, 0),
                        dynamicXML.createImageView(this, place.getImage(), Gravity.CENTER, 0, 5, 0, 5, 350, LinearLayout.LayoutParams.WRAP_CONTENT),
                        dynamicXML.createTextView(this, place.getDescription(), "sans-serif-condensed", 15, Color.BLACK, Gravity.CENTER, 0, 0, 0, 0),
                        place,user);
            } else if(user.getRole().toLowerCase().equals(Finals.MINISTRY_OF_HEALTH)){
                    placeView = new PlaceView(this,
                        dynamicXML.createTextView(this, place.getName(), "sans-serif-condensed", 20, Color.BLACK, Gravity.TOP, 0, 0, 0, 0),
                        dynamicXML.createImageView(this, place.getImage(), Gravity.CENTER, 0, 5, 0, 5, 350, LinearLayout.LayoutParams.WRAP_CONTENT),
                        dynamicXML.createTextView(this, place.getNumOfVisitors() + "/" + place.getMaxVisitors(), "sans-serif-condensed", 15, Color.BLACK, Gravity.CENTER, 0, 0, 0, 0),
                        place,user);
            }else if(user.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                placeView = new PlaceView(this,
                        dynamicXML.createTextView(this, place.getName(), "sans-serif-condensed", 20, Color.BLACK, Gravity.TOP, 0, 0, 0, 0),
                        dynamicXML.createImageView(this, place.getImage(), Gravity.CENTER, 0, 5, 0, 5, 350, LinearLayout.LayoutParams.WRAP_CONTENT),
                        dynamicXML.createTextView(this, place.getNumOfVisitors() + "/" + place.getMaxVisitors(), "sans-serif-condensed", 15, Color.BLACK, Gravity.CENTER, 0, 0, 0, 0),
                        place,user);
            }

            linearToPlaceMap.put(placeView.getCard(),place);
            placesView.add(placeView);
            placesHolder.addView(placeView.getCard());

            setOnClickPlaceViewListeners(placeView);
        }

    }

    private void setOnClickPlaceViewListeners(PlaceView placeView){

        placeView.getCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getRole().toLowerCase().equals(Finals.VISITOR)) {
                    goToVisitorAboutPlaceActivity(((Place) linearToPlaceMap.get(view)).getIndex());
                } else if(user.getRole().toLowerCase().equals(Finals.MINISTRY_OF_HEALTH)){
                    goToMinistryOfHealthAboutPlaceActivity(((Place) linearToPlaceMap.get(view)).getIndex());
                }
                 else if(user.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
                    goToPoliceAboutPlaceActivity(((Place) linearToPlaceMap.get(view)).getIndex());
                }
            }
        });


    }



    private void goToPoliceAboutPlaceActivity(int pressedPlaceIndex) {

        Intent intent = new Intent(this, PoliceAboutPlaceActivity.class);
        intent.putExtra(Finals.USER,user);
        intent.putExtra(Finals.PLACE_INDEX,pressedPlaceIndex);
        startActivity(intent);
    }

    private void goToMinistryOfHealthAboutPlaceActivity(int pressedPlaceIndex) {

        Intent intent = new Intent(this, MinistryOfHealthAboutPlaceActivity.class);
        intent.putExtra(Finals.USER,user);
        intent.putExtra(Finals.PLACE_INDEX,pressedPlaceIndex);
        startActivity(intent);
        finish();
        
    }

    private void goToVisitorAboutPlaceActivity(int pressedPlaceIndex){
        Intent intent = new Intent(this, VisitorAboutPlaceActivity.class);
        intent.putExtra(Finals.USER,user);
        intent.putExtra(Finals.PLACE,pressedPlaceIndex);
        startActivity(intent);
        finish();
    }

    private void goToSideMenuActivity(){
        Intent intent = null;
        if(user.getRole().toLowerCase().equals(Finals.MINISTRY_OF_HEALTH)) {
             intent = new Intent(this, MinistryOfHealthSideMenuActivity.class);
        }else if(user.getRole().toLowerCase().equals(Finals.VISITOR)){
            intent = new Intent(this, VisitorSideMenuActivity.class);
        }else if(user.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)) {
            intent = new Intent(this, PoliceSideMenuActivity.class);
        }

        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void searchPlaces(CharSequence charSequence) {

        searchedPlaces.clear();
        
        for(Place place : WaitingActivity.places){
            if(place.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                searchedPlaces.add(place);
            }
        }
    }

    private void setIds() {
        searchEditText = findViewById(R.id.search_editText);
        placesHolder = findViewById(R.id.places_holder);
        menu = findViewById(R.id.menu);
        searchWindow = findViewById(R.id.search_window);
    }
}