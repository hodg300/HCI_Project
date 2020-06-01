package com.example.hci_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;

public class OwnerHomeActivity extends AppCompatActivity {

    private Button closeBtn;
    private Button updateBtn;
    private ImageView editPlace;
    private ImageView imagePlace;
    private ImageView menu;
    private TextView nameOfPlace;
    private TextView description;
    private TextView numOfVisitors;
    private TextView maxNumOfVisitors;
    private TextView openHour;
    private TextView cuisines;
    private User user;
    private DynamicXML dynamicallyXML;
    public static Place place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dynamicallyXML = new DynamicXML();
        setContentView(R.layout.activity_owner_home);
        //Can change to any place we want from the list
        place = WaitingActivity.places.get(1);
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

    private void setIds() {

        menu =  findViewById(R.id.menu);
        imagePlace  = findViewById(R.id.image_place);
        nameOfPlace = findViewById(R.id.place_name);
        description =  findViewById(R.id.description_text);
        numOfVisitors = findViewById(R.id.visitors);
        maxNumOfVisitors = findViewById(R.id.max_visitors);
        openHour = findViewById(R.id.hours);
        cuisines = findViewById(R.id.cuisines_type);
        closeBtn = findViewById(R.id.close_place_btn);
        editPlace = findViewById(R.id.edit_place);
        updateBtn = findViewById(R.id.update_btn);

    }

    private void setOnClickListeners() {

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePlace();
            }
        });
        editPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEditPlaceActivity();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOwnerSideMenuActivity();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdateVisitorsActivity();
            }
        });
    }

    private void goToUpdateVisitorsActivity() {
        Intent intent = new Intent(this, UpdateVisitorsActivity.class);
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
    }

    private void goToOwnerSideMenuActivity() {
            Intent intent = new Intent(this, OwnerSideMenuActivity.class);
            intent.putExtra(Finals.USER, user);
            startActivity(intent);
            this.overridePendingTransition(R.anim.left_to_right,
                    R.anim.right_to_left);
    }

    private void goToEditPlaceActivity() {
        Intent intent = new Intent(this,EditPlaceActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
    }

    private void closePlace() {
        if(closeBtn.getText().toString().toLowerCase().equals("open place")){
            changeOpenCloseButton("Close Place",R.drawable.close_button_shape);
        } else {
            popUpAlertDialog();
        }

    }

    private void popUpAlertDialog() {

        TextView alertMessage = dynamicallyXML.createTextView(this,
                "Any invitations to this place will be dismissed.",
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
                                changeOpenCloseButton("Open\nPlace", R.drawable.open_button_shape);
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();

    }

    private void changeOpenCloseButton(String title,int backgroundResource) {

        closeBtn.setText(title);
        closeBtn.setBackgroundResource(backgroundResource);
    }
}
