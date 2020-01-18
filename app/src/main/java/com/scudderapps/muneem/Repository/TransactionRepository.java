package com.scudderapps.muneem.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.scudderapps.muneem.DAO.TransactionDAO;
import com.scudderapps.muneem.Database.TransactionDatabase;
import com.scudderapps.muneem.Model.TransactionData;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TransactionRepository {

    private TransactionDAO transactionDAO;
    private LiveData<List<TransactionData>> allTransaction;

    public TransactionRepository(Application application) {
        TransactionDatabase transactionDatabase = TransactionDatabase.getInstance(application);
        transactionDAO = transactionDatabase.getTransactionDAO();
        allTransaction = transactionDAO.getTransactionData();
    }

    public void insert(TransactionData transactionData) {
        new InsertTransaction(transactionDAO).execute(transactionData);
    }

    public void delete(TransactionData transactionData){
        new TransactionRepository.DeleteTransaction(transactionDAO).execute(transactionData);
    }

    public void update(TransactionData transactionData){
        new TransactionRepository.UpdateTransaction(transactionDAO).execute(transactionData);
    }

    public LiveData<List<TransactionData>> getAllTransaction() {
        return allTransaction;
    }

    private static class InsertTransaction extends AsyncTask<TransactionData, Void, Void> {
        private TransactionDAO transactionDAO;

        private InsertTransaction(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(TransactionData... transactionData) {
            transactionDAO.insert(transactionData[0]);
            return null;
        }
    }

    private static class UpdateTransaction extends AsyncTask<TransactionData, Void, Void>{
        private TransactionDAO transactionDAO;

        private UpdateTransaction(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(TransactionData...  transactionData) {
            transactionDAO.update(transactionData[0]);
            return null;
        }
    }

    private static class DeleteTransaction extends AsyncTask<TransactionData, Void, Void> {
        private TransactionDAO transactionDAO;

        private DeleteTransaction(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(TransactionData...  transactionData) {
            transactionDAO.delete(transactionData[0]);
            return null;
        }
    }
}