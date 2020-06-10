package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


public class AllReportsActivity extends Activity {

    private LinearLayout reportsHolder;
    private ImageView menu;
    private DynamicXML dynamicXML;
    private String nameOfPlace;
    private User user;
    private RelativeLayout allReportsWindow;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        nameOfPlace = getIntent().getStringExtra(Finals.NAME_OF_PLACE);
        dynamicXML = new DynamicXML();
        activity = this;
        setContentView(R.layout.activity_all_reports);
        setIds();
        setOnClickListeners();
        showPlaces();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(allReportsWindow);
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
        Intent intent = new Intent(this, MinistryOfHealthSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setIds() {

        reportsHolder = findViewById(R.id.reports_holder);
        menu = findViewById(R.id.menu);
        allReportsWindow = findViewById(R.id.all_reports_window);
    }

    private void showPlaces() {

        reportsHolder.removeAllViews();

        if(WaitingActivity.allReports.size() == 0){
            TextView noResultTV = dynamicXML.createTextView(this,"You don't have any reports yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            reportsHolder.addView(noResultTV);
            return;
        }

        for(Report report : WaitingActivity.allReports){

            ReportView reportView = new ReportView(
                    this,
                    dynamicXML.createTextView(this,report.getNameOfPlace(),"sans-serif-condensed",20,Color.BLACK,Gravity.TOP,0,0,0,0),
                    dynamicXML.createImageView(this,report.getImage(),Gravity.CENTER,0,5,0,5,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT),
                    dynamicXML.createTextView(this,report.getReporter(),"sans-serif-condensed",15,Color.BLACK,Gravity.CENTER,0,0,0,0),reportsHolder,
                    WaitingActivity.allReports,report,user
            );

            reportsHolder.addView(reportView.getCard());

        }
    }
}