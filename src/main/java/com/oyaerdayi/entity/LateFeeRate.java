package com.oyaerdayi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


//Gecikme zammı oranı tablosu
@Entity
@Table(name ="LATE_FEE_RATE")
public class LateFeeRate {

    @SequenceGenerator(name = "generator", sequenceName = "LATE_FEE_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date firstDate;

    @Column(name = "LAST_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastDate;

    @Column(name = "RATE", nullable = false, scale=2)
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
