package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;
import com.example.hci_project.Views.ReportView;


public class MyReportsActivity extends Activity {

    private LinearLayout reportsHolder;
    private ImageView menu;
    private DynamicXML dynamicXML;
    private String nameOfPlace;
    private RelativeLayout myReportWindow;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        nameOfPlace = getIntent().getStringExtra(Finals.NAME_OF_PLACE);
        dynamicXML = new DynamicXML();
        setContentView(R.layout.activity_my_reports);
        setIds();
        setOnClickListeners();
        showPlaces();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(myReportWindow);
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
        Intent intent = new Intent(this, PoliceSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setIds() {

        reportsHolder = findViewById(R.id.reports_holder);
        menu = findViewById(R.id.menu);
        myReportWindow = findViewById(R.id.my_reports_window);
    }

    private void showPlaces() {

        reportsHolder.removeAllViews();

        if(SearchActivity.allReports.size() == 0){
            TextView noResultTV = dynamicXML.createTextView(this,"You don't have any reports yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            reportsHolder.addView(noResultTV);
            return;
        }

        for(Report report : SearchActivity.allReports){

            Log.d("BABA", report.getReporter().toLowerCase());
            Log.d("BABA",  user.firstName + user.lastName);


            if(report.getReporter().toLowerCase().equals(user.firstName.toLowerCase() +" " +  user.lastName.toLowerCase())) {

                ReportView reportView = new ReportView(
                        this,
                        dynamicXML.createTextView(this, report.getNameOfPlace(), "sans-serif-condensed", 20, Color.BLACK, Gravity.TOP, 0, 0, 0, 0),
                        dynamicXML.createImageView(this, report.getImage(), Gravity.CENTER, 0, 5, 0, 5, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT),
                        dynamicXML.createTextView(this, report.getReporter(), "sans-serif-condensed", 15, Color.BLACK, Gravity.CENTER, 0, 0, 0, 0), reportsHolder,
                        SearchActivity.allReports, report, user
                );


                reportsHolder.addView(reportView.getCard());
            }

        }

    }
}