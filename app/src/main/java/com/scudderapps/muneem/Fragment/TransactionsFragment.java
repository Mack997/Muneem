package com.scudderapps.muneem.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scudderapps.muneem.Adapter.TransactionAdapter;
import com.scudderapps.muneem.Dialogs.AddTransactionDialog;
import com.scudderapps.muneem.R;

public class TransactionsFragment extends Fragment {

    TransactionAdapter td;

    public TransactionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);
        RecyclerView transactionsView = rootView.findViewById(R.id.transaction_recycler_view);

        return rootView;

    }
}
