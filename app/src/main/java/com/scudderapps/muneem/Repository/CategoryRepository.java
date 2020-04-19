package com.scudderapps.muneem.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.scudderapps.muneem.Database.CategoryDatabase;
import com.scudderapps.muneem.DAO.CategoryDAO;
import com.scudderapps.muneem.Model.CategoryData;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class CategoryRepository {

    private CategoryDAO categoryDAO;
    private LiveData<List<CategoryData>> allCategory;
    private CategoryDatabase database;
    private CategoryData categoryDataById;

    public  CategoryRepository(Application application) {
        
        database = CategoryDatabase.getInstance(application);
        categoryDAO = database.getCategoryDAO();
        allCategory = categoryDAO.getCategoryData();
    }

    public void insert(CategoryData categoryData) {
        new InsertCategory(categoryDAO).execute(categoryData);
    }

    public void delete(CategoryData categoryData){
        new DeleteCategory(categoryDAO).execute(categoryData);
    }
    
    public void update(CategoryData categoryData){
        new UpdateCategory(categoryDAO).execute(categoryData);
    }

    public LiveData<List<CategoryData>> getAllCategory() {
        return allCategory;
    }

    public CategoryData getCategoryById(String id) {
//        AsyncTask.execute(() ->  categoryDataById = categoryDAO.getCategoryById(id));
        try {
            categoryDataById = new CategoryById(categoryDAO).execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return categoryDataById;
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
    
    private static class CategoryById extends AsyncTask<String, Void, CategoryData> {
        private CategoryDAO categoryDAO;
        
        private CategoryById(CategoryDAO categoryDao) {
            this.categoryDAO = categoryDao;
        }
        
        @Override
        protected CategoryData doInBackground(String...  categoryData) {
            return categoryDAO.getCategoryById(categoryData[0]);
        }
    }
    
    private static class UpdateCategory extends AsyncTask<CategoryData, Void, Void>{
        private CategoryDAO categoryDAO;
    
        private UpdateCategory(CategoryDAO categoryDao) {
            this.categoryDAO = categoryDao;
        }
    
        @Override
        protected Void doInBackground(CategoryData...  categoryData) {
            categoryDAO.update(categoryData[0]);
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
