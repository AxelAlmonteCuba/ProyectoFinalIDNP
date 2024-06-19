package com.example.proyectofinalplataformas.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyectofinalplataformas.Entitys.AccountEntity;
import com.example.proyectofinalplataformas.Repositorios.AccountRepository;

public class ShareViewModel extends ViewModel {
    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private final MutableLiveData<AccountEntity> accountLiveData = new MutableLiveData<>();

    public void SetAccount (AccountEntity accountEntity){
        accountRepository.setAccountEntity(accountEntity);
        accountLiveData.setValue(accountEntity);
    }

    public LiveData<AccountEntity> getAccount() {
        return accountLiveData;
    }
}
