package com.twoyul.myapplication;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class MainMap extends AppCompatActivity implements View.OnClickListener {

    View pinHadong, pinIncheon, pinBusan, pinJeju, pinGreenpower, pinAndong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_home);

        // pinHadong.findViewById(R.id.pin_left_area).setBackgroundColor(getColor(R.color.colorYellow));

        pinHadong = findViewById(R.id.hadong);
        pinIncheon = findViewById(R.id.incheon);
        pinBusan = findViewById(R.id.busan);
        pinJeju = findViewById(R.id.jeju);
        pinGreenpower = findViewById(R.id.greenpower);
        pinAndong = findViewById(R.id.andong);

        pinHadong.setOnClickListener(this);
        pinIncheon.setOnClickListener(this);
        pinBusan.setOnClickListener(this);
        pinJeju.setOnClickListener(this);
        pinGreenpower.setOnClickListener(this);
        pinAndong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        switch (v.getId()) {

        case R.id.hadong:
            mainIntent.putExtra("number", 0);
            startActivity(mainIntent);
            break;

        case R.id.incheon:
            mainIntent.putExtra("number", 1);
            startActivity(mainIntent);
            break;

        case R.id.busan:
            mainIntent.putExtra("number", 2);
            startActivity(mainIntent);
            break;

        case R.id.jeju:
            mainIntent.putExtra("number", 3);
            startActivity(mainIntent);
            break;

        case R.id.greenpower:
            mainIntent.putExtra("number", 4);
            startActivity(mainIntent);
            break;

        case R.id.andong:
            mainIntent.putExtra("number", 5);
            startActivity(mainIntent);
            break;
        }
    }
}
