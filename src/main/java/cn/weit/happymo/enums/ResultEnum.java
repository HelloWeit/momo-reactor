package cn.weit.happymo.enums;

import lombok.Getter;

/**
 * @author weitong
 */

@Getter
public enum ResultEnum {

	THREAD_INTERRUPTED(1, "线程中断");

	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
