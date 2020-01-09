package com.scudderapps.muneem.Model;

public class TransactionData {

    String Amount, Date, Category, Notes;

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public TransactionData(String amount, String date, String notes) {
        Amount = amount;
        Date = date;
        Notes = notes;
    }
}
