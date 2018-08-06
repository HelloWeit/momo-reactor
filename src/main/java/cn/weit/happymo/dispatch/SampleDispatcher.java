package cn.weit.happymo.dispatch;

import cn.weit.happymo.channel.BaseMoChannel;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */
public class SampleDispatcher implements BaseMoDispatcher {
	@Override
	public void onChannelReadEvent(BaseMoChannel channel, Object readObject, SelectionKey key) {

	}
}
