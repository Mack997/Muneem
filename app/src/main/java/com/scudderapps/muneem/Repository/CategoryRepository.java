package com.scudderapps.muneem.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.scudderapps.muneem.Database.CategoryDatabase;
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
        new InsertCategory(categoryDAO).execute(categoryData);
    }

    public void delete(CategoryData categoryData){
        new DeleteCategory(categoryDAO).execute(categoryData);
    }

    public LiveData<List<CategoryData>> getAllCategory() {
        return allCategory;
    }

    private static class InsertCategory extends AsyncTask<CategoryData, Void, Void> {
        private CategoryDAO categoryDAO;

        private InsertCategory(CategoryDAO categoryDao) {
            this.categoryDAO = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryData...  categoryData) {
            categoryDAO.insert(categoryData[0]);
            return null;
        }
    }

    private static class DeleteCategory extends AsyncTask<CategoryData, Void, Void> {
        private CategoryDAO categoryDAO;

        private DeleteCategory(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(CategoryData...  categoryData) {
            categoryDAO.delete(categoryData[0]);
            return null;
        }
    }

}
