package com.iee.Quizzy.Creator.factoryMethod.product;

import java.util.ArrayList;

public class QCM extends Question {

    public QCM(String question, ArrayList<String> answers, String[] choices, double weight) {
        super(question, answers, choices, weight);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }


    @Override
    public double evaluate(ArrayList<String> reponses) {
        if (reponses.size()==getAnswers().size()){
            int i=0;
            int sum=0;
            while (i<reponses.size()){
                int j=0;
                while (j<reponses.size()){
                    if (!reponses.get(i).equals(getAnswers().get(j))){
                        j++;
                    }else {
                        sum ++;
                        break;
                    }

                }
                i++;
            }
            return (getWeight()*sum)/(getAnswers().size());
        }else {
            return 0;
        }
    }

}
