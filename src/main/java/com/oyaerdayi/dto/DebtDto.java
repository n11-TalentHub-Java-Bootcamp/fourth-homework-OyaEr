package com.oyaerdayi.dto;

import java.math.BigDecimal;
import java.util.Date;

//Bu dto save,delete vs metotları için yapılmıştır. Update metodu için farklı bir dto kullanılacaktır.

public class DebtDto {

    private BigDecimal debtAmount;
    private BigDecimal remainingDebtAmount;
    private Date dueDate;
    private Long userId;

    public DebtDto(BigDecimal debtAmount, BigDecimal remainingDebtAmount, Date dueDate, Long userId) {
        this.debtAmount = debtAmount;
        this.remainingDebtAmount = remainingDebtAmount;
        this.dueDate = dueDate;
        this.userId = userId;
    }



    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getRemainingDebtAmount() {
        return remainingDebtAmount;
    }

    public void setRemainingDebtAmount(BigDecimal remainingDebtAmount) {
        this.remainingDebtAmount = remainingDebtAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
