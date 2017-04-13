package com.niepeng.goldcode.designpattern.observerjdk;

import java.util.Observable;
/**
 * <p>标题: 主题</p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年3月23日  下午4:43:28</p>
 * <p>作者：niepeng</p>
 */ 
public class SpecialRepoter extends Observable {
    
    public void getNewNews(String msg) {
        this.setChanged();
        this.notifyObservers(msg);
    }
}