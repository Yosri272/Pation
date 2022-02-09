package com.example.dell.pation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NUchoose extends AppCompatActivity {
    EditText d_name,d_num,teket;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuchoose);
        d_name = (EditText) findViewById(R.id.nu1);

        d_num = (EditText) findViewById(R.id.nu2);

        teket = (EditText) findViewById(R.id.nu3);

    }

    public void y2(View view) {
/////////////////////////

        if (d_name.length()==0) {
            Toast.makeText(NUchoose.this, "الرجاء ادخال الأسم الطبيب", Toast.LENGTH_LONG).show();
            return;

        }

        if (teket.length()<2){
            Toast.makeText(NUchoose.this,"رقم التزكرة غير صحيح",Toast.LENGTH_LONG).show();
            return;
        }



////////
        dialog = ProgressDialog.show(NUchoose.this, "", "تسجيل دخول", true);


        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/nuchoose.php?d_name="+d_name.getText().toString()+"&d_num="+d_num.getText().toString()+"&teket="+teket.getText().toString(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if(s.matches("ok")){
                            Toast.makeText(NUchoose.this, " تم الغاء الحجز", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                            Intent a=new Intent(NUchoose.this,bagin.class);
                            startActivity(a);
                        }
                        else{
                            Toast.makeText(NUchoose.this, "هنالك خطأ في الغاء الحجز", Toast.LENGTH_LONG).show();
                            dialog.cancel();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(NUchoose.this,"الخادم غير متصل", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        rq.add(sr);
    }

}










