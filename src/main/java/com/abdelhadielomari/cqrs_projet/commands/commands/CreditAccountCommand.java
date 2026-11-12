package com.abdelhadielomari.cqrs_projet.commands.commands;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
@Getter
public class CreditAccountCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private Double amount;
}
