package com.example.dell.pation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class login_pation extends AppCompatActivity {
    EditText p_username, p_password;
    Button log;
    ProgressDialog dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pation);
        p_username = (EditText) findViewById(R.id.user);
        p_password = (EditText) findViewById(R.id.pass);
        log =(Button)findViewById(R.id.login);


    }


    public void but17(View view) {
///////check fields///////
        if (p_username.length()==0){
            Toast.makeText(login_pation.this,"الرجاء ادخال أسم المستخدم",Toast.LENGTH_LONG).show();
            return;
        }
        if (p_password.length()==0){
            Toast.makeText(login_pation.this,"الرجاء ادخال كلمة المرور",Toast.LENGTH_LONG).show();
            return;
        }

///////////////////////////////////////////check in database/////////////


        dialog = ProgressDialog.show(login_pation.this, "", "تسجيل دخول", true);

        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/p_sync.php?p_username="+p_username.getText().toString()+"&p_password="+p_password.getText().toString(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        dialog.cancel();
                        if(s.matches("login")){
                            Intent go = new Intent(login_pation.this,hello_patin.class);
                            startActivity(go);
                        }
                        else{
                            Toast.makeText(login_pation.this, "هنالك خطأ في اسم المستخدم او كلمة المرور", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(login_pation.this,"الخادم غير متصل", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        rq.add(sr);



    }
}
