package com.oyaerdayi.service;

import com.oyaerdayi.converter.DebtConverter;
import com.oyaerdayi.dao.DebtDao;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DebtService {

    @Autowired
    private DebtDao debtDao;

    double rateBefore2018 = 1.5; //2018 öncesi için gecikme zammı oranı.
    double rateAfter2018 = 2.0; //2018 sonrası için gecikme zammı oranı.



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

    public List<DebtDto> getDebtByDateRange(Date date1, Date date2){

        List<Debt> debtList = debtDao.findAll();

        List <Debt> debtList2 = new ArrayList<>();

        for (Debt debt : debtList) {

            if(date1.compareTo(debt.getDueDate()) < 0 && date2.compareTo(debt.getDueDate()) > 0){

                debtList2.add(debt);
            }
        }

        List<DebtDto> debtDtoList = DebtConverter.INSTANCE.convertAllDebtListToDebtDtoList(debtList2);

        return debtDtoList;
    }

    public List<DebtDto> getDebtByUserId(Long userId){

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        List <Debt> debtList2 = new ArrayList<>();

        BigDecimal zero = new BigDecimal("0");

        for (Debt debt : debtList) {

            if(debt.getRemainingDebtAmount().compareTo(zero) == 1 ){

                debtList2.add(debt);
            }
        }

        List<DebtDto> debtDtoList = DebtConverter.INSTANCE.convertAllDebtListToDebtDtoList(debtList2);

        return debtDtoList;

    }

    public List<DebtDto> getDebtByDueDate(Long userId){

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        List <Debt> debtList2 = new ArrayList<>();

        for (Debt debt : debtList) {

            if(debt.getDueDate().before(new Date()) == true ){

                debtList2.add(debt);
            }
        }

        List<DebtDto> debtDtoList = DebtConverter.INSTANCE.convertAllDebtListToDebtDtoList(debtList2);

        return debtDtoList;

    }

    public String getTotalDebtByUserId(Long userId){

        BigDecimal totalDebt = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        for (Debt debt : debtList) {

            if (debt.getRemainingDebtAmount().compareTo(zero) == 1){

                totalDebt = totalDebt.add(debt.getRemainingDebtAmount());
            }
        }

        return "Remaining Total Debt: " + totalDebt;

    }

    public String getTotalDebtOutOfDueDateByUserId(Long userId){

        BigDecimal totalDebt = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        for (Debt debt : debtList) {
            if (debt.getDueDate().before(new Date())) {
                if (debt.getRemainingDebtAmount().compareTo(zero) == 1) {

                    totalDebt = totalDebt.add(debt.getRemainingDebtAmount());
                }

            }
        }
        return "Remaining Total Debt Out Of Due Date: " + totalDebt;

    }

    private String getTotalLateFeeByUserId(Long userId){

        double lateFee=0; //gecikme zammı
        long day=0;
        BigDecimal totalDebt;
        Date currentDate = new Date();

        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdformat.parse("2018-01-01");

            List<Debt> debtList = debtDao.findAllByUserId(userId);

            for (Debt debt : debtList) {
                if (debt.getDueDate().before(date)) {

                    day= currentDate.getTime() - debt.getDueDate().getTime();

                    lateFee+ =((debt.getDebtAmount()*rateBefore2018)/100) * day;






                }





            }
            return "Remaining Total Debt Out Of Due Date: " + totalDebt;

        }
        catch (Exception e){

            return e.getMessage();
        }

    }
}
