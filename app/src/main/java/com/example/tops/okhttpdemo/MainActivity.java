package com.example.tops.okhttpdemo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
ListView listView;
    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
    public static String url="https://api.androidhive.info/contacts/";
    public static String TAG_Contact="contacts";
    public static String TAG_ID="id";
    public static String TAG_NAME="name";
    public static String TAG_EMAIL="email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    listView=(ListView)findViewById(R.id.lst);
        ArrayList<HashMap<String,String>>hashMaps=new ArrayList<>();
StrictMode.setThreadPolicy(policy);
        JSonParser jSonParser=new JSonParser();
        try {
            JSONObject object=jSonParser.getJsonFromUrl(url);
            JSONArray array=object.getJSONArray(TAG_Contact);
            for (int i=0;i<array.length();i++)
            {
                JSONObject jsonObject=array.getJSONObject(i);
                String id=jsonObject.getString(TAG_ID);
                String name=jsonObject.getString(TAG_NAME);
                String email=jsonObject.getString(TAG_EMAIL);

                HashMap<String,String>map=new HashMap<>();
                map.put(TAG_ID,id);
                map.put(TAG_NAME,name);
                map.put(TAG_EMAIL,email);
                hashMaps.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter=new SimpleAdapter(MainActivity.this,hashMaps,R.layout.custlist,new String[]{TAG_ID,TAG_NAME,TAG_EMAIL},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
        listView.setAdapter(adapter);
    }
}
