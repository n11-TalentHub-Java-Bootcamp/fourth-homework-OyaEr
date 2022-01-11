package com.oyaerdayi.service;

import com.oyaerdayi.dao.LateFeeRateDao;
import com.oyaerdayi.entity.LateFeeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LateFeeRateService {

    @Autowired
    LateFeeRateDao lateFeeRateDao;

    public List<LateFeeRate> findAll(){
       return lateFeeRateDao.findAll();
    }

    public void deleteById(Long id){
        lateFeeRateDao.deleteById(id);
    }

    public void save(LateFeeRate lateFeeRate){
        lateFeeRateDao.save(lateFeeRate);
    }




}
