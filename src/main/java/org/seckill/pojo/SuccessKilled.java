package org.seckill.pojo;

import java.util.Date;

public class SuccessKilled {
    private Long seckillId;

    private Long userPhone;

    private Byte state;//状态标识:  -1  无效  0成功  1  已付款  2已发货  

    private Date createTime;
    
    private Seckill seckill;
    
    public SuccessKilled() {
	}
    
    public SuccessKilled(Long seckillId, Long userPhone, Byte state) {
		super();
		this.seckillId = seckillId;
		this.userPhone = userPhone;
		this.state = state;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + ", seckill=" + seckill + "]";
	}
    
}