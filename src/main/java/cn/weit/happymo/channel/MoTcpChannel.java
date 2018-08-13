package cn.weit.happymo.channel;

import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.handler.BaseMoHandler;
import cn.weit.happymo.reactor.MoReactor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * @author weitong
 */
@Slf4j
public class MoTcpChannel extends BaseMoChannel {
	@Getter
	private ServerSocketChannel channel;

	public MoTcpChannel(BaseMoHandler handler) {
		setHandler(handler);
	}

	@Override
	public void bind(int port){
		try {
			this.channel = ServerSocketChannel.open();
			channel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
			channel.configureBlocking(false);
			log.info("bind port:{}", port);
		} catch (Exception e) {
			throw new MoException(ResultEnum.BIND_FAILED);
		}

	}

	@Override
	public void write(Object data , SelectionKey key) {
		Queue<Object> queue = getQueue(key.channel());
		queue.offer(data);
		key.interestOps(SelectionKey.OP_WRITE);
	}

	@Override
	public Object read(SelectionKey key)  {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int read;
		try {
			read = socketChannel.read(buffer);
			buffer.flip();
		} catch (Exception e) {
			throw new MoException(ResultEnum.IO_EXCEPTION);
		}
		if (read == -1) {
			throw new MoException(ResultEnum.SOCKET_CLOSE);
		}
		return buffer;
	}
}
