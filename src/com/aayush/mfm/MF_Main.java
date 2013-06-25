package com.aayush.mfm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class MF_Main extends Activity {

	/*
	 * Buttons and search bar need implementation.
	 * List view required.
	 */
	Button debt,equity,index;
	String mf;
	TextView tv11,tv12,tv13,tv21,tv22,tv23,tv31,tv32,tv33,nav;
	 ArrayList<String> s=new ArrayList<String>();
	 HashMap<String,ArrayList<String>> mfmodel=new HashMap<String, ArrayList<String>>();
	 	ArrayList<String> keys= new ArrayList<String>();
	 	ArrayList<String> mfs= new ArrayList<String>();
	 	public String data="";
	 	public ArrayList<String> mfunds= new ArrayList<String>();
	 	String cat;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Intent intent=getIntent();
		mf=intent.getStringExtra("mf_type");
		cat= intent.getStringExtra("cat_type");
		
			setContentView(R.layout.mf_main);
			tv11=(TextView)findViewById(R.id.ttv11);
			tv12=(TextView)findViewById(R.id.ttv12);
			tv13=(TextView)findViewById(R.id.ttv13);
			tv21=(TextView)findViewById(R.id.ttv21);
			tv22=(TextView)findViewById(R.id.ttv22);
			tv23=(TextView)findViewById(R.id.ttv23);
			tv31=(TextView)findViewById(R.id.ttv31);
			tv32=(TextView)findViewById(R.id.ttv32);
			tv33=(TextView)findViewById(R.id.ttv33);
			nav=(TextView)findViewById(R.id.navee);
			
		
		new Load().execute();
		

		
		
		

}	
	class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
       
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(MF_Main.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	//do something while spinning circling show
        	try{
    			HttpClient hClient = new DefaultHttpClient();
    			HttpGet hGet = new HttpGet("http://mfundserver.appspot.com/mfdata");
    			ResponseHandler<String> rHandler = new BasicResponseHandler();
    			data = hClient.execute(hGet,rHandler);
    			
    			
    			int start_mft=-1;int end_mft=0;String mft="",data_mft="";
       			int start_dmft=0;int end_dmft=0;
       			for(int j=1;j<=11;j++){
       				end_mft=data.indexOf("[", start_mft+2);
       	   			mft=data.substring(start_mft+3,end_mft-2);
       	   			start_mft=data.indexOf("]",end_mft);
       			
       	   			start_dmft=data.indexOf("[",end_dmft);
       	   			end_dmft=data.indexOf("]", start_dmft);
       	   			data_mft=data.substring(start_dmft,end_dmft+1);
       	   			
       	   			mfunds = new ArrayList<String>();
       	   			JSONArray jArray = new JSONArray(data_mft);
       			
       	   			for(int i=0;i<jArray.length();i++){
       	   			String SuggestKey = jArray.getString(i);
       	   			SuggestKey=SuggestKey.substring(1,SuggestKey.length()-1);
       	   			mfunds.add(SuggestKey);
       	   			}
       	   			keys.add(mft);
       	   			mfmodel.put(mft, mfunds);
       	   			if(mft.equals(cat)){
       	   				mfs=mfunds;
       	   			}
       	   			
       			}
       			}
       			catch(Exception e){}
        	
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
        	super.onPostExecute(unused);
        	String mf_main ="";
        	for(int i=0;i<mfs.size();i++){
        		String temp=mfs.get(i);
        		if(temp.contains(mf)){
        			mf_main=temp;
        		}
        	}
        	//Toast.makeText(getApplicationContext(), mf_main, Toast.LENGTH_SHORT).show();
            String[] temp=mf_main.split(",");
            Toast.makeText(getApplicationContext(), temp[7], Toast.LENGTH_SHORT).show();
        	
            tv11.setText(temp[7].substring(temp[7].indexOf(":")+2,temp[7].length()-1));
            tv12.setText(temp[17].substring(temp[17].indexOf(":")+2,temp[17].length()-1)+"%");
			tv13.setText(temp[4].substring(temp[4].indexOf(":")+2,temp[4].length()-1)+"%");
			tv21.setText(temp[14].substring(temp[14].indexOf(":")+2,temp[14].length()-1)+"%");
			tv22.setText(temp[0].substring(temp[0].indexOf(":")+2,temp[0].length()-1)+"%");
			tv23.setText(temp[16].substring(temp[16].indexOf(":")+2,temp[16].length()-1)+"%");
			tv31.setText(temp[13].substring(temp[13].indexOf(":")+2,temp[13].length()-1)+"%");
			tv32.setText(temp[12].substring(temp[12].indexOf(":")+2,temp[12].length()-1)+"%");
			tv33.setText(temp[11].substring(temp[11].indexOf(":")+2,temp[11].length()-1)+"%");
			nav.setText("NAV : "+temp[8].substring(temp[8].indexOf(":")+2,temp[8].length()-1));
            
            
        	progDailog.dismiss();
        }
    }
	
	
}