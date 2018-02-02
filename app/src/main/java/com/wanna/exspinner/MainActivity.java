package com.wanna.exspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        ArrayAdapter<String> choosecity = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, type);
        sp.setAdapter(choosecity);
        sp.setOnItemSelectedListener(selectListener);


    }
    //第一個下拉類別的監看式
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
            //讀取第一個下拉選單是選擇第幾個
            ArrayList<Map<String,String>> list = citys[position].AreaList;
            String[] listname = new String[list.size()];

            for(int i=0;i<list.size();i++)
            {
                listname[i]=list.get(i).get("AreaName");
            }
            Log.d("LIST:" ,listname.toString());
            ArrayAdapter<String> choosearea = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,listname);
            sp2.setAdapter(choosearea);



        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };

}
