package com.westernacher.app.service.mapper;

import com.westernacher.app.entity.Account;
import com.westernacher.app.web.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

    default Page<AccountDto> toDtoPage(Page<Account> entityPage) {
        List<AccountDto> dtos = entityPage.getContent().stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalPages());
    }
}
