package com.example.daichi.mediadesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartActivity extends AppCompatActivity implements SensorEventListener {

    private static final float THRESHOLD = 1;
    private static final float THRESHOLD2 = 150;

    SensorManager manager;
    Sensor sensor;

    ImageView imageView;

    private float dx;
    private float dy;
    private float dz;

    private List<SensorParams> sensorParamsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        imageView = (ImageView) findViewById(R.id.image1);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (Math.abs(dx - x) < THRESHOLD
                && Math.abs(dy - y) < THRESHOLD
                && Math.abs(dz - z) < THRESHOLD) {
            return;
        } else {
            dx = x;
            dy = y;
            dz = z;
        }

        SensorParams param = new SensorParams(x, y, z);
        sensorParamsList.add(param);

        if (threshold(sensorParamsList)) {
            sensorParamsList.clear();
            Random r = new Random();
            int n = r.nextInt(3);

            try {
                switch (n) {
                    case 0:
                        imageView.setImageBitmap(trimBitmap(R.drawable.eisuke1));
                        break;
                    case 1:
                        imageView.setImageBitmap(trimBitmap(R.drawable.taiyou));
                        break;
                    case 2:
                        imageView.setImageBitmap(trimBitmap(R.drawable.cupid));
                        break;
                }

            } catch (OutOfMemoryError outOfMemoryError) {
                Toast.makeText(this, "Out Of Memory Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap trimBitmap(@DrawableRes int imageRes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //画像をメモリー展開をしないで、rescaleする
        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), imageRes, options);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    private boolean threshold(List<SensorParams> sensorParamsList) {
        float sum = 0;
        for (SensorParams param : sensorParamsList) {
            sum += param.getX() + param.getY() + param.getZ();
        }
        return sum > THRESHOLD2;
    }
}