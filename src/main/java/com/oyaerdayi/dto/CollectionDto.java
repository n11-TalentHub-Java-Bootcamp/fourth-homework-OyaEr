package com.oyaerdayi.dto;
import java.util.Date;

public class CollectionDto {


    private Date recordDate;
    private Long debtId;

    public CollectionDto(Date recordDate, Long debtId) {
        this.recordDate = recordDate;
        this.debtId = debtId;
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
}




