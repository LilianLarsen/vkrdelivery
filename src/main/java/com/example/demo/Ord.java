package com.example.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long oid;

    private String startAddress;
    private String endAddress;
    private long clientId;
    private long courierId;
    private int cost;
    private String deliveryType;
    private String deliveryObj;
    private Date deliveryDate;
    private String deliveryTime;
    private String status;
    private Date startDate;

    protected Ord(){}

    public long getO_id() {
        return oid;
    }

    public String getStartAddress() {
        return startAddress;
    }
    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }
    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getCourierId() {
        return courierId;
    }
    public void setCourierId(long courierId) {
        this.courierId = courierId;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDeliveryType() {
        return deliveryType;
    }
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryObj() {
        return deliveryObj;
    }
    public void setDeliveryObj(String deliveryObj) {
        this.deliveryObj = deliveryObj;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
