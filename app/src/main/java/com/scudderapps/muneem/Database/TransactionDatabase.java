package com.scudderapps.muneem.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.scudderapps.muneem.DAO.TransactionDAO;
import com.scudderapps.muneem.Model.TransactionData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TransactionData.class}, version = 2, exportSchema = false)
public abstract class TransactionDatabase extends RoomDatabase {

    private static TransactionDatabase instance;
    public abstract TransactionDAO getTransactionDAO();

    public static synchronized TransactionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionDatabase.class, "TransactionData")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionDAO transactionDAO;

        private PopulateDbAsyncTask(TransactionDatabase db) {
            transactionDAO = db.getTransactionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDAO.insert();
            return null;
        }
    }
}
