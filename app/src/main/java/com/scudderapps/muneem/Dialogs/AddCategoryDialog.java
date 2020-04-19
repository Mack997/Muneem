package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.CategoryActivity;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;
import petrov.kristiyan.colorpicker.ColorPicker;

public class AddCategoryDialog extends AppCompatDialogFragment  {

    private Context context;
    private String updated_cat_name, cat_type;
    private int colorID, expenseTypeID;
    private CategoryData categoryData;
    private int editTypeUpdated;

    private RadioButton categoryType;
    private RelativeLayout layout;
    private CategoryViewModel categoryViewModel;
    private Bundle bundle;

    private EditText cat_name;
    private RadioGroup expense_type;
    private Button selectedColor;
    private FloatingActionButton add;
    private RadioButton expense, income;

    public AddCategoryDialog(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(view);

        //Initiating widgets
        cat_name = view.findViewById(R.id.new_cat_name);
        expense_type = view.findViewById(R.id.cat_type);
        selectedColor = view.findViewById(R.id.new_cat_color);
        add = view.findViewById(R.id.new_cat_add);
        expense = view.findViewById(R.id.expense);
        income = view.findViewById(R.id.income);
        layout = view.findViewById(R.id.catDialogTop);
        expense.setButtonDrawable(R.drawable.expense_50);
        income.setButtonDrawable(R.drawable.income_50);

        //reading data from bundle sent from Category Activity
        bundle = getArguments();
        String bundleName = bundle.getString("name");
        String bundleType = bundle.getString("type");
        final int bundleColor = bundle.getInt("color");

        //setting the default values to the dialog box
        cat_name.setText(bundleName);
        layout.setBackgroundColor(bundleColor);
        selectedColor.setBackgroundColor(bundleColor);
        if (bundleType.equals("Expense")) {
            expense.setChecked(true);
        } else if (bundleType.equals("Income")){
            income.setChecked(true);
        } else{
            expense.setChecked(true);
            income.setChecked(false);
        }

        //getting color from color selector
        selectedColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker.setRoundColorButton(true);

                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        layout.setBackgroundColor(color);
                        selectedColor.setBackgroundColor(color);
                        colorPicker.dismissDialog();
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(context, "Please select a color for the Category", Toast.LENGTH_SHORT).show();
                    }
                }).setColumns(5)
                        .setTitle("Select Color")
                        .show();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updated_cat_name = cat_name.getText().toString();
                expenseTypeID = expense_type.getCheckedRadioButtonId();
                categoryType = view.findViewById(expenseTypeID);
                cat_type = categoryType.getText().toString();
                ColorDrawable colorDrawable = (ColorDrawable) layout.getBackground();
                colorID = colorDrawable.getColor();

                if (updated_cat_name.isEmpty()) {
                    cat_name.setError("Enter Category");
                    Toast.makeText(context, "Enter Category", Toast.LENGTH_SHORT).show();
                } else {
                    if(bundle.getInt("edit") == 1){

                        categoryData = ((CategoryActivity)getActivity()).getCategoryData();
                        layout.setBackgroundColor(categoryData.getColor());
                        editTypeUpdated = 1 ;
                    }else{
                        categoryData = new CategoryData();
                    }

                    categoryData.setName(updated_cat_name);
                    categoryData.setType(cat_type);
                    categoryData.setColor(colorID);
                    categoryData.setEdited(editTypeUpdated);
                    categoryData.setId(updated_cat_name+"-"+cat_type);

                    categoryViewModel = new CategoryViewModel(getActivity().getApplication());

                    if(bundle.getInt("edit") == 1){
                        categoryViewModel.update(categoryData);
                    }else{
                        categoryViewModel.insert(categoryData);
                    }
                }
                getDialog().dismiss();
            }
        });
        return builder.create();
    }
}