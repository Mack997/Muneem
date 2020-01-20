package com.scudderapps.muneem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scudderapps.muneem.Adapter.TransactionAdapter;
import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;
import com.scudderapps.muneem.ViewModels.TransactionViewModel;

import java.util.List;

public class TransactionsFragment extends Fragment {

    TransactionAdapter transactionAdapter;
    RecyclerView transactionView;
    private TransactionViewModel transactionViewModel;

    public TransactionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);
        transactionView = rootView.findViewById(R.id.transaction_recycler_view);

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        transactionViewModel.getAllTransactions().observe(this, new Observer<List<TransactionData>>() {
            @Override
            public void onChanged(@Nullable List<TransactionData> transactionData) {
                //setting up the data in recycler view.
                transactionView.setLayoutManager(new LinearLayoutManager(getContext()));
                transactionAdapter= new TransactionAdapter();
                transactionView.setAdapter(transactionAdapter);
                transactionAdapter.setTransactionList(transactionData);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                transactionViewModel.delete(transactionAdapter.getTransactionAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(transactionView);

        return rootView;
    }
}
