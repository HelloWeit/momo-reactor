package cn.weit.happymo.channel;

import lombok.Getter;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */
@Getter
public abstract class BaseMoChannel {
	// TODO 完成 doWrite()，doRead(). ,bind()  write（） read（）需要继承类
	private void doWrite() {
		write();
	}

	private void doRead(SelectionKey key) {
		read(key);
	}

	abstract public void bind();

	abstract public void write();

	abstract public Object read(SelectionKey key);

}
