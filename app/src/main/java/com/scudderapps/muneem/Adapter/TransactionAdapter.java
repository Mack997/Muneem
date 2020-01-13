package com.scudderapps.muneem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {


    List<TransactionData> transactionData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TransactionData transactions = transactionData.get(position);
        holder.catName.setText(transactions.getCategory());
        holder.amount.setText(transactions.getAmount());
        holder.comment.setText(transactions.getComment());
        holder.date.setText(transactions.getDate());

    }


    @Override
    public int getItemCount() {
        return transactionData.size();
    }

    public void setTransactionList(List<TransactionData> transactionData){
        this.transactionData = transactionData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView catName, comment, amount, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.cat_name);
            comment = itemView.findViewById(R.id.notes);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.cat_date);
        }
    }
}
