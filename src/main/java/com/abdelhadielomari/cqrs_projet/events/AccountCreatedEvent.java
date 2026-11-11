package com.abdelhadielomari.cqrs_projet.events;

import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

}
