package com.scudderapps.muneem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryData> categoryList;

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_list_item,parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        CategoryData expense = categoryList.get(position);
        holder.catName.setText(expense.getName());
        holder.catLayout.setBackgroundColor(expense.getColor());

        if (expense.getType().equals("Expense")){
            holder.catType.setBackgroundResource(R.drawable.expense_sub_icon);
        } else {
            holder.catType.setBackgroundResource(R.drawable.income_add_icon);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<CategoryData> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView catName;
        LinearLayout catLayout;
        ImageView catType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.category_item);
            catLayout = itemView.findViewById(R.id.cat_layout);
            catType = itemView.findViewById(R.id.add_cat_image);
        }
    }
}