package com.abdelhadielomari.cqrs_projet.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class DebitAccountCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private double amount;


}
