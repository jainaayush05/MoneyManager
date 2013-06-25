package com.aayush.mfm;

import java.io.IOException;
import java.util.ArrayList;

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
public class Top_GL extends Activity {

	/*
	 * Buttons and search bar need implementation.
	 * List view required.
	 */
	Button debt,equity,index;
	String code;
	TextView tv11,tv12,tv13,tv21,tv22,tv23,tv31,tv32,tv33,tv41,tv42,tv43,tv51,tv52,tv53;
	 ArrayList<String> s=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Intent intent=getIntent();
		code=intent.getStringExtra("code");
		if(code.equals("gainers")){
			setContentView(R.layout.top_gl);
			tv11=(TextView)findViewById(R.id.textView11);
			tv12=(TextView)findViewById(R.id.textView12);
			tv13=(TextView)findViewById(R.id.textView13);
			tv21=(TextView)findViewById(R.id.textView21);
			tv22=(TextView)findViewById(R.id.textView22);
			tv23=(TextView)findViewById(R.id.textView23);
			tv31=(TextView)findViewById(R.id.textView31);
			tv32=(TextView)findViewById(R.id.textView32);
			tv33=(TextView)findViewById(R.id.textView33);
			tv41=(TextView)findViewById(R.id.textView41);
			tv42=(TextView)findViewById(R.id.textView42);
			tv43=(TextView)findViewById(R.id.textView43);
			tv51=(TextView)findViewById(R.id.textView51);
			tv52=(TextView)findViewById(R.id.textView52);
			tv53=(TextView)findViewById(R.id.textView53);
		}
		else{
			setContentView(R.layout.top_gl_l);
			tv11=(TextView)findViewById(R.id.text11);
			tv12=(TextView)findViewById(R.id.text12);
			tv13=(TextView)findViewById(R.id.text13);
			tv21=(TextView)findViewById(R.id.text21);
			tv22=(TextView)findViewById(R.id.text22);
			tv23=(TextView)findViewById(R.id.text23);
			tv31=(TextView)findViewById(R.id.text31);
			tv32=(TextView)findViewById(R.id.text32);
			tv33=(TextView)findViewById(R.id.text33);
			tv41=(TextView)findViewById(R.id.text41);
			tv42=(TextView)findViewById(R.id.text42);
			tv43=(TextView)findViewById(R.id.text43);
			tv51=(TextView)findViewById(R.id.text51);
			tv52=(TextView)findViewById(R.id.text52);
			tv53=(TextView)findViewById(R.id.text53);
		}
		new Load().execute();
		

		
		
		

}	
	class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
       
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Top_GL.this);
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
			
			//Toast.makeText(getApplicationContext(), s.get(0), Toast.LENGTH_SHORT).show();
			
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            tv11.setText(s.get(0));
			tv12.setText(s.get(1));
			tv13.setText(s.get(2));
			tv21.setText(s.get(3));
			tv22.setText(s.get(4));
			tv23.setText(s.get(5));
			tv31.setText(s.get(6));
			tv32.setText(s.get(7));
			tv33.setText(s.get(8));
			tv41.setText(s.get(9));
			tv42.setText(s.get(10));
			tv43.setText(s.get(11));
			tv51.setText(s.get(12));
			tv52.setText(s.get(13));
			tv53.setText(s.get(14));
            progDailog.dismiss();
        }
    }
	public ArrayList<String> scrapper() throws IOException{
		Document doc = Jsoup.connect("http://in.finance.yahoo.com/"+code+"?e=bo").get();
		ArrayList<String> s=new ArrayList<String>();
        //select all the tr elements within the tbody elements within
        //the table with class name table_text_small
        //and iterate over all of those elements
        for (Element e : doc.select("tbody > tr")) {
            //this is a little hackish, but check to make sure this tr has
            //at least 5 children (tds)
            if (e.children().size() >= 6) {
                String temp;
            	if(e.child(1).text().length()>12){
            		temp=e.child(1).text().substring(0, 12);
            	}
            	else{
            		temp=e.child(1).text();
            	}
                s.add(temp);
                s.add(e.child(2).text().split(" ")[0]);
                s.add(e.child(3).text());
            }
        }
    
		return s;
		
	}
	
}