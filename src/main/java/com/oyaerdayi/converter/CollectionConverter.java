package com.oyaerdayi.converter;
import com.oyaerdayi.dto.CollectionDto;
import com.oyaerdayi.entity.Collection;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionConverter {

    CollectionConverter INSTANCE = Mappers.getMapper(CollectionConverter.class);

    @Mapping(target = "debtId", source = "debtId.id")
    List<CollectionDto> convertAllCollectionListToCollectionDtoList (List<Collection> collectionList);

    @Mapping(source = "debtId", target = "debtId.id")
    Collection convertCollectionDtoToCollection(CollectionDto collectionDto);


    @AfterMapping
    default void setNulls(@MappingTarget final Collection collection, CollectionDto collectionDto) {
        if (collectionDto.getDebtId() == null) {
            collection.setId(null);
        }
    }

}