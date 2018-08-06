package cn.weit.happymo.reactor;

import cn.weit.happymo.channel.BaseMoChannel;
import cn.weit.happymo.dispatch.BaseMoDispatcher;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */

public class MoReactor implements Runnable{

	private SelectionKey key;

	private BaseMoDispatcher dispatcher;

	public MoReactor(SelectionKey key) {
		this.key = key;
	}

	@Override
	public void run() {
		if (key.isReadable()) {
			Object readObject = ((BaseMoChannel) key.attachment()).read(key);
			dispatcher.onChannelReadEvent((BaseMoChannel) key.attachment(),readObject, key);
		}
	}



}
