package com.abdelhadielomari.cqrs_projet.commands.controllers;

import com.abdelhadielomari.cqrs_projet.commands.commands.AddAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.CreditAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.DebitAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.UpdateAccountStatuCommand;
import com.abdelhadielomari.cqrs_projet.commands.dtos.AddNewAccountRequestDTO;
import com.abdelhadielomari.cqrs_projet.commands.dtos.CreditAccountRequestDTO;
import com.abdelhadielomari.cqrs_projet.commands.dtos.DebitAccountRequestDTO;
import com.abdelhadielomari.cqrs_projet.commands.dtos.UpdateAccountStatuRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> reponse = commandGateway.send(new AddAccountCommand(UUID.randomUUID().toString(),request.initialBalance(),request.currency()));
        return reponse;
    }

    @PutMapping("/debit/{id}")
    public void debutAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO, @PathVariable String id){
        commandGateway.send(new DebitAccountCommand(id,debitAccountRequestDTO.amount()));
    }
    @PutMapping("/credit/{id}")
    public void debutAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO, @PathVariable String id){
        commandGateway.send(new CreditAccountCommand(id,creditAccountRequestDTO.amount()));
    }
    @PutMapping("/UpdateStatu/{id}")
    public void UpdateStatu(@PathVariable String id, @RequestBody UpdateAccountStatuRequestDTO updateAccountStatuRequestDTO){
        commandGateway.send(new UpdateAccountStatuCommand(id,updateAccountStatuRequestDTO.statue()));
    }
}
