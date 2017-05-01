package com.example.saksham.contactlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    //    String[] names = {"vohra","amla","marsh","maxwell","miller"};
    ArrayList<String> ar1 = new ArrayList<String>();
    ArrayList<String> ar2 = new ArrayList<String>();
    ArrayList<String> ar5 = new ArrayList<String>();

//    String[] contact = {"89311","59600","90999","91022","98339"};
//    int[] images = {R.drawable.alex_action, R.drawable.imageempty, R.drawable.images2, R.drawable.images3, R.drawable.alex_action};

    ArrayList<Integer> ar3 = new ArrayList<Integer>();

    ArrayList<Bitmap> ar4 = new ArrayList<Bitmap>();

    private TextView tv4;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        lv = (ListView) findViewById(R.id.listView1);

/*        ar1.add("vohra");
        ar1.add("amla");
        ar1.add("marsh");
        // ar1.add("maxwell");
        //ar1.add("miller");

        ar2.add("55555");
        ar2.add("79594");
        ar2.add("91921");
        //  ar2.add("09101");
        // ar2.add("54345");

        ar3.add(R.drawable.alex_action);
        ar3.add(R.drawable.imageempty);
        ar3.add(R.drawable.images2);
        //     ar3.add(R.drawable.images3);
        //   ar3.add(R.drawable.alex_action);

*/


        DatabaseOperations DB = new DatabaseOperations(this);
    //    DB.onUpgrade(SQLiteDatabase db, , int 2);

        Adapter adapter = new Adapter(this,ar1,ar2,ar4,ar5);
        lv.setAdapter(adapter);

        DatabaseOperations DOP = new DatabaseOperations(this);
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = new Date(System.currentTimeMillis());

try {
    while (CR.moveToNext()) {
        Calendar c1 = Calendar.getInstance();
        Calendar cTotal1 = (Calendar) c1.clone();
        int year1 = cTotal1.get(Calendar.YEAR);
        int month1 = cTotal1.get(Calendar.MONTH);
        int date1 = cTotal1.get(Calendar.DATE);

        String imm2 = date1 + "/" + month1 + "/" + year1;
        String imm1 = CR.getString(5);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date da1 = new Date();
        Date da2 = new Date();

        try {
            da1 = formatter.parse(imm1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            da2 = formatter.parse(imm2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (da1.compareTo(da2) < 0) {
            String con = CR.getString(1);
            DOP.deleteUser(DOP, con);
        }
    }
    }
catch(Exception e)
{

}

        DatabaseOperations DOP1 = new DatabaseOperations(this);
        Cursor CR1 = DOP1.getInformation(DOP1);
        CR1.moveToFirst();


 /*       ar1.add(CR1.getString(0));
        ar2.add(CR1.getString(1));
        ar3.add(R.drawable.alex_action);
        ar5.add(CR1.getString(3));

        byte[] ima = CR1.getBlob(2);
        Bitmap abc = BitmapFactory.decodeByteArray(ima, 0, ima.length);

        ar4.add(abc);
*/
        while(CR1.moveToNext())
        {
            ar1.add(CR1.getString(0));
            ar2.add(CR1.getString(1));
            ar3.add(R.drawable.alex_action);
            ar5.add(CR1.getString(3));

            byte[] ima = CR1.getBlob(2);
            Bitmap abc = BitmapFactory.decodeByteArray(ima, 0, ima.length);

            ar4.add(abc);

        }



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                String value = item.toString();
                boy(value);
            }
        });

    }


    public void viewContactDetails(View view)
    {
       Intent intent = new Intent(this,contactDetails.class);
//        Intent intent = new Intent(this,viewContact.class);

        startActivity(intent);
    }

    public void boy(String co)
    {
        Intent intent90 = new Intent(this,viewContact.class);
        intent90.putExtra("val",co);
        startActivity(intent90);
    }
}

