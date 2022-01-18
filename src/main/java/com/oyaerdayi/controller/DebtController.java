package com.oyaerdayi.controller;

import com.oyaerdayi.converter.DebtConverter;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Debt;
import com.oyaerdayi.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/debts")
public class DebtController {

    @Autowired
    private DebtService debtService;


    @PostMapping("")
    public String saveDebt(@RequestBody DebtDto debtDto){ //TODO: userId ve id kontrolü yapılması lazım.

        try{
            Debt debt = DebtConverter.INSTANCE.convertAllDebtDtoListToDebtList(debtDto);

            debtService.saveDebt(debt);

            return "Debt was successfully saved.";

        }
        catch (Exception e){

            return e.getMessage();
        }
    }

    //Belirtilen tarihler arası borçlar listelenebilmelidir.
    @GetMapping("dateRange/{date1}/{date2}")
    public List<DebtDto> getDebtByDateRange(@PathVariable ("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date1, @PathVariable ("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2 ){

        return debtService.getDebtByDateRange(date1,date2);
    }

    //Bir kullanıcının tüm borçlarını listeleye metot(borç tutarı sıfırdan büyük olanlar,yani tahsilatı yapılmamış)
    @GetMapping("userId/{userId}")
    public List<DebtDto> getDebtByUserId(@PathVariable Long userId ){

        return debtService.getDebtByUserId(userId);
    }

    //Bir kullanıcının vadesi geçmiş borçlarını listeleyen servis
    @GetMapping("dueDate/{userId}")
    public List<DebtDto> getDebtByDueDate(@PathVariable Long userId ){

        return debtService.getDebtByDueDate(userId);
    }

    //Bir kullanıcının toplam güncel  borçlarını listeleyen servis
    @GetMapping("{userId}")
    public String getTotalDebtByUserId(@PathVariable Long userId ){

        return debtService.getTotalDebtByUserId(userId);
    }

    //Bir kullanıcının vadesi geçmiş toplam borç tutarını dönen servis
    @GetMapping("dueDate/totalDebt/{userId}")
    public String getTotalDebtOutOfDueDateByUserId(@PathVariable Long userId ){

        return debtService.getTotalDebtOutOfDueDateByUserId(userId);
    }

    @GetMapping("lateFee/{userId}")
    public BigDecimal getTotalLateFeeByUserId(@PathVariable Long userId ){

        return debtService.getTotalLateFeeByUserId(userId);
    }




}
