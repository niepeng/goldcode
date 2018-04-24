
create database test1;
use test1;
CREATE TABLE `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` VARCHAR(32) DEFAULT NULL COMMENT '用户名',
  `passWord` VARCHAR(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

create database test2;
use test2;
CREATE TABLE `user_informations` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userid` BIGINT(20) DEFAULT NULL COMMENT '用户id',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '邮件',
  `address` VARCHAR(32) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;