package com.example.hci_project.Utils;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.example.hci_project.WaitingActivity;

public class Util {

    public static void changeBackgroundColor(RelativeLayout layout){
        if(WaitingActivity.isDarkModeOn){
            layout.setBackgroundColor(Color.GRAY);
        } else {
            layout.setBackgroundColor(Color.WHITE);
        }
    }
}
