package com.scudderapps.muneem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.scudderapps.muneem.Adapter.MainAdapter;
import com.scudderapps.muneem.Dialogs.AddTransactionDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

                AddTransactionDialog addTransactionDialog = new AddTransactionDialog(MainActivity.this);
                addTransactionDialog.setCanceledOnTouchOutside(true);
                addTransactionDialog.show();

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
