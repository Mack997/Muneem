package com.scudderapps.muneem;

import android.content.Context;
import android.os.AsyncTask;

import com.scudderapps.muneem.DAO.CategoryDAO;
import com.scudderapps.muneem.Model.CategoryData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CategoryData.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {

    private static CategoryDatabase instance;
    public abstract CategoryDAO getCategoryDAO();

    public static synchronized CategoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CategoryDatabase.class, "CategoryData")
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
        private CategoryDAO categoryDAO;

        private PopulateDbAsyncTask(CategoryDatabase db) {
            categoryDAO = db.getCategoryDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDAO.insert();
            return null;
        }
    }
}
