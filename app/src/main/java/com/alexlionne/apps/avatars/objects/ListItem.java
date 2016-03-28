package com.alexlionne.apps.avatars.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18/03/16.
 */
public class ListItem extends ArrayList {


    private String title;
    private ArrayList<Item> list;
    private ArrayList<String> listlabel;
    private Item item;


    public ListItem() {
        this.list = new ArrayList<>();
        this.listlabel = new ArrayList<>();
    }

    public ListItem setTitle(String title) {
        this.title = title;

        return null;
    }

    public String getTitle() {
        return this.title;
    }
    public void addItem(String label,Item item) {
        list.add(item);
        listlabel.add(label);
    }

    public void removeItem(int position){
        list.remove(position);
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
    public ArrayList<String> getLabel(){
        return this.listlabel;
    }
}

