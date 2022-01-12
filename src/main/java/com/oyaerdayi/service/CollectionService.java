package com.oyaerdayi.service;

import com.oyaerdayi.dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CollectionService {

    @Autowired
    private CollectionDao collectionDao;
}
