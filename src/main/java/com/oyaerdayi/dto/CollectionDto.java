package com.oyaerdayi.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CollectionDto {

    private Long id;
    private BigDecimal lateFee;
    private Date recordDate;
    private Long debtId;
    private Long userId;

    public CollectionDto(Long id, BigDecimal lateFee, Date recordDate, Long debtId, Long userId) {
        this.id = id;
        this.lateFee = lateFee;
        this.recordDate = recordDate;
        this.debtId = debtId;
        this.userId = userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
