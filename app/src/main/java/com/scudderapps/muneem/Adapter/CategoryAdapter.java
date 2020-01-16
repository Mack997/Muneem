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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<CategoryData> categoryList;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        CategoryData expense = categoryList.get(position);
        holder.catName.setText(expense.getName());
        holder.catLayout.setBackgroundColor(expense.getColor());

        if (expense.getType().equals("Expense")){
            holder.catType.setBackgroundResource(R.drawable.expense);
        } else {
            holder.catType.setBackgroundResource(R.drawable.income);
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

    public CategoryData getCategoryAt(int position){
        return categoryList.get(position);
    }


    class CategoryHolder extends RecyclerView.ViewHolder {

        TextView catName;
        LinearLayout catLayout;
        ImageView catType;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.category_item);
            catLayout = itemView.findViewById(R.id.cat_layout);
            catType = itemView.findViewById(R.id.add_cat_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(categoryList.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(CategoryData categoryData);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}