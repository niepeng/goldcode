package com.niepeng.goldcode.designpattern.observer;

/**
 * <p>标题: 观察者</p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年3月23日  下午4:43:28</p>
 * <p>作者：niepeng</p>
 */ 
public interface Observer {

    //  method to update the observer, used by subject
    public void update();

    //  attach with subject to observe
    public void setSubject(Subject sub);
}