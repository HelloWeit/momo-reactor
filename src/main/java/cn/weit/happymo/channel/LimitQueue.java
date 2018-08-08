package cn.weit.happymo.channel;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LimitQueue<T> extends ConcurrentLinkedQueue<T>{
	private int limit;

	Queue<T> queue = new ConcurrentLinkedQueue<T>();

	public LimitQueue(int limit){
		this.limit = limit;
	}

	@Override
	public boolean offer(T t){
		if(queue.size() >= limit){
			queue.poll();
		}
		return queue.offer(t);
	}

	@Override
	public T poll() {
		return queue.poll();
	}
}
