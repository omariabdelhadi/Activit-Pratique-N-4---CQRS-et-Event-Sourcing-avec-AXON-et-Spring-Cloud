package com.abdelhadielomari.cqrs_projet.commands.aggregates;

import com.abdelhadielomari.cqrs_projet.commands.commands.AddAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.CreditAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.DebitAccountCommand;
import com.abdelhadielomari.cqrs_projet.commands.commands.UpdateAccountStatuCommand;
import com.abdelhadielomari.cqrs_projet.enums.AccountStatus;
import com.abdelhadielomari.cqrs_projet.events.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Data
public class BankAccount {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    @CommandHandler
    public BankAccount(AddAccountCommand command){
        if(command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance doit étre > 0");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getAccountId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
        AggregateLifecycle.apply(new AccountActivatedEvent(command.getAccountId(),AccountStatus.ACTIVATED));
    }

    @CommandHandler
    public void credit(CreditAccountCommand command){
        if (this.status != AccountStatus.ACTIVATED){
            throw new IllegalStateException("Le compte n'est pas actif.");
        }
        AggregateLifecycle.apply(new AccountCreditEvent(command.getAccountId(),command.getAmount()));
    }

    @CommandHandler
    public void debit(DebitAccountCommand command){
        if (this.status != AccountStatus.ACTIVATED){
            throw new IllegalStateException("Le compte n'est pas actif.");
        }
        if (this.balance<command.getAmount()) throw new IllegalStateException("Fonds insuffisants !");
        AggregateLifecycle.apply(new AccountDebitEvent(command.getAccountId(),command.getAmount()));
    }

    @CommandHandler
    public void UpdateStatu(UpdateAccountStatuCommand updateAccountStatuCommand){
        AggregateLifecycle.apply(new AccountUpdatStatueEvent(updateAccountStatuCommand.getAccountId(),updateAccountStatuCommand.getAccountStatus()));
    }

    @EventSourcingHandler
    public void on(AccountUpdatStatueEvent event){
        this.status=event.getAccountStatus();
    }

    @EventSourcingHandler
    public void on(AccountDebitEvent event){
        this.balance-=event.getAmount();
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getAccountId();
        this.balance=event.getBalance();
        this.status=event.getStatus();
        this.currency=event.getCurrency();
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }

    @EventSourcingHandler
    public void on(AccountCreditEvent event){
        this.balance+=event.getAmount();
    }

}
