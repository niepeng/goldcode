package com.niepeng.goldcode.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2015年2月23日  下午7:12:10</p>
 * <p>作者：niepeng</p>
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/lsb/data/code/git/goldcode/goldcode/src/main/java/com/niepeng/goldcode/serializable/tmpself");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SeriBean sb = new SeriBean();
		sb.setAge(12);
		sb.setName("hello");
		oos.writeObject(sb);
		oos.flush();
		oos.close();
	}
	
	public static void main1(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		SeriBean sb = new SeriBean();
		sb.setAge(12);
		sb.setName("hello");
		System.out.println(sb);
		
		out.writeObject(sb);
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		SeriBean sb2 = (SeriBean) in.readObject();
		System.out.println(sb2);
	}
}