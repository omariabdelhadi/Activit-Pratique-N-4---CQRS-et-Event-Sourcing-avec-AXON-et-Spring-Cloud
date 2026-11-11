package com.abdelhadielomari.cqrs_projet.commands.dtos;

import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;

public record UpdateAccountStatuRequestDTO(AccountStatus statue) {
}
