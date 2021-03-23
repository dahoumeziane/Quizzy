package com.iee.Quizzy.Creator.factoryMethod;

import com.iee.Quizzy.Creator.factoryMethod.product.QCM;
import com.iee.Quizzy.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public class QCMCreator extends QuestionCreator {

    @Override
    public Question createQuestion(String question, ArrayList<String> answers, String[] choices, double weight) {
        return new QCM(question,answers,choices,weight);
    }
}
