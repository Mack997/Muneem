package com.scudderapps.muneem.DAO;

import com.scudderapps.muneem.Model.CategoryData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryData... categoryData);

    @Update
    void update(CategoryData... categoryData);

    @Delete
    void delete(CategoryData... categoryData);

    @Query("SELECT * FROM categorydata ORDER BY name")
    LiveData<List<CategoryData>> getCategoryData();
    
    @Query("SELECT * FROM categorydata WHERE id = :id limit 1")
    CategoryData getCategoryById(String id);
}
