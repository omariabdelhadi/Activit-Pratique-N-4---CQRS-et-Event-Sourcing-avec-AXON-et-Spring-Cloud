package com.abdelhadielomari.cqrs_projet.commands.commands;

import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class AddAccountCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private double initialBalance;
    private String currency;

}
