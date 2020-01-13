package com.scudderapps.muneem.ViewModels;

import android.app.Application;

import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.Repository.CategoryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository repository;
    private LiveData<List<CategoryData>> allCategory;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new CategoryRepository(application);
        allCategory = repository.getAllCategory();
    }

    public void insert(CategoryData categoryData) {
        repository.insert(categoryData);
    }

    public void delete(CategoryData categoryData){
        repository.delete(categoryData);
    }

    public LiveData<List<CategoryData>> getAllCategory() {
        return allCategory;
    }
}