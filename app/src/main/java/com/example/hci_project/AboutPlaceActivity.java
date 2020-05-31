package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;

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
    private ImageView menu;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_place);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        place = VisitorMapActivity.places.get(getIntent().getIntExtra(PlaceDialog.PLACE,0));
        setIds();
        setTextOnViews();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });
    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this,SideMenuActivity.class);
        startActivity(intent);
        intent.putExtra(Finals.USER,user);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
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
        menu = findViewById(R.id.menu);
    }
}