package com.iee.BootcampApp.Behavioral.Iterator;

import com.iee.BootcampApp.Creator.factoryMethod.product.Question;

import java.util.ArrayList;

public class QuestionsList implements Container {
    private ArrayList<Question> questions;
    public int size;

    public QuestionsList() {

        questions = new ArrayList<>();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator createIterator() {
        return new QuestionIterator();
    }
    public void append(Question question) {
        questions.add(size++,question);
    }

    public Question get(int index) {
        return questions.get(index);
    }
    public class QuestionIterator  implements Iterator{
        private int index;


        public QuestionIterator() {
            // TODO Auto-generated constructor stub
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return index < size;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            return questions.get(index++);
        }
    }
}
