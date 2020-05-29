package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Views.PlaceView;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    private EditText searchEditText;
    private ArrayList<Place> searchedPlaces = new ArrayList<>();
    private ArrayList<PlaceView> placesView = new ArrayList<>();
    private LinearLayout placesHolder;
    private DynamicXML dynamicXML = new DynamicXML();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setIds();
        setOnClickListeners();
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

            PlaceView placeView = new PlaceView(this,
                    dynamicXML.createTextView(this,place.getName(),"sans-serif-condensed",20,Color.BLACK,Gravity.TOP,0,0,0,0),
                    dynamicXML.createImageView(this,place.getImage(),Gravity.CENTER,0,5,0,5),
                    dynamicXML.createTextView(this,place.getDescription(),"sans-serif-condensed",15,Color.BLACK,Gravity.CENTER,0,0,0,0));
            placesView.add(placeView);
            placesHolder.addView(
                    placeView.getCard()
            );
        }
    }

    private void searchPlaces(CharSequence charSequence) {

        searchedPlaces.clear();
        
        for(Place place : MapActivity.places){
            if(place.getName().contains(charSequence)){
                searchedPlaces.add(place);
            }
        }
    }

    private void setIds() {
        searchEditText = findViewById(R.id.search_editText);
        placesHolder = findViewById(R.id.places_holder);
    }
}