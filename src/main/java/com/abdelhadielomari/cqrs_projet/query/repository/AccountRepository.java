package com.abdelhadielomari.cqrs_projet.query.repository;

import com.abdelhadielomari.cqrs_projet.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
