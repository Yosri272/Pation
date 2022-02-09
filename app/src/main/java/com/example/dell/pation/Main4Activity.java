package com.example.dell.pation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main4Activity extends AppCompatActivity {

    private List<doctor> doctorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private doctoradapter2 madapter;
    android.view.View ChildView;
    String aa[]= new String[100];
    String bb[]= new String[100];
    String cc[]= new String[100];
    String dd[]= new String[100];
    String ee[]= new String[100];
    String ff[]= new String[100];
    int RecyclerViewItemPosition;
    String time,a,b,a0;

    ArrayList<HashMap<String, String>> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = (RecyclerView) findViewById(R.id.recitem3);
        a =getIntent().getExtras().getString("spe");
        b =getIntent().getExtras().getString("d_address");

        madapter = new doctoradapter2(doctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(madapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(Main4Activity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);


                    //   Toast.makeText(Main2Activity.this,  ""+RecyclerViewItemPosition, Toast.LENGTH_LONG).show();

                    //Toast.makeText(getActivity(), (CharSequence) viewList.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main4Activity.this,display.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("d_id",aa[RecyclerViewItemPosition]);
                    bundle.putString("d_name",bb[RecyclerViewItemPosition]);
                    bundle.putString("d_spe",cc[RecyclerViewItemPosition]);
                    bundle.putString("d_address",dd[RecyclerViewItemPosition]);
                    bundle.putString("d_balans",ee[RecyclerViewItemPosition]);
                    bundle.putString("d_mo",ff[RecyclerViewItemPosition]);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }


            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        showList();
    }
    protected void showList() {


        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/S2.php?d_spe=" + a.toString() +"&d_address="+b.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {


                            JSONArray jsonArray = new JSONArray(s);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                time = jsonObject.getString("d_id");
                                String a0 = jsonObject.getString("d_name");
                                String a2 = jsonObject.getString("d_spe");
                                String a1 = jsonObject.getString("d_address");
                                String a3 = jsonObject.getString("d_balans");
                                String a4 = jsonObject.getString("d_mo");

                                doctor doctor = new doctor(a0, a2, a1, a3, a4);
                                doctorList.add(doctor);
                                ff[i]=time;
                                aa[i]=a0;
                                bb[i]=a1;
                                cc[i]=a2;
                                dd[i]=a3;
                                ee[i]=a4;

                            }
                            Toast.makeText(Main4Activity.this, time.toString(), Toast.LENGTH_SHORT).show();

                            madapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Main4Activity.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(str);

    }

    public void booked2(View view) {



      Intent x = new Intent(Main4Activity.this,choose.class);
        x.putExtra("name2", a0);
        startActivity(x);

    }
}