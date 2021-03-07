package com.westernacher.app.web.controller;

import com.westernacher.app.service.AccountService;
import com.westernacher.app.web.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid AccountDto account) {
        log.info("REST request for creating account");
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
        log.info("REST request to get account by id");
        return new ResponseEntity<>(accountService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AccountDto>> getAllSortedBy(@RequestParam String criteria, @RequestParam String direction) {
        log.info("REST request to get all accounts sorted by {}, with direction {}", criteria, direction);
        return new ResponseEntity<>(accountService.getAllAndSortByCriteriaAndDirection(criteria, direction), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        log.info("REST request to update account with id");
        return new ResponseEntity<>(accountService.updateAccount(id, accountDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request for deleting account");
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
