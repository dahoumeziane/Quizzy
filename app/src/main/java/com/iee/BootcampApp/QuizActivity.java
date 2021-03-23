package com.iee.BootcampApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iee.BootcampApp.Behavioral.Iterator.QuestionsList;
import com.iee.BootcampApp.Behavioral.Observer.Observable;
import com.iee.BootcampApp.Behavioral.Observer.Observer;
import com.iee.BootcampApp.Behavioral.Observer.QuizCounter;
import com.iee.BootcampApp.Creator.factoryMethod.QCMCreator;
import com.iee.BootcampApp.Creator.factoryMethod.QCUCreator;
import com.iee.BootcampApp.Structurel.Decorator.Bonus;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private TextView questionDisplay, scoreDisplay;
    private ProgressBar myProgress, timerProgress;
    private double score = 0;
    private int qstNb = 0, progress = 1;
    private TextView counter;
    private TextView nbQuestion;
    private long totalTime = 30000;
    private TextView[] choices;
    private ArrayList<String> selectedAnswers;
    private Button submit;
    private QuestionsList questionsList;
    private QCMCreator qcmCreator ;
    private QCUCreator qcuCreator;
    private QuizCounter quizCounter;
    public static Observable observable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initializeItems();


        initIterator();
        initCreator();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EvaluateAnswers();
            }
        });


    }

    private void EvaluateAnswers() {
        score=score+questionsList.get(qstNb).evaluate(selectedAnswers);
        Log.d("Score", String.valueOf(score));
        scoreDisplay.setText(String.valueOf(score));
        toTheNextQuestion();
    }

    private void initCreator() {

        questionDisplay.setText(questionsList.get(0).getQuestion());
        for (int i = 0; i < questionsList.get(0).getChoices().length; i++) {

            choices[i].setText(questionsList.get(qstNb).getChoices()[i]);
        }
        nbQuestion.setText("Question: " + String.valueOf(qstNb));
        quizCounter.startTimer();
    }

    private void initIterator(){
        questionsList = new QuestionsList();
        ArrayList<String> answers = new ArrayList<>(1);
        answers.add("1999");
        questionsList.append(new Bonus((qcmCreator.createQuestion("When is my raouf birthday ?", answers, new String[]{"1999", "2000", "1998", "1997"}
                , 10))));
        questionsList.append(qcmCreator.createQuestion("When is my birthday ?", answers, new String[]{"1999", "2000", "1998", "1997"}
                , 10));
        questionsList.append(qcuCreator.createQuestion("Qcu question", answers, new String[]{"option 1", "option 2", "option 3", "option 4"}
                , 10));
    }

    private void initializeItems() {
        qcmCreator = new QCMCreator();
        qcuCreator = new QCUCreator();
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
        if (qstNb < questionsList.getSize() - 1) {
            totalTime = 30000;
            qstNb++;
            nbQuestion.setText("Question: " + String.valueOf(qstNb));
            questionDisplay.setText(questionsList.get(qstNb).getQuestion());
            for (int i = 0; i < questionsList.get(qstNb).getChoices().length; i++) {

                choices[i].setText(questionsList.get(qstNb).getChoices()[i]);
            }
            updateProgress();
            quizCounter.cancelTimer();
            quizCounter.startTimer();
            // Reset the answers List

            ClearPreviousAnswers();

        } else {

            qstNb = 0;
            progress = 0;
            score = 0;
            quizCounter.cancelTimer();
            initCreator();
        }
    }

    private void ClearPreviousAnswers() {
        selectedAnswers= new ArrayList<>();
        for (TextView choice : choices) {
            choice.setBackgroundResource(R.color.white);
        }
    }

    private void updateProgress() {
        progress = (int) (((double) (qstNb + 1) / (double) questionsList.getSize()) * 100);
        myProgress.setProgress(progress);
    }

    @Override
    public void onClick(View view) {

        checkAnswer(view);

    }

    private void checkAnswer(View view) {
        TextView textView = findViewById(view.getId());


        if (!selectedAnswers.contains(textView.getText().toString())) {
            selectedAnswers.add(textView.getText().toString());
            textView.setBackgroundResource(R.color.grey);
            Log.d("selected", "selected");

        } else {
            selectedAnswers.remove(textView.getText().toString());
            textView.setBackgroundResource(R.color.white);
            Log.d("unselected", "unselected item");


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