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
import android.widget.EditText;


public class new_activity_3 extends Activity {

    EditText drug1;
    EditText drug2;
    String name1;
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity_3);
        Intent newActivity5=new Intent();
        setResult(RESULT_OK, newActivity5);
        drug1=(EditText)findViewById(R.id.editText);
        drug2=(EditText)findViewById(R.id.editText2);
        // Log.d("my_tag","clicking");
        Button b = (Button) findViewById(R.id.my_click);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Log.d("my_tag","clicked");
                name1=drug1.getText().toString();
                name2=drug2.getText().toString();
                Intent newActivity5_1 = new Intent(new_activity_3.this, new_activity_3_1.class);
                // Intent i=new Intent();
                Bundle b=new Bundle();
                b.putString("name1",name1);
                Log.d("my_tag",name1);
                b.putString("name2",name2);
                Log.d("my_tag",name2);
                //Log.d("my_tag",drug_name);
                newActivity5_1.putExtras(b);
                setResult(1, newActivity5_1);

                // Log.d("my_tag","first");
                startActivity(newActivity5_1);
                //Log.d("my_tag","made");
            }
        });
    }
    }

