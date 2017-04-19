package com.example.personal.final_;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class activity_main extends Activity {
    int[] image_resource={R.drawable.firstaid,R.drawable.pres,R.drawable.interaction};
    adapter adapter_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mytag","entered main");
        setContentView(R.layout.activity_activity_main);
        String[] items = {"first aid","prescription knowledge", "drug interactions"};
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView myList = (ListView) findViewById(R.id.myList);
        int i=0;
        adapter_=new adapter(getApplicationContext(),R.layout.row_layout);
        myList.setAdapter(adapter_);
        for(String data:items)
        {
            data_provider data_pro=new data_provider(image_resource[i],data);
            adapter_.add(data_pro);
            i++;
        }
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent newActivity1 = new Intent(activity_main.this, newactivity_1.class);
                        startActivity(newActivity1);
                        break;
                    case 1:Intent newActivity2 = new Intent(activity_main.this, new_activity_2.class);
                        startActivity(newActivity2);
                        break;
                    case 2:Intent newActivity3 = new Intent(activity_main.this, new_activity_3.class);
                        startActivity(newActivity3);
                        break;

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Confirm");
        alert.setMessage("Do you really want to exit the application");
        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              finish();
            }
        });
        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
        AlertDialog my_alert=alert.create();
        my_alert.show();
    }
}
