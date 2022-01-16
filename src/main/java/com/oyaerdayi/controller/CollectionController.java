package com.oyaerdayi.controller;

import com.oyaerdayi.converter.CollectionConverter;
import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.entity.Collection;
import com.oyaerdayi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/collections")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("")
    public String saveCollection(@RequestBody CollectionDto collectionDto){

        try{
            Collection collection = CollectionConverter.INSTANCE.convertCollectionDtoToCollection(collectionDto);

            collectionService.saveCollection(collection);

            return "Collection was successfully saved.";

        }
        catch (Exception e){

            return e.getMessage();
        }
    }
}
