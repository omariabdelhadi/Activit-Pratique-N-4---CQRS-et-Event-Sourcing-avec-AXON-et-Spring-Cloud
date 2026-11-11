package com.abdelhadielomari.cqrs_projet.events;

import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountActivatedEvent {
    private String accountId;
    private AccountStatus status;
}
