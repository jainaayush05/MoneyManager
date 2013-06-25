package com.aayush.mfm;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.androidhive.jsonparsing.R;

import android.widget.SimpleAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Index_Search extends ListActivity{
	public String data;
	public ArrayList<String> suggest;
	public AutoCompleteTextView autoComplete;
    public ArrayAdapter<String> aAdapter;
    HashMap<String,ArrayList<String>> hash;
    
	/** Called when the activity is first created. */
    ArrayList<HashMap<String,String>> list= new ArrayList<HashMap<String,String>>(); 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        suggest = new ArrayList<String>();
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        
        autoComplete.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable editable) {
				// TODO Auto-generated method stub
				
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String newText = s.toString();
				new getJson().execute(newText);
			}
        	
        });
		
    }
    private void populateList(ArrayList<String> suggest) {
    	 list= new ArrayList<HashMap<String,String>>(); 
    	for(int i=0;i<suggest.size();i++){
    	HashMap<String,String> temp = new HashMap<String,String>();
    	temp.put("ticker",suggest.get(i));
    	list.add(temp);
    	}
    }

	
   class getJson extends AsyncTask<String,String,String>{

	@Override
	protected String doInBackground(String... key) {
		String newText = key[0];
		newText = newText.trim();
		
		try{
			HttpClient hClient = new DefaultHttpClient();
			HttpGet hGet = new HttpGet("http://d.yimg.com/autoc.finance.yahoo.com/autoc?query="+newText+"&callback=YAHOO.Finance.SymbolSuggest.ssCallback");
			ResponseHandler<String> rHandler = new BasicResponseHandler();
			data = hClient.execute(hGet,rHandler);
			data=data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
			suggest = new ArrayList<String>();
			JSONArray jArray = new JSONArray(data);
			for(int i=0;i<jArray.length();i++){
			String SuggestKey = jArray.getString(i);
			SuggestKey=SuggestKey.substring(1,SuggestKey.length()-1);
			String bse_sym="",nse_sym = "";
			if(SuggestKey.contains("\"type\":\"I\"")){
				if(SuggestKey.contains("\"exch\":\"NSI\"")){
						String[] s= SuggestKey.split(",");
						for(int j=0;j<s.length;j++){
							if(s[j].startsWith("\"name\"")){
								SuggestKey= s[j].substring(s[j].indexOf("\"name\"")+8, s[j].length()-1).trim();
							}
							if(s[j].startsWith("\"symbol\"")){
								nse_sym= s[j].substring(s[j].indexOf("\"symbol\"")+10, s[j].length()-1).trim();
							}
	
						}
						suggest.add("NSE - "+SuggestKey+" : "+nse_sym);
				}
				else if(SuggestKey.contains("\"exch\":\"BSE\"")){
					String[] s= SuggestKey.split(",");
					for(int j=0;j<s.length;j++){
						if(s[j].startsWith("\"name\"")){
							SuggestKey= s[j].substring(s[j].indexOf("\"name\"")+8, s[j].length()-1).trim();
						}
						if(s[j].startsWith("\"symbol\"")){
							bse_sym= s[j].substring(s[j].indexOf("\"symbol\"")+10, s[j].length()-1).trim();
						}
					}
					suggest.add("BSE - "+SuggestKey+" : "+bse_sym);
				}
			}
			
			}}catch(Exception e){
			Log.w("Error", e.getMessage());
		}
		runOnUiThread(new Runnable(){
			public void run(){
				
				SimpleAdapter adapter = new SimpleAdapter(Index_Search.this,list,R.layout.item,new String[] {"ticker"},new int[] {R.id.tv});
				populateList(suggest);
		        setListAdapter(adapter);
			}
		});
		
		return null;
	}
	   
   }
   protected void onListItemClick(ListView l, View v,int position,long id){
		
		//get selected items
		HashMap<String,String> temp1 = new HashMap<String,String>();
		temp1=(HashMap<String, String>) getListAdapter().getItem(position);
		String ticker_c=temp1.get("ticker");
		Toast.makeText(this, (ticker_c.substring(ticker_c.indexOf(":")+1)).trim(), Toast.LENGTH_SHORT).show();
		Intent i=new Intent(Index_Search.this,Ticker_Info.class);
		
		i.putExtra("scrip",(ticker_c.substring(ticker_c.indexOf(":")+1)).trim().replace("^", "%5E") );
		startActivity(i);


	}



}