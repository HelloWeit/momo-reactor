package cn.weit.happymo.reactor;

import cn.weit.happymo.channel.BaseMoChannel;
import cn.weit.happymo.channel.MoTcpChannel;
import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.handler.BaseMoHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Set;

/**
 * @author weitong
 */
@Slf4j
public class ReactorService {

	private Selector selector;

	public ReactorService() throws IOException {
		this.selector = Selector.open();
	}

	private class MainRunnable implements Runnable  {

		@Override
		public void run() {
			while (true) {
				if (Thread.interrupted()) {
					throw new MoException(ResultEnum.THREAD_INTERRUPTED);
				}
				try {
					selector.select();
				} catch (IOException e) {
					throw new MoException(ResultEnum.THREAD_INTERRUPTED);
				}

				Set<SelectionKey> keys = selector.selectedKeys();
				keys.stream().forEach(key -> {
					if (key.isAcceptable()) {
						try {
							SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
							socketChannel.configureBlocking(false);
							socketChannel.register(selector,SelectionKey.OP_READ).attach(key.attachment());
						} catch (IOException e) {
							throw new MoException(ResultEnum.IO_EXCEPTION);
						}
					}
					if (key.isValid()) {
						ThreadPoolReactor.instance().run(key);
					}
				});
			}
		}
	}


	public ReactorService withChannel(int port, BaseMoHandler handler) throws IOException {
		MoTcpChannel channel = new MoTcpChannel(handler);
		channel.bind(port);
		SelectionKey key = channel.getChannel().register(selector, SelectionKey.OP_ACCEPT);
		key.attach(channel);
		return this;
	}


	public void start() {
		Thread thread = new Thread(new MainRunnable());
		thread.start();
	}
}
