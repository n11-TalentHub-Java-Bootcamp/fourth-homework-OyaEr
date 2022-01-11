package com.oyaerdayi.entity;

import javax.persistence.*;
import java.util.Date;


//Gecikme zammı oranı tablosu
@Entity
@Table(name ="LATE_FEE_RATE")
public class LateFeeRate {

    @SequenceGenerator(name = "generator", sequenceName = "USER_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_DATE", nullable = false)
    private Date firstDate;

    @Column(name = "LAST_DATE", nullable = false)
    private Date lastDate;

    @Column(name = "RATE", nullable = false)
    private Double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
