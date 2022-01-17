package com.oyaerdayi.controller;

import com.oyaerdayi.converter.CollectionConverter;
import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Collection;
import com.oyaerdayi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    //Belirtilen tarihler arasında bir kullanıcının yaptığı tahsilatlar.
    @GetMapping("/{date1}/{date2}")
    public List<CollectionDto> getCollectionByDateRange(@PathVariable ("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1, @PathVariable ("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2 ){

        return collectionService.getCollectionByDateRange(date1,date2);
    }

}
