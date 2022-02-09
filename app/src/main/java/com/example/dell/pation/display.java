package com.example.dell.pation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class display extends AppCompatActivity {
    ImageView img;

    TextView tx,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,r1,r2,r3,r4,r5,r6,l1,l2,l3,l4,l5,l6;
    String a1,a2,a3,ch;
    String to,to1,to2,to3,to4,to5;
    String x[]= new String[6];
    String r[]= new String[6];
    String l[]= new String[6];
    String time,time1,tim;
    String muu,muuu,m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        img =(ImageView) findViewById(R.id.imageView);
        tx = (TextView) findViewById(R.id.textView);
        tx2 = (TextView) findViewById(R.id.textView2);
        tx3 = (TextView) findViewById(R.id.textView3);
        tx4 = (TextView) findViewById(R.id.textView4);
        tx5 = (TextView) findViewById(R.id.textView5);
////////////////////////////////////
        Bundle bundle= getIntent().getExtras();
        to=   bundle.getString("d_id");
        to1 = bundle.getString("d_name");
        to2=  bundle.getString("d_spe");

        to3=  bundle.getString("d_address");
        to4=  bundle.getString("d_balans");
        to5=  bundle.getString("d_mo");
        tx.setText(to);
        tx2.setText(to1);
        tx3.setText(to2);
        tx4.setText(to3);
        tx5.setText(to4);

        ///////////////////////////

        Glide.with(display.this).load("http://192.168.43.34/hassan/yosri/img.php?d_name="+to.toString()).into(img);
        ////////////////



        Toast.makeText(display.this, to5, Toast.LENGTH_LONG).show();
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
        //////////////////
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET,"http://192.168.43.34/hassan/time.php?d_id="+to5.toString(),

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


                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });
        rq.add(sr);




    }

    public void yos2(View view) {
        /////////////////////
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/queue.php?d_name=" + to.toString() + "&day=" + ch.toString(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {
                            Intent go = new Intent(display.this, choose.class);
                            go.putExtra("name", to);
                            go.putExtra("day", ch);
                            go.putExtra("muu",m);
                            startActivity(go);
                        } else {
                            Toast.makeText(display.this, "لايمكن الحجر", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });
        rq.add(sr);

    }
    public void ss1(View view) {
        ch = "السبت";
///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);
////////////////////////

    }

    public void ss2(View view) {
        ch="الاحد";

        ///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);

    }
    public void ss3(View view) {
        ch="الاثنين";
        ///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);

    }

    public void ss4(View view) {

        ch="الثلاثاء";

        ///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);

    }

    public void ss5(View view) {
        ch="الاربعاء";
        ///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);
    }

    public void ss6(View view) {
        ch="الخميس";
        ///////////
        RequestQueue rq11 = Volley.newRequestQueue(this);
        StringRequest str1 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/gettime.php?d_name=" + to.toString()  + "&day=" + ch.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if (s.matches("yes")) {

                            RequestQueue rq1 = Volley.newRequestQueue(display.this);
                            StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess.php?d_id=" + to5.toString()  + "&d_dayofwork=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    m = jsonObject.getString("d_timeofwork");
                                                    //Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();

                                                }


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq1.add(str);


                        } else {
                            RequestQueue rq2 = Volley.newRequestQueue(display.this);
                            StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/hassan/timess1.php?d_name=" + to.toString() + "&day=" + ch.toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {

                                            try {


                                                JSONArray jsonArray = new JSONArray(s);

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    muu = jsonObject.getString("time");


                                                }
                                                //      Toast.makeText(display.this, muu, Toast.LENGTH_SHORT).show();

                                                String mon = muu.toString();
                                                SimpleDateFormat dateFormate = new SimpleDateFormat("hh:mm:ss");
                                                Date da = dateFormate.parse(mon);
                                                da.setSeconds(1800);
                                                m = new SimpleDateFormat("hh:mm:ss").format(da.getTime());
                                                //    System.out.print(muu);
                                                //  Toast.makeText(display.this, m, Toast.LENGTH_SHORT).show();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rq2.add(str2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(display.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

            }
        });

        rq11.add(str1);

    }
}

