package com.niepeng.goldcode.designpattern.chain;

/**
 * 责任链模式 (方便使用加了静态工厂)：
 * 抽象类RemoteManager，最核心的抽象的execute方法，有几个子类，分别是做：子类的构造方法中有一个参数RemoteManager。
 * 1.真正的网络请求得到结果
 * 2.尝试重试次数
 * 3.添加安全性参数
 * 4.检查网络状态，如果不行就返回(app端)
 * 5.记录网络请求花费时间，如果时间操过一定时间记录信息
 * 
 * 使用的时候，提供了几个静态工厂方法，new几个类型的RemoteManager，直接在构造方法中嵌套，就写在RemoteManager中了
 * 
 * Description:   
 * @author:     niepeng 
 * @version:    1.0  
 * Create at:   2017年4月12日 下午10:05:41  
 */
public abstract class RemoteManager {
    
    public abstract Request createQueryRequest(String target);
    
    public abstract Request createPostRequest(String target);
    
    public abstract Response execute(Request request);
    
    /**
     * 原生的RemoteManager，没有做过任何处理， 一般不推荐使用
     * @return
     */
    public static RemoteManager getRawRemoteManager() {
        return new HttpRemoteManager();
    }
    
    /**
     * 后台触发的不用登录建议使用这个
     * 
     * 带安全签名的RemoteManager
     * @return
     */
    public static RemoteManager getSecurityRemoteManager() {
        return new CheckNetworkStateRemoteManager(new SecurityRemoteManager(new HttpRemoteManager()));
    }
    
    /**
     * 新增数据的时候建议用这个，这个不会进行重试
     * 
     * 1、带安全签名
     * 2、检查网络状态
     * @return
     */
    public static RemoteManager getPostOnceRemoteManager() {
        return new CheckNetworkStateRemoteManager(new SecurityRemoteManager(new HttpRemoteManager()));
    }
    
    /**
     * 用户主动触发的一般建议使用这个
     * 
     * 1、带安全签名
     * 2、自动重连（3次）
     * 3、检查网络状态
     * @return
     */
    public static RemoteManager getFullFeatureRemoteManager() {
        return new RecordCallTimeRemoteManager(new CheckNetworkStateRemoteManager(new AutoConnectRemoteManager(new SecurityRemoteManager(new HttpRemoteManager()), 1)));
    }
}
