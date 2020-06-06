package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class UpdateVisitorsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


   private Button date;
   private Button time;
   private ImageView menu;
   private TextView maxVisitorText;
   private TextView numOfVisitorsText;
   private ImageView plusSign;
   private ImageView minusSign;
   private Calendar calendar = Calendar.getInstance();
   private int numOfVisitors;
   private int maxNumOfVisitors;
   private User user;
   private boolean dateHasBeenSet = false;
   private boolean timeHasBeenSet = false;
   private RelativeLayout updateVisitorWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_visitors);


        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
        setInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(updateVisitorWindow);
    }

    private void setInfo() {

        numOfVisitorsText.setText(OwnerHomeActivity.place.getNumOfVisitors() + "");
        maxVisitorText.setText(OwnerHomeActivity.place.getMaxVisitors() + "");

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
               plusButtonHasBeenPressed();
            }
        });

        minusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               minusButtonHasBeenPressed();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

    }

    private void plusButtonHasBeenPressed(){
        if(dateHasBeenSet && timeHasBeenSet) {
            addVisitor();
        }else {
            Toast.makeText(this, "You need to choose date and time", Toast.LENGTH_SHORT).show();

        }
    }

    private void minusButtonHasBeenPressed(){

        if(dateHasBeenSet & timeHasBeenSet) {
            subVisitor();
        } else {
            Toast.makeText(this, "You need to choose date and time", Toast.LENGTH_SHORT).show();
        }

    }

    private void subVisitor() {

         numOfVisitors = Integer.parseInt(numOfVisitorsText.getText().toString());
         maxNumOfVisitors = Integer.parseInt(maxVisitorText.getText().toString());

        if(numOfVisitors > 0){
            numOfVisitors--;
            numOfVisitorsText.setText(numOfVisitors + "");
            OwnerHomeActivity.place.setNumOfVisitors(numOfVisitors);
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
            OwnerHomeActivity.place.setNumOfVisitors(numOfVisitors);
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

        plusSign = findViewById(R.id.plus_btn);
        minusSign = findViewById(R.id.minus_btn);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        maxVisitorText = findViewById(R.id.max_visitors);
        numOfVisitorsText = findViewById(R.id.visitors_number);
        menu = findViewById(R.id.menu);
        updateVisitorWindow = findViewById(R.id.update_visitor_window);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        date.setText(dayOfMonth +" / " + month + " / " + year);
        dateHasBeenSet = true;
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
        time.setText(hourText + " : " + minuteText);
        timeHasBeenSet = true;

    }
}
