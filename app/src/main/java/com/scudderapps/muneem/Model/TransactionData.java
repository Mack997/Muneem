package com.scudderapps.muneem.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionData {

    @PrimaryKey(autoGenerate = true)
    int id;
    String date;
    String amount;
    String comment;
    String Type;
    int color;

    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;

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
}
