package com.oyaerdayi.converter;

import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Collection;
import com.oyaerdayi.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionConverter {

    CollectionConverter INSTANCE= Mappers.getMapper(CollectionConverter.class);

    List<CollectionDto> convertAllCollectionListToCollectionDtoList (List<Collection> collectionList);

    Collection convertAllCollectionDtoListToCollectionList(CollectionDto collectionDto);
}
