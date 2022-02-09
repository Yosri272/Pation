package com.example.dell.pation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class add_pation extends AppCompatActivity {
    EditText p_username;
    EditText p_name;
    EditText p_phone;
    EditText p_barthday;
    EditText p_password;
    EditText p_password1;
    EditText e_mail;
    Button ins;
    ProgressDialog dialog = null;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pation);
        p_name = (EditText) findViewById(R.id.yo1);
        p_username = (EditText) findViewById(R.id.d2);
        e_mail = (EditText) findViewById(R.id.p3);
        p_password = (EditText) findViewById(R.id.d3);
        p_password1 = (EditText) findViewById(R.id.m1);
        p_phone = (EditText) findViewById(R.id.d6);
        p_barthday = (EditText) findViewById(R.id.p8);
        ins = (Button) findViewById(R.id.p5);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);

    }

    public void but11(View view) {

        /////////////////////////Check fields/////////
        if (p_name.length()==0){
            Toast.makeText(add_pation.this,"الرجاء ادخال الأسم",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_name.length()<2){
            Toast.makeText(add_pation.this,"الاسم قصير",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_username.length()==0){
            Toast.makeText(add_pation.this,"الرجاء ادخال أسم المستخدم",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_username.length()<4){
            Toast.makeText(add_pation.this,"اسم المستخدم قصير",Toast.LENGTH_LONG).show();
            return;
        }

        if (p_phone.length()!=10){
            Toast.makeText(add_pation.this,"رقم الهاتف غير صحيح",Toast.LENGTH_LONG).show();
            return;
        }
        if (e_mail.length()==0){
            Toast.makeText(add_pation.this,"الرجاء ادخال البريد الألكتروني",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_password.length()==0){
            Toast.makeText(add_pation.this,"الرجاء ادخال كلمة المرور",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_password.length()<4){
            Toast.makeText(add_pation.this,"كلمة المرور قصيرة",Toast.LENGTH_LONG).show();
            return;
        }
        if(!((p_password.getText().toString()).equals(p_password1.getText().toString()))) {

            Toast.makeText(add_pation.this,"كلمة المرور غير مطابقة",Toast.LENGTH_LONG).show();
            return;
        }
        ////////////////////////add in database////////////
        dialog = ProgressDialog.show(add_pation.this, "", "اضافة مستخدم جديد", true);

        RequestQueue rq = Volley.newRequestQueue(add_pation.this);
        StringRequest sr = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/p_insert.php?p_username=" + p_username.getText().toString() + "&p_name=" + p_name.getText().toString() + "&e_mail=" + e_mail.getText().toString() + "&p_password=" + p_password.getText().toString() + "&p_phone=" + p_phone.getText().toString() + "&p_barthday=" + p_barthday.getText().toString()+"&p_gender="+str,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(add_pation.this, s, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(add_pation.this,"الخادم غير متصل", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        rq.add(sr);

    }

    public void fathi1(View view) {

        str="ذكر";

    }

    public void fathi(View view) {
        str="انثي";

    }
}

