package com.wanna.exspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Spinner sp,sp2;
    ArrayList<Map<String,String>> mylist;
    City[] citys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        InputStream is = getResources().openRawResource(R.raw.mydata);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            str = br.readLine();
            sb.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            while((str = br.readLine()) !=null)
//            {
//                sb.append(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String str1=sb.toString();
        Log.d("NET",str1);
        Gson gson=new Gson();
        citys=gson.fromJson(str1,City[].class);
        Log.d("city",citys.length+"");
        String[] type = new String[citys.length];

        for(int i=0;i<citys.length;i++)
        {
            Log.d("city",citys[i].CityName);
            type[i]=citys[i].CityName;
            mylist=citys[i].AreaList;
           for(int j=0;j<mylist.size();j++)
            {
                Log.d("country",mylist.get(j).get("AreaName"));
            }
        }

        ArrayAdapter<String> choosekind = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, type);
        sp.setAdapter(choosekind);
    }
}
