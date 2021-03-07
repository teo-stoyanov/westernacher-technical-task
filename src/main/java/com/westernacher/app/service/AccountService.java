package com.westernacher.app.service;

import com.westernacher.app.entity.Account;
import com.westernacher.app.repository.AccountRepository;
import com.westernacher.app.service.mapper.AccountMapper;
import com.westernacher.app.web.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Long createAccount(AccountDto accountDto) {
        return accountRepository.save(accountMapper.toEntity(accountDto)).getId();
    }

    public AccountDto getById(Long id) {
        if (!accountRepository.existsById(id))
            throw new EntityNotFoundException("There is no account with id " + id);

        return accountMapper.toDto(accountRepository.getOne(id));
    }

    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id))
            throw new EntityNotFoundException("There is no account with id " + id);

        accountRepository.deleteById(id);
    }

    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        if (!accountRepository.existsById(id))
            throw new EntityNotFoundException("There is no account with id " + id);

        Account account = accountMapper.toEntity(accountDto);
        account.setId(id);
        return accountMapper.toDto(accountRepository.save(account));
    }

    public Page<AccountDto> getAllAndSortByCriteriaAndDirection(String criteria, String direction) {
        Pageable pageable;
        if (direction.equalsIgnoreCase("ASC"))
            pageable = PageRequest.of(0, 5, Sort.by(criteria).ascending());
        else
            pageable = PageRequest.of(0, 5, Sort.by(criteria).descending());

        return accountMapper.toDtoPage(accountRepository.findAll(pageable));
    }
}
