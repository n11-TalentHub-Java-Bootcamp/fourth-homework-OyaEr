package com.oyaerdayi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//Tahsilat entitysi.

@Entity
@Table(name ="COLLECTION")
public class Collection {


    @SequenceGenerator(name = "generator", sequenceName = "COLLECTION_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;


    //Tahsilat zamanı gelen gecikme zammı tutarı.
    @Column(name = "LATE_FEE", precision = 19,scale = 2)
    private BigDecimal lateFee;

    //Kayıt tarihi = Tahsilat yapılan tarih
    @Column(name = "RECORD_DATE")
    private Date recordDate;

    //Tahsilatın hangi borca yapılacağı bilgisi için.
    @Column(name = "DEBT_ID", nullable = false)
    private Long debtId;


    //Tahsilatın kim tarafından yapılacağı bilgisi için.
    @Column(name = "USER_ID", nullable = false)
    private Long userId;



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

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", lateFee=" + lateFee +
                ", recordDate=" + recordDate +
                ", debtId=" + debtId +
                ", userId=" + userId +
                '}';
    }


}
