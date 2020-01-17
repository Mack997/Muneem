package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    private String new_cat_name;
    private String cat_type;
    private int colorID;
    int expenseTypeID;

    private RadioButton categoryType;
    private RelativeLayout layout;
    private CategoryViewModel categoryViewModel;

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
        final EditText cat_name = view.findViewById(R.id.new_cat_name);
        final RadioGroup expense_type = view.findViewById(R.id.cat_type);
        final TextView selectedColor = view.findViewById(R.id.new_cat_color);
        final FloatingActionButton add = view.findViewById(R.id.new_cat_add);
        final RadioButton expense = view.findViewById(R.id.expense);
        final RadioButton income = view.findViewById(R.id.income);
        layout = view.findViewById(R.id.catDialogTop);
        expense.setButtonDrawable(R.drawable.expense_50);
        income.setButtonDrawable(R.drawable.income_50);

        //reading data from bundle sent from Category Activity
        final Bundle bundle = getArguments();
        String bundleName = bundle.getString("name");
        String bundleType = bundle.getString("type");
        final int bundleColor = bundle.getInt("color");

        //setting the default values to the dialog box
        cat_name.setText(bundleName);
        layout.setBackgroundColor(bundleColor);
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

                //Reading details from the user from dialog box
                new_cat_name = cat_name.getText().toString();
                expenseTypeID = expense_type.getCheckedRadioButtonId();
                categoryType = view.findViewById(expenseTypeID);
                cat_type = categoryType.getText().toString();
                ColorDrawable colorDrawable = (ColorDrawable) layout.getBackground();
                colorID = colorDrawable.getColor();

                CategoryData categoryData;
                if (TextUtils.isEmpty(new_cat_name)) {
                    cat_name.setError("Enter a category");
                }

                //inserting data to view model.
                if(bundle.getInt("edit") == 1){
                    categoryData = ((CategoryActivity)getActivity()).getCategoryData();
                    layout.setBackgroundColor(categoryData.getColor());
                }else{
                    categoryData = new CategoryData();
                }

                categoryData.setName(new_cat_name);
//                categoryData.setId(bundle.getInt("id"));
                categoryData.setType(cat_type);
                categoryData.setColor(colorID);
                categoryViewModel = new CategoryViewModel(getActivity().getApplication());
                if(bundle.getInt("edit") == 1){
                    categoryViewModel.update(categoryData);
                }else{
                    categoryViewModel.insert(categoryData);
                }

                getDialog().dismiss();
            }
        });
        return builder.create();
    }
}