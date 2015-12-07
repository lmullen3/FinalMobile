package com.example.elon.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class InputCheck extends Activity {

    private TextView hourView;
    private TextView minuteView;
    private TextView printView;
    private String hourText, minuteText, printText;
    private int printNum;

    private final String baseURL = "http://trumpy.cs.elon.edu:5000/printer/set/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_check);

        hourView = (TextView) findViewById(R.id.hourCheck);
        minuteView = (TextView) findViewById(R.id.minuteCheck);
        printView = (TextView) findViewById(R.id.printCheck);

        Intent intent = getIntent();
        hourText = intent.getStringExtra("hourInput");
        minuteText = intent.getStringExtra("minuteInput");
        printText = intent.getStringExtra("printerText");
        printNum = intent.getIntExtra("printerInput", 0);

        System.out.println("printNum = " + printNum);

        hourView.setText(hourText);
        minuteView.setText(minuteText);
        printView.setText(printText);


    }

    public void onClickNext(View view) {
       new updateSite().execute();
        Intent intent = new Intent(this, Thanks.class);
        startActivity(intent);

    }

    private class updateSite extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            try {
                String param = "hour=" + URLEncoder.encode(hourText, "UTF-8") +
                        "&minute=" + URLEncoder.encode(minuteText, "UTF-8");

                System.out.println(param);
                URL url = new URL(baseURL + URLEncoder.encode(printNum + "", "UTF-8") + "?" + param);


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");

                String response = "";
                Scanner sc = new Scanner(urlConnection.getInputStream());

                while (sc.hasNextLine()) {
                    response += sc.nextLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

    }
}
