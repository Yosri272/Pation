package com.example.dell.pation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;

public class start extends AppCompatActivity {
    ImageView img1;
    //  EditText n;
    private Spinner spinner;
    private Spinner spinner2;
    EditText p;
    String a,a0,a2,a1,a3,a4;

    String n,n1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        p = (EditText) findViewById(R.id.p);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] con ={"جميع التخصصات","اسنان", "عظام", "أمراض جلدية","سكري","باطنية","مخ واعصاب"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, con);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                n=(String)spinner.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] con2 = {"جميع المدن","بحري", "امدرمان", "الخرطوم"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, con2);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             n1=(String) spinner2.getItemAtPosition(position);

                //Toast.makeText(start.this,n1, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent i = new Intent(this, login_pation.class);
                startActivity(i);
                return true;
            case R.id.add_pation:
                Intent j = new Intent(this, add_pation.class);
                startActivity(j);
                return true;
            case R.id.nuchoose:
                Intent l = new Intent(this, NUchoose.class);
                startActivity(l);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void btn111(View view) {

        if (p.length() == 0 & n.isEmpty() & n1.isEmpty()) {

            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
            return;
        }

       if (!(p.length() == 0 & n.isEmpty() & n1.isEmpty())) {


            RequestQueue rq = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/S.php?d_name=" + p.getText().toString() + "&d_spe=" + n.toString()+"&d_address="+ n1.toString(),

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(s);


                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                a = jsonObject.getString("d_id");
                                a0 = jsonObject.getString("d_name");
                                a2 = jsonObject.getString("d_spe");
                                a1 = jsonObject.getString("d_address");
                                a3 = jsonObject.getString("d_balans");
                                a4 = jsonObject.getString("d_mo");


                                Intent i = new Intent(start.this, find1.class);
                                i.putExtra("d_id",a);
                                i.putExtra("name",a0);
                                i.putExtra("spe", a2);
                                i.putExtra("d_address", a1);
                                i.putExtra("d_balans", a3);
                                i.putExtra("d_mo", a4);
                                startActivity(i);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {


                    Toast.makeText(start.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

                }
            });
            rq.add(sr);

      }
        if (n.isEmpty() || n1.isEmpty()) {

            Intent i = new Intent(this, Main3Activity.class);
            i.putExtra("spe",n);
            i.putExtra("d_address",n1);

            startActivity(i);
            return;
        }


        if (!(n.isEmpty() && n1.isEmpty())) {

            Intent i = new Intent(this, Main4Activity.class);
            i.putExtra("spe",n);
            i.putExtra("d_address",n1);

            startActivity(i);
            return;
        }

    }

}
