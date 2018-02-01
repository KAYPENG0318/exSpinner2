package com.wanna.exspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        City[] citys=gson.fromJson(str1,City[].class);
        Log.d("city",citys.length+"");
        for(int i=0;i<citys.length;i++)
        {
            Log.d("city",citys[i].CityName);
            ArrayList<Map<String,String>> mylist=citys[i].AreaList;
            for(int j=0;j<mylist.size();j++)
            {
                Log.d("country",mylist.get(j).get("AreaName"));
            }
        }

    }
}
