package com.alexlionne.apps.avatars.adapters;

import android.content.Context;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Contact;
import com.alexlionne.apps.avatars.objects.Kit;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ContactsAdapters extends RecyclerView.Adapter<ContactsAdapters.ViewHolder> {

    private final ArrayList<Contact> itemData;
    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private int lastPosition = -1;
    private CheckBox checkbox;


    public interface OnItemClickListener {
        void onItemClick(int position) throws RemoteException, OperationApplicationException, IOException;
    }

    public ContactsAdapters(Context context, ArrayList<Contact> items, OnItemClickListener onItemClickListener) {
        this.itemData = items;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setCheckbox(holder.checkBox);
        Contact contact = itemData.get(position);
        holder.name.setText(contact.getName());
        /*if(contact.getPicture()!=null) {
            Picasso.with(context).load(loadPic(contact.getPicture())).into(holder.icon);
        }*/

    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    public Contact getItemAtPosition(int position) {
        return itemData.get(position);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView icon;
        final TextView name;
        final OnItemClickListener onItemClickListener;
        final CheckBox checkBox;

        public ViewHolder(View v, OnItemClickListener onItemClickListener) {
            super(v);
            v.setClickable(true);
            v.setOnClickListener(this);
            icon = (ImageView) v.findViewById(R.id.contact_icon);
            name = (TextView) v.findViewById(R.id.contact_name);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);

            this.onItemClickListener = onItemClickListener;


        }

        @Override
        public void onClick(View v) {
            try {
                onItemClickListener.onItemClick(getAdapterPosition());
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public void setCheckbox(CheckBox checkbox){
         this.checkbox = checkbox;
    }
    public CheckBox getCheckbox(){
        return this.checkbox;
    }
    private File loadPic(Bitmap bitmap){
        try {
        //create a file to write bitmap data
        File f = new File(context.getCacheDir(), "tmp");

            f.createNewFile();



        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
            return f;
        } catch (IOException e) {
            e.printStackTrace();
        }
   return null;}

}
