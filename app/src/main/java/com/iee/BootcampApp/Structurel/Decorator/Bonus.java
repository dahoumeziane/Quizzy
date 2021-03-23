package com.iee.BootcampApp.Structurel.Decorator;


import com.iee.BootcampApp.Creator.factoryMethod.product.Question;

public class Bonus extends BaseDecorator {
    public Bonus(Question question){
        super(question.getQuestion(),question.getAnswers(),question.getChoices(),question.getWeight());
    }

    @Override
    public double getWeight() {
        return 5+weight;
    }


}
