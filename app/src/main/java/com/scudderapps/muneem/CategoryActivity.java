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

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Dialogs.AddCategoryDialog;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryView;
    CategoryAdapter categoryAdapter;
    FloatingActionButton addCategory;
    private AddCategoryDialog addCategoryDialog;
    private CategoryViewModel categoryViewModel;
    
    public CategoryData getCategoryData() {
        return categoryData;
    }
    
    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }
    
    public CategoryData categoryData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addCategory = findViewById(R.id.addCategory);
        addCategoryDialog = new AddCategoryDialog(CategoryActivity.this);

        //Setting up the Drawer Layout
        final PrimaryDrawerItem categoryItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.category)
                .withDescription("Manage your Categories")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.category_icon);

        final PrimaryDrawerItem settingItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.settings)
                .withDescription("Manage your preferences")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.settings_icon);

        final PrimaryDrawerItem transactionsItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.transactions)
                .withDescription("View your Transactions")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.graph);

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withSelectedItem(1)
                .addDrawerItems(transactionsItem, categoryItem, settingItem)
                .withHasStableIds(true)
                .withDrawerWidthDp(280)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                         if (drawerItem.equals(transactionsItem)) {
                             back();
                         }
                        return true;
                    }
                })
                .build();

        categoryView = findViewById(R.id.category_view);
        categoryView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryView.setHasFixedSize(true);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int color = Color.parseColor("#8bbabb");
                Bundle bundle = new Bundle();
                bundle.putString("name", "");
                bundle.putString("type", "");
                bundle.putInt("color", color );

                addCategoryDialog.setArguments(bundle);
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
                categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CategoryData categoryData) {
                        setCategoryData(categoryData);
                        String catName = categoryData.getName();
                        String expenseType = categoryData.getType();
                        int id = categoryData.getId();
                        int color = categoryData.getColor();

                        Bundle bundle = new Bundle();
                        bundle.putString("name", catName);
                        bundle.putString("type", expenseType);
                        bundle.putInt("color", color);
                        bundle.putInt("id",id);
                        bundle.putInt("edit",1);
                        addCategoryDialog.setArguments(bundle);
                        addCategoryDialog.show(getSupportFragmentManager(), "Update Category");
                    }
                });
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

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }

    public void back(){
        Intent transactions = new Intent(CategoryActivity.this, MainActivity.class);
        startActivity(transactions);
        finish();
    }
}
