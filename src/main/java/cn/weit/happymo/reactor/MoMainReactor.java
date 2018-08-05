package cn.weit.happymo.reactor;

import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author weitong
 */
//TODO 是否需要提出一个 Threadfactory 该factory增加线程异常的处理
public class MoMainReactor {

	private Selector selector;

	public MoMainReactor() throws IOException {
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


	public void start() {
		Thread thread = new Thread(new MainRunnable());
		thread.start();
	}
}
