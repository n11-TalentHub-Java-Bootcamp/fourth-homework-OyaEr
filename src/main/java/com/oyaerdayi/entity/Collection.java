package com.oyaerdayi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//Tahsilat entitysi.

@Entity
@Table(name ="COLLECTION")
public class Collection implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;


    //Kayıt tarihi = Tahsilat yapılan tarih
    @Column(name = "RECORD_DATE")
    private Date recordDate;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "DEBT_ID", foreignKey = @ForeignKey(name = "FK_DEBT_COLLECTION_ID"))
    private Debt debtId;

    public Collection(Long id, Date recordDate, Debt debtId) {
        this.id = id;
        this.recordDate = recordDate;
        this.debtId = debtId;
    }

    public Collection(){

    }

    public Long getId() {
        return id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public Debt getDebtId() {
        return debtId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public void setDebtId(Debt debtId) {
        this.debtId = debtId;
    }


}

