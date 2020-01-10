package com.scudderapps.muneem.Model;

public class CategoryData {

    String Name, Type;
    int Color;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public CategoryData(String name, String type, int color) {
        Name = name;
        Type = type;
        Color = color;
    }
}
