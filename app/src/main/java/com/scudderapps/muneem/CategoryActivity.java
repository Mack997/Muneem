package com.scudderapps.muneem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Dialogs.AddCategoryDialog;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryView;
    CategoryAdapter categoryAdapter;
    FloatingActionButton addCategory;

    private CategoryViewModel categoryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addCategory = findViewById(R.id.addCategory);

        categoryView = findViewById(R.id.category_view);
        categoryView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryView.setHasFixedSize(true);

//        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(CategoryData categoryData) {
//
//            }
//        });

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCategoryDialog addCategoryDialog = new AddCategoryDialog(CategoryActivity.this);
                addCategoryDialog.show(getSupportFragmentManager(), "Add Category");
            }
        });
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().observe(this, new Observer<List<CategoryData>>() {
            @Override
            public void onChanged(@Nullable List<CategoryData> categoryData) {
                //setting up the data in recycler view.
                categoryAdapter = new CategoryAdapter();
                categoryView.setAdapter(categoryAdapter);
                categoryAdapter.setCategoryList(categoryData);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                categoryViewModel.delete(categoryAdapter.getCategoryAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(categoryView);
    }
}
