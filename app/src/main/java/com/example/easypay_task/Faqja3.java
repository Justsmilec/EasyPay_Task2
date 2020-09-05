package com.example.easypay_task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.com.sample.R;

public class Faqja3 extends Fragment {

    public static ArrayList<String[]> list = new ArrayList<>();
    LinearLayout myLayout = null;
    public int count = 0;
    Button reset_button;
    ProgressDialog pd;
    RecyclerView recyclerView;
    Recycler_Adapter adapter;
    ArrayList<String> mylist = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v_View = inflater.inflate(R.layout.fragment_faqja3, container, false);
        reset_button = (Button) v_View.findViewById(R.id.btnReset);

        recyclerView =  (RecyclerView) v_View.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myLayout = (LinearLayout) v_View.findViewById(R.id.mylayout);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myLayout.removeAllViews();
                new JsonTask().execute();

                adapter = new Recycler_Adapter(getContext(),list);
                recyclerView.setAdapter(adapter);

            }
        });

        return v_View;

    }



    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(getContext());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
           String jsonData = null;
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jsonData = responses.body().string();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return jsonData;
        }
            @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
            if (pd.isShowing()){
                pd.dismiss();
            }
            try {
                list.clear();
                JSONArray Jarray = new JSONArray(jsonData);

                for (int i = 0; i < Jarray.length(); i++) {
                    JSONObject object = Jarray.getJSONObject(i);
                    String[] myString = new String[3];
                    myString[0] = object.getString("first");
                    myString[1] = object.getString("last");
                    myString[2] = object.getString("email");
                    list.add(myString);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
