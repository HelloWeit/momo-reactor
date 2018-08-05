package cn.weit.happymo.reactor;

import java.nio.channels.SelectionKey;
import java.util.concurrent.*;

/**
 * @author weitong
 */
public class ThreadPoolReactor {
	private ExecutorService executorService;

	private volatile static ThreadPoolReactor instance;

	private ThreadPoolReactor() {
		this.executorService = new ThreadPoolExecutor(10, 100,10,
				TimeUnit.MINUTES,  new ArrayBlockingQueue(100),
				r -> new Thread(r, "reactor" + r.hashCode())
		);
	}

	public static ThreadPoolReactor instance() {
		if (instance == null) {
			synchronized (ThreadPoolReactor.class) {
				if (instance == null) {
					instance = new ThreadPoolReactor();
				}
			}
		}
		return instance;
	}

	public void run(SelectionKey key) {

	}



}
