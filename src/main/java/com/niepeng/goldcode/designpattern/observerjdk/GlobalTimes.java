package com.niepeng.goldcode.designpattern.observerjdk;

import java.util.Observable;
import java.util.Observer;

public class GlobalTimes extends NewspaperOffice implements Observer {
    private Observable observable;

    public GlobalTimes(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof SpecialRepoter) {
            System.out.println("Global Timse brings you the latest news!");
        }
    }

    public void remove() {
        observable.deleteObserver(this);
    }
}