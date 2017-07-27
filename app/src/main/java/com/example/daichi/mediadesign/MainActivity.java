package com.example.daichi.mediadesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

/**
 * Created by daichi on 2017/07/20.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.button1);
        start.setOnClickListener(startButton);
    }

    public View.OnClickListener startButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        }
    };


}