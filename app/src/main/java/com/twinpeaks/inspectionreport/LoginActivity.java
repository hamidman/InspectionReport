package com.twinpeaks.inspectionreport;

import com.twinpeaks.inspectionreport.core.ProfileResponse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.android.volley.Request.Method;
import com.twinpeaks.inspectionreport.core.ProfileResponse;


public class LoginActivity extends Activity implements OnClickListener {

    Button btn1;
    EditText txtUS, txtPS;

    //private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";
    private String urlJsonObj = "http://192.168.0.162:8080/";
    //private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";
    private String urlJsonArry = "http://192.168.0.162:8080/asdfqwer";
    private static String TAG = LoginActivity.class.getSimpleName();
    private String jsonResponse;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        txtUS = (EditText) findViewById(R.id.edtUS);
        txtUS.requestFocus();
        txtPS = (EditText) findViewById(R.id.edtPS);

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);//getApplicationContext());
        }

    }

    @Override
    public void onClick(View v) {
        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //startActivity(intent);

        makeLoginRequest(txtUS.getText().toString(), txtPS.getText().toString());

    }

    private void makeLoginRequest(String _id, String _ps) {
        String _request = urlJsonObj + "ID:" + _id + ",PS:" + _ps;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                _request, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    if ( response.has("Message") ) {
                        String strMSG = response.getString("Message").trim();
                        if( strMSG.equals("User is authenticated.") ) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if (strMSG.indexOf("Error 300") >= 0) {
                            txtUS.setText("");
                            txtUS.requestFocus();
                            txtPS.setText("");
                            Toast.makeText(getApplicationContext(),
                                    "Error: Wrong Username or Password.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            txtUS.setText("");
                            txtUS.requestFocus();
                            txtPS.setText("");
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + strMSG,
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Bad Response
                        txtUS.setText("");
                        txtUS.requestFocus();
                        txtPS.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Error: Server Error or Bad Response.",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        //ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
        mRequestQueue.add(jsonObjReq);
    }

    private void makeJsonObjectRequest(String _id, String _ps) {
        String _request = urlJsonObj + "ID:" + _id + ",PS:" + _ps;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                _request, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Home: " + home + "\n\n";
                    jsonResponse += "Mobile: " + mobile + "\n\n";

                    //ProfileResponse prfResp = new ProfileResponse(response);

                    //txtResponse.setText(jsonResponse);
                    Log.d("Volley", "jsonResponse: " + jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                //hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog
                //hidepDialog();
            }
        });

        // Adding request to request queue
        //ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
        mRequestQueue.add(jsonObjReq);
    }

    private void makeJsonArrayRequest() {

       JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("name");
                                String email = person.getString("email");
                                JSONObject phone = person
                                        .getJSONObject("phone");
                                String home = phone.getString("home");
                                String mobile = phone.getString("mobile");

                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Email: " + email + "\n\n";
                                jsonResponse += "Home: " + home + "\n\n";
                                jsonResponse += "Mobile: " + mobile + "\n\n\n";

                            }

                            //txtResponse.setText(jsonResponse);
                            Log.d("Volley", "jsonResponse: " + jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        //hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                //hidepDialog();
            }
        });

        // Adding request to request queue
        //AppController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

}
