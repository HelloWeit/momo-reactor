package cn.weit.happymo.demo;

import cn.weit.happymo.Mo;

import java.io.IOException;

/**
 * @author weitong
 */
public class Demo {
	public static void main(String[] args) throws IOException {
		
		Mo.moServerBuilder()
				.withChannel(8080, new TestHandler())
				.start();
	}
}
