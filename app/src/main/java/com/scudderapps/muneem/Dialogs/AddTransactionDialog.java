package com.scudderapps.muneem.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.TransactionViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddTransactionDialog extends AppCompatDialogFragment{

    private Context context;
    Calendar c;
    
    TextView category;
    TextView date;
    EditText amount;
    TextView comment;
    TransactionData transactionData;
    RelativeLayout transactionRL;
    int month;
    int day;
    int year;
    Integer choose_color = null;
    
    
    private  String choose_amount, choose_comment, choose_category, choosed_date;
    private TransactionViewModel transactionViewModel;
    Bundle data;
    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_transaction_dialog, null);
        c = Calendar.getInstance();
        context = getActivity();
        builder.setView(view);
        
        date = view.findViewById(R.id.trans_date);
        amount = view.findViewById(R.id.trans_amount);
        category = view.findViewById(R.id.trans_cat);
        comment = view.findViewById(R.id.trans_notes);
        FloatingActionButton addTrans = view.findViewById(R.id.new_trans_add);
        LinearLayout transactionLayout = view.findViewById(R.id.transaction_item);
        transactionRL = view.findViewById(R.id.transDialogTop);
    
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        year = c.get(Calendar.YEAR);
        c.set(year, month, day, 0, 0, 0);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        String choose_date = dateFormat.format(c.getTime());
        date.setText(choose_date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
                        choosed_date = dateFormat.format(c.getTime());
                        date.setText(choosed_date);
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

                //inserting data to viewmodel.
                transactionData = new TransactionData();
                transactionData.setDate(choosed_date);
                transactionData.setAmount(choose_amount);
                transactionData.setComment(choose_comment);
                transactionData.setCategory(choose_category);
                if(choose_color != null){
                    transactionData.setColor(choose_color);
                }

                transactionViewModel = new TransactionViewModel(getActivity().getApplication());
                transactionViewModel.insert(transactionData);

                getDialog().dismiss();

            }
        });

        return builder.create();

    }
    
    public void setCategoryToTransactionDialog(String categoryName, int color){
        category.setText(categoryName);
        transactionRL.setBackgroundColor(color);
        choose_color = color;
    }
}