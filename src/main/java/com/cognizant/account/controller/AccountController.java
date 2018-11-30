package com.cognizant.account.controller;

import com.cognizant.account.domain.Account;
import com.cognizant.account.domain.Prize;
import com.cognizant.account.domain.Ticket;
import com.cognizant.account.service.IAccountService;
import com.cognizant.account.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("${url.account}")
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPrizeService prizeService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${url.ticket}")
    private String ticketBaseURL;
    @Value("${url.prize}")
    private String prizeBaseURL;
    @Value("${url.ticket.stub}")
    private String ticketStubURL;
    @Value("${url.prize.stub}")
    private String prizeStubURL;

    @PostMapping()
    public Account createAccount(@RequestBody Account account) {
        final Ticket ticket = restTemplate.getForObject(ticketBaseURL + ticketStubURL, Ticket.class);
        account.setAccountNumber(ticket.getTicketNumber());
        return accountService.createAccount(account);
    }

    @GetMapping("${url.account.id.redeem}")
    public String checkPrize(@PathVariable String accountNumber) {
        final Account account = accountService.retrieveAccount(accountNumber);
        final Prize prize = restTemplate.getForObject(prizeBaseURL + prizeStubURL + accountNumber, Prize.class);

        account.setPrize(prize);
        prize.setAccount(account);

        jmsTemplate.convertAndSend("AccountQueue", account);

        prizeService.createPrize(prize);
        accountService.updateAccount(account.getAccountNumber(), account);
        return prize.getValue().toString();
    }

    @GetMapping()
    public List<Account> list() {
        return accountService.getAllAccounts();
    }

    @GetMapping("${url.account.id}")
    public Account get(@PathVariable String accountNumber) {
        return accountService.retrieveAccount(accountNumber);
    }

    @PutMapping("${url.account.id}")
    public String update(@PathVariable String accountNumber, @RequestBody Account account) {
        return accountService.updateAccount(accountNumber, account);
    }

    @DeleteMapping("${url.account.id}")
    public String delete(@PathVariable String accountNumber) {
        return accountService.deleteAccount(accountNumber);
    }

}
