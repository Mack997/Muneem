package com.scudderapps.muneem.Dialogs;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.scudderapps.muneem.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTransactionDialog extends Dialog implements View.OnClickListener{

    private Context context;
    final Calendar c = Calendar.getInstance();

    private PassTransacationDetails passTransacationDetails;

    public AddTransactionDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final TextView date = findViewById(R.id.trans_date);
        final EditText amount = findViewById(R.id.trans_amount);
        final TextView category = findViewById(R.id.trans_cat);
        final TextView comment = findViewById(R.id.trans_notes);
        final Button addTrans = findViewById(R.id.new_trans_add);

        final Button canelTrans = findViewById(R.id.new_trans_cancel);


        final Integer month=c.get(Calendar.MONTH);
        final Integer day=c.get(Calendar.DAY_OF_MONTH);
        final Integer year=c.get(Calendar.YEAR);

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
                },year, month, day);
                datePickerDialog.show();
            }
        });

        String choose_amount = amount.getText().toString();
        passTransacationDetails.TransacationDetails(choose_amount, choose_date);

    }

    @Override
    public void onClick(View v) {

    }

    public interface PassTransacationDetails{
        void TransacationDetails(String amount, String date);
    }

}