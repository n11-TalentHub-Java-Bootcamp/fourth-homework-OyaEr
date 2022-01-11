package com.oyaerdayi.dao;

import com.oyaerdayi.entity.LateFeeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LateFeeRateDao extends JpaRepository <LateFeeRate,Long> {
}
