package com.scudderapps.muneem.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.scudderapps.muneem.CategoryActivity;
import com.scudderapps.muneem.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddTransactionDialog extends AppCompatDialogFragment {

    private Context context;
    final Calendar c = Calendar.getInstance();

    public AddTransactionDialog(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.add_transaction_dialog, null);
        builder.setView(view);

        final TextView date = view.findViewById(R.id.trans_date);
        final EditText amount = view.findViewById(R.id.trans_amount);
        final TextView category = view.findViewById(R.id.trans_cat);
        final TextView comment = view.findViewById(R.id.trans_notes);
        final Button addTrans = view.findViewById(R.id.new_trans_add);

        final Button canelTrans = view.findViewById(R.id.new_trans_cancel);


        final Integer month = c.get(Calendar.MONTH);
        final Integer day = c.get(Calendar.DAY_OF_MONTH);
        final Integer year = c.get(Calendar.YEAR);

        c.set(year, month, day, 0, 0, 0);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
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
                        String choosed_date = dateFormat.format(c.getTime());
                        date.setText(choosed_date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDialog categoryDialog = new CategoryDialog(getContext());
                categoryDialog.show(getActivity().getSupportFragmentManager(), "Select Category");
            }
        });

        String choose_amount = amount.getText().toString();
        String choose_comment = comment.getText().toString();

        return builder.create();

    }
}