package com.example.accelerometerapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView txtX, txtY, txtZ;
    private View viewBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // XML layout bạn đã sửa

        // Liên kết giao diện
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        viewBackground = findViewById(R.id.viewBackground);

        // Lấy dịch vụ cảm biến
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Đăng ký lắng nghe cảm biến khi app hoạt động
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Dừng lắng nghe khi app tạm dừng
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            txtX.setText("Trục X: " + String.format("%.2f", x) + " m/s²");
            txtY.setText("Trục Y: " + String.format("%.2f", y) + " m/s²");
            txtZ.setText("Trục Z: " + String.format("%.2f", z) + " m/s²");

            // Tính gia tốc tổng hợp
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

            // Đổi màu nền nếu gia tốc vượt ngưỡng
            if (acceleration > 2.0f) {
                viewBackground.setBackgroundColor(Color.RED);
            } else {
                viewBackground.setBackgroundColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Không xử lý gì ở đây
    }
}
