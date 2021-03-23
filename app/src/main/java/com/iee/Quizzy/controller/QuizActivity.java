package com.iee.Quizzy.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iee.Quizzy.Behavioral.Observer.Observable;
import com.iee.Quizzy.Behavioral.Observer.Observer;
import com.iee.Quizzy.Behavioral.Observer.QuizCounter;
import com.iee.Quizzy.Creator.factoryMethod.product.Question;
import com.iee.Quizzy.R;
import com.iee.Quizzy.model.QuizBrain;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private TextView questionDisplay, scoreDisplay;
    private ProgressBar myProgress, timerProgress;
    private int  progress = 1;
    private TextView counter;
    private TextView nbQuestion;
    private long totalTime = 30000;
    private TextView[] choices;
    private ArrayList<String> selectedAnswers;
    private Button submit;

    private QuizCounter quizCounter;
    public static Observable observable;

    private QuizBrain quizBrain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initializeItems();
        quizBrain= new QuizBrain();
        quizBrain.initIterator();
        initFirstQuestion();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateScore();
            }
        });




    }

    private void UpdateScore() {
        quizBrain.UpdateScore(selectedAnswers);
        scoreDisplay.setText(String.valueOf(quizBrain.getScore()));
        toTheNextQuestion();
    }

    private void initFirstQuestion() {

        questionDisplay.setText(quizBrain.getQuestionsList().get(0).getQuestion());
        for (int i = 0; i < quizBrain.getQuestionsList().get(0).getChoices().length; i++) {

            choices[i].setText(quizBrain.getQuestionsList().get(quizBrain.getQstNb()).getChoices()[i]);
        }
        nbQuestion.setText("Question: " + String.valueOf(quizBrain.getQstNb()));
        quizCounter.startTimer();
    }



    private void initializeItems() {
        quizCounter= new QuizCounter();
        observable = new QuizCounter();
        observable.subscribe(this);
        quizCounter.setCounter();
        questionDisplay = findViewById(R.id.question);
        scoreDisplay = findViewById(R.id.score);
        myProgress = findViewById(R.id.myProgressBar);
        counter = findViewById(R.id.counter);
        timerProgress = findViewById(R.id.timerProgress);
        submit = findViewById(R.id.submitBtn);
        choices = new TextView[4];
        choices[0] = findViewById(R.id.choice1);
        choices[1] = findViewById(R.id.choice2);
        choices[2] = findViewById(R.id.choice3);
        choices[3] = findViewById(R.id.choice4);
        choices[0].setOnClickListener(this);
        choices[1].setOnClickListener(this);
        choices[2].setOnClickListener(this);
        choices[3].setOnClickListener(this);
        selectedAnswers = new ArrayList<>();
        nbQuestion = findViewById(R.id.nbQuestion);
    }

    private void toTheNextQuestion() {
        if (quizBrain.getQstNb() < quizBrain.getQuestionsList().size-1){
            Question nextQuestion = quizBrain.getNextQuestion();
            totalTime = 30000;
            nbQuestion.setText("Question: " + String.valueOf(quizBrain.getQstNb()));
            questionDisplay.setText(nextQuestion.getQuestion());
            for (int i = 0; i < nextQuestion.getChoices().length; i++) {

                choices[i].setText(nextQuestion.getChoices()[i]);
            }
            quizBrain.UpdateProgress();
            updateProgressUI();
            // Reset the answers List
            ClearPreviousAnswers();
            quizCounter.cancelTimer();
            quizCounter.startTimer();
        }else {
            quizCounter.cancelTimer();
            AlertDialog.Builder mbuilder = new AlertDialog.Builder(QuizActivity.this);
            mbuilder.setTitle("Game over");
            mbuilder.setMessage("The game ended and your score is : "+quizBrain.getScore());
            mbuilder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    quizBrain.ResetGame();
                    ClearPreviousAnswers();
                    initFirstQuestion();
                    quizCounter.setCounter();
                    scoreDisplay.setText("0");
                }
            });
            mbuilder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            mbuilder.show();


        }

    }

    private void ClearPreviousAnswers() {
        selectedAnswers= new ArrayList<>();
        for (TextView choice : choices) {
            choice.setBackgroundResource(R.color.white);
        }
    }

    private void updateProgressUI() {
        progress = quizBrain.getProgress();
        myProgress.setProgress(progress);
    }

    @Override
    public void onClick(View view) {

        checkOption(view);

    }

    private void checkOption(View view) {
        TextView textView = findViewById(view.getId());


        if (!selectedAnswers.contains(textView.getText().toString())) {
            selectedAnswers.add(textView.getText().toString());
            textView.setBackgroundResource(R.color.grey);

        } else {
            //Uncheck option
            selectedAnswers.remove(textView.getText().toString());
            textView.setBackgroundResource(R.color.white);

        }
        Log.d("array", selectedAnswers.toString());

    }

    @Override
    public void update(Observable observable, long l) {
        if (l>0){
            counter.setText(String.valueOf(l / 1000));
            double percentage = (double) (l * 100) / (double) (totalTime);
            int restProgress = (int) percentage;
            timerProgress.setProgress(restProgress);
        }else {
            toTheNextQuestion();
            Toast.makeText(QuizActivity.this, "You passed the timeout !", Toast.LENGTH_SHORT).show();
        }

    }


}