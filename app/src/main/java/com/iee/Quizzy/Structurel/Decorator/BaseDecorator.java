package com.iee.Quizzy.Structurel.Decorator;

import com.iee.Quizzy.Creator.factoryMethod.product.QCM;
import com.iee.Quizzy.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public abstract  class BaseDecorator extends QCM {

    protected Question question;

    public BaseDecorator(String question, ArrayList<String> answers, String[] choices, double weight) {
        super(question, answers, choices, weight);
    }

}
