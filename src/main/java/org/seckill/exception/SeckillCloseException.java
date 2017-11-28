package org.seckill.exception;
/**
 * 秒杀结束异常
 */
public class SeckillCloseException extends SeckillException {

	public SeckillCloseException() {
		super();
	}

	public SeckillCloseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}

	public SeckillCloseException(Throwable cause) {
		super(cause);
	}

}
