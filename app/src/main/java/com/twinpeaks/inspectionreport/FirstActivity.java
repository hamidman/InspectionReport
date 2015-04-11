package com.twinpeaks.inspectionreport;


import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity  {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        new Timer().schedule(new TimerTask(){
            public void run() {
                //Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }


}
