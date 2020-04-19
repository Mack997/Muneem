package com.scudderapps.muneem.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryData {

    @NonNull
    @PrimaryKey
    private String Id;
    private String Name;
    private String Type;
    private int Color;
    private int edited = 0;

    public int getEdited() {
        return edited;
    }

    public void setEdited(int edited) {
        this.edited = edited;
    }

    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }
}
