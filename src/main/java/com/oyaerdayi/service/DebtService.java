package com.oyaerdayi.service;

import com.oyaerdayi.dao.DebtDao;
import com.oyaerdayi.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DebtService {

    @Autowired
    private DebtDao debtDao;

    public void saveDebt(Debt debt){
        debtDao.save(debt);
    }

//    if (date1.compareTo(date2) > 0) {
//        System.out.println("Date1 is after Date2");
//    } else if (date1.compareTo(date2) < 0) {
//        System.out.println("Date1 is before Date2");
//    } else {
//        System.out.println("Date1 is equal to Date2");
//    }

    public List<Debt> getDebtByDateRange(Date date1, Date date2){

        List<Debt> debtList = debtDao.findAll();

        List <Debt> debtList2 = new ArrayList<>();

        for (Debt debt : debtList) {

            if(date1.compareTo(debt.getDueDate()) < 0 && date2.compareTo(debt.getDueDate()) > 0){

                debtList2.add(debt);
            }
        }
        return debtList2;
    }
}
