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
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DebtService {

    @Autowired
    private DebtDao debtDao;


    double rateBefore2018 = 1.5; //2018 öncesi için gecikme zammı oranı.
    double rateAfter2018 = 2.0; //2018 sonrası için gecikme zammı oranı.


    public void saveDebt(Debt debt){

        debt.setDebtType("NORMAL");
        debtDao.save(debt);
    }


    public List<DebtDto> getDebtByCreationDateRange(Date date1, Date date2){

        List<Debt> debtList = debtDao.findAll();

        List <Debt> debtList2 = new ArrayList<>();


        for (Debt debt : debtList) {

            if(debt.getCreationDate()!=null){
                if(date1.compareTo(debt.getCreationDate()) < 0 && date2.compareTo(debt.getCreationDate()) > 0){
                    System.out.println(debt);
                    debtList2.add(debt);
                }
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

    //Bir kullanıcının toplam borç tutarı. Gecikme zammı dahildir.
    public String getTotalDebtByUserId(Long userId){

        BigDecimal totalDebt = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal lateFee = new BigDecimal(0);

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        for (Debt debt : debtList) {

            if (debt.getRemainingDebtAmount().compareTo(zero) == 1){

                totalDebt = totalDebt.add(debt.getRemainingDebtAmount());
            }
        }

        lateFee= getTotalLateFeeByUserId(userId);
        totalDebt = totalDebt.add(lateFee);

        return "Remaining Total Debt with Late Fee: " + totalDebt;

    }

    public String getTotalDebtOutOfDueDateByUserId(Long userId){

        BigDecimal totalDebt = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal lateFee = new BigDecimal(0);

        List<Debt> debtList = debtDao.findAllByUserId(userId);

        for (Debt debt : debtList) {
            if (debt.getDueDate().before(new Date())) {
                if (debt.getRemainingDebtAmount().compareTo(zero) == 1) {

                    totalDebt = totalDebt.add(debt.getRemainingDebtAmount());
                }

            }
        }

        lateFee= getTotalLateFeeByUserId(userId);
        totalDebt = totalDebt.add(lateFee);
        return "Remaining Total Debt Out Of Due Date (with Late Fee): " + totalDebt;

    }

    // Burası hesaplanırken vade tarihi 2018'den önce olanlar için 1.5 oran verilip hangi tarihte ödediyse o güne kadar
    // hep 1.5 oran ile çarpılmıştır. 2018den sonrakiler içinse 2.0 oran verilip ödediği tarihe kadar 2.0 ile çarpılmıştır.
    public BigDecimal getTotalLateFeeByUserId(Long userId){

        long milis=0;
        BigDecimal lateFee= new BigDecimal(0);
        Date currentDate = new Date();


        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdformat.parse("2018-01-01");

            List<Debt> debtList = debtDao.findAllByUserId(userId);


            for (Debt debt : debtList) {

                if(debt.getRemainingDebtAmount().compareTo(BigDecimal.ZERO) != 0){

                    if (debt.getDueDate().before(date)) { //2018'ten öncekiler için gecikme zammı

                        milis = currentDate.getTime() - debt.getDueDate().getTime();

                        long days = TimeUnit.MILLISECONDS.toDays(milis);

                        lateFee = lateFee.add((((debt.getDebtAmount().multiply(new BigDecimal(rateBefore2018))).divide(new BigDecimal(100))).multiply(new BigDecimal(days))));

                    }

                    if (debt.getDueDate().after(date) && debt.getDueDate().before(currentDate)){ //2018'ten sonra ama currentDate'ten önce vade tarihi

                        milis = currentDate.getTime() - debt.getDueDate().getTime();

                        long days = TimeUnit.MILLISECONDS.toDays(milis);

                        lateFee = lateFee.add((((debt.getDebtAmount().multiply(new BigDecimal(rateAfter2018))).divide(new BigDecimal(100))).multiply(new BigDecimal(days))));

                    }
                }

            }

            return  lateFee;

        }
        catch (Exception e){

             e.getMessage();
             return null;
        }

    }
}
