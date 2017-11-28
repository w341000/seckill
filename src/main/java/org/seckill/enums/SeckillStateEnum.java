package org.seckill.enums;
/**
 * 枚举表示常量字段
 */
public enum SeckillStateEnum {
	/**
	 * 秒杀成功
	 */
	SUCCESS(1,"秒杀成功"),
	/**
	 * 秒杀结束
	 */
	END(0,"秒杀结束"),
	/**
	 * 重复秒杀
	 */
	REPEAT_KILL(-1,"重复秒杀"),
	/**
	 * 系统异常
	 */
	INNER_ERROR(-2,"系统异常"),
	/**
	 * 数据被篡改
	 */
	DATA_REWRITE(-3,"数据被篡改");
	private int state;
	private String stateInfo;
	private SeckillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public static SeckillStateEnum stateOf(int state) {
		for (SeckillStateEnum stateEnum : values()) {
			if(stateEnum.getState()==state) {
				return stateEnum;
			}
		}
		return null;
	}
}
