package cn.weit.happymo.dispatch;

import cn.weit.happymo.channel.BaseMoChannel;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */
public class MoThreadPoolDispatcher implements BaseMoDispatcher {
	@Override
	public void onChannelReadEvent(BaseMoChannel channel, Object readObject, SelectionKey key) {
		//TODO 掉用线程池 来处理 handler
	}
}
