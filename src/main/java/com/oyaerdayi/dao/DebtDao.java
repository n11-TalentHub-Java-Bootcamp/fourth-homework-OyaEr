package com.oyaerdayi.dao;
import com.oyaerdayi.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtDao extends JpaRepository<Debt,Long> {


}
