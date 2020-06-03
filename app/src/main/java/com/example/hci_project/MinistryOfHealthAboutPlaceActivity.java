package com.example.hci_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;

public class MinistryOfHealthAboutPlaceActivity extends AppCompatActivity {

    public static Button closePlaceBtn;
    private Button updateMaxVisitorsBtn;
    private Button policeReportsBtn;
    private DynamicXML dynamicXML = new DynamicXML();
    private Place place;
    private User user;
    private ImageView imagePlace;
    private ImageView menu;
    private TextView nameOfPlace;
    private TextView description;
    private TextView numOfVisitors;
    private TextView maxNumOfVisitors;
    private TextView openHour;
    private TextView cuisines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministry_of_health_about_place);
        place = WaitingActivity.places.get(getIntent().getIntExtra(Finals.PLACE_INDEX,0));
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
        setInfo();
    }

    private void setInfo() {

        nameOfPlace.setText(place.getName());
        description.setText(place.getDescription());
        numOfVisitors.setText(place.getNumOfVisitors() + "");
        maxNumOfVisitors.setText(place.getMaxVisitors() + "");
        openHour.setText(place.getOpenHour());
        cuisines.setText(place.getCuisines());
        imagePlace.setImageResource(place.getImage());

        if(Integer.parseInt(numOfVisitors.getText().toString()) ==
                Integer.parseInt(maxNumOfVisitors.getText().toString()
                )){
            numOfVisitors.setTextColor(Color.RED);

        }else {
            numOfVisitors.setTextColor(Color.rgb(63, 135, 0));
        }
    }

    private void setOnClickListeners() {

        closePlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(closePlaceBtn.getText().toString().toLowerCase().equals("close place")) {
                    ClosePlaceDialog dialog = new ClosePlaceDialog(place, user);
                    dialog.show(getSupportFragmentManager(), "CarPickerDialog");
                } else {
                    changeOpenCloseButton("Close Place",R.drawable.close_button_shape);
                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMinistryOfHealthSideMenuActivity();
            }
        });
        updateMaxVisitorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdateMaxVisitorsDialog();
            }
        });

    }


    private void goToUpdateMaxVisitorsDialog() {

        UpdateMaxVisitorsDialog dialog = new UpdateMaxVisitorsDialog(place,user);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }

    private void goToMinistryOfHealthSideMenuActivity() {
    }

    private void setIds() {

        menu = findViewById(R.id.menu);
        closePlaceBtn = findViewById(R.id.close_place_btn);
        updateMaxVisitorsBtn = findViewById(R.id.update_btn);
        policeReportsBtn = findViewById(R.id.police_reports_btn);
        imagePlace  = findViewById(R.id.image_place);
        nameOfPlace = findViewById(R.id.place_name);
        description =  findViewById(R.id.description_text);
        numOfVisitors = findViewById(R.id.visitors);
        maxNumOfVisitors = findViewById(R.id.max_visitors);
        openHour = findViewById(R.id.hours);
        cuisines = findViewById(R.id.cuisines_type);
    }

    public static void closePlace() {
        if(closePlaceBtn.getText().toString().toLowerCase().equals("open place")){
            changeOpenCloseButton("Close Place",R.drawable.close_button_shape);
        } else {
            changeOpenCloseButton("Open Place",R.drawable.open_button_shape);
        }

    }
    public static void changeOpenCloseButton(String title,int backgroundResource) {

        closePlaceBtn.setText(title);
        closePlaceBtn.setBackgroundResource(backgroundResource);
    }


}
