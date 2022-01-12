package com.oyaerdayi.converter;


import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtConverter {

    DebtConverter INSTANCE= Mappers.getMapper(DebtConverter.class);

    List<DebtDto> convertAllDebtListToDebtDtoList (List<Debt> debtList);

    Debt convertAllDebtDtoListToDebtList(DebtDto debtDto);

//    @AfterMapping
//    default void setNulls(@MappingTarget Debt debt, DebtDto deptDto){
//        if (deptDto.getId() == null){
//            debt.setId(null);
//        }
//    }


}
