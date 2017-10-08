package com.example.shubham.classtest_2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getApplicationContext(), "Accelerometer", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        double w = Math.sqrt(x * x + y * y + z * z);

        TextView a = (TextView) findViewById(R.id.accX);
        TextView b = (TextView) findViewById(R.id.accY);
        TextView c = (TextView) findViewById(R.id.accz);
        TextView d = (TextView) findViewById(R.id.accnet);

        a.setText(String.format(Locale.getDefault(), "%.9f", x));
        b.setText(String.format(Locale.getDefault(), "%.9f", y));
        c.setText(String.format(Locale.getDefault(), "%.9f", z));
        d.setText(String.format(Locale.getDefault(), "%.9f", w));
    }

    public void graph(View v) {

        //TextView textView = (Button) findViewById(R.id.button);
        Intent intent = new Intent (this,graph.class);
        startActivity(intent);
    }

}

