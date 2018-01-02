package com.junlong0716.module.common.rxbus;

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午1:15 2018/1/1
 * @modified by:
 */
public class BusData {
    String id;
    String status;

    public BusData() {
    }

    public BusData(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
