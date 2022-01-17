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
            optional = false
    )
    @JoinColumn(name = "DEBT_ID", foreignKey = @ForeignKey(name = "FK_DEBT_COLLECTION_ID"))
    private Debt debt;

    public Collection(Long id, Date recordDate, Debt debt) {
        this.id = id;
        this.recordDate = recordDate;
        this.debt = debt;
    }

    public Collection(){

    }

    public Long getId() {
        return id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public Debt getDebt() {
        return debt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public void setDebt(Debt debt) {
        this.debt = debt;
    }


}

