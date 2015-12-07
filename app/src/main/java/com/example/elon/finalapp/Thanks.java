package com.example.elon.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Thanks extends Activity {

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(4000);
                    }
                } catch (InterruptedException ex) {
                }
                Intent intent = new Intent(Thanks.this, MainActivity.class);
                startActivity(intent);
            }

        };
        thread.start();

    }
}











