package com.abdelhadielomari.cqrs_projet.query.controllers;


import com.abdelhadielomari.cqrs_projet.query.dtos.AccountStatementDTO;
import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import com.abdelhadielomari.cqrs_projet.query.entities.AccountOperation;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAccountSatementQuery;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAccountQuery;
import com.abdelhadielomari.cqrs_projet.query.queries.GetAllAcountOperations;
import com.abdelhadielomari.cqrs_projet.query.queries.WatchEventQuery;
import jdk.dynalink.Operation;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @GetMapping("/accountOperations/{id}")
    public CompletableFuture<List<AccountOperation>> getAllAcountOperations(@PathVariable String id){
        CompletableFuture<List<AccountOperation>> reponse=queryGateway.query(new GetAllAcountOperations(id),ResponseTypes.multipleInstancesOf(AccountOperation.class));
        return reponse;
    }

    @GetMapping("/accountStatement/{id}")
    public CompletableFuture<Account> getAccountStatement(@PathVariable String id){
        CompletableFuture<Account> reponse=queryGateway.query(new GetAccountSatementQuery(id),ResponseTypes.instanceOf(Account.class));
        return reponse;
    }



    @GetMapping(value = "/watch/{accountId}", produces = MediaType. TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountOperation> watch(@PathVariable String accountId){

        SubscriptionQueryResult<AccountOperation,AccountOperation> result=queryGateway.subscriptionQuery(new WatchEventQuery(accountId),ResponseTypes.instanceOf(AccountOperation.class),ResponseTypes.instanceOf(AccountOperation.class));
        return result.initialResult().concatWith(result.updates());
    }



}
