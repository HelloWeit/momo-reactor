package cn.weit.happymo.handler;

import cn.weit.happymo.channel.BaseMoChannel;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */
public interface BaseMoHandler {

	void handleChannelRead(BaseMoChannel channel, SelectionKey key, Object msg);
}
