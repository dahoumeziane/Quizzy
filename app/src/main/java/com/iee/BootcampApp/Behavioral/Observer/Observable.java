package com.iee.BootcampApp.Behavioral.Observer;

public interface Observable {
    public void subscribe(Observer observer);
    public void unsubscribe(Observer observer);
    public void notify(long data);
}
