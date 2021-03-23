package com.iee.BootcampApp.Creator.factoryMethod;

import com.iee.BootcampApp.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public abstract class QuestionCreator {

    public abstract Question createQuestion(String question, ArrayList<String> answers, String[] choices, double weight);
}
