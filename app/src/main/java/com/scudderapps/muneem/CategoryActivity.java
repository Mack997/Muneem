package com.scudderapps.muneem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Dialogs.AddCategoryDialog;
import com.scudderapps.muneem.Model.CategoryData;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AddCategoryDialog.PassResult {

    List<CategoryData> catList = new ArrayList();
    RecyclerView categoryView;
    CategoryAdapter categoryAdapter;
    String catName;
    FloatingActionButton addCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryView =  findViewById(R.id.category_view);
        addCategory = findViewById(R.id.addCategory);

        categoryAdapter = new CategoryAdapter(getApplicationContext(), catList);
        categoryView.setAdapter(categoryAdapter);
        categoryView.setLayoutManager(new LinearLayoutManager(this));

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCategoryDialog addCategoryDialog = new AddCategoryDialog(CategoryActivity.this);
                addCategoryDialog.show(getSupportFragmentManager(), "Add Category");
            }
        });
    }

    @Override
    public void categoryDetails(String CatName) {
        catName = CatName;
        CategoryData categoryData = new CategoryData(catName);
        catList.add(categoryData);
        categoryAdapter.notifyDataSetChanged();
    }
}
