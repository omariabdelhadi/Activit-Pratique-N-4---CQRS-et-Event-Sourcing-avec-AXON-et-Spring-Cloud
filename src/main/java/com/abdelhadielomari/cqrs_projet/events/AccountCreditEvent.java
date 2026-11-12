package com.abdelhadielomari.cqrs_projet.events;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountCreditEvent {
    private String accountId;
    private Double amount;
}
