package com.twinpeaks.inspectionreport;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twinpeaks.inspectionreport.Adapters.ProjectsAdapter;
import com.twinpeaks.inspectionreport.core.ProjectsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends FragmentActivity implements OnClickListener {

    private ImageView mBt1, mBt2, mBt3, mBt4;
    private ImageView mSelBg;
    private Button btnLogout;
    private LinearLayout mTab_item_container;
    private FragmentManager mFM = null;
    private int mSelectIndex = 0;
    private View last, now;
    private static Boolean isQuit = false;
    private Timer timer = new Timer();
    private String currentID="";
    private static String TAG = MainActivity.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private String urlJsonArry = "";

    public JSONArray ja_projects;

    View v1, v2;
    LinearLayout content_container;
    Intent m_Intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {
        mTab_item_container = (LinearLayout) findViewById(R.id.tab_item_container);

        mBt1 = (ImageView) findViewById(R.id.tab_bt_1);
        mBt2 = (ImageView) findViewById(R.id.tab_bt_2);
        mBt3 = (ImageView) findViewById(R.id.tab_bt_3);
        mBt4 = (ImageView) findViewById(R.id.tab_bt_4);

        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);
        mBt4.setOnClickListener(this);

        btnLogout = (Button) findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(this);

        mSelBg = (ImageView) findViewById(R.id.tab_bg_view);
        content_container = (LinearLayout) findViewById(R.id.content_container);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        currentID = prefs.getString("prefUsername", "");

        urlJsonArry = "http://192.168.0.162:8080/GetAllProjects";
        //makeProjectsRequest();
        makeProjectsStringRequest();
    }

    private void makeProjectsStringRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlJsonArry;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        JSONObject jo;
                        try {
                            Log.d(TAG, "Response is: " + response);
                            jo = new JSONObject(response);
                            ja_projects = jo.getJSONArray("Projects");
                            //ProjectsResponse pr = new ProjectsResponse((JSONObject)ja.get(0));
                            changeToProjects();
                        } catch (Exception ex) {
                            Log.d(TAG, "Error in parsing");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        LayoutParams lp = mSelBg.getLayoutParams();
        lp.width = mTab_item_container.getWidth() / 4;
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.tab_bt_1:
                last = mTab_item_container.getChildAt(mSelectIndex);
                now = mTab_item_container.getChildAt(0);
                startAnimation(last, now);
                mSelectIndex = 0;

                changeToProjects();
                break;
            case R.id.tab_bt_2:
                last = mTab_item_container.getChildAt(mSelectIndex);
                now = mTab_item_container.getChildAt(1);
                startAnimation(last, now);
                mSelectIndex = 1;

                changeToJobs();
                break;
            case R.id.tab_bt_3:
                last = mTab_item_container.getChildAt(mSelectIndex);
                now = mTab_item_container.getChildAt(2);
                startAnimation(last, now);
                mSelectIndex = 2;

                changeToMaps();
                break;
            case R.id.tab_bt_4:
                last = mTab_item_container.getChildAt(mSelectIndex);
                now = mTab_item_container.getChildAt(3);
                startAnimation(last, now);
                mSelectIndex = 3;

                changeToSetting();
                break;
            case R.id.button_logout:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                prefs.edit().putBoolean("prefIsLoggedIn", false).apply();
                prefs.edit().putString("prefUsername", "").apply();
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void startAnimation(View last, View now) {
        TranslateAnimation ta = new TranslateAnimation(last.getLeft(),
                now.getLeft(), 0, 0);
        ta.setDuration(300);
        ta.setFillAfter(true);
        mSelBg.startAnimation(ta);
    }

    private void changeToJobs() {
        Fragment f = new FrgJobs();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    public void changeToProjects() {
        Fragment f = new FrgProjects();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();

    }

    public void changeToMaps() {
        Fragment f = new FrgMap();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    public void changeToSetting() {
        Fragment f = new FrgSettings();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                Toast.makeText(getBaseContext(), "my message",
                        Toast.LENGTH_SHORT).show();
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
            }
        } else {
        }
        return false;
    }


    public JSONArray getProjectsJsonArray() {
        return ja_projects;
    }


}
