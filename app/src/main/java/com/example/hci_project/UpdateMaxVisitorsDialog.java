package com.example.hci_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.hci_project.Utils.Util;


public class UpdateMaxVisitorsDialog extends DialogFragment {

    private ImageView plusSign;
    private ImageView minusSign;
    private Button confirmBtn;
    private Button cancelBtn;
    private TextView maxVisitors;
    private Place place;
    private User user;
    private RelativeLayout updateMaxVisitorsWindow;



    public UpdateMaxVisitorsDialog(Place place,User user) {

        this.place = place;
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_max_visitors,container, false);

        setIds(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setInfo();
        setOnClickListeners();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.changeBackgroundColor(updateMaxVisitorsWindow);
    }

    private void setOnClickListeners() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                place.setMaxVisitors(Integer.parseInt(maxVisitors.getText().toString()));
                getDialog().dismiss();


            }
        });

        minusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subMaxVisitors();
            }
        });

        plusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addMaxVisitor();
            }

        });
    }

    private void subMaxVisitors() {
        if(Integer.parseInt(maxVisitors.getText().toString()) > 0){
            maxVisitors.setText( "" + (Integer.parseInt(maxVisitors.getText().toString()) - 1));
        }
    }

    private void addMaxVisitor() {
        maxVisitors.setText( "" + (Integer.parseInt(maxVisitors.getText().toString()) + 1));

    }


   private void setInfo(){

        maxVisitors.setText(place.getMaxVisitors() + "");

   }

    private void setIds(View view) {

       plusSign = view.findViewById(R.id.plus_box);
       minusSign = view.findViewById(R.id.minus_box);
       confirmBtn = view.findViewById(R.id.confirm_button);
       cancelBtn = view.findViewById(R.id.cancel_button);
       maxVisitors = view.findViewById(R.id.max_visitors_text);
       updateMaxVisitorsWindow = view.findViewById(R.id.update_max_visitor_window);
    }

}
