package com.abdelhadielomari.cqrs_projet.query.controllers;


import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import com.abdelhadielomari.cqrs_projet.query.entities.AccountOperation;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAccountOperationsQuery;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAccountQuery;
import jdk.dynalink.Operation;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccount(){
        CompletableFuture<List<Account>> reponse=queryGateway.query(new GetAllAccountQuery(), ResponseTypes.multipleInstancesOf(Account.class));
        return reponse;
    }
    @GetMapping("/allOperations/{id}")
    public CompletableFuture<List<AccountOperation>> getAllAccountOperations(@PathVariable String id){
        CompletableFuture<List<AccountOperation>> reponse=queryGateway.query(new GetAllAccountOperationsQuery(id),ResponseTypes.multipleInstancesOf(AccountOperation.class));
        return reponse;
    }
}
