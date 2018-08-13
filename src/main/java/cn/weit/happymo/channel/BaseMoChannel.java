package cn.weit.happymo.channel;

import cn.weit.happymo.handler.BaseMoHandler;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author weitong
 */
@Getter
public abstract class BaseMoChannel {

	private final Map<SelectableChannel, Queue<Object>> writeQueueMap = new ConcurrentHashMap<>();

	@Setter
	private BaseMoHandler handler;

	abstract public void bind(int port) throws IOException;

	abstract public Object read(SelectionKey key);

	abstract public void write(Object data , SelectionKey key);

	public void doWrite(SelectionKey key) throws IOException {
		Queue<Object> queue = writeQueueMap.get(key.channel());
		while (true) {
			Object object = queue.poll();
			if (object == null) {
				key.interestOps(SelectionKey.OP_READ);
				break;
			}

			ByteBuffer buffer = (ByteBuffer) object;
			((SocketChannel) key.channel()).write(buffer);
		}
	}

	public Queue<Object> getQueue(SelectableChannel channel) {
		if (!writeQueueMap.containsKey(channel)) {
			Queue<Object> queue = new ConcurrentLinkedQueue<>();
			writeQueueMap.put(channel, queue);
			return queue;
		}
		return writeQueueMap.get(channel);
	}
}
