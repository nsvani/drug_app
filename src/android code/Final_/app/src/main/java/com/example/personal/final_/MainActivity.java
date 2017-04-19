package com.example.personal.final_;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            public void run() {
                try
                {
                    sleep(3000);//Will sleep the thread for 3 sec ie 3000 milli sec and display the splash screen
                    //After 3 secs a new activity will be started which shows disclaimer
                    startActivity(new Intent(getApplicationContext(),disclaimer.class));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }};
        thread.start();
    }
}
