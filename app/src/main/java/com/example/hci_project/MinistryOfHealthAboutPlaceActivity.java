package com.example.hci_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class MinistryOfHealthAboutPlaceActivity extends AppCompatActivity {

    public static Button closePlaceBtn;
    private Button updateMaxVisitorsBtn;
    private Button policeReportsBtn;
    private DynamicXML dynamicXML;
    private Place place;
    private User user;
    private ImageView imagePlace;
    private ImageView menu;
    private TextView nameOfPlace;
    private TextView description;
    private TextView numOfVisitors;
    public static TextView maxNumOfVisitors;
    private TextView openHour;
    private TextView cuisines;
    private RelativeLayout ministryOfHealthAboutPlaceWindow;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministry_of_health_about_place);
        place = WaitingActivity.places.get(getIntent().getIntExtra(Finals.PLACE_INDEX,0));
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        activity = this;
        dynamicXML = new DynamicXML();
        setIds();
        setOnClickListeners();
        setInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        maxNumOfVisitors.setText(place.getMaxVisitors()+"");
        Util.changeBackgroundColor(ministryOfHealthAboutPlaceWindow);
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
                    popUpAlertDialog();
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

        policeReportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPlaceReportsActivity();
            }
        });

    }

    private void popUpAlertDialog() {

        TextView alertMessage = dynamicXML.createTextView(this,
                "By press confirm the place will open for bussiness again?.",
                "sans-serif-condensed",15, Color.BLACK, Gravity.CENTER,
                0,0,0,0);
        alertMessage.setPadding(40,40,40,40);

        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setView(alertMessage)
                .setIcon(R.drawable.ic_warning_sign)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                changeOpenCloseButton("Close Place",R.drawable.close_button_shape);
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();

    }

    private void goPlaceReportsActivity() {
        Intent intent = new Intent(this, PlaceReportsActivity.class);
        intent.putExtra(Finals.NAME_OF_PLACE,place.getName());
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
    }


    private void goToUpdateMaxVisitorsDialog() {

        UpdateMaxVisitorsDialog dialog = new UpdateMaxVisitorsDialog(place,user);
        dialog.show(getSupportFragmentManager(),"CarPickerDialog");
    }

    private void goToMinistryOfHealthSideMenuActivity() {
        Intent intent = new Intent(this, MinistryOfHealthSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
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
        ministryOfHealthAboutPlaceWindow = findViewById(R.id.ministry_of_health_about_place_window);
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
