# momo-reactor
### 1. 说明
代码是根据reactor模型编写的。最开始计划一周内完成，结果断断续续搞了3周，时间有点不够。还未完成的有UDP服务，TCP、UDP客户端实现，注解形式调用。
### 2. 运行条件
jdk8 
### 3. 示例demo
```Java
public class Demo {
	public static void main(String[] args) throws IOException {
		
		Mo.moServerBuilder()
				.withChannel(8080, new TestHandler())
				.start();
	}
}

public class TestHandler implements BaseMoHandler{
	@Override
	public void handleChannelRead(BaseMoChannel channel, SelectionKey key, Object msg) {
		log.info("msg:{}", msg.toString());
		channel.write("hello momo", key);
	}
}
```
### 4. 版本
   - v1.0 并发框架reactor 支持TCP服务 （待测试）