package com.example.daichi.mediadesign;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final float THRESHOLD = 1;
    private static final float THRESHOLD2 = 500;

    SensorManager manager;
    Sensor sensor;
    TextView xTextView;
    TextView yTextView;
    TextView zTextView;

    private float dx;
    private float dy;
    private float dz;

    private List<SensorParams> sensorParamsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xTextView = (TextView) findViewById(R.id.xValue);
        yTextView = (TextView) findViewById(R.id.yValue);
        zTextView = (TextView) findViewById(R.id.zValue);

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

        xTextView.setText(String.valueOf(x));
        yTextView.setText(String.valueOf(y));
        zTextView.setText(String.valueOf(z));

        SensorParams param = new SensorParams(x, y, z);
        sensorParamsList.add(param);

        if (threshold(sensorParamsList)) {
            Toast.makeText(this, "降ったなお前", Toast.LENGTH_SHORT).show();
            sensorParamsList.clear();
        }
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
