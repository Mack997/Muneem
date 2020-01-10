package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.scudderapps.muneem.R;

import petrov.kristiyan.colorpicker.ColorPicker;

public class AddCategoryDialog extends AppCompatDialogFragment  {

    private Context context;
    private String new_cat_name;
    private String cat_type;
    private int colorID;
    private RadioButton categoryType;
    private LinearLayout layout;
    private PassResult mPassResult;

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

        final TextView cat_name = view.findViewById(R.id.new_cat_name);
        final RadioGroup expense_type = view.findViewById(R.id.cat_type);
        TextView selectedColor = view.findViewById(R.id.new_cat_color);

        Button add = view.findViewById(R.id.new_cat_add);
        Button cancel = view.findViewById(R.id.new_cat_cancel);

        layout = view.findViewById(R.id.catDialog);

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
                        // put code
                    }
                }).setColumns(5)
                        .setTitle("Select Color")
                        .show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_cat_name = cat_name.getText().toString();

                final int expenseTypeID = expense_type.getCheckedRadioButtonId();
                categoryType = view.findViewById(expenseTypeID);
                cat_type = categoryType.getText().toString();

                ColorDrawable colorDrawable = (ColorDrawable) layout.getBackground();
                colorID = colorDrawable.getColor();

                mPassResult.categoryDetails(new_cat_name, cat_type, colorID);

                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mPassResult = (PassResult) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Please implement PassResult");
        }
    }

    public interface PassResult{
        void categoryDetails(String CatName, String Type, int Color);
    }
}