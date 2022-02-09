package com.example.dell.pation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class choose extends AppCompatActivity {
    EditText name,id_bank;
    Button sendBtn;
    EditText txtphoneNo;
    String txtMessage;
    String a,b,hh,ch,m,time1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        name = (EditText) findViewById(R.id.yo1);
        id_bank = (EditText) findViewById(R.id.yo2);
        a =getIntent().getExtras().getString("name");
        ch =getIntent().getExtras().getString("day");
        m =getIntent().getExtras().getString("muu");
      Toast.makeText(choose.this,m.toString(),Toast.LENGTH_LONG).show();

        b =getIntent().getExtras().getString("name1");
       Toast.makeText(choose.this,b,Toast.LENGTH_LONG).show();

        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        txtMessage = hh;
    }

    public void y2(View view) {
        //////////////////////////Random//////////////////////////

        Random randomGenerator = new Random();
        StringBuilder hg=new StringBuilder();

        for (int i = 1; i <= 10; i++){

            int   n=randomGenerator.nextInt(9);

            hg.append(n) ;
        }
        hh=hg.toString();
        txtMessage = hh;

        /////////////////////////Check fields/////////
        if (name.length()==0){
            Toast.makeText(choose.this,"الرجاء ادخال الأسم",Toast.LENGTH_LONG).show();
            return;
        }
        if (name.length()<2){
            Toast.makeText(choose.this,"الاسم قصير",Toast.LENGTH_LONG).show();
            return;
        }

//////////////////////////////////database////////////////////////
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/choose.php?name="+name.getText().toString()+"&d_num="+id_bank.getText().toString()+"&d_name="+a+"&teket="+hh+"&day="+ch+"&muu="+m,

                 new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if(s.matches("ok")){


                            AlertDialog.Builder builder=new AlertDialog.Builder(choose.this);
                            builder.setCancelable(false);
                            builder.setTitle("كود الحجز هو");
                            builder.setMessage(hh);


                            builder.setPositiveButton("ok!!!",new DialogInterface.OnClickListener(){



                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sendSMSMessage();


                                    Toast.makeText(choose.this, " تم الحجز", Toast.LENGTH_LONG).show();

                                }
                            });




                            builder.create().show();

                        }

                        else{
                            Toast.makeText(choose.this, "هنالك خطأ في الحجز", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(choose.this,"الخادم غير متصل", Toast.LENGTH_LONG).show();
                //dialog.cancel();
            }
        });
        rq.add(sr);
    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.toString()+"زمن الحجز هو"+m.toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



}

