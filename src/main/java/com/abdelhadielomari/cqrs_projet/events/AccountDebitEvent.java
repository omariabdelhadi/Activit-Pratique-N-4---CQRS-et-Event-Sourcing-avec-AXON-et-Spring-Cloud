package com.abdelhadielomari.cqrs_projet.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDebitEvent {
    private String accountId;
    private Double amount;
}
