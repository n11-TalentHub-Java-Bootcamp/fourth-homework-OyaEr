package com.oyaerdayi.controller;

import com.oyaerdayi.converter.DebtConverter;
import com.oyaerdayi.converter.UserConverter;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Debt;
import com.oyaerdayi.entity.User;
import com.oyaerdayi.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    public List<Debt> getDebtByDateRange(@PathVariable ("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date1, @PathVariable ("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2 ){

        return debtService.getDebtByDateRange(date1,date2);
    }


}
