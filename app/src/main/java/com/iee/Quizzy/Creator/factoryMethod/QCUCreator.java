package com.iee.Quizzy.Creator.factoryMethod;

import com.iee.Quizzy.Creator.factoryMethod.product.QCU;
import com.iee.Quizzy.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public class QCUCreator extends QuestionCreator {
    @Override
    public Question createQuestion(String question, ArrayList<String> answers, String[] choices, double weight) {
        return new QCU(question,answers,choices,weight);
    }
}
