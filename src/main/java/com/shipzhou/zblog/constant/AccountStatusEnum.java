package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
public enum  AccountStatusEnum {
    NORMAL("正常",1),
    FROZEN("冻结",2);

    private String statusName;
    private Integer status;

    AccountStatusEnum(String statusName, Integer status) {
        this.statusName = statusName;
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public Integer getStatus() {
        return status;
    }
}
