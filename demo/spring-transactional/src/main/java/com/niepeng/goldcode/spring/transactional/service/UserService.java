package com.niepeng.goldcode.spring.transactional.service;

import com.niepeng.goldcode.spring.transactional.SpringUtil;
import com.niepeng.goldcode.spring.transactional.dao.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/3/24
 */
@Service
public class UserService {

  /**
   * 事务传播机制:
   * PROPAGATION_REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
   PROPAGATION_SUPPORTS--支持当前事务，如果当前没有事务，就以非事务方式执行。
   PROPAGATION_MANDATORY--支持当前事务，如果当前没有事务，就抛出异常。
   PROPAGATION_REQUIRES_NEW--新建事务，如果当前存在事务，把当前事务挂起，执行当前新建事务完成以后，上下文事务恢复再执行。
   PROPAGATION_NOT_SUPPORTED--以非事务方式执行操作，如果当前存在事务，就把当前事务挂起，执行当前逻辑，结束后恢复上下文的事务。
   PROPAGATION_NEVER--以非事务方式执行，如果当前存在事务，则抛出异常。

   隔离级别:
   1.ISOLATION_DEFAULT： 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.
   另外四个与JDBC的隔离级别相对应
   2. ISOLATION_READ_UNCOMMITTED： 这是事务最低的隔离级别，它充许令外一个事务可以看到这个事务未提交的数据。
   这种隔离级别会产生脏读，不可重复读和幻像读。
   3. ISOLATION_READ_COMMITTED： 保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据
   4. ISOLATION_REPEATABLE_READ： 这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。
   它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)。
   5. ISOLATION_SERIALIZABLE 这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。
   除了防止脏读，不可重复读外，还避免了幻像读。

   0.更新丢失（Lost update）： 两个事务都同时更新一行数据但是第二个事务却中途失败退出导致对数据两个修改都失效了这是系统没有执 行任何锁操作因此并发事务并没有被隔离开来。
   a.脏读取（Dirty Reads）： 一个事务开始读取 了某行数据但是另外一个事务已经更新了此数据但没有能够及时提交。这是相当危险很可能所有操作都被回滚。
   b. 不可重复读取（Non-repeatable Reads）： 一 个事务对同一行数据重复读取两次但是却得到了不同结果。例如在两次读取中途有另外一个事务对该行数据进行了修改并提交。
   c. 两次更新问题（Second lost updates problem）： 无法重复读取特例，有两个并发事务同时读取同一行数据然后其中一个对它进行修改提交而另一个也进行了修改提交这就会造成 第一次写操作失效。
   d. 幻读（Phantom Reads）： 也称为幻像（幻 影）。事务在操作过程中进行两次查询，第二次查询结果包含了第一次查询中未出现的数据（这里并不要求两次查询SQL语句相同）这是因为在两次查询过程中有 另外一个事务插入数据造成的。

   隔离级别     更新丢失      脏读取      重复读取     幻读
   未授权读取      N            Y         Y          Y
   授权读取        N            N         Y          Y
   可重复 读取     N            N         N          Y
   串行           N            N         N          N

   */


  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserService userService;

  @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
  public void opt1() {
    try {

      /**
       * 直接调用this.opt2() 并没有开启事务
       */
      // opt2();

      /**
       * 这里直接调用opt2()方法,注解事务失效,
       * 因为这里的this.opt2()调用的并不是Spring的代理对象(aop是通过代理实现)
       *
       * 解决办法有3种类
       */

      /**
       * 方案一:通过spring容器去获取代理类
       */
//      SpringUtil.getBean(UserService.class).opt2();

      /**
       * 方案二:通过注入
       */
//      userService.opt2();

      /**
       * 方案三:通过aopContext获取threadlocal中获取值
       * 暴露出aop代理，让其在方法中，获取到代理类的对象,需要开启:expose-proxy=“true”
       * 获取代理对象：AopContext.currentProxy()
       */
      ((UserService)AopContext.currentProxy()).opt2();

    } catch(Exception e) {
      e.printStackTrace();
    }
    userMapper.create("opt1", 18);
  }


  @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
  public void opt2() {
    userMapper.create("opt2", 19);
    int i = 2 / 0 ;
  }



}