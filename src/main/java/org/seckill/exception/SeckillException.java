package org.seckill.exception;
/**
 * 秒杀相关异常
 */
public class SeckillException extends RuntimeException {

	public SeckillException() {
	}

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(Throwable cause) {
		super(cause);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
