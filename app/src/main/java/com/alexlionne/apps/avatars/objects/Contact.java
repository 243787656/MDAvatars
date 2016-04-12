package com.alexlionne.apps.avatars.objects;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by root on 12/04/16.
 */
public class Contact {
    private Context context;
    private Bitmap picture;
    private String name;
    private Bitmap bitmap;
    public Contact(){

    }
    public Contact(Context context){
        this.context = context;
    }
    public Contact(String name){
        this.name = name;
    }
    public void setContext(Context context){
        this.context = context;
    }

public String getName(){
    return this.name;
}
    public Bitmap getPicture(){
        return this.bitmap;
    }
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact>contact = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);


        if (cur.getCount() > 0)
        {

            while (cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {

                            Contact people = new Contact(name);
                            contact.add(people);

            }
            }
            cur.close();
    }

        return contact;}
}
