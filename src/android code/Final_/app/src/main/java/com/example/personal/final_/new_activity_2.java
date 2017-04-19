package com.example.personal.final_;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class new_activity_2 extends Activity {

    EditText drug1;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity_2);
        Intent newActivity2=new Intent();
        setResult(RESULT_OK, newActivity2);
        drug1=(EditText)findViewById(R.id.editText3);
        Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = drug1.getText().toString();
                Intent newActivity2_1 = new Intent(new_activity_2.this, new_activity_2_1.class);
                Bundle b=new Bundle();
                b.putString("name1",name1);
                newActivity2_1.putExtras(b);
                startActivity(newActivity2_1);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_activity_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
