package com.example.calendarnotesapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView txtDate;
    EditText edtWork, edtHour, edtMinute;
    Button btnAddWork;
    ListView lv;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        txtDate = findViewById(R.id.txt_date);
        edtWork = findViewById(R.id.edt_work);
        edtHour = findViewById(R.id.edt_hour);
        edtMinute = findViewById(R.id.edt_minute);
        btnAddWork = findViewById(R.id.btn_work);
        lv = findViewById(R.id.lv);

        // Hiện ngày hiện tại
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hôm nay: " + sdf.format(currentDate));

        // Thiết lập adapter
        arrayWork = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        lv.setAdapter(adapter);

        // Bắt sự kiện nút thêm
        btnAddWork.setOnClickListener(v -> {
            String work = edtWork.getText().toString().trim();
            String hour = edtHour.getText().toString().trim();
            String minute = edtMinute.getText().toString().trim();

            if (work.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thiếu thông tin")
                        .setMessage("Vui lòng điền đầy đủ công việc, giờ và phút.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                String str = work + " - " + hour + ":" + minute;
                arrayWork.add(str);
                adapter.notifyDataSetChanged();

                edtWork.setText("");
                edtHour.setText("");
                edtMinute.setText("");
            }
        });
    }
}
