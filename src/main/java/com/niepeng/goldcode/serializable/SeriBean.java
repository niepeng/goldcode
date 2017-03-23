package com.niepeng.goldcode.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年2月23日  下午7:12:29</p>
 * <p>作者：niepeng</p>
 */
public class SeriBean implements Serializable {

	private static final long serialVersionUID = -5065182671776135335L;

	private String name;
	
	private int age;
	
	private void readObject(ObjectInputStream in) throws IOException {
//		this.name = in.readUTF();
		this.age = in.readInt();
	}
	
	private void writeObject(ObjectOutputStream os) throws IOException {
//		os.writeUTF(name);
		os.writeInt(age);
	}
	
	@Override
	public String toString() {
		return "name=" + name + ",apge=" + age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}