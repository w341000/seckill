package org.seckill.exception;
/**
 * 重复秒杀异常
 * @author Administrator
 *
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException() {
	}

	public RepeatKillException(String arg0) {
		super(arg0);
	}

	public RepeatKillException(Throwable arg0) {
		super(arg0);
	}

	public RepeatKillException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RepeatKillException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
