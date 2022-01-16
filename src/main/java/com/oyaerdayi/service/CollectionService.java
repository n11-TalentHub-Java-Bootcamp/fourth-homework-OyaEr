package com.oyaerdayi.service;

import com.oyaerdayi.dao.CollectionDao;
import com.oyaerdayi.dao.DebtDao;
import com.oyaerdayi.entity.Collection;
import com.oyaerdayi.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private DebtDao debtDao;

    double rateBefore2018 = 1.5; //2018 öncesi için gecikme zammı oranı.
    double rateAfter2018 = 2.0; //2018 sonrası için gecikme zammı oranı.

    Debt debt;
    Collection collection;

    //Burada tahsilat yapıyoruz.
    public void saveCollection( Collection collection){

        try {

            collectionDao.save(collection);

            List<Debt> debtList = debtDao.findAllById(collection.getDebtId().getId());

            Debt debt  = debtList.get(0);

            //Varsa eğer gecikme_zammı kaydı atılmalı. O da ödeneceği için kayıt atılıp onun da remainingi 0'lanmalı.
            BigDecimal lateFee= calculateLateFee(debt.getId(),collection.getRecordDate());

            Debt debtLateFee= new Debt();
            debtLateFee.setDebtType("Late_Fee");
            debtLateFee.setPreDebtId(debt);
            debtLateFee.setRemainingDebtAmount(new BigDecimal(0));
            debtLateFee.setDebtAmount(lateFee);
            debtLateFee.setDueDate(null);
            debtLateFee.setUser(debt.getUser());

            debtDao.save(debtLateFee);


            //Tahsilat yapıldığı için remaininDebtAmount 0'landı.
            debt.setRemainingDebtAmount(new BigDecimal(0));

        }
        catch (Exception e){

            System.out.println(e.getMessage());
        }
    }

    public BigDecimal calculateLateFee(Long debtId, Date recordDate){

        long milis=0;
        BigDecimal lateFee= new BigDecimal(0);

//        Date currentDate = new Date();

        try {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdformat.parse("2018-01-01");

            List<Debt> debtList = debtDao.findAllById(debtId);

            Debt debt  = debtList.get(0);

                if (debt.getRemainingDebtAmount().compareTo(BigDecimal.ZERO) != 0) {

                    if (debt.getDueDate().before(date)) { //2018'ten öncekiler için gecikme zammı

                        milis = recordDate.getTime() - debt.getDueDate().getTime();

                        long days = TimeUnit.MILLISECONDS.toDays(milis);

                        lateFee = lateFee.add(((debt.getDebtAmount().multiply(new BigDecimal(rateBefore2018))).divide(new BigDecimal(100))).multiply(new BigDecimal(days)));

                    }

                    if (debt.getDueDate().after(date) && debt.getDueDate().before(recordDate)) { //2018'ten sonra ama currentDate'ten önce vade tarihi

                        milis = recordDate.getTime() - debt.getDueDate().getTime();

                        long days = TimeUnit.MILLISECONDS.toDays(milis);

                        lateFee = lateFee.add(((debt.getDebtAmount().multiply(new BigDecimal(rateAfter2018))).divide(new BigDecimal(100))).multiply(new BigDecimal(days)));


                    }
                }

                return lateFee;

        }
        catch (Exception e){

            e.getMessage();
            return new BigDecimal(111);
        }

    }
}
