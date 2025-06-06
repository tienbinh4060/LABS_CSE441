package com.example.intentserviceapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Myservice extends Service {
    private MediaPlayer mymedia;

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Không hỗ trợ binding
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mymedia = MediaPlayer.create(this, R.raw.trentinhbanduoitinhyeu);
        mymedia.setLooping(true); // Lặp lại bài hát
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mymedia != null && !mymedia.isPlaying()) {
            mymedia.start();
        }
        return START_STICKY; // Giữ service chạy
    }

    @Override
    public void onDestroy() {
        if (mymedia != null) {
            if (mymedia.isPlaying()) {
                mymedia.stop();
            }
            mymedia.release();
            mymedia = null;
        }
        super.onDestroy();
    }
}
