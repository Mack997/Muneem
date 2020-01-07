package com.scudderapps.muneem;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Adapter.MainAdapter;
import com.scudderapps.muneem.Fragment.GraphFragment;
import com.scudderapps.muneem.Fragment.TransactionsFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TabLayout main_tab;
    ViewPager main_pager;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM, YYYY", Locale.getDefault());
        String currentDate = df.format(c.getTime());
        getSupportActionBar().setTitle(currentDate);

        main_tab = findViewById(R.id.main_tab);
        main_pager = findViewById(R.id.main_pager);

        main_pager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        main_tab.setupWithViewPager(main_pager);
        add = findViewById(R.id.add);
        main_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                main_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new  AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.add_transaction_dialog, null);
                final TextView date = mView.findViewById(R.id.trans_date);
                final EditText amount = mView.findViewById(R.id.trans_amount);
                final TextView category = mView.findViewById(R.id.trans_cat);
                final TextView comment = mView.findViewById(R.id.trans_notes);
                alertDialog.setView(mView);
                AlertDialog dialog = alertDialog.create();
                dialog.setCanceledOnTouchOutside(true);


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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new  AlertDialog.Builder(MainActivity.this);
                        View mView = getLayoutInflater().inflate(R.layout.category_list_item, null);
                        alertDialog.setView(mView);
                        AlertDialog dialog = alertDialog.create();
                        dialog.setCanceledOnTouchOutside(true);

                        dialog.show();

                    }
                });


                dialog.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.categories :
                Intent startCat = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(startCat);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
