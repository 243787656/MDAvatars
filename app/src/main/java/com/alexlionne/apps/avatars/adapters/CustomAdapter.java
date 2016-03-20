package com.alexlionne.apps.avatars.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Bubble;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private final ListItem itemData;
    private final Context context;
    private final OnItemClickListener onItemClickListener;




    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public CustomAdapter(Context context, ListItem items, OnItemClickListener onItemClickListener) {
        this.itemData = items;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Drawable background = holder.color.getBackground();


        ListItem list = itemData;
        holder.text.setText(list.getItem(position).getName());
        boolean checkbox = list.getItem(position).getCheckbox();
        boolean color = list.getItem(position).getColorchooser();
        boolean state = list.getItem(position).isEnable();
        if (checkbox) {
            holder.icon.setVisibility(View.VISIBLE);
        }
        if(state){
            holder.icon.setChecked(true);
        }
        if (color) {
            list.getItem(position).attachImageView(holder.color);
            holder.color.setVisibility(View.VISIBLE);
            if (background instanceof ShapeDrawable) {
                ((ShapeDrawable) background).getPaint().setColor(list.getItem(position).getBubble().getColor());
            } else if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setColor(list.getItem(position).getBubble().getColor());
            } else if (background instanceof ColorDrawable) {
                ((ColorDrawable) background).setColor(list.getItem(position).getBubble().getColor());
            }

        }
    }



    public Item getItemAtPosition(int position) {
        return itemData.getItem(position);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final CheckBox icon;
        final TextView text;
        final ImageView color;
        final OnItemClickListener onItemClickListener;
        final RelativeLayout content;


        public ViewHolder(View v, OnItemClickListener onItemClickListener) {
            super(v);
            v.setClickable(true);
            v.setOnClickListener(this);
            icon = (CheckBox) v.findViewById(R.id.checkBox);
            text = (TextView) v.findViewById(R.id.text1);
            color = (ImageView) v.findViewById(R.id.color);
            content = (RelativeLayout) v.findViewById(R.id.content);
            this.onItemClickListener = onItemClickListener;


        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }



    }

}
