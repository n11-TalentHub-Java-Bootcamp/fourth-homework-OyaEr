package com.oyaerdayi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="DEBT")
public class Debt implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    //ana borç tutarı, değiştirilemez olan!
    @Column(name = "DEBT_AMOUNT", precision = 19, scale = 2)
    private BigDecimal debtAmount;

    //Tahsilat sonrası kalan borç !
    @Column(name = "REMAINING_DEBT", precision = 19, scale = 2)
    private BigDecimal remainingDebtAmount;

    //vade tarihi alanı
    @Column(name = "DUE_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    @Column (name="DEBT_TYPE ")
    private String debtType;

    @OneToOne(
            fetch = FetchType.LAZY
//            optional = false
    )
    @JoinColumn(name = "PRE_DEBT", foreignKey = @ForeignKey(name = "FK_PRE_DEBT_ID"))
    private Debt preDebtId;


    @ManyToOne(
//            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_DEPT_ID"))
    private User user;

    public Debt(Long id, BigDecimal debtAmount, BigDecimal remainingDebtAmount, Date dueDate, String debtType, Debt preDebtId, User user) {
        this.id = id;
        this.debtAmount = debtAmount;
        this.remainingDebtAmount = remainingDebtAmount;
        this.dueDate = dueDate;
        this.debtType = debtType;
        this.preDebtId = preDebtId;
        this.user = user;
    }

    public Debt() {

    }

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

    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }

    public Debt getPreDebtId() {
        return preDebtId;
    }

    public void setPreDebtId(Debt preDebtId) {
        this.preDebtId = preDebtId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", debtAmount=" + debtAmount +
                ", remainingDebtAmount=" + remainingDebtAmount +
                ", dueDate=" + dueDate +
                ", debtType='" + debtType + '\'' +
                ", preDebtId=" + preDebtId +
                ", user=" + user +
                '}';
    }
}
