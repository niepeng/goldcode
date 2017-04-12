package com.niepeng.goldcode.designpattern.chain;

/**
 * 
 * Description:   
 * @author:     niepeng 
 * @version:    1.0  
 * Create at:   2017年4月12日 下午10:34:15  
 *
 */
public class TestChain {

    public static void main(String[] args) {
        RemoteManager r1 = RemoteManager.getFullFeatureRemoteManager();
        r1.execute(null);
        
        System.out.println("------------------");
        
        RemoteManager r2 = RemoteManager.getPostOnceRemoteManager();
        r2.execute(null);
    }
    
}
