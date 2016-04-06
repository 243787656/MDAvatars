package com.alexlionne.apps.avatars.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.ListItem;
import com.squareup.picasso.Picasso;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private final ListItem itemData;
    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private static int fragmentPosition;




    public interface OnItemClickListener {
        void onItemClick(View v, int position,int fragmentPosition);
    }

    public CustomAdapter(Context context, ListItem items, OnItemClickListener onItemClickListener,int fragmentPosition) {
        CustomAdapter.fragmentPosition = fragmentPosition;
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


        ListItem list = itemData;
        Item i = list.getItem(position);

        if(i!=null) {

            holder.background_image.setVisibility(View.GONE);
            i.attachBackgroundImage(holder.background_image);
            holder.icon.setClickable(false);
            Drawable background = holder.color.getBackground();
            holder.text.setText(list.getItem(position).getName());
            i.attachTextView(holder.text);
            i.attachLayout(holder.content);
            i.attachCard(holder.card);
            SharedPreferences preferences = AvatarActivity.getActivity().getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);

            if(i.getDefaultCardColor()!=0){
                i.getCard().setCardBackgroundColor(preferences.getInt(i.getId(), 0));
                if(i.getIcon()!=null) {
                    i.setIconicsDrawable(i.getIcon().color(Utils.getAccentLightColor(preferences.getInt(i.getId(), 0))));
                }
                i.getTextView().setTextColor(Utils.getAccentLightColor(preferences.getInt(i.getId(),0)));


            }
            if(i.getDrawable()!=0){
                i.getBackgroundImage().setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(i.getDrawable())
                        .resize(500, 500)
                        .into(holder.background_image);
            }

            if (list.getLabel() != null) {
                holder.label.setVisibility(View.VISIBLE);
                holder.label.setText(list.getLabel().get(position));
            }
            if (i.getOptions().getCheckBox()) {
                holder.icon.setVisibility(View.VISIBLE);
                list.getItem(position).attachCheckBox(holder.icon);
            }
            if (i.getOptions().getState()) {
                holder.icon.setChecked(true);
            }

            if (i.getOptions().getColorChooser()) {
                list.getItem(position).attachImageView(holder.color);
                holder.color.setVisibility(View.VISIBLE);
                holder.color.setImageDrawable(list.getItem(position).getIcon());
            }
        }else{
            holder.card.setCardBackgroundColor(android.R.color.transparent);
            holder.card.setCardElevation(0);
            holder.card.setClickable(false);
            holder.card.setEnabled(false);
        }
    }



    public Item getItemAtPosition(int position) {
        return itemData.getItem(position);
    }
    public static int  getFragmentPosition(){
        return  CustomAdapter.fragmentPosition;
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final CheckBox icon;
        final TextView text,label;
        final ImageView color,background_image;
        final OnItemClickListener onItemClickListener;
        final RelativeLayout content;
        final CardView card;


        public ViewHolder(View v, OnItemClickListener onItemClickListener) {
            super(v);
            v.setClickable(true);
            v.setOnClickListener(this);
            icon = (CheckBox) v.findViewById(R.id.checkBox);
            text = (TextView) v.findViewById(R.id.text1);
            label = (TextView) v.findViewById(R.id.label);
            color = (ImageView) v.findViewById(R.id.color);
            content = (RelativeLayout) v.findViewById(R.id.content);
            card = (CardView) v.findViewById(R.id.card);
            background_image = (ImageView) v.findViewById(R.id.imageView2);
            this.onItemClickListener = onItemClickListener;


        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition(),getFragmentPosition());

        }



    }

}
