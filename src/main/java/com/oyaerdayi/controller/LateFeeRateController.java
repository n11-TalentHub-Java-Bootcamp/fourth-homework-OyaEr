package com.oyaerdayi.controller;

import com.oyaerdayi.converter.LateFeeRateConverter;
import com.oyaerdayi.converter.UserConverter;
import com.oyaerdayi.dto.LateFeeRateDto;
import com.oyaerdayi.dto.UserDto;
import com.oyaerdayi.entity.LateFeeRate;
import com.oyaerdayi.entity.User;
import com.oyaerdayi.service.LateFeeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/rates")
public class LateFeeRateController {

    @Autowired
    LateFeeRateService lateFeeRateService;

    @PostMapping("")
    public void saveRate(@RequestBody LateFeeRateDto rateDto){

        try {

            LateFeeRate lateFeeRate = LateFeeRateConverter.INSTANCE.convertAllLateFeeRateDtoListToLateFeeRateList(rateDto);

             lateFeeRateService.save(lateFeeRate);

        }
        catch(Exception e){

            System.out.println(e.getMessage());
        }

    }

    @GetMapping("")
    public List <LateFeeRateDto> findAll(){

        List<LateFeeRate> rateList = lateFeeRateService.findAll();

        List<LateFeeRateDto> rateDtoList = LateFeeRateConverter.INSTANCE.convertAllLateFeeRateListToLateFeeRateDtoList(rateList);

        return rateDtoList;

    }

    @DeleteMapping("/{id}")
    public String deleteRate(@PathVariable Long id){

        try{

            lateFeeRateService.deleteById(id);
            return"Late Fee Rate has been deleted.";

        }
        catch(Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("")
    public List<LateFeeRateDto> update(@RequestBody LateFeeRateDto rateDto){

        LateFeeRate lateFeeRate = LateFeeRateConverter.INSTANCE.convertAllLateFeeRateDtoListToLateFeeRateList(rateDto);

        lateFeeRateService.save(lateFeeRate);

        List<LateFeeRate> rateList=new ArrayList<LateFeeRate>();

        rateList.add(lateFeeRate);

        List<LateFeeRateDto> rateDtoList = LateFeeRateConverter.INSTANCE.convertAllLateFeeRateListToLateFeeRateDtoList(rateList);

        return rateDtoList;
    }
}
