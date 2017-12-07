package com.example.tops.okhttpdemo;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by tops on 14/8/17.
 */

public class JSonParser {

        JSONObject object=null;
    String json="";
    OkHttpClient client;
    public JSONObject getJsonFromUrl(String url) throws IOException, JSONException {
        client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

        Response response=client.newCall(request).execute();
        json=response.body().string();

        object=new JSONObject(json);
        return object;

    }
}
