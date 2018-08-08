package cn.weit.happymo.enums;

import lombok.Getter;

/**
 * @author weitong
 */

@Getter
public enum ResultEnum {

	THREAD_INTERRUPTED(1, "线程中断"),
	IO_EXCEPTION(2, "IO异常"),
	SOCKET_CLOSE(3, "socket关闭"),
	BIND_FAILED(4, "绑定失败"),

	;
	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
