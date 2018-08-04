package cn.weit.happymo.reactor;

import java.util.concurrent.*;

/**
 * @author weitong
 */
public class ThreadPoolReactor {
	private ExecutorService executorService;

	public ThreadPoolReactor(int poolSize)
	{
		this.executorService = Executors.newFixedThreadPool(poolSize);
	}
	//TODO 考虑用guava线程池 直接替换
	public ThreadPoolReactor() {
		this.executorService = new ThreadPoolExecutor(10, 100,10,
				TimeUnit.MINUTES,  new ArrayBlockingQueue(100),
				(ThreadFactory) r -> new Thread(r, "reactor" + r.hashCode())
		);
	}

}
