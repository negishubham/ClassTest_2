package com.example.shubham.classtest_2;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graph extends AppCompatActivity implements SensorEventListener{

    LineGraphSeries<DataPoint> series;
    int count=0,i=0;
    //double  [] w= new double[10];
    double w;
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(),"Accelerometer", Toast.LENGTH_LONG).show();
            System.exit(1);
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>();
        series.appendData(new DataPoint(0,0), true, 50);
        series.setColor(Color.RED);
        series.setThickness(2);
        graph.addSeries(series);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        //w[i] = Math.sqrt(x * x + y * y + z * z);
        //series = new LineGraphSeries<DataPoint>();
        w = Math.sqrt(x * x + y * y + z * z);
        series.appendData(new DataPoint(count,w),true,50);
        //i++;
        count++;
      //  if (i==11)
        //    i=0;
    }


    public void back(View v) {
        Intent intent = new Intent(graph.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
