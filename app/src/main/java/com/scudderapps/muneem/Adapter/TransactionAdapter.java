package com.scudderapps.muneem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {


    List<TransactionData> transactionList;
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

        TransactionData transactions = transactionList.get(position);
        holder.catName.setText(transactions.getCategory());
        holder.amount.setText(transactions.getAmount());
        holder.comment.setText(transactions.getComment());
        holder.date.setText(transactions.getDate());
        if (transactions.getType().equals("Expense")) {
            holder.image_type.setBackgroundResource(R.drawable.expense_50);
        }else {
            holder.image_type.setBackgroundResource(R.drawable.income_50);
        }
        holder.itemView.setBackgroundColor(transactions.getColor());
    }


    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public void setTransactionList(List<TransactionData> transactionData){
        this.transactionList = transactionData;
        notifyDataSetChanged();
    }

    public TransactionData getTransactionAt(int position){
        return transactionList.get(position);
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView catName, comment, amount, date;
        ImageView image_type;
        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.cat_name);
            comment = itemView.findViewById(R.id.notes);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.cat_date);
            image_type = itemView.findViewById(R.id.trans_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(transactionList.get(position));
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