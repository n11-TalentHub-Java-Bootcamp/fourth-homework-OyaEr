package com.oyaerdayi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//Tahsilat entitysi.

@Entity
@Table(name ="COLLECTION")
public class Collection {


    @SequenceGenerator(name = "generator", sequenceName = "USER_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    //Tahsilat zamanı gelen gecikme zammı tutarı.
    @Column(name = "LATE_FEE", nullable = false, precision = 19,scale = 2)
    private BigDecimal lateFee;

    //Kayıt tarihi = Tahsilat yapılan tarih
    @Column(name = "RECORD_DATE", nullable = false, precision = 19,scale = 2)
    private Date recordDate;

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
}
