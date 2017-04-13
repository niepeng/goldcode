package com.niepeng.goldcode.designpattern.observerjdk;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>标题: 观察者</p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年3月23日  下午4:43:28</p>
 * <p>作者：niepeng</p>
 */ 
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
