package com.example.ducnguyenvan.httprequestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btn;
    private RequestQueue rq;
    private StringRequest sr;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.text);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestThenPrintResponse();
            }
        });




    }

    private void sendRequestThenPrintResponse() {
        rq = Volley.newRequestQueue(this);
        sr = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/posts/1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response: " + response.toString());
                txt.setText("Response: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error: " + error.toString());
                txt.setText("Error: " + error.toString());
            }
        });

        sr.setTag(REQUESTTAG);
        rq.add(sr);
        //rq.cancelAll(REQUESTTAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(rq != null) {
            rq.cancelAll(REQUESTTAG);
        }
    }
}
