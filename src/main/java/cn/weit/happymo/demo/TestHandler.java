package cn.weit.happymo.demo;

import cn.weit.happymo.channel.BaseMoChannel;
import cn.weit.happymo.handler.BaseMoHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.SelectionKey;

/**
 * @author weitong
 */
@Slf4j
public class TestHandler implements BaseMoHandler{
	@Override
	public void handleChannelRead(BaseMoChannel channel, SelectionKey key, Object msg) {
		log.info("msg:{}", msg.toString());
		channel.write("hello momo", key);
	}
}
