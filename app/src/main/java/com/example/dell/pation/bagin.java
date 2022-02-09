package com.example.dell.pation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class bagin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;

    String jname50=new String();
    String goj50=new String();
    String seatnumber50=new String();

    Spinner list;
    Spinner list2;
    Spinner list5;

    ProgressDialog dialog = null;


    EditText p;
    String a,a0,a2,a1,a3,a4,a5;



    String n,n1,n2;


final Context context=this;

    ArrayList<String> namej = new ArrayList<String>();
    ArrayList<String> namej2 = new ArrayList<String>();
    ArrayList<String> namej3 = new ArrayList<String>();

    Integer id1[] = new Integer[20];
    String name1[] = new String[20];
    Integer go_id[] = new Integer[20];
    String spe[] = new String[20];

    int share_text;
    int share_meg;
    int share_via;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        p = (EditText) findViewById(R.id.p);
        list = (Spinner) findViewById(R.id.spinner2);
        list2 = (Spinner) findViewById(R.id.spinner12);
        list5 = (Spinner) findViewById(R.id.spinner);


        // عرض المدن من//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        dialog = ProgressDialog.show(bagin.this, "", "waiting", true);
        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest str = new StringRequest(Request.Method.GET, "http://192.168.43.34/go/display.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Integer a1 = jsonObject.getInt("c_id");
                        String a2 = jsonObject.getString("c_city");

                        namej.add(a2);

                        id1[i] = a1;
                        name1[i] = a2;

                        dialog.cancel();


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(bagin.this, "Øلخادم غير متصل", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        rq.add(str);
        //<---------------------------------------------------------------نهاية عرض المدن---------------------------------------------------->//

        // عرض التخصص//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //dialog = ProgressDialog.show(MainActivity.this, "", "waiting", true);
        RequestQueue rq5 = Volley.newRequestQueue(this);

        StringRequest str5 = new StringRequest(Request.Method.GET, "http://192.168.43.34/go/display1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String a7 = jsonObject.getString("s_spe");

                        namej3.add(a7);


                        spe[i] = a7;

                        dialog.cancel();


                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
                adapter3.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(bagin.this, "Øلخادم غير متصل", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        rq5.add(str5);
        //<---------------------------------------------------------------نهاية عرض التخصص ---------------------------------------------------->//
        //spinner 1  //
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, namej);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);
        namej.add("");
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> adapterView2, View view2, int ii, long ll) {

                namej2.clear();

                namej2.add("");
                seatnumber50="";
                if (ii != 0) {
                    jname50=namej.get(ii);

                    RequestQueue rq2 = Volley.newRequestQueue(bagin.this);

                    StringRequest str2 = new StringRequest(Request.Method.GET, "http://192.168.43.34/go/displayjern.php?c_id=" +id1[ii-1], new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONArray jsonArray = new JSONArray(s);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    Integer a1 = jsonObject.getInt("c_id");
                                    String a22 = jsonObject.getString("l_local");
                                    namej2.add(a22);
                                    go_id[i]=a1;
                                }

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                            adapter2.notifyDataSetChanged();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(bagin.this, "Øلخادم غير متصل", Toast.LENGTH_SHORT).show();

                        }
                    });
                    rq2.add(str2);
                }
                n1=(String) list.getItemAtPosition(ii);
                Toast.makeText(bagin.this, n1.toString(), Toast.LENGTH_SHORT).show();

            }

            public void onNothingSelected(AdapterView<?> adapterView2) {
                return;
            }
        });
        //<--------------------------------------------------------------------------------------------------------------------------------------->//

        //spinner 2//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, namej2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list2.setAdapter(adapter2);

        list2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> adapterView2, View view2, int ii, long ll) {

                seatnumber50 = "";
                goj50 = namej2.get(ii);
                n2=(String) list2.getItemAtPosition(ii);
                Toast.makeText(bagin.this, n2.toString(), Toast.LENGTH_SHORT).show();



            }

            public void onNothingSelected(AdapterView<?> adapterView2) {
                return;
            }
        });

//<--------------------------------------------------------------نهاية ------------------------------------------->//
        //spinner 5  //

        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        //<--------------------------------------------------------------------------------------------------------------------------------------->//
        adapter3= new ArrayAdapter(this, android.R.layout.simple_spinner_item,namej3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list5.setAdapter(adapter3);

        namej3.clear();
        namej3.add("");


        list5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> adapterView2, View view2, int ii, long ll) {
                n=(String) list5.getItemAtPosition(ii);
                Toast.makeText(bagin.this, n.toString(), Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> adapterView2) {return;}
        });




    //<----------------------------------------------------------------on clike button------------------------------------------------------------>//



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bagin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(this, login_pation.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, add_pation.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, NUchoose.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            AlertDialog();
        } else
            if (id == R.id.nav_share) {

///////////////////////share//////////////
                Intent myIntent=new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody="your body here";
                String shareSub ="your subject here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"share using"));


        } else if (id == R.id.nav_send) {
            Intent i = new Intent(this, information.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


                                Intent i = new Intent(bagin.this, find1.class);
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


                    Toast.makeText(bagin.this, "الخادم غير متصل", Toast.LENGTH_LONG).show();

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
    public void AlertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("هل تود الخروج!!!")
                .setCancelable(false)
                .setPositiveButton("نعم",new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface dialog,int id) {
                        System.exit(0);

                    }
                })
                .setNegativeButton("لا",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id)
                    {

                        dialog.cancel();

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();


//////////////////////
    }

}


