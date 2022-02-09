package com.example.dell.pation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class find2 extends AppCompatActivity {
    ImageView img;
    TextView tx,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,r1,r2,r3,r4,r5,r6,l1,l2,l3,l4,l5,l6;
    String h1[]=new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find2);


       //startActivity(new Intent(this, Main3Activity.class));







        ///////////////////information///////////////////////////
        img = (ImageView) findViewById(R.id.imageView);
        tx = (TextView) findViewById(R.id.textView);
        tx2 = (TextView) findViewById(R.id.textView2);
        tx3 = (TextView) findViewById(R.id.textView3);
        tx4 = (TextView) findViewById(R.id.textView4);
        tx5 = (TextView) findViewById(R.id.textView5);

        /////////////////////days of work////////////////////////
        tx6 = (TextView) findViewById(R.id.textView6);
        tx7 = (TextView) findViewById(R.id.textView7);
        tx8 = (TextView) findViewById(R.id.textView8);
        tx9 = (TextView) findViewById(R.id.textView9);
        tx10 = (TextView) findViewById(R.id.textView10);
        tx11 = (TextView) findViewById(R.id.textView11);
///////////////////////////time right///////////////////////////////
        r1 = (TextView) findViewById(R.id.r1);
        r2 = (TextView) findViewById(R.id.r2);
        r3 = (TextView) findViewById(R.id.r3);
        r4 = (TextView) findViewById(R.id.r4);
        r5 = (TextView) findViewById(R.id.r5);
        r6 = (TextView) findViewById(R.id.r6);
        /////////////////////////time left/////////////////////////

        l1 = (TextView) findViewById(R.id.l1);
        l2 = (TextView) findViewById(R.id.l2);
        l3 = (TextView) findViewById(R.id.l3);
        l4 = (TextView) findViewById(R.id.l4);
        l5 = (TextView) findViewById(R.id.l5);
        l6 = (TextView) findViewById(R.id.l6);


        /////////////////////////////
      //  String time =getIntent().getExtras().getString("key");
    //    Toast.makeText(find2.this,""+ h1.toString(), Toast.LENGTH_SHORT).show();



    }



}
