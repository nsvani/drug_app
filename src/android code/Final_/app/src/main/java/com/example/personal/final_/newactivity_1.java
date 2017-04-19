package com.example.personal.final_;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.personal.final_.R;


public class newactivity_1 extends Activity {


    Adapter_check dataAdapter = null;
    public static final String URL =
            "http://localhost:8080/drug_intr/first_aid_1";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newactivity_1);
        //Generate list View from ArrayList
        Log.d("my_tag", "before fun 1");
        GetXMLTask task = new GetXMLTask();
        Log.d("my_tag","calling nw");
        task.execute(new String[]{URL});
         displayListView();
        Log.d("my_tag", "before fun 2");
        checkButtonClick();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<data> dataList = new ArrayList<data>();
        data data = new data("cold", false);
        dataList.add(data);
        data = new data("fever", false);
        dataList.add(data);
        data = new data("headache", false);
        dataList.add(data);
        //create an ArrayAdaptar from the String Array
        dataAdapter = new Adapter_check(this, R.layout.row_layout_1, dataList);
        ListView listView = (ListView) findViewById(R.id.listView2);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                data data = (data) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + data.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.bu);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                if(dataAdapter==null)
                {
                    Toast.makeText(getApplicationContext(),
                            "Please select some symptoms", Toast.LENGTH_LONG).show();

                }
                else
                {
                    ArrayList<data> dataList = dataAdapter.list;
                    for (int i = 0; i < dataList.size(); i++) {
                        data data = dataList.get(i);
                        if (data.isSelected()) {
                            responseText.append(data.getName()+";");
                        }
                    }
                    Intent newActivity1_1 = new Intent(newactivity_1.this, new_activity_1_1.class);
                    Bundle b = new Bundle();
                    String symp=responseText.toString();
                    Log.d("my_tag",symp);
                    b.putString("symp",symp);
                    newActivity1_1.putExtras(b);
                    startActivity(newActivity1_1);
                }


            }
        });

    }

    private class GetXMLTask extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... urls) {
            //Log.d("my_tag",urls.toString());
            String output = null;
            for (String url : urls) {
                output = getOutputFromUrl(url);
            }
            return output;
        }

        protected void onPostExecute(String output) {
            Log.d("my_tag", "got list");
            String[] items = output.split(";");
            ArrayList<data> dataList = new ArrayList<data>();
            for (String data1 : items) {
                Log.d("my_tag", data1);
                data data_pro = new data(data1, false);
                dataList.add(data_pro);
            }
            dataAdapter = new Adapter_check(getApplicationContext(),R.layout.row_layout_1,dataList);
            ListView listView = (ListView) findViewById(R.id.listView2);
            // Assign adapter to ListView
            listView.setAdapter(dataAdapter);


            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // When clicked, show a toast with the TextView text
                    data data = (data) parent.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(),
                            "Clicked on Row: " + data.getName(),
                            Toast.LENGTH_LONG).show();
                }
            });

        }

        private String getOutputFromUrl(String url) {
            Log.d("my_tag", url.toString());
            url = url.replace("localhost", getResources().getString(R.string.ip));
            StringBuffer output = new StringBuffer("");
            url = url.toString();
            Log.d("my_tag", url.toString());
            InputStream stream;
            try {
                stream = getHttpConnection(url);

                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(stream));
                String s = "";
                while ((s = buffer.readLine()) != null)
                    output.append(s);
            } catch (IOException e1) {
                e1.printStackTrace();
                Log.e("my_tag", "error is", e1);
            }
            //Log.d("my_tag", output.toString());
            return output.toString();
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {

            InputStream stream = null;
            java.net.URL url;
            url = new java.net.URL(urlString);
            Log.d("my_tag", url.toString());
            Log.d("my_tag", "making connection");
            URLConnection connection = url.openConnection();
            Log.d("my_tag", "connection made");
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("my_tag", "error is", ex);

            }

            return stream;
        }
    }
}