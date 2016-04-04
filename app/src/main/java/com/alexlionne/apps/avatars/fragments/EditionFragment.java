package com.alexlionne.apps.avatars.fragments;

/**
 * Created by Alex on 09/03/2016.
 */

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.adapters.CustomAdapter;
import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;

import java.util.ArrayList;

public class EditionFragment extends Fragment implements CustomAdapter.OnItemClickListener{
    protected View view;
    private TextView textView;
    private ArrayList<ListItem> list;
    private String title;
    private int position;
    private ArrayList<CustomAdapter.OnItemClickListener> listener;
    private int progress;
    private int mColumnCount;
    private int numColumns = 1;
    private static int DEFAULT_COLUMNS_PORTRAIT;
    private static int DEFAULT_COLUMNS_LANDSCAPE;
    public static RecyclerView recyclerView;
    private CustomAdapter kitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kit> kits;
    private ArrayList<Kit> kits_result;

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
    public void setList(ArrayList<ListItem> list){
        this.list = list;
    }
    public ArrayList getList(){
        return this.list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edition,
                container, false);
        UI();

        //textView = (TextView) view.findViewById(R.id.text_title);
        //textView.setText(title);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        setRecyclerView(recyclerView);
        //recylerViewChanged();
        kitAdapter = null;
        DEFAULT_COLUMNS_PORTRAIT = 2;
        DEFAULT_COLUMNS_LANDSCAPE = 2;
        final boolean isLandscape = isLandscape();
        int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
        int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }

        return view;
    }
    public void setListener(ArrayList<CustomAdapter.OnItemClickListener> listener){
        this.listener = listener;
    }
    public  CustomAdapter.OnItemClickListener getListener(int position){
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






    public void UI() {


        class UpdateUI extends AsyncTask<Void, Void, Void> {

            @Override
            public void onPreExecute() {
            }

            @Override
            protected Void doInBackground(Void... params) {

                list = AvatarActivity.getKit().getAllcategories();



                return null;
            }

            @Override
            protected void onPostExecute(Void args) {

                layoutManager = new GridLayoutManager(getActivity(), numColumns);
                kitAdapter = new CustomAdapter(getActivity(), list.get(position), listener.get(position),position);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(kitAdapter);
                kitAdapter.notifyDataSetChanged();
            }
        }
        new UpdateUI().execute();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


    @Override
    public void onItemClick(View v, int position,int fragp) {
        Item item = ((CustomAdapter) recyclerView.getAdapter()).getItemAtPosition(position);

    }
    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }
    public RecyclerView getRecyclerView(){
        return  recyclerView;
    }
    private void recylerViewChanged() {

        getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {


            int mPosition = 0;
            int mOffset = 0;

            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                // TODO Auto-generated method stub
                GridLayoutManager layoutManager = ((GridLayoutManager) view.getLayoutManager());
                int position = layoutManager.findFirstVisibleItemPosition();

                View v = getRecyclerView().getChildAt(0);
                int offset = (v == null) ? 0 : v.getTop();

                if (mPosition < position || (mPosition == position && mOffset < offset)) {
                    AvatarActivity.view.setVisibility(View.GONE);

                } else {
                    // Scrolled down
                    AvatarActivity.view.setVisibility(View.VISIBLE);

                }
            }
        });
    }



}
