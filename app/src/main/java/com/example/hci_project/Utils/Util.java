package com.example.hci_project.Utils;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.example.hci_project.AllReportsActivity;
import com.example.hci_project.DoneActivity;
import com.example.hci_project.EditPlaceActivity;
import com.example.hci_project.FillReportActivity;
import com.example.hci_project.HealthStatementFormActivity;
import com.example.hci_project.InvitationActivity;
import com.example.hci_project.LoginActivity;
import com.example.hci_project.MapActivity;
import com.example.hci_project.MinistryOfHealthAboutPlaceActivity;
import com.example.hci_project.MinistryOfHealthSideMenuActivity;
import com.example.hci_project.MyInvitationsActivity;
import com.example.hci_project.MyReportsActivity;
import com.example.hci_project.OwnerHomeActivity;
import com.example.hci_project.OwnerSideMenuActivity;
import com.example.hci_project.PersonalInfoActivity;
import com.example.hci_project.PlaceReportsActivity;
import com.example.hci_project.PoliceAboutPlaceActivity;
import com.example.hci_project.PoliceSideMenuActivity;
import com.example.hci_project.ReportActivity;
import com.example.hci_project.SearchActivity;
import com.example.hci_project.SettingsActivity;
import com.example.hci_project.SignUpActivity;
import com.example.hci_project.UpdateVisitorsActivity;
import com.example.hci_project.VisitorAboutPlaceActivity;
import com.example.hci_project.VisitorSideMenuActivity;
import com.example.hci_project.WaitingActivity;

public class Util {

    public static void changeBackgroundColor(RelativeLayout layout){
        if(WaitingActivity.isDarkModeOn){
            layout.setBackgroundColor(Color.GRAY);
        } else {
            layout.setBackgroundColor(Color.WHITE);
        }
    }

    public static void finishAllActivities(){

        if( AllReportsActivity.activity != null) {
            AllReportsActivity.activity.finish();
        }
        if( DoneActivity.activity != null) {
            DoneActivity.activity.finish();
        }
        if( EditPlaceActivity.activity != null) {
            EditPlaceActivity.activity.finish();
        }
        if( FillReportActivity.activity != null) {
            FillReportActivity.activity.finish();
        }
        if( HealthStatementFormActivity.activity != null) {
            HealthStatementFormActivity.activity.finish();
        }
        if( InvitationActivity.activity != null) {
            InvitationActivity.activity.finish();
        }
        if( LoginActivity.activity != null) {
            LoginActivity.activity.finish();
        }
        if( MapActivity.activity != null) {
            MapActivity.activity.finish();
        }
        if( MinistryOfHealthAboutPlaceActivity.activity != null) {
            MinistryOfHealthAboutPlaceActivity.activity.finish();
        }
        if( MinistryOfHealthSideMenuActivity.activity != null) {
            MinistryOfHealthSideMenuActivity.activity.finish();
        }
        if( MyInvitationsActivity.activity != null) {
            MyInvitationsActivity.activity.finish();
        }

        if( MyReportsActivity.activity != null) {
            MyReportsActivity.activity.finish();
        }
        if( OwnerHomeActivity.activity != null) {
            OwnerHomeActivity.activity.finish();
        }

        if( OwnerSideMenuActivity.activity != null) {
            OwnerSideMenuActivity.activity.finish();
        }

        if( PersonalInfoActivity.activity != null) {
            PersonalInfoActivity.activity.finish();
        }

        if( PlaceReportsActivity.activity != null) {
            PlaceReportsActivity.activity.finish();
        }

        if( PoliceAboutPlaceActivity.activity != null) {
            PoliceAboutPlaceActivity.activity.finish();
        }

        if( PoliceSideMenuActivity.activity != null) {
            PoliceSideMenuActivity.activity.finish();
        }

        if( ReportActivity.activity != null) {
            ReportActivity.activity.finish();
        }

        if( SearchActivity.activity != null) {
            SearchActivity.activity.finish();
        }

        if( SettingsActivity.activity != null) {
            SettingsActivity.activity.finish();
        }

        if( SignUpActivity.activity != null) {
            SignUpActivity.activity.finish();
        }

        if( UpdateVisitorsActivity.activity != null) {
            UpdateVisitorsActivity.activity.finish();
        }

        if( VisitorAboutPlaceActivity.activity != null) {
            VisitorAboutPlaceActivity.activity.finish();
        }

        if( VisitorSideMenuActivity.activity != null) {
            VisitorSideMenuActivity.activity.finish();
        }

        if( WaitingActivity.activity != null) {
            WaitingActivity.activity.finish();
        }

    }
}
