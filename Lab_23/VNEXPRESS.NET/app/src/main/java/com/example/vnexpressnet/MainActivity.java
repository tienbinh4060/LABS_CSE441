package com.example.vnexpressnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv1;
    ArrayList<ListArticle> articleList;
    MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        articleList = new ArrayList<>();

        Bitmap sampleImg = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        articleList.add(new ListArticle(sampleImg, "Tiêu đề bài 1", "Đây là tóm tắt bài viết số 1", "https://vnexpress.net/1"));
        articleList.add(new ListArticle(null, "Tiêu đề bài 2", "Tóm tắt bài 2 không có ảnh", "https://vnexpress.net/2"));

        adapter = new MyArrayAdapter(this, R.layout.layout_item, articleList);
        lv1.setAdapter(adapter);
    }
}
