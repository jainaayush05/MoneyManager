package com.aayush.mfm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.androidhive.jsonparsing.R;
public class News_List extends ListActivity{

/*
 * Buttons and other things need implementation
 */ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
 static ArrayList<String> s;
 String code;
 SimpleAdapter adapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ta_list);
		Intent intent=getIntent();
		code=intent.getStringExtra("code");
		//code="category-stocks";
		
		
		adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.single_list_item_view,
        		new String[] {"news"},
        		new int[] {R.id.product_label}
        		);
       
        new Load().execute();
        
}

	class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(News_List.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	
        	try {
    			s=scrapper();
    			
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            populateList(s);
            setListAdapter(adapter);
            progDailog.dismiss();
        }
    }
    private void populateList(ArrayList<String> news) {
    	
    	
    	for(int i=0;i<news.size();i++){
    	HashMap<String,String> tempts1 = new HashMap<String,String>();
    	tempts1.put("news",news.get(i));
    	
    	list.add(tempts1);
    	}
    	
    	
    	
    	
    	
    }


   
    public ArrayList<String> scrapper() throws IOException{
    	Document doc = Jsoup.connect("http://in.finance.yahoo.com/news/"+code).get();
    	ArrayList<String> s=new ArrayList<String>();
        int i=0;
        for (Element e : doc.select("div > div > div > div > div > span > ul > li")) {
            //this is a little hackish, but check to make sure this tr has
            //at least 5 children (tds)
            
                //if so, print out the 1st child (country name)
                //and the 5th child (exchange rate)
                s.add(e.child(0).text());
                
            
        }

    	return s;
    	
    }

	
}





