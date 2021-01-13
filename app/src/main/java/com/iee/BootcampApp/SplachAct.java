package com.iee.BootcampApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplachAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (Exception e ){
                    e.printStackTrace();
                }finally {
                    // Send to another activity
                    Intent intent = new Intent(SplachAct.this,HomeAct.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        thread.start();

    }



}