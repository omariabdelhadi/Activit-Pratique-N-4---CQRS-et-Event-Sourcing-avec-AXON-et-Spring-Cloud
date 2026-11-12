package com.abdelhadielomari.cqrs_projet.query.handlers;

import com.abdelhadielomari.cqrs_projet.enums.OperationType;
import com.abdelhadielomari.cqrs_projet.events.*;
import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import com.abdelhadielomari.cqrs_projet.query.entities.AccountOperation;
import com.abdelhadielomari.cqrs_projet.query.repository.AccountRepository;
import com.abdelhadielomari.cqrs_projet.query.repository.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent, EventMessage eventMessage){
        Account account=Account.builder()
                .id(accountCreatedEvent.getAccountId())
                .balance(accountCreatedEvent.getBalance())
                .currency(accountCreatedEvent.getCurrency())
                .status(accountCreatedEvent.getStatus())
                .createdAt(eventMessage.getTimestamp())
                .build();
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent){
        Account account=accountRepository.findById(accountActivatedEvent.getAccountId()).orElse(null);
        account.setStatus(accountActivatedEvent.getStatus());
        accountRepository.save(account);

    }

    @EventHandler
    public void on(AccountUpdatStatueEvent accountUpdatStatueEvent){
        Account account=accountRepository.findById(accountUpdatStatueEvent.getAccountId()).orElse(null);
        account.setStatus(accountUpdatStatueEvent.getAccountStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitEvent accountDebitEvent,EventMessage eventMessage){
        Account account=accountRepository.findById(accountDebitEvent.getAccountId()).orElse(null);
        AccountOperation accountOperation=AccountOperation.builder()
                .type(OperationType.DEBIT)
                .amount(accountDebitEvent.getAmount())
                .date(eventMessage.getTimestamp())
                .account(account)
                .build();
        operationRepository.save(accountOperation);
        account.setBalance(account.getBalance()-accountDebitEvent.getAmount());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountCreditEvent accountCreditEvent, EventMessage eventMessage){
        Account account=accountRepository.findById(accountCreditEvent.getAccountId()).orElse(null);
        AccountOperation accountOperation=AccountOperation.builder()
                .type(OperationType.CREDIT)
                .amount(accountCreditEvent.getAmount())
                .date(eventMessage.getTimestamp())
                .account(account)
                .build();
        operationRepository.save(accountOperation);
        account.setBalance(account.getBalance()+accountCreditEvent.getAmount());
        accountRepository.save(account);
    }
}
