package com.alexlionne.apps.avatars.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18/03/16.
 */
public class ListItem extends ArrayList {


    private String title;
    private ArrayList<Item> list;
    private Item item;
    public ListItem() {
        this.list = new ArrayList<>();
    }

    public ListItem setTitle(String title) {
        this.title = title;

        return null;
    }

    public String getTitle() {
        return this.title;
    }
    public void addItem(Item item) {
        list.add(item);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    public ArrayList<Item> getList() {
        return this.list;
    }
    public Item getItem(int position){
        return this.list.get(position);
    }
}

