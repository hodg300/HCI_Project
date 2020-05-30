package com.example.hci_project;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutPlaceActivity extends Activity {
    final String CURRENT_VISITORS = "Current visitors: ";
    private Place place;
    private ImageView imagePlace;
    private TextView nameOfPlace;
    private TextView description;
    private TextView numOfVisitors;
    private TextView openHour;
    private TextView cuisines;
    private Button invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_place);

        setIds();
        setTextOnViews();
        setOnClickListeners();
    }

    private void setOnClickListeners() {

    }

    private void setTextOnViews() {
        imagePlace.setImageResource(place.getImage());
        nameOfPlace.setText(place.getName());
        description.setText(place.getDescription());
        numOfVisitors.setText(CURRENT_VISITORS + place.getNumOfVisitors());
        openHour.setText(place.getOpenHour());
        cuisines.setText(place.getCuisines());
    }

    private void setIds() {
        imagePlace = findViewById(R.id.image_place);
        nameOfPlace = findViewById(R.id.place_name);
        description = findViewById(R.id.description_text);
        numOfVisitors = findViewById(R.id.visitors_textView);
        openHour = findViewById(R.id.hours);
        cuisines = findViewById(R.id.Cuisines_type);
        invite = findViewById(R.id.invite_btn);
    }
}