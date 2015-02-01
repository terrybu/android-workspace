package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        
        //in this particular app, we are concerned with backwards compatibility with Froyo, Honeycomb, etc
        //If not, you can use getFragmentManager instead
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
        	fragment = new CrimeFragment();
        	fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
        
        
    }


}