package com.example.demo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Courier {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;

/*    @OneToOne(cascade = CascadeType.ALL, mappedBy = "courier")
    Person person;*/

/*    @OneToMany()
    @JoinColumn(name = "o_id",referencedColumnName = "c_id")
    private Set<Order> c_order;*/

    private Double rating;
    private int markCount;
    private Double markSum;
    private int appealCount;
    private int warningCount;
    private String passportSerias;
    private String passportNum;
    private Date birthDate;
    private String motionType;

    protected Courier() {}

    public long getCid() {
        return cid;
    }
    public void setCid(long cid) {
        this.cid = cid;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getMarkCount() {
        return markCount;
    }
    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }

    public Double getMarkSum() {
        return markSum;
    }
    public void setMarkSum(Double markSum) {
        this.markSum = markSum;
    }

    public int getAppealCount() {
        return appealCount;
    }
    public void setAppealCount(int appealCount) {
        this.appealCount = appealCount;
    }

    public int getWarningCount() {
        return warningCount;
    }
    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public String getPassportSerias() {
        return passportSerias;
    }
    public void setPassportSerias(String passportSerias) {
        this.passportSerias = passportSerias;
    }

    public String getPassportNum() {
        return passportNum;
    }
    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMotionType() {
        return motionType;
    }
    public void setMotionType(String motionType) {
        this.motionType = motionType;
    }
}
