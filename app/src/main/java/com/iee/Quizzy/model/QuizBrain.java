package com.iee.Quizzy.model;

import com.iee.Quizzy.Behavioral.Iterator.Iterator;
import com.iee.Quizzy.Behavioral.Iterator.QuestionsList;
import com.iee.Quizzy.Behavioral.Observer.Observable;
import com.iee.Quizzy.Behavioral.Observer.QuizCounter;
import com.iee.Quizzy.Creator.factoryMethod.QCMCreator;
import com.iee.Quizzy.Creator.factoryMethod.QCUCreator;
import com.iee.Quizzy.Creator.factoryMethod.product.Question;
import com.iee.Quizzy.Structurel.Decorator.Bonus;

import java.util.ArrayList;

public class QuizBrain {
    private double score = 0;
    private int qstNb = 0, progress = 1;
    private long totalTime = 30000;
    private QuestionsList questionsList;
    private QCMCreator qcmCreator ;
    private QCUCreator qcuCreator;
    public static Observable observable;

    public QuizBrain() {
        qcmCreator = new QCMCreator();
        qcuCreator = new QCUCreator();


        initIterator();
    }

    public void UpdateScore(ArrayList<String> selectedAnswers) {
        score = score + questionsList.get(qstNb).evaluate(selectedAnswers);

    }
    public void initIterator(){
        questionsList = new QuestionsList();
        ArrayList<String> answers = new ArrayList<>();
        answers.add("2");
        answers.add("-2");
        questionsList.append(qcmCreator.createQuestion("What is the solution of x^2=4", answers, new String[]{"2", "99", "-2", "1,5"}
                , 5));
        answers = new ArrayList<>();
        answers.add("Italy");

        questionsList.append(qcuCreator.createQuestion("Rom is Capital of...", answers, new String[]{"Algeria", "Italy", "France", "Egypt"}
                , 12));
        answers = new ArrayList<>();
        answers.add("100 degrees");
        answers.add("212 fahrenheit");
        questionsList.append(new Bonus(qcmCreator.createQuestion("The water evaporate in...", answers, new String[]{"212 fahrenheit", "60 degrees", "100 degrees", "170 degrees"}
                , 20)));
        answers = new ArrayList<>();
        answers.add("No answer");
        questionsList.append(new Bonus((qcmCreator.createQuestion("RAM stands for...", answers, new String[]{"Read only memory", "Read access memory", "All answers", "No answer"}
                , 60))));
    }
    public Question getNextQuestion() {
        qstNb++;
        Iterator iterator = questionsList.createIterator();

        return questionsList.get(qstNb);

    }
    public void UpdateProgress(){
        progress= (int) (((double) (qstNb + 1) / (double) questionsList.getSize()) * 100);
    }
    public void ResetGame(){
        qstNb=0;
        progress = 0;
        score = 0;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setQstNb(int qstNb) {
        this.qstNb = qstNb;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public void setQuestionsList(QuestionsList questionsList) {
        this.questionsList = questionsList;
    }

    public void setQcmCreator(QCMCreator qcmCreator) {
        this.qcmCreator = qcmCreator;
    }

    public void setQcuCreator(QCUCreator qcuCreator) {
        this.qcuCreator = qcuCreator;
    }


    public static void setObservable(Observable observable) {
        QuizBrain.observable = observable;
    }

    public double getScore() {
        return score;
    }

    public int getQstNb() {
        return qstNb;
    }

    public int getProgress() {
        return progress;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public QuestionsList getQuestionsList() {
        return questionsList;
    }

    public QCMCreator getQcmCreator() {
        return qcmCreator;
    }

    public QCUCreator getQcuCreator() {
        return qcuCreator;
    }



    public static Observable getObservable() {
        return observable;
    }
}
