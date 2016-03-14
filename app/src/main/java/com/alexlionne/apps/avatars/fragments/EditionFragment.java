package com.alexlionne.apps.avatars.fragments;

/**
 * Created by Alex on 09/03/2016.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;

import java.util.ArrayList;

public class EditionFragment extends Fragment {
    protected View mView;
    protected View view;
    public static ListView listView;
    private TextView textView;
    private ArrayList<ArrayList<String>> list;
    private String title;
    private int position;
    private ArrayList<AdapterView.OnItemClickListener> listener;
    private int progress;


public EditionFragment(){

}
    public void setPosition(int pos){
        this.position = pos;
    }
    public int getPosition(){
        return this.position;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setList(ArrayList list){
        this.list = list;
    }
    public ArrayList getList(){
        return this.list;
    }
    public ListView getListView(){
        return listView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edition,
                container, false);

        listView = (ListView) view.findViewById(R.id.list);
        textView = (TextView) view.findViewById(R.id.text_title);
        listView.setDividerHeight(0);
        textView.setText(title);

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(listener.get(position));

        return view;
    }
    public void setListener(ArrayList<AdapterView.OnItemClickListener> listener){
        this.listener = listener;
    }
    public AdapterView.OnItemClickListener getListener(int position){
        return listener.get(position);

    }
    public String getTitle(){
        return title;
    }
    public void setProgress(int progress){
        this.progress = progress;
    }
    public int getProgress(){
        return this.progress;
    }




}
