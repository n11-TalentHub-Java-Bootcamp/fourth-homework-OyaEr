package com.oyaerdayi.service;

import com.oyaerdayi.converter.CollectionConverter;
import com.oyaerdayi.converter.DebtConverter;
import com.oyaerdayi.dao.CollectionDao;
import com.oyaerdayi.dao.DebtDao;
import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Collection;
import com.oyaerdayi.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    double rateBefore2018 = 1.5;
    double rateAfter2018 = 2.0;


    //Burada tahsilat yapıyoruz.
    public void saveCollection( Collection collection){

        try {

            collectionDao.save(collection);

            List<Debt> debtList = debtDao.findAllById(collection.getDebt().getId());

            Debt debt  = debtList.get(0);

            //Varsa eğer gecikme_zammı kaydı atılmalı. O da ödeneceği için kayıt atılıp onun da remainingi 0'lanmalı.
            BigDecimal lateFee= calculateLateFee(debt.getId(),collection.getRecordDate());

            Debt debtLateFee= new Debt();
            debtLateFee.setDebtType("LATE_FEE");
            debtLateFee.setPreDebtId(debt);
            debtLateFee.setRemainingDebtAmount(new BigDecimal(0));
            debtLateFee.setDebtAmount(lateFee);
            debtLateFee.setDueDate(debt.getDueDate());
            debtLateFee.setUser(debt.getUser());

            debtDao.save(debtLateFee);


            //Tahsilat yapıldığı için remaininDebtAmount 0'landı.
            debt.setRemainingDebtAmount(new BigDecimal(0));

        }
        catch (Exception e){

            System.out.println(e.getMessage());
        }
    }

    //Borç id'sine göre kullanıcının gecikme zammının hesaplanması.
    public BigDecimal calculateLateFee(Long debtId, Date recordDate){

        long milis=0;


        try {
            BigDecimal lateFee= new BigDecimal(0);
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdformat.parse("2018-01-01");

            List<Debt> debtList = debtDao.findAllById(debtId);

            Debt debt  = debtList.get(0);

                if (debt.getRemainingDebtAmount().compareTo(BigDecimal.ZERO) != 0) {

                    if (debt.getDueDate().before(date) && debt.getDueDate().before(recordDate)) { //2018'ten öncekiler için gecikme zammı

                        milis = recordDate.getTime() - debt.getDueDate().getTime();

                        long days = TimeUnit.MILLISECONDS.toDays(milis);

                        lateFee = lateFee.add(((debt.getDebtAmount().multiply(new BigDecimal(rateBefore2018))).divide(new BigDecimal(100))).multiply(new BigDecimal(days)));

                    }

                    if (debt.getDueDate().after(date) && debt.getDueDate().before(recordDate)) { //2018'ten sonra ama recordDate'ten önce vade tarihi

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

    public List<CollectionDto> getCollectionByDateRange(Date date1, Date date2){

        List<Collection> collectionList = collectionDao.findAll();

        List <Collection> collectionList2 = new ArrayList<>();

        for (Collection collection : collectionList) {

            if(date1.compareTo(collection.getRecordDate()) < 0 && date2.compareTo(collection.getRecordDate()) > 0){
                System.out.println(collection);
                collectionList2.add(collection);
            }
        }

        List<CollectionDto> collectionDtoList = CollectionConverter.INSTANCE.convertAllCollectionListToCollectionDtoList(collectionList2);

        return collectionDtoList;
    }

    //UserId'ye göre tahsilatı yapılan borçlar listelendi.
    public List<DebtDto> getCollectionByUserId(Long userId) {

        List<Debt> debtList = debtDao.findAllByUserId(userId);
        List<Debt> debtList2 = new ArrayList<>();


        for (Debt debt : debtList) {

            try{
                if(debt.getDebtType().equals("LATE_FEE")){
                    debtList2.add(debt);
                }
            }
            catch(Exception e){
                e.getMessage();
            }
        }

        List<DebtDto> debtDtoList = DebtConverter.INSTANCE.convertAllDebtListToDebtDtoList(debtList2);

        return debtDtoList;
    }

    //Kullanıcının ödediği toplam gecikme zammı.
    public BigDecimal getTotalLateFeeByUserId(Long userId) {

        List<Debt> debtList = debtDao.findAllByUserId(userId);
        List<Debt> debtList2 = new ArrayList<>();

                BigDecimal totalLateFee = new BigDecimal(0);
                for (Debt debt : debtList) {
                    try{
                        if (debt.getDebtType().equals("LATE_FEE")) {
                            debtList2.add(debt);
                        }

                    }
                    catch(Exception e){
                        e.getMessage();
                    }

                }
                for (Debt debt2 : debtList2) {

                    totalLateFee = totalLateFee.add(debt2.getDebtAmount());

        }
                return totalLateFee;
    }
}
