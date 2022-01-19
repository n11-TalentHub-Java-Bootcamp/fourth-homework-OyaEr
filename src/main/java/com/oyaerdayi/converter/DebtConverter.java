package com.oyaerdayi.converter;
import com.oyaerdayi.dto.DebtDto;
import com.oyaerdayi.entity.Debt;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtConverter {

    DebtConverter INSTANCE = Mappers.getMapper(DebtConverter.class);

    @Mapping(target = "userId", source = "user.id")
    List<DebtDto> convertAllDebtListToDebtDtoList (List<Debt> debtList);

    @Mapping(source = "userId", target = "user.id")
    Debt convertAllDebtDtoListToDebtList(DebtDto debtDto);

    @Mapping(target = "userId", source = "user.id")
    DebtDto convertAllDebtListToDebtDtoList (Debt debtList);

}