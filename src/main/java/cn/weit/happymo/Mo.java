package cn.weit.happymo;

import cn.weit.happymo.enums.ResultEnum;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.reactor.ReactorService;

public class Mo {
	private Mo() {
		throw new MoException(ResultEnum.INIT_ERROR);
	}


	public static ReactorService moServerBuilder() {
		return new ReactorService();
	}
}

//TODO 目前进展 需要将 channel注册到key中
