package com.example.alumnis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent i=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };thread.start();
    }

}