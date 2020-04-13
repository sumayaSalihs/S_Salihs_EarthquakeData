/*
Name: Sumaya Ahmed Salihs
Student ID: S1803463
*/

package com.example.mymac.earthquakedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymac.earthquakedata.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_detail);


        TextView details = (TextView)findViewById(R.id.TextView_TEXTVIEW);

        details.setText(getIntent().getStringExtra("text"));

    }
}
