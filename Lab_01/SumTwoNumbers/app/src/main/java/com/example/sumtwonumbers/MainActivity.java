package com.example.sumtwonumbers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB, edtKQ;
    Button btnCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnCong = findViewById(R.id.btnCong);
        edtKQ = findViewById(R.id.edtKQ);

        // Xử lý sự kiện khi nhấn nút "TỔNG"
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Lấy giá trị từ 2 ô nhập số
                    int a = Integer.parseInt(edtA.getText().toString());
                    int b = Integer.parseInt(edtB.getText().toString());

                    // Tính tổng
                    int c = a + b;

                    // Hiển thị kết quả
                    edtKQ.setText(String.valueOf(c));
                } catch (NumberFormatException e) {
                    // Nếu nhập không phải số, hiển thị lỗi
                    edtKQ.setText("Vui lòng nhập số hợp lệ!");
                }
            }
        });
    }
}