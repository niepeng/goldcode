package com.niepeng.goldcode.spring.transactional2.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Entity {

    @Override
    public String toString() {
 	return ToStringBuilder.reflectionToString(this);
     }
}

