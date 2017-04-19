package com.example.personal.final_;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class disclaimer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        Button b = (Button) findViewById(R.id.button);
       b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.d("mytag", "clicked ");
                startActivity(new Intent(getApplicationContext(), activity_main.class));

            }
        });


    }
}
