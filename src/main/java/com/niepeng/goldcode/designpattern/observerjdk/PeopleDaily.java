package com.niepeng.goldcode.designpattern.observerjdk;

import java.util.Observable;
import java.util.Observer;

public class PeopleDaily extends NewspaperOffice implements Observer {
    
    private Observable observable;

    public PeopleDaily(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SpecialRepoter) {
            System.out.println("People's Daily brings you the latest news!");
        }
    }

    public void remove() {
        observable.deleteObserver(this);
    }
}
