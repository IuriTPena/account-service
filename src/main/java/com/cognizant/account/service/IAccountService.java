package com.cognizant.account.service;

import com.cognizant.account.domain.Account;

import java.util.List;

public interface IAccountService {
    Account createAccount(Account account);

    Account retrieveAccount(String accountId);

    String updateAccount(String accountId, Account account);

    String deleteAccount(String accountId);

    List<Account> getAllAccounts();
}
