package com.iee.BootcampApp.Structurel.Decorator;

import com.iee.BootcampApp.Creator.factoryMethod.product.QCM;
import com.iee.BootcampApp.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public abstract  class BaseDecorator extends QCM {

    protected Question question;

    public BaseDecorator(String question, ArrayList<String> answers, String[] choices, double weight) {
        super(question, answers, choices, weight);
    }

}
