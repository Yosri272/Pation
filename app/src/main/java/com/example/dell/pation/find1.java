package com.example.dell.pation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class find1 extends AppCompatActivity {
    ImageView img;
    TextView tx,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,r1,r2,r3,r4,r5,r6,l1,l2,l3,l4,l5,l6;
    String f,a,b,c,d,e,ch,ad;
    String a1,a2,a3;
    String x[]= new String[6];
    String r[]= new String[6];
    String l[]= new String[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find1);
        img =(ImageView) findViewById(R.id.imageView);
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
        /////////////////////////time left////////////////////////////////////

        l1 = (TextView) findViewById(R.id.l1);
        l2 = (TextView) findViewById(R.id.l2);
        l3 = (TextView) findViewById(R.id.l3);
        l4 = (TextView) findViewById(R.id.l4);
        l5 = (TextView) findViewById(R.id.l5);
        l6 = (TextView) findViewById(R.id.l6);


        /////////////////////////////

        f =getIntent().getExtras().getString("d_id");
        a =getIntent().getExtras().getString("name");
        b =getIntent().getExtras().getString("spe");
        c =getIntent().getExtras().getString("d_address");
        d =getIntent().getExtras().getString("d_balans");
        e =getIntent().getExtras().getString("d_mo");

        tx.setText(a);
        tx2.setText(b);
        tx3.setText(c);
        tx4.setText(d);
        tx5.setText(e);



        Glide.with(find1.this).load("http://192.168.43.34/hassan/yosri/img.php?d_name="+a.toString()).into(img);



        //////////////////

        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/time.php?d_id="+f.toString(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                    JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(s);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                a1 = jsonObject.getString("d_dayofwork");
                                a2 = jsonObject.getString("d_timeofwork");
                                a3 = jsonObject.getString("d_timeofwork1");
                                x[i]=a1;
                                r[i]=a2;
                                l[i]=a3;
                              }
                           ////////////////day in text//////////
                                 tx6.setText(x[0]);
                                 tx7.setText(x[1]);
                                 tx8.setText(x[2]);
                                 tx9.setText(x[3]);
                                 tx10.setText(x[4]);
                                 tx11.setText(x[5]);
                             ////////////////time right in text//////////
                                 r1.setText(r[0]);
                                 r2.setText(r[1]);
                                 r3.setText(r[2]);
                                 r4.setText(r[3]);
                                 r5.setText(r[4]);
                                 r6.setText(r[5]);
                            ////////////////time right in text//////////
                                 l1.setText(l[0]);
                                 l2.setText(l[1]);
                                 l3.setText(l[2]);
                                 l4.setText(l[3]);
                                 l5.setText(l[4]);
                                 l6.setText(l[5]);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Toast.makeText(find1.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });
        rq.add(sr);



        /////////////












    }

public void yos1(View view) {


    Toast.makeText(find1.this,a.toString(), Toast.LENGTH_SHORT).show();

    Toast.makeText(find1.this,ch.toString(), Toast.LENGTH_SHORT).show();
    RequestQueue rq = Volley.newRequestQueue(this);
    StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/queue.php?d_name="+a.toString() +"&day="+ch.toString(),

            new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {

                    if(s.matches("yes")){
                        Intent go = new Intent(find1.this,choose.class);
                        go.putExtra("name",a);
                        go.putExtra("day",ch);
                        startActivity(go);
                    }
                    else{
                        Toast.makeText(find1.this, "لايمكن الحجر", Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

            Toast.makeText(find1.this,"الخادم غير متصل", Toast.LENGTH_LONG).show();

        }
    });
    rq.add(sr);



}

    public void s1(View view) {



        ch="السبت";

    }

    public void s2(View view) {
        ch="الاحد";
    }

    public void s3(View view) {
        ch="الاثنين";
    }

    public void s4(View view) {
        ch="الثلاثاء";
    }

    public void s5(View view) {
        ch="الاربعاء";
    }

    public void s6(View view) {
        ch="الخميس";
    }



   /*         Intent go = new Intent(find1.this,choose.class);
      go.putExtra("name",a);
        startActivity(go);*/

}

