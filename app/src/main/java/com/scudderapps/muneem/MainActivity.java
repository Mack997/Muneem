package com.scudderapps.muneem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Adapter.MainAdapter;
import com.scudderapps.muneem.Dialogs.AddCategoryDialog;
import com.scudderapps.muneem.Dialogs.AddTransactionDialog;
import com.scudderapps.muneem.Dialogs.CategoryDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

                AddTransactionDialog transactionDialog = new AddTransactionDialog(MainActivity.this);
                transactionDialog.show(getSupportFragmentManager(), "Add Transaction");

            }
        });

        //Setting up the Drawer Layout
        final PrimaryDrawerItem categoryItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.category)
                .withDescription("Manage your Categories")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.category_icon);

        final PrimaryDrawerItem settingItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.settings)
                .withDescription("Manage your preferences")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.settings_icon);

        final PrimaryDrawerItem transactionsItem = new PrimaryDrawerItem()
                .withSelectedColor(getColor(R.color.colorPrimaryDark))
                .withName(R.string.transactions)
                .withDescription("View your Transactions")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.colorPrimary))
                .withIcon(R.drawable.graph);

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withSelectedItem(0)
                .addDrawerItems(transactionsItem, categoryItem, settingItem)
                .withHasStableIds(true)
                .withDrawerWidthDp(280)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.equals(categoryItem)) {
                            Intent category = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(category);
                            finish();
                        }
                        return true;
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
        }
        return super.onOptionsItemSelected(item);
    }
}
