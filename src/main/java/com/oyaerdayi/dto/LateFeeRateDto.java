package com.oyaerdayi.dto;

//Gecikme Zammı Oranı Dto

import java.util.Date;

public class LateFeeRateDto {

    private Long id;
    private Date firstDate;
    private Date lastDate;
    private Double rate;

    public LateFeeRateDto(Long id, Date firstDate, Date lastDate, Double rate) {
        this.id = id;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.rate = rate;
    }


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
