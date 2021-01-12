package com.iee.BootcampApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeAct extends AppCompatActivity {
    private TextView questionDisplay,scoreDisplay;
    private Button trueBtn,falseBtn;
    private ProgressBar myProgress;
    private Question [] questionsList ;
    private int score = 0;
    private int qstNb = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inisailizeItems();

        questionsList = new Question[]
                {new Question("If we add three to five we'll get nine","False"),
                        new Question("The number of days in week is equals to seven","True"),
                        new Question("Algeria is the biggest country in africa","True"),
                        new Question("html is a programming language","False"),
                        new Question("lol means lot of luck","False"),
                        new Question("If we heat the water in 100 degree it will evaporate","True"),
                        new Question("Inelec has a good administration","False"),
                        new Question("we always use  the semicolon in python code","False"),
                        new Question("Algeria connection is the best in the world ","False")
                };
        questionDisplay.setText(questionsList[0].getQuestion());
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(trueBtn);
                toTheNextQuestion();

            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(falseBtn);
                toTheNextQuestion();
            }
        });




    }
    private void checkAnswer(Button button){
        if (questionsList[qstNb].getAnswer().equals(button.getText().toString())){ // The user is right
            Toast.makeText(this, "You are right !", Toast.LENGTH_SHORT).show();
            updateScore();
        }else {
            Toast.makeText(this, "You are wrong !", Toast.LENGTH_SHORT).show(); // The user is wrong
        }

    }
    private  void toTheNextQuestion(){

        if (qstNb<questionsList.length-1){
            qstNb++;

        }else {
            qstNb =0;
        }
        questionDisplay.setText(questionsList[qstNb].getQuestion());

    }

    private void updateScore(){

        if (qstNb<questionsList.length-1){
            score++;

        }else {
            score= 0;
        }
        scoreDisplay.setText("Your score is : "+String.valueOf(score));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(HomeAct.this);
        mbuilder.setTitle("Alert Message ");
        mbuilder.setMessage(getString(R.string.exit_msg));
        mbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeAct.super.onBackPressed();
            }
        });
        mbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mbuilder.show();


    }


    private void inisailizeItems(){
        questionDisplay=findViewById(R.id.question);
        scoreDisplay=findViewById(R.id.score);
        trueBtn=findViewById(R.id.trueBtn);
        falseBtn=findViewById(R.id.falseBtn);
        myProgress=findViewById(R.id.myProgressBar);

    }

}