package com.oyaerdayi.dao;

import com.oyaerdayi.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDao extends JpaRepository<Collection,Long> {
}
