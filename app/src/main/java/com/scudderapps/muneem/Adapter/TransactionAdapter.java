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

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {


    List<TransactionData> transactionData;
    private TransactionAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item,parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {

        TransactionData transactions = transactionData.get(position);
        holder.catName.setText(transactions.getCategory());
        holder.amount.setText(transactions.getAmount());
        holder.comment.setText(transactions.getComment());
        holder.date.setText(transactions.getDate());
        holder.itemView.setBackgroundColor(transactions.getColor());

    }


    @Override
    public int getItemCount() {
        return transactionData.size();
    }

    public void setTransactionList(List<TransactionData> transactionData){
        this.transactionData = transactionData;
        notifyDataSetChanged();
    }

    public TransactionData getTransactionAt(int position){
        return transactionData.get(position);
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView catName, comment, amount, date;
        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.cat_name);
            comment = itemView.findViewById(R.id.notes);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.cat_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(transactionData.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(TransactionData transactionData);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}