package com.iee.Quizzy.Creator.factoryMethod.product;

import java.util.ArrayList;

public abstract class Question implements IQuestion {

    private String question ;
    private ArrayList<String> answers;
    private String [] choices;
    protected double weight;


    public Question(String question, ArrayList<String> answers, String[] choices, double weight) {
        this.question = question;
        this.answers = answers;
        this.choices = choices;
        this.weight = weight;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public abstract double getWeight() ;

    public void setWeight(double weight) {
        this.weight = weight;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }



}
