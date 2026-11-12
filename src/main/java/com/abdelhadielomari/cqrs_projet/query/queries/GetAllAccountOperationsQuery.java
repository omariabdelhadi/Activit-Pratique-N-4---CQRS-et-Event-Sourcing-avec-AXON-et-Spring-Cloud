package com.abdelhadielomari.cqrs_projet.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllAccountOperationsQuery {
    private String id;
}
