package com.codecaine.mpurse.activity;

import android.os.Bundle;
import android.app.Activity;

import com.codecaine.mpurse.R;

public class RequestsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
