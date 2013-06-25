package com.aayush.mfm;

import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Async extends Activity  {
     
      
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          //setContentView(R.layout.rssfeedreaderactivity);
          
          AsyncTaskExample asyncTask = new AsyncTaskExample();
          asyncTask.execute();
    }
    private class AsyncTaskExample extends AsyncTask<String, Void, String> {
              // private String Content;
              private ProgressDialog Dialog;
              String response = "";

              @Override
              protected void onPreExecute() {
                     Dialog = new ProgressDialog(Async.this);
                     Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                     Dialog.setMessage("Loading data...");
                     Dialog.setCancelable(false);
                     Dialog.show();
             
              }
              @Override
              protected String doInBackground(String... urls) {
                       try {
                            
                             response ="Success";
                             }
                       catch (Exception e) {
                             response ="Failed";
                             }
                     return response;
              }
              @Override
              protected void onPostExecute(String result) {
                Dialog.dismiss();
                Dialog = null;
   
                    
              }
       }
   
}