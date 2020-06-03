package com.example.hci_project;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class ClosePlaceDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private Button fromBtn;
    private Button confirm;
    private Button cancel;
    private ImageView plusSign;
    private ImageView minusSign;
    private TextView numOfDaysText;
    private Place place;
    private User user;
    private boolean dateHasBeenSet;
    private int numOfDays;



    public ClosePlaceDialog(Place place,User user) {

        this.place = place;
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_close_place,container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setIds(view);
        numOfDays = Integer.parseInt(numOfDaysText.getText().toString());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setOnClickListeners();


        return view;
    }

    private void setOnClickListeners() {
        fromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePickerDialog();

            }
        });

        plusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDay();
            }
        });

        minusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subDay();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!dateHasBeenSet){
                    Toast.makeText(getContext(), "You need to choose date and time", Toast.LENGTH_SHORT).show();
                    return;
                }
                MinistryOfHealthAboutPlaceActivity.closePlace();
                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();
            }

        });
    }

    private void subDay() {
        if(numOfDays > 0){
            numOfDays--;
            numOfDaysText.setText(numOfDays + "");
        }
    }

    private void addDay() {
        numOfDays++;
        numOfDaysText.setText(numOfDays + "");
    }


    private void setIds(View view) {

        fromBtn = view.findViewById(R.id.from);
        confirm = view.findViewById(R.id.confirm_button);
        cancel = view.findViewById(R.id.cancel_button);
        plusSign = view.findViewById(R.id.plus_box);
        minusSign = view.findViewById(R.id.minus_box);
        numOfDaysText = view.findViewById(R.id.num_of_days_text);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        fromBtn.setText(dayOfMonth +" / " + month + " / " + year);
        dateHasBeenSet = true;
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();

    }






}
