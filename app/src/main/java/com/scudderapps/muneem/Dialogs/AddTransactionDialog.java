package com.scudderapps.muneem.Dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.TransactionViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddTransactionDialog extends AppCompatDialogFragment{

    private Context context;
    private Calendar c;

    private TextView category, date,comment;
    private EditText amount;
    private TransactionData transactionData;
    private RelativeLayout transactionRL;
    private int month, day, year;
    private Integer choose_color = null;
    private String fetched_type;
    private FloatingActionButton addTrans;
    private String choose_amount, choose_comment, choose_category, choose_date;
    private TransactionViewModel transactionViewModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_transaction_dialog, null);
        builder.setView(view);

        c = Calendar.getInstance();
        context = getActivity();

        date = view.findViewById(R.id.trans_date);
        amount = view.findViewById(R.id.trans_amount);
        category = view.findViewById(R.id.trans_cat);
        comment = view.findViewById(R.id.trans_notes);
        addTrans = view.findViewById(R.id.new_trans_add);
        transactionRL = view.findViewById(R.id.transDialogTop);

        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        year = c.get(Calendar.YEAR);
        c.set(year, month, day);
        String choose_date = dateFormat.format(c.getTime());
        date.setText(choose_date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        AddTransactionDialog.this.choose_date = dateFormat.format(c.getTime());
                        date.setText(AddTransactionDialog.this.choose_date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CategoryDialog categoryDialog = new CategoryDialog(getActivity());
                fragmentTransaction.add(categoryDialog, "Select Category");
                fragmentTransaction.commit();
            }
        });


        addTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choose_amount = amount.getText().toString();
                choose_comment = comment.getText().toString();
                choose_category = category.getText().toString();
                if (choose_amount.isEmpty()){
                    amount.setError("Please Enter an amount");
                    Toast.makeText(context, "Please Enter a amount", Toast.LENGTH_SHORT).show();
                } else if (choose_category.isEmpty()){
                    category.setError("Please choose a category");
                    Toast.makeText(context, "Please select a Category", Toast.LENGTH_SHORT).show();
                } else {
                    transactionData = new TransactionData();
                    transactionData.setDate(date.getText().toString());
                    transactionData.setAmount(choose_amount);
                    transactionData.setComment(choose_comment);
                    transactionData.setCategory(choose_category);
                    transactionData.setType(fetched_type);
                    if (choose_color != null) {
                        transactionData.setColor(choose_color);
                    }

                    transactionViewModel = new TransactionViewModel(getActivity().getApplication());
                    transactionViewModel.insert(transactionData);
                    getDialog().dismiss();
                }
            }
        });
        return builder.create();
    }
    
    public void setCategoryToTransactionDialog(String categoryName, int color, String getType){
        category.setText(categoryName);
        transactionRL.setBackgroundColor(color);
        choose_color = color;
        fetched_type = getType;
    }
}