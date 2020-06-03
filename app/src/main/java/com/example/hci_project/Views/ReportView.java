package com.example.hci_project.Views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.hci_project.R;
import com.example.hci_project.Report;
import com.example.hci_project.ReportActivity;
import com.example.hci_project.User;
import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;

import java.util.ArrayList;

public class ReportView {

    private TextView nameOfPlace;
    private TextView nameOfOffice;
    private ImageView image;
    private LinearLayout card;
    private DynamicXML dynamicallyXML;
    private Context context;
    private LinearLayout container;
    private ArrayList<Report> invitations;
    private Report report;
    private User user;



    public ReportView(Context context, TextView nameOfPlace, ImageView image, TextView nameOfOffice, LinearLayout container, ArrayList<Report> invitations, Report report, User user){
        dynamicallyXML = new DynamicXML();
        this.context = context;
        this.image = image;
        this.nameOfOffice = nameOfOffice;
        this.card = new LinearLayout(context);
        this.container = container;
        this.invitations = invitations;
        this.nameOfPlace = nameOfPlace;
        this.report = report;
        this.user = user;
        createCard();


    }

    private void createCard() {
        
        LinearLayout.LayoutParams cardParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                300
        );
        card.setBackgroundResource(R.drawable.card_view_shape);
        cardParam.topMargin = 15;
        card.setLayoutParams(cardParam);
        //Set card sum
        card.setWeightSum(2f);
        card.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout placeDescriptionLayout = new LinearLayout(context);
        LinearLayout.LayoutParams placeParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        placeParam.leftMargin = 20;
        placeDescriptionLayout.setOrientation(LinearLayout.VERTICAL);
        placeDescriptionLayout.setLayoutParams(placeParam);

        placeDescriptionLayout.addView(nameOfPlace);
        placeDescriptionLayout.addView(nameOfOffice);

        LinearLayout imageLayout = new LinearLayout(context);
        imageLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        imageLayout.setLayoutParams(imageParams);
        imageLayout.addView(image);


        nameOfPlace.setTypeface(nameOfPlace.getTypeface(), Typeface.BOLD);

        //Create Params
        card.addView(placeDescriptionLayout);
        card.addView(imageLayout);

        setClickListeners(card);
    }

    void setClickListeners(LinearLayout cardView){


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReportActivity();
            }
        });
    }

    private void goToReportActivity() {

        Intent intent = new Intent(context, ReportActivity.class);
        intent.putExtra(Finals.USER, user);
        intent.putExtra(Finals.REPORT,report);
        context.startActivity(intent);

    }

    public LinearLayout getCard() {
        return card;
    }

    
}
