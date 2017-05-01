package com.example.saksham.contactlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewContact extends AppCompatActivity {



    private TextView tv;
    private TextView tv1;
    private de.hdodenhof.circleimageview.CircleImageView im;
    private TextView tv33;
    private TextView tv333;
    private TextView tv3333;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Intent intent = getIntent();
        final String co = intent.getStringExtra("val");

        tv = (TextView) findViewById(R.id.textView3);
        tv1 = (TextView) findViewById(R.id.textView);
        im = (CircleImageView) findViewById(R.id.imageView2);
        tv33 = (TextView) findViewById(R.id.textView33);
        tv333 = (TextView) findViewById(R.id.textView333);
        tv3333 = (TextView) findViewById(R.id.textView3333);

/*        String valid_until = "1/5/2017";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(valid_until);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().after(strDate)) {
            tv.setText("successful");
        }
        else tv.setText(("lol"));
*/


        DatabaseOperations DOP = new DatabaseOperations(this);
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();




    do {
        if(CR.getString(1).equals(co))
        {
            tv.setText(CR.getString(1));
            tv1.setText(CR.getString(0));
            tv33.setText((CR).getString(3));
            String lop = "Contact Expiry date -   ";
            if(CR.getString(5).equals("31/12/2500"))
            {
                tv333.setText(lop + "not provided");
            }
            else
            {
                tv333.setText(lop + (CR).getString(5));
            }
            tv3333.setText((CR).getString(4));
            byte[] ima = CR.getBlob(2);
            Bitmap abc = BitmapFactory.decodeByteArray(ima, 0, ima.length);
            im.setImageBitmap(abc);

        }
        //ar1.add(CR.getString(0));
        //ar2.add(CR.getString(1));
        //ar3.add(R.drawable.alex_action);
        //byte[] ima = CR.getBlob(2);
        //Bitmap abc = BitmapFactory.decodeByteArray(ima, 0, ima.length);
        //ar4.add(abc);
    } while(CR.moveToNext());


}


    public void callp(View view) {
//        int abd = Integer.parseInt(tv.getText().toString());
        String baba = tv.getText().toString();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + baba));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }


    public void emailp(View view)
    {
        String pill = tv33.getText().toString();
        String[] TO = {pill};
        String[] CC = {};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }


    public void deleteContact(View view)
    {
        DatabaseOperations DOP = new DatabaseOperations(this);
        String con = tv.getText().toString();
        DOP.deleteUser(DOP, con);

        Intent intent8 = new Intent(this,MainActivity.class);
        startActivity(intent8);
    }

}

