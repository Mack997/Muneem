package com.scudderapps.muneem.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.scudderapps.muneem.CategoryDatabase;
import com.scudderapps.muneem.DAO.CategoryDAO;
import com.scudderapps.muneem.Model.CategoryData;
import java.util.List;
import androidx.lifecycle.LiveData;

public class CategoryRepository {

    private CategoryDAO categoryDAO;
    private LiveData<List<CategoryData>> allCategory;

    public  CategoryRepository(Application application) {
        CategoryDatabase database = CategoryDatabase.getInstance(application);
        categoryDAO = database.getCategoryDAO();
        allCategory = categoryDAO.getCategoryData();
    }

    public void insert(CategoryData categoryData) {
        new InsertNoteAsyncTask(categoryDAO).execute(categoryData);
    }

    public LiveData<List<CategoryData>> getAllCategory() {
        return allCategory;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<CategoryData, Void, Void> {
        private CategoryDAO categoryDAO;

        private InsertNoteAsyncTask(CategoryDAO noteDao) {
            this.categoryDAO = noteDao;
        }

        @Override
        protected Void doInBackground(CategoryData...  categoryData) {
            categoryDAO.insert(categoryData[0]);
            return null;
        }
    }

}
