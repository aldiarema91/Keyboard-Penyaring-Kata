package com.imgncreative.keyboardsehat;

import java.util.ArrayList;

public class ExpandGroup {

    private String Name;
    private ArrayList<ExpandChild> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<ExpandChild> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ExpandChild> Items) {
        this.Items = Items;
    }

}
