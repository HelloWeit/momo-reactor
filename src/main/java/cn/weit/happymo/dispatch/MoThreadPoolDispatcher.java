package cn.weit.happymo.dispatch;

import cn.weit.happymo.channel.BaseMoChannel;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weitong
 */
public class MoThreadPoolDispatcher implements BaseMoDispatcher {
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	@Override
	public void onChannelReadEvent(BaseMoChannel channel, Object readObject, SelectionKey key) {
		executorService.execute(() -> channel.getHandler().handleChannelRead(channel, key, readObject));
	}
}
