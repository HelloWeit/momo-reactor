package cn.weit.happymo.channel;

import lombok.Getter;

/**
 * @author weitong
 */
@Getter
public abstract class BaseMoChannel {
	// TODO 完成 doWrite()，doRead(). ,bind()  write（） read（）需要继承类
	private void doWrite() {
		write();
	}

	private void doRead() {
		read();
	}

	abstract public void bind();

	abstract public void write();

	abstract public void read();

}
