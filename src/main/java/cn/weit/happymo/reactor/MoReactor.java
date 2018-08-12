package cn.weit.happymo.reactor;

import cn.weit.happymo.channel.BaseMoChannel;
import cn.weit.happymo.dispatch.BaseMoDispatcher;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */

public class MoReactor implements Runnable{

	private SelectionKey key;

	private BaseMoDispatcher dispatcher;

	public MoReactor(SelectionKey key, BaseMoDispatcher dispatcher) {
		this.key = key;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		if (key.isReadable()) {
			Object readObject = ((BaseMoChannel) key.attachment()).read(key);
			dispatcher.onChannelReadEvent((BaseMoChannel) key.attachment(),readObject, key);
		}
		if (key.isWritable()) {
			BaseMoChannel channel = (BaseMoChannel) key.attachment();
			try {
				channel.doWrite(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



}
