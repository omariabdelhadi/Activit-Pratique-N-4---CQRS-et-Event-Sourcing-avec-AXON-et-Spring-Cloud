package com.abdelhadielomari.cqrs_projet.query.dtos;

import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import com.abdelhadielomari.cqrs_projet.query.entities.AccountOperation;

import java.util.List;

public record AccountStatementDTO(Account account, List<AccountOperation> accountOperations) {
}
