package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class PoliceAboutPlaceActivity extends Activity {
    final String CURRENT_VISITORS = "Current visitors: ";
    private Place place;
    private ImageView imagePlace;
    private TextView nameOfPlace;
    private TextView description;
    private TextView numOfVisitors;
    private TextView openHour;
    private TextView cuisines;
    private Button report;
    private ImageView menu;
    private User user;
    private RelativeLayout policeAboutPlaceWindow;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_about_place);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        place = WaitingActivity.places.get(getIntent().getIntExtra(Finals.PLACE,0));
        setIds();
        activity = this;
        setTextOnViews();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(policeAboutPlaceWindow);
    }

    private void setOnClickListeners() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFillReportActivity();
            }
        });
    }

    private void goToFillReportActivity() {

        Intent intent = new Intent(this,FillReportActivity.class);
        intent.putExtra(Finals.PLACE_INDEX, WaitingActivity.places.indexOf(place));
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this, PoliceSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
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
        report = findViewById(R.id.report_btn);
        menu = findViewById(R.id.menu);
        policeAboutPlaceWindow = findViewById(R.id.police_about_place_window);
    }
}