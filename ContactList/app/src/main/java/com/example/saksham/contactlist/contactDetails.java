package com.example.saksham.contactlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class contactDetails extends AppCompatActivity {


    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private de.hdodenhof.circleimageview.CircleImageView img;
    private static final int SELECT_PHOTO = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details2);


        img = (CircleImageView) findViewById(R.id.imageView);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void add(View view) {

        String nameIs = et1.getText().toString();
        String contactIs = et2.getText().toString();
        String emailIs = et3.getText().toString();
        String descriptionIs = et4.getText().toString();
        String expiryIs = et5.getText().toString();
        int t = 0;
        if(expiryIs.equals("0"))
        {
            t=1;
        }
        DatabaseOperations DB = new DatabaseOperations(this);


        Calendar c = Calendar.getInstance();
        Calendar cTotal = (Calendar) c.clone();
        cTotal.add(Calendar.DATE, Integer.parseInt(expiryIs));
        int year1 = cTotal.get(Calendar.YEAR);
        int month1 = cTotal.get(Calendar.MONTH);
        int date1 = cTotal.get(Calendar.DATE);
        int hour1 = cTotal.get(Calendar.HOUR);
        int minute1 = cTotal.get(Calendar.MINUTE);
        int second1 = cTotal.get(Calendar.SECOND);

//        String bolIs =  year1 + "-" + month1 + "-" + date1 + " " + hour1 + ":" + minute1 + ":" + second1;


        String bolIs = "";

        if(t==0)
        {
            bolIs = date1 + "/" + month1 + "/" + year1;
        }
        else if(t==1)
        {
           bolIs = "31/12/2500";
        }

        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] arrIs = stream.toByteArray();


        DB.putInformation(DB, nameIs, contactIs, emailIs, descriptionIs, bolIs, arrIs);

        Intent intent10 = new Intent(this, MainActivity.class);
        startActivity(intent10);
    }

    public void upl(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);

                    img.setImageBitmap(yourSelectedImage);
                }
        }


    }

}


