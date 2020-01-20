package com.scudderapps.muneem.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.Repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository repository;
    private LiveData<List<TransactionData>> allTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.getAllTransaction();
    }

    public void insert(TransactionData transactionData) {
        repository.insert(transactionData);
    }

    public LiveData<List<TransactionData>> getAllTransactions() {
        return allTransactions;
    }

    public void delete(TransactionData transactionData){
        repository.delete(transactionData);
    }
}