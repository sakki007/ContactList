package com.example.saksham.contactlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by saksham on 30/4/17.
 */


public class Adapter extends ArrayAdapter<String>
{

    //    int [] imgs={};
//    String[] names = {};
//    String[] contact = {};
    Context c;
    LayoutInflater inflater;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> contact = new ArrayList<String>();
    //    ArrayList<Integer> imgs = new ArrayList<Integer>();
    ArrayList<Bitmap> imgs = new ArrayList<Bitmap>();
    ArrayList<String> email = new ArrayList<String>();


    public Adapter(Context context, ArrayList<String> contact, ArrayList<String> names, ArrayList<Bitmap> imgs, ArrayList<String> email)
    {
        super(context, R.layout.filevalues,names);

        this.c = context;
        this.names = names;
        this.contact = contact;
        this.imgs = imgs;
        this.email = email;
    }


    public class ViewHolder
    {
        ImageView imgYup;
        TextView namesYup;
        TextView contactYup;
        TextView emailYup;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView==null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.filevalues,null);
        }

        final ViewHolder holder = new ViewHolder();

        holder.namesYup = (TextView) convertView.findViewById(R.id.namesYeah);
        holder.contactYup = (TextView) convertView.findViewById(R.id.contactYeah);
        holder.imgYup = (CircleImageView) convertView.findViewById(R.id.image1);
        holder.emailYup = (TextView) convertView.findViewById(R.id.emailYeah);

        //     holder.imgYup.setImageResource(imgs[position]);
        //    holder.namesYup.setText(names[position]);
        holder.namesYup.setText(names.get(position));
        // holder.contactYup.setText(contact[position]);
        holder.contactYup.setText(contact.get(position));
//        holder.imgYup.setImageResource(imgs.get(position));

//        Byte[] pis = imgs.get(position);
        //       Bitmap bitmaps = BitmapFactory.decodeByteArray(pis, 0, pis.length);
        holder.imgYup.setImageBitmap(imgs.get(position));

        holder.emailYup.setText(email.get(position));

//        return super.getView(position, convertView, parent);
        return convertView;
    }
}
