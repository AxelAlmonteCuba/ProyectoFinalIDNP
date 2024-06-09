package com.example.proyectofinalplataformas.Repositorios;

import com.example.proyectofinalplataformas.Entitys.AccountEntity;

public class AccountRepository {
    private static AccountRepository instance;
    private AccountEntity accountEntity;
    private AccountRepository(){}

    public static synchronized AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
