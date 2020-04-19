package com.scudderapps.muneem.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionData {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String amount;
    private String comment;
    private String Type;
    private int color;
    private String categoryName;
    private String categoryId;
    
    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getType() {
        return Type;
    }
    
    public void setType(String type) {
        Type = type;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
