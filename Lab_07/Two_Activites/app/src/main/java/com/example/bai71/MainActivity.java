package com.example.bai71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 =(Button) findViewById(R.id.btnOpenChild);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent1 = new
                        Intent(MainActivity.this,ChildActivity.class);
                startActivity(intent1);
            }
        });
    }
}