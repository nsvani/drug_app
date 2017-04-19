package com.example.personal.final_;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class new_activity_1_1 extends Activity {

    public static final String URL =
            "http://localhost:8080/drug_intr/first_aid";
    TextView text;
    String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity_1_1);
        Intent newActivity1_1 = new Intent();
        setResult(RESULT_OK, newActivity1_1);
        Log.d("my_tag", "entered next");
        String symp=getIntent().getExtras().getString("symp");
        name=symp.split(";");
        Log.d("my_tag",name[0]);
        text=(TextView)findViewById(R.id.me);
        // text.setText(name);
        GetXMLTask task = new GetXMLTask();
        task.execute(new String[]{URL});
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

            //String[] items=output.split(";");
            output=output.replaceAll(";","\n");
            text.setText(output);

        }
    }

    private String getOutputFromUrl(String url) {
        Log.d("my_tag", url.toString());
        url = url.replace("localhost", getResources().getString(R.string.ip));
        Log.d("my_tag",url.toString());

        try{
            for(int i=0;i<name.length;i++)
            {
                if(i==0)
                    url=url.toString()+ "?symp="+ URLEncoder.encode(name[i], "UTF-8");
                else
                    url=url.toString()+ "&symp="+ URLEncoder.encode(name[i], "UTF-8");
            }
            Log.d("my_tag",url);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            Log.e("my_tag", "error is", e1);
        }
        Log.d("my_tag",url.toString());
        StringBuffer output = new StringBuffer("");
        InputStream stream;
        try {

            stream = getHttpConnection(url);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(stream));
            String s = "";
            while (buffer!=null&&(s = buffer.readLine()) != null)
                output.append(s);
        } catch (IOException e1) {
            e1.printStackTrace();
            Log.e("my_tag", "error is", e1);
        }

        return output.toString();
    }

    // Makes HttpURLConnection and returns InputStream
    private InputStream getHttpConnection(String urlString)
            throws IOException {

        InputStream stream = null;
        java.net.URL url;
        url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            httpConnection.setRequestMethod("GET");

            httpConnection.connect();
            Log.d("my_tag","connected"+ (httpConnection.getResponseCode()));
            if (httpConnection!=null&&httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                if(httpConnection!=null)
                    stream = httpConnection.getInputStream();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("my_tag", "error is", ex);

        }

        return stream;
    }
}
