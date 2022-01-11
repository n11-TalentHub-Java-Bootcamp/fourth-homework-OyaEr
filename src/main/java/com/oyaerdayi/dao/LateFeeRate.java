package com.oyaerdayi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LateFeeRate extends JpaRepository <LateFeeRate,Long> {
}
