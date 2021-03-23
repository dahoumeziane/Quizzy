package com.iee.Quizzy.Creator.factoryMethod.product;

import java.util.ArrayList;

public class QCU  extends Question{


    public QCU(String question, ArrayList<String> answers, String[] choices, double weight) {
        super(question, answers, choices, weight);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public double evaluate(ArrayList<String> reponses) {
       if (reponses.get(0).equals(getAnswers().get(0))){
           return getWeight();
       }else {
           return 0;
       }
    }
}
