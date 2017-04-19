package com.example.personal.final_;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class new_activity_3_1 extends Activity
{
    public static final String URL =
            "http://localhost:8080/drug_intr/drug_intr";
    TextView outputText;
    String drug1;
    String drug2;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_3_1);
        Intent newActivity5_1 = new Intent();
        setResult(RESULT_OK, newActivity5_1);
        outputText = (TextView) findViewById(R.id.my_text);

        drug1=getIntent().getExtras().getString("name1");

        drug2=getIntent().getExtras().getString("name2");
        progress = new ProgressDialog(this);
        progress.setMessage("loading...");
        progress.show();
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

            progress.dismiss();
            outputText.setText(Html.fromHtml(output), TextView.BufferType.SPANNABLE);
            //outputText.setText(output);
        }
    }

    private String getOutputFromUrl(String url) {
        Log.d("my_tag",url.toString());
        url = url.replace("localhost", getResources().getString(R.string.ip));
        Log.d("my_tag",url.toString());

        try{
            url=url.toString()+ "?drug1="+URLEncoder.encode(drug1,"UTF-8")+"&drug2="+URLEncoder.encode(drug2,"UTF-8");
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
        URL url;
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