package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hci_project.Utils.Finals;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class PlaceStateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

   public static Place place;
   private TextView placeName;
   private TextView date;
   private TextView time;
   private ImageView menu;
   private TextView maxVisitorText;
   private TextView numOfVisitorsText;
   private ImageView plusSign;
   private ImageView minusSign;
   private Calendar calendar = Calendar.getInstance();
   private int numOfVisitors;
   private int maxNumOfVisitors;
   private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_state);

        //Can change to any place we want from the list
        place = WaitingActivity.places.get(1);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
        setInfo();




    }

    private void setInfo() {

        placeName.setText(place.getName());
        date.setText(ZonedDateTime.now().getDayOfMonth() + " / "  +ZonedDateTime.now().getMonthValue() + " / " + ZonedDateTime.now().getYear());
        numOfVisitorsText.setText(place.getNumOfVisitors() + "");
        maxVisitorText.setText(place.getMaxVisitors() + "");

        if(Integer.parseInt(numOfVisitorsText.getText().toString()) ==
                Integer.parseInt(maxVisitorText.getText().toString()
                )){
            numOfVisitorsText.setTextColor(Color.RED);

        }else {
            numOfVisitorsText.setTextColor(Color.rgb(63, 135, 0));
        }


    }

    private void setOnClickListeners() {

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        plusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisitor();
            }
        });

        minusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subVisitor();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

    }

    private void subVisitor() {

         numOfVisitors = Integer.parseInt(numOfVisitorsText.getText().toString());

        if(numOfVisitors > 0){
            numOfVisitors--;
            numOfVisitorsText.setText(numOfVisitors + "");
        }

        if(numOfVisitors < maxNumOfVisitors){
            numOfVisitorsText.setTextColor(Color.rgb(63, 135, 0));
        }
    }

    private void addVisitor() {

        maxNumOfVisitors = Integer.parseInt(maxVisitorText.getText().toString());
        numOfVisitors = Integer.parseInt(numOfVisitorsText.getText().toString());

        if(numOfVisitors < maxNumOfVisitors){
            numOfVisitors++;
            numOfVisitorsText.setText(numOfVisitors + "");
        }

        if(numOfVisitors == maxNumOfVisitors){
            numOfVisitorsText.setTextColor(Color.RED);
        }
    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this, OwnerSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setIds() {

        placeName = findViewById(R.id.place_name);
        plusSign = findViewById(R.id.plus_btn);
        minusSign = findViewById(R.id.minus_btn);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        maxVisitorText = findViewById(R.id.max_visitors);
        numOfVisitorsText = findViewById(R.id.visitors_number);
        menu = findViewById(R.id.menu);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        date.setText("Date : " + dayOfMonth +" / " + month + " / " + year);
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();

    }

    private void showTimePickerDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,this,
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE) ,
                android.text.format.DateFormat.is24HourFormat(this));

        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {

        String hourText = hour  > 9 ? ""+ hour : "0" + hour;
        String minuteText =  minutes  > 9 ? ""+ minutes : "0" + minutes;
        time.setText("Time : " + hourText + " : " + minuteText);

    }
}
