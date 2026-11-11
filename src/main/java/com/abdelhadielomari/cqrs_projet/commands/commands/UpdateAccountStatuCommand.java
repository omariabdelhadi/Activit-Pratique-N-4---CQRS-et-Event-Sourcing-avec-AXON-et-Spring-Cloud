package com.abdelhadielomari.cqrs_projet.commands.commands;

import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class UpdateAccountStatuCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private AccountStatus accountStatus;
}
