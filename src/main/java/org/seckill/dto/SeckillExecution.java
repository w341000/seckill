package org.seckill.dto;
/**
 * 封装秒杀执行结果
 */

import org.seckill.enums.SeckillStateEnum;
import org.seckill.pojo.SuccessKilled;

public class SeckillExecution {
	
	private long seckillId;
	//秒杀执行的结果状态
	private int state;
	//状态信息
	private String stateInfo;
	//秒杀成功对象
	private SuccessKilled successKilled;
	public SeckillExecution(long seckillId, SeckillStateEnum state, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = state.getState();
		this.stateInfo = state.getStateInfo();
		this.successKilled = successKilled;
	}
	public SeckillExecution(long seckillId,SeckillStateEnum state) {
		super();
		this.seckillId = seckillId;
		this.state = state.getState();
		this.stateInfo = state.getStateInfo();
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
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
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
	

}
