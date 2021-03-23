package com.iee.Quizzy.Behavioral.Iterator;

import com.iee.Quizzy.Creator.factoryMethod.product.Question;

public interface Iterator {
    public boolean hasNext();

    public Question next();
}
