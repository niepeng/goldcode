package com.niepeng.goldcode.designpattern.observerjdk;

public class Test {
    public static void main(String[] args) {

        SpecialRepoter repoter = new SpecialRepoter();
        GlobalTimes n1 = new GlobalTimes(repoter);
        PeopleDaily n2 = new PeopleDaily(repoter);
        XinhuaDaily n3 = new XinhuaDaily(repoter);

        repoter.getNewNews("new news!");
        n2.remove();
        repoter.getNewNews("another new news!");
    }
}