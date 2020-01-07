package com.scudderapps.muneem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryView;
    FloatingActionButton addCategory;

    String[] s1;

    CategoryAdapter categoryAdapter;

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

        s1 = getResources().getStringArray(R.array.cat);

        categoryAdapter = new CategoryAdapter(this, s1);

        categoryView.setAdapter(categoryAdapter);
        categoryView.setLayoutManager(new LinearLayoutManager(this));


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new  AlertDialog.Builder(CategoryActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.add_category_dialog, null);
                final TextView cat_name = mView.findViewById(R.id.add_cat_name);
                final RadioGroup expense_type = mView.findViewById(R.id.type);
                final TextView selectedColor = mView.findViewById(R.id.color);
                final LinearLayout layout = mView.findViewById(R.id.catDialog);
                alertDialog.setView(mView);
                AlertDialog dialog = alertDialog.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                selectedColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ColorPicker colorPicker = new ColorPicker(CategoryActivity.this);
                        colorPicker.setRoundColorButton(true);

                        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                layout.setBackgroundColor(color);
                                colorPicker.dismissDialog();
                            }

                            @Override
                            public void onCancel() {
                                // put code
                            }
                        }).setColumns(5)
                            .setTitle("Select Color")
                            .show();
                    }
                });

            }
        });

    }
}
