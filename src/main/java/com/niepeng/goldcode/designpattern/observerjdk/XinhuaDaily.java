package com.niepeng.goldcode.designpattern.observerjdk;

import java.util.Observable;
import java.util.Observer;

public class XinhuaDaily extends NewspaperOffice implements Observer {
    
    private Observable observable;

    public XinhuaDaily(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SpecialRepoter) {
            System.out.println("Xinhua Daily brings you the latest news!");
        }
    }
    
    public void remove() {
        observable.deleteObserver(this);
    }
    
}