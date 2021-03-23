package com.iee.Quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.iee.Quizzy.controller.QuizActivity;

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
                    Intent intent = new Intent(SplachAct.this, QuizActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        thread.start();

    }



}