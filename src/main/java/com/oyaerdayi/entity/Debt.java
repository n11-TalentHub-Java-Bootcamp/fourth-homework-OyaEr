package com.oyaerdayi.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="DEBT")
public class Debt {

    @SequenceGenerator(name = "generator", sequenceName = "DEBT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    //ana borç tutarı, değiştirilemez olan!
    @Column(name = "DEBT", precision = 19, scale = 2)
    private BigDecimal debtAmount;

    //Tahsilat sonrası kalan borç !
    @Column(name = "REMAINING_DEBT", precision = 19, scale = 2)
    private BigDecimal remainingDebtAmount;

    //vade tarihi alanı
    @Column(name = "DUE_DATE", nullable = false)
    private String dueDate; //TODO: Date yapılabilir?

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", debtAmount=" + debtAmount +
                ", remainingDebtAmount=" + remainingDebtAmount +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
