package com.example.personal.final_;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class new_activity_2_1 extends Activity {
    public static final String URL ="http://localhost:8080/drug_intr/drug_info";
    TextView outputText;
    String drug1;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity_2_1);
        Intent newActivity5_1 = new Intent();
        setResult(RESULT_OK, newActivity5_1);
        outputText = (TextView) findViewById(R.id.text);
        drug1 = getIntent().getExtras().getString("name1");
        progress = new ProgressDialog(this);
        progress.setMessage("loading...");
        progress.show();
        Servlet_Connection con = new Servlet_Connection();
        con.execute(new String[]{URL});
    }

    private class Servlet_Connection extends AsyncTask<String, Void, String> {
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
            //Log.d("my_tag", "to the end");
            progress.dismiss();
            output = output.replace(".", ".<br>");
            outputText.setText(Html.fromHtml(output), TextView.BufferType.SPANNABLE);
        }
    }
    private String getOutputFromUrl(String url) {
        //Log.d("my_tag", url.toString());
        url = url.replace("localhost", getResources().getString(R.string.ip));
        //Log.d("my_tag", url.toString());
        url = url.toString() + "?drug1=" + drug1;
        //Log.d("my_tag", url.toString());
        StringBuffer output = new StringBuffer("");
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
       // Log.d("my_tag", output.toString());
        return output.toString();
    }
    private InputStream getHttpConnection(String urlString) throws IOException {
        InputStream stream = null;
        java.net.URL url;
        url = new URL(urlString);
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

   


