package com.alexlionne.apps.avatars.objects;

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
    private String image_uri = "";
    private Bitmap bitmap = null;
    private Bitmap picture;
    private String name;
    public Contact(){

    }
    public Contact(Context context){
        this.context = context;
    }
    public Contact(String name, Bitmap picture){
        this.name = name;
        this.picture = picture;
    }
    public void setContext(Context context){
        this.context = context;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact>contact = new ArrayList<>();
        Cursor phones = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);
        assert phones != null;
        while (phones.moveToNext())
        {
            try {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            image_uri=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            if (image_uri != null) {
                    bitmap = MediaStore.Images.Media
                            .getBitmap(context.getContentResolver(),
                                    Uri.parse(image_uri));

                    Contact people = new Contact(name,bitmap);
                    contact.add(people);
                }} catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


        }

        phones.close();
        return contact;}
public String getName(){
    return this.name;
}
    public Bitmap getPicture(){
        return this.bitmap;
    }
}
