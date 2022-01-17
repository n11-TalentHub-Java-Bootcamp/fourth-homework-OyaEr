package com.oyaerdayi.converter;

import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.entity.Collection;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;


    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface CollectionConverter {

        com.oyaerdayi.converter.CollectionConverter INSTANCE = Mappers.getMapper(com.oyaerdayi.converter.CollectionConverter.class);

        @Mapping(target = "debtId", source = "debt.id")
        List<CollectionDto> convertAllCollectionListToCollectionDtoList (List<Collection> collectionList);

        @Mapping(source = "debtId", target = "debt.id")
        Collection convertCollectionDtoToCollection(CollectionDto collectionDto);

        @Mapping(target = "debtId", source = "debt.id")
        CollectionDto convertAllCollectionListToCollectionDtoList (Collection collectionList);

//        @Mapping(source = "debtId", target = "debt.id")
//        Collection convertCollectionDtoToCollection(CollectionDto collectionDto);


        @AfterMapping
        default void setNulls(@MappingTarget final Collection collection, CollectionDto collectionDto) {
            if (collectionDto.getDebtId() == null) {
                collection.setId(null);
            }
        }

    }

