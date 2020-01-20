package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;

import java.util.List;

public class CategoryDialog extends AppCompatDialogFragment {

    private Context context;
    private RecyclerView categoryView;
    private CategoryAdapter categoryAdapter;
    private FloatingActionButton fab;
    private AddTransactionDialog addTransactionDialog;
    

    public CategoryDialog(Context context) {
        this.context = context;
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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

        addTransactionDialog = new AddTransactionDialog();
        categoryView = view.findViewById(R.id.category_view);
        categoryAdapter = new CategoryAdapter();
        fab = view.findViewById(R.id.addCategory);
        fab.hide();

        CategoryViewModel categoryViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().observe(this, new Observer<List<CategoryData>>() {
            @Override
            public void onChanged(@Nullable List<CategoryData> categoryData) {
                //setting up the data in recycler view.
                categoryView.setLayoutManager(new LinearLayoutManager(getContext()));
                categoryView.setAdapter(categoryAdapter);
                categoryAdapter.setCategoryList(categoryData);
                Bundle transData = new Bundle();
                transData.putString("name", "");
                transData.putString("type", "");
                transData.putInt("color", 0);
                Log.e("Databefore", transData.toString());
                addTransactionDialog.setArguments(transData);
                categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CategoryData categoryData) {
                        String catName = categoryData.getName();
                        String expenseType = categoryData.getType();
                        int color = categoryData.getColor();
                        if (getParentFragment() instanceof AddTransactionDialog) {
                            ((AddTransactionDialog) getParentFragment())
                                    .setCategoryToTransactionDialog(catName, color, expenseType);
                        }

                        Bundle transData = new Bundle();
                        transData.putString("name", catName);
                        transData.putString("type", expenseType);
                        transData.putInt("color", color);
                        Log.e("Dataafter", transData.toString());
                        addTransactionDialog.setArguments(transData);
                        getDialog().dismiss();
                    }
                });
            }
        });



    return builder.create();
    }
}
