package com.niepeng.goldcode.designpattern.observer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>标题: 实现具体的主题</p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年3月23日  下午4:43:28</p>
 * <p>作者：niepeng</p>
 */ 
public class MyTopic implements Subject {

    private List<Observer> observers;

    private String         message;

    // 这个变量是必须的，因为如果没有更新，但是有人调用notifyObservers()方法，他就不能发送错误的通知信息给观察者
    private AtomicBoolean  changed = new AtomicBoolean(false);

    private final Object   MUTEX   = new Object();

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        if (obj == null)
            throw new NullPointerException("Null Observer");
        if (!observers.contains(obj))
            observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        // 确保在消息被发布给主题之前，通知只能被发送到注册的观察者处
        synchronized (MUTEX) {
            if (!changed.get())
                return;
            observersLocal = new ArrayList<>(this.observers);
            changed.set(false);
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }

    // method to post message to the topic
    public void postMessage(String msg) {
        System.out.println("Message Posted to Topic:" + msg);
        this.message = msg;
        changed.set(true);
        notifyObservers();
    }
}