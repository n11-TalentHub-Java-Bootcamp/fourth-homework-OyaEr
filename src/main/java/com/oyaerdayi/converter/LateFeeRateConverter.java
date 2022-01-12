package com.oyaerdayi.converter;

import com.oyaerdayi.dto.LateFeeRateDto;
import com.oyaerdayi.entity.LateFeeRate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LateFeeRateConverter {

    LateFeeRateConverter INSTANCE = Mappers.getMapper(LateFeeRateConverter.class);

    List<LateFeeRateDto> convertAllLateFeeRateListToLateFeeRateDtoList (List<LateFeeRate> rateList);

    LateFeeRate convertAllLateFeeRateDtoListToLateFeeRateList(LateFeeRateDto lateFeeRateDto);

//    @AfterMapping
//    default void setNulls(@MappingTarget LateFeeRate lateFeeRate, LateFeeRateDto lateFeeRateDto){
//        if (lateFeeRateDto.getId() == null){
//            lateFeeRate.setId(null);
//        }
//    }
}
