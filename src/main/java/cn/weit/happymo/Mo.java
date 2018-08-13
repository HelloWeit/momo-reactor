package cn.weit.happymo;

import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.reactor.ReactorService;

import java.io.IOException;

/**
 * @author weitong
 */
public class Mo {

	private Mo() {
		throw new MoException(ResultEnum.INIT_ERROR);
	}


	public static ReactorService moServerBuilder() throws IOException {
		return new ReactorService();
	}
}

