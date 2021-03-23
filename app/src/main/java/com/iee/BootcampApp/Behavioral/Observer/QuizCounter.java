package com.iee.BootcampApp.Behavioral.Observer;

import android.os.CountDownTimer;


import java.util.ArrayList;
import java.util.List;

import static com.iee.BootcampApp.QuizActivity.observable;

public  class QuizCounter implements Observable {
    private List<Observer> observers;
    private long totalTime = 30000;
    private CountDownTimer countDownTimer;
    public QuizCounter() {
        this.observers = new ArrayList<>();
    }
    public void startTimer(){
        countDownTimer.start();
    }
    public void cancelTimer(){
        countDownTimer.cancel();

    }
    public void setCounter(){
        countDownTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long l) {
                observable.notify(l);

            }

            @Override
            public void onFinish() {
                //timeout
                observable.notify(0);
            }
        };
        countDownTimer.start();

    }

    @Override
    public void subscribe(Observer observer) {
        // TODO Auto-generated method stub
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        // TODO Auto-generated method stub
        this.observers.remove(observer);
    }

    @Override
    public void notify(long l) {
        // TODO Auto-generated method stub
        for (Observer observer : observers) {
            observer.update(this, l);
        }
    }


}