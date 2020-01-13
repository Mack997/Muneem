package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryDialog extends AppCompatDialogFragment {

    private Context context;
    RecyclerView categoryView;
    CategoryAdapter categoryAdapter;
    FloatingActionButton fab;
    private CategoryViewModel categoryViewModel;

    public CategoryDialog(Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.activity_category, null);
        builder.setView(view);

        Toolbar toolbar = view.findViewById(R.id.cat_toolbar);
        toolbar.setTitle("Select Category");

        categoryView = view.findViewById(R.id.category_view);
        fab = view.findViewById(R.id.addCategory);
        fab.hide();
        categoryViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().observe(this, new Observer<List<CategoryData>>() {
            @Override
            public void onChanged(@Nullable List<CategoryData> categoryData) {
                //setting up the data in recycler view.
                categoryView.setLayoutManager(new LinearLayoutManager(getContext()));
                categoryAdapter = new CategoryAdapter();
                categoryView.setAdapter(categoryAdapter);
                categoryAdapter.setCategoryList(categoryData);
            }
        });
    return builder.create();
    }
}
