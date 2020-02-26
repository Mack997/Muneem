package com.scudderapps.muneem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Trash");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setting up the Drawer Layout
        final PrimaryDrawerItem categoryItem = new PrimaryDrawerItem()
                .withName(R.string.category)
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.black_de))
                .withIcon(R.drawable.category_icon);

        final PrimaryDrawerItem settingItem = new PrimaryDrawerItem()
                .withName(R.string.settings)
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.black_de))
                .withIcon(R.drawable.settings_icon);

        final PrimaryDrawerItem transactionsItem = new PrimaryDrawerItem()
                .withName(R.string.transactions)
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.black_de))
                .withIcon(R.drawable.graph);

        final PrimaryDrawerItem trashItem = new PrimaryDrawerItem()
                .withName("Trash")
                .withSetSelected(false)
                .withSelectedTextColor(getColor(R.color.black_de))
                .withIcon(R.drawable.trash);


        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withSelectedItem(1)
                .addDrawerItems(transactionsItem, categoryItem, trashItem, settingItem)
                .withHasStableIds(true)
                .withDrawerWidthDp(280)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.equals(transactionsItem)) {
                            back();
                        } else if (drawerItem.equals(categoryItem)) {
                            Intent category = new Intent(TrashActivity.this, CategoryActivity.class);
                            startActivity(category);
                            finish();
                        }
                        return true;
                    }
                })
                .build();
    }

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }

    public void back(){
        Intent transactions = new Intent(TrashActivity.this, MainActivity.class);
        startActivity(transactions);
        finish();
    }
}

