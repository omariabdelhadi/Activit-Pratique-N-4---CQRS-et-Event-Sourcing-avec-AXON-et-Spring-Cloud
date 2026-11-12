package com.abdelhadielomari.cqrs_projet.query.handlers;

import com.abdelhadielomari.cqrs_projet.query.dtos.AccountStatementDTO;
import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import com.abdelhadielomari.cqrs_projet.query.entities.AccountOperation;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAccountSatementQuery;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAccountQuery;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAcountOperations;
import com.abdelhadielomari.cqrs_projet.query.queries.WatchEventQuery;
import com.abdelhadielomari.cqrs_projet.query.repository.AccountRepository;
import com.abdelhadielomari.cqrs_projet.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;

    @QueryHandler
    public List<Account> on(GetAllAccountQuery getAllAccountQuery){
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountSatementQuery getAccountSatementQuery){
        Account account=accountRepository.findById(getAccountSatementQuery.getId()).orElse(null);
        return account;
    }

    @QueryHandler
    public List<AccountOperation> on(GetAllAcountOperations getAllAcountOperations){
        return operationRepository.findByAccountId(getAllAcountOperations.getAccountId());
    }

    @QueryHandler
    public AccountOperation on(WatchEventQuery watchEventQuery){
        return AccountOperation.builder().build();
    }

}
