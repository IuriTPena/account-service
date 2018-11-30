package com.cognizant.account.service;

import com.cognizant.account.domain.Account;
import com.cognizant.account.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public Account createAccount(final Account account) {
        accountRepository.save(account);

        return account;
    }

    public Account retrieveAccount(final String accountId) {
        Optional<Account> accountDB = accountRepository.findById(accountId);
        return accountDB.get();
    }

    public String updateAccount(final String accountId, final Account account) {
        Optional<Account> accountDB = accountRepository.findById(accountId);
        String res;

        if (!accountDB.isPresent())
            res = "Account does not exists";
        else {
            account.setAccountNumber(accountId);
            accountRepository.save(account);
            res = "Account successfully updated";
        }

        return res;
    }

    public String deleteAccount(final String accountId) {
        accountRepository.deleteById(accountId);
        return "Account successfully deleted";
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
