package com.aayush.mfm;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class MF_Cat extends ListActivity implements OnClickListener {

/*
 * Buttons and other things need implementation
 */ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
 	HashMap<String,ArrayList<String>> mfmodel=new HashMap<String, ArrayList<String>>();
 	ArrayList<String> keys= new ArrayList<String>();
 	ArrayList<String> mfs= new ArrayList<String>();
 	public String data="";
 	public ArrayList<String> mfunds= new ArrayList<String>();
 	String cat;
 	SimpleAdapter adapter;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ta_list);
		Intent i = getIntent();
		cat=i.getStringExtra("cat_type");
		Toast.makeText(getApplicationContext(), cat, Toast.LENGTH_SHORT).show();
		mfs= new ArrayList<String>();
		
		new Load().execute();
        
   	   		
   			
   			
		
		
        
        
}
	class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(MF_Cat.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
        	
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
       			
       			executeatend();
            }
       			catch(Exception e){
       				
       			}
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            for(int i=0;i<mfs.size();i++){
            	String[] temp=mfs.get(i).split(",");
            	String t = temp[7];
            	mfs.set(i, t.substring(t.indexOf(":")+2,t.length()-1));
            }
            populateList(mfs);
            setListAdapter(adapter);
            progDailog.dismiss();
        }
    }
	public void executeatend(){
		adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.single_list_item_view,
        		new String[] {"mf_type"},
        		new int[] {R.id.product_label}
        		);
		
	}
    private void populateList(ArrayList<String> mfs) {
    	for(String s: mfs){
    	HashMap<String,String> tempts1 = new HashMap<String,String>();
    	tempts1.put("mf_type",s);
    	
    	list.add(tempts1);
    	}
    	
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
    	HashMap<String,String> temp1 = new HashMap<String,String>();
    	temp1=(HashMap<String, String>) getListAdapter().getItem(position);
		String mf_type=temp1.get("mf_type");
		Toast.makeText(this, mf_type, Toast.LENGTH_SHORT).show();
		Intent ta = new Intent(this, MF_Main.class);
		ta.putExtra("mf_type",mf_type );
		ta.putExtra("cat_type",cat );
		
				
	   startActivity(ta);
	    

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
