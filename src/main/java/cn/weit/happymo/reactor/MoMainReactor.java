package cn.weit.happymo.reactor;

import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;

import java.nio.channels.Selector;

/**
 * @author weitong
 */
//TODO 是否需要提出一个 Threadfactory 该factory增加线程异常的处理
public class MoMainReactor {

	private Selector selector;

	private class MainRunable implements Runnable  {

		@Override
		public void run() {
			//TODO 循环事件
			while (true) {
				if (Thread.interrupted()) {
					throw new MoException(ResultEnum.THREAD_INTERRUPTED);
				}


			}
		}
	}


	public void start() {
		Thread thread = new Thread(new MainRunable());
		thread.start();
	}
}
