package com.niepeng.goldcode.designpattern.observer;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年3月23日  下午4:43:28</p>
 * <p>作者：niepeng</p>
 */ 
public class MyTopicSubscriber implements Observer {
    
    private String  name;
    private Subject topic;

    public MyTopicSubscriber(String nm) {
        this.name = nm;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else {
            System.out.println(name + ":: Consuming message::" + msg);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }

}