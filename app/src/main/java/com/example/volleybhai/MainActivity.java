package com.example.volleybhai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLCV_7ng-CP3XWHnMOZCL_gXN2IMW75bnj&key=AIzaSyAqj8xjpM-0GJ3Hpd1yCUX546qT77uMupo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String val = response.getJSONObject("items").getJSONObject("0").getJSONObject("snippet").getJSONObject("resourceId").get("videoId").toString();
                    Toast.makeText(MainActivity.this, "" + val, Toast.LENGTH_SHORT).show();
                    Log.d("TAG1", "onResponse: " + val);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG2", "onResponse: Error");
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest, "json_obj_req");
    }
}
