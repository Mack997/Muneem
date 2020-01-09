package com.scudderapps.muneem.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.Model.TransactionData;
import com.scudderapps.muneem.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryData> categoryList;
    Context context;

    public CategoryAdapter(Context ctx, List<CategoryData> categoryList){
        context = ctx;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_list_item,parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryData expense = categoryList.get(position);
        holder.t1.setText(expense.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView t1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.category_item);
        }
    }
}