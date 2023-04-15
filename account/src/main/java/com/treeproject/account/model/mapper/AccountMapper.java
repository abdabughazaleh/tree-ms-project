package com.treeproject.account.model.mapper;

import com.treeproject.account.model.dto.AccountDto;
import com.treeproject.account.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  /*  @Mapping(source = "ID", target = "accountId")
    @Mapping(source = "account_type", target = "accountType")
    @Mapping(source = "account_number", target = "accountNumber")*/
    AccountDto toDto(Account entity);

    /*@Mapping(source = "accountId", target = "ID")
    @Mapping(source = "accountType", target = "account_type")
    @Mapping(source = "accountNumber", target = "account_number")*/
    Account toEntity(AccountDto dto);

    List<Account> toEntities(List<AccountDto> dtos);

    List<AccountDto> toDtos(List<Account> entities);

    Account mapToUpdate(AccountDto dto, @MappingTarget Account account);
}
