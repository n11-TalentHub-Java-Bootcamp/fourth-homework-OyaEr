package com.oyaerdayi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="DEBT")
public class Debt {


    @SequenceGenerator(name = "generator", sequenceName = "DEBT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    //ana borç tutarı, değiştirilemez olan!
    @Column(name = "DEBT_AMOUNT", precision = 19, scale = 2)
    private BigDecimal debtAmount;

    //Tahsilat sonrası kalan borç !
    @Column(name = "REMAINING_DEBT", precision = 19, scale = 2)
    private BigDecimal remainingDebtAmount;

    //vade tarihi alanı
    @Column(name = "DUE_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;


    //Borcun hangi kullanıcıya ait olduğu bilgisi için.
    @Column(name= "USER_ID", nullable = false)
    private Long userId;



    //TODO: Gecikme zammı hesaplanacak, alan tutulacak "transient" olarak. vade tarihi geçince gecikme zammı eklenecek??
    //TODO: Gecikme zammının  hangi borca bağlı olduğu bilgisi burada tutulmalı.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", debtAmount=" + debtAmount +
                ", remainingDebtAmount=" + remainingDebtAmount +
                ", dueDate=" + dueDate +
                ", userId=" + userId +
                '}';
    }


}
