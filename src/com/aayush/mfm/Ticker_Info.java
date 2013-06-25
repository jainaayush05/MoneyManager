package com.aayush.mfm;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;

import com.androidhive.jsonparsing.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Ticker_Info extends Activity implements OnClickListener {

	TextView ticker, ticker_company_name, high, low, high52, low52, pe, volume, open, cap, change, change_per, price;
	Button button_buy, button_news, button_sell,button_ta,button_ao,button_ae,button_pr;
	WebView graph;
	String Intent_ticker,Intent_price,Intent_name,scrip;
	StockModel stock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticker_info);
			
		Intent intent=getIntent();
		scrip=intent.getStringExtra("scrip");
		
		 	
		 	
		 	
		 	 button_buy= (Button) findViewById(R.id.button_buy);
			 button_sell= (Button) findViewById(R.id.button_sell);
			 button_news= (Button) findViewById(R.id.button_news);
			 button_ta= (Button) findViewById(R.id.button_ta);
			 button_ao=(Button) findViewById(R.id.button_ao);
			 button_ae=(Button) findViewById(R.id.button_ae);
			 button_pr=(Button) findViewById(R.id.button_pr);

			 graph= (WebView) findViewById(R.id.graph);
			 //loading= (ProgressBar) findViewById(R.id.loading);
			
			 ticker= (TextView) findViewById(R.id.ticker);
			 ticker_company_name= (TextView) findViewById(R.id.ticker_company_name);
			 price= (TextView) findViewById(R.id.price);
			 change= (TextView) findViewById(R.id.change);
			 change_per= (TextView) findViewById(R.id.change_per);
			 high= (TextView) findViewById(R.id.high);
			 low= (TextView) findViewById(R.id.low);
			 open= (TextView) findViewById(R.id.open);
			 cap= (TextView) findViewById(R.id.cap);
			 high52= (TextView) findViewById(R.id.high52);
			 low52= (TextView) findViewById(R.id.low52);
			 pe= (TextView) findViewById(R.id.pe);
			 volume= (TextView) findViewById(R.id.volume);
			 
			button_buy.setOnClickListener(this);
			button_news.setOnClickListener(this);
			button_sell.setOnClickListener(this);
			button_ta.setOnClickListener(this);
			button_ae.setOnClickListener(this);
			button_ao.setOnClickListener(this);
			button_pr.setOnClickListener(this);
			
			new Load().execute();
			
			
	      
	        
	        

	}
	
	class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Ticker_Info.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	stock = StockAPI.getStockData(scrip);
        	graph.loadUrl(stock.getChartUrlSmall());
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            price.setText("Rs."+(Intent_price=stock.getPrice()));
			change.setText(stock.getChange());
			change_per.setText(stock.getChangePercent());
			high.setText(stock.getDayHigh());
			low.setText(stock.getDayLow());
			open.setText(stock.getOpen());
			cap.setText(stock.getMarketCap());
			high52.setText(stock.getYearHigh());
			low52.setText(stock.getyearLow());
			pe.setText(stock.getPrevClose());
			volume.setText(stock.getVolume());
			
	        
	        
	        ticker.setText((Intent_ticker=scrip).replace("%5E", ""));
	        ticker_company_name.setText((Intent_name=stock.getName()));
	        
            progDailog.dismiss();
        }
    }
	
	public void button_buy() {	
		//Toast tbb= Toast.makeText(getApplicationContext(), "button buy",Toast.LENGTH_LONG);
		//tbb.show();//go to buy screen for selecting no.of stocks to buy
		Intent button_buy = new Intent(this, Buy_Screen.class);
		button_buy.putExtra("scrip", Intent_ticker);
		button_buy.putExtra("company_name", Intent_name);
		button_buy.putExtra("price", Intent_price);	
		Intent button_buy_index = new Intent(this, Buy_Screen_Index.class);
		button_buy_index.putExtra("scrip", Intent_ticker);
		button_buy_index.putExtra("company_name", Intent_name);
		button_buy_index.putExtra("price", Intent_price);
		if(Intent_ticker.startsWith("%5E")){
			startActivity(button_buy_index);
		}
		else{
			startActivity(button_buy);
		}
	    
	    }
	public void button_sell() {
		//Toast tbs= Toast.makeText(getApplicationContext(), "button sell",Toast.LENGTH_LONG);
		//tbs.show();//go to buy screen for selecting no.of stocks to sell
		Intent button_sell = new Intent(this, Sell_Screen.class);
		button_sell.putExtra("scrip", Intent_ticker);
		button_sell.putExtra("company_name", Intent_name);
		button_sell.putExtra("price", Intent_price);
		Intent button_sell_index = new Intent(this, Sell_Screen_Index.class);
		button_sell_index.putExtra("scrip", Intent_ticker);
		button_sell_index.putExtra("company_name", Intent_name);
		button_sell_index.putExtra("price", Intent_price);	
		if(Intent_ticker.startsWith("%5E")){
			startActivity(button_sell_index);
		}
		else{
			startActivity(button_sell);
		}
	    
	    }
	public void button_news() {
		//Toast tbn= Toast.makeText(getApplicationContext(), "button news",Toast.LENGTH_LONG);
		//tbn.show();//go to news where you get a list view for news
		Intent button_news = new Intent(this, Headlines_List.class);
		button_news.putExtra("scrip", scrip.replace("^","%5E"));
	    startActivity(button_news);
	    
	    }
	public void button_ta() {
		//Toast tbn= Toast.makeText(getApplicationContext(), "button news",Toast.LENGTH_LONG);
		//tbn.show();//go to news where you get a list view for news
		Intent button_ta = new Intent(this, TA_List.class);
		button_ta.putExtra("scrip", Intent_ticker.replace("^","%5E"));
		button_ta.putExtra("company_name", Intent_name);
				
	    startActivity(button_ta);
	    
	    }
	public void button_ao() {
		//Toast tbn= Toast.makeText(getApplicationContext(), "button news",Toast.LENGTH_LONG);
		//tbn.show();//go to news where you get a list view for news
		Intent button_ao = new Intent(this, AO_List.class);
		button_ao.putExtra("scrip", Intent_ticker.replace("^","%5E"));
		button_ao.putExtra("company_name", Intent_name);
				
	    startActivity(button_ao);
	    
	    }
	public void button_ae() {
		//Toast tbn= Toast.makeText(getApplicationContext(), "button news",Toast.LENGTH_LONG);
		//tbn.show();//go to news where you get a list view for news
		Intent button_ae = new Intent(this, AE_List.class);
		button_ae.putExtra("scrip", Intent_ticker.replace("^","%5E"));
		button_ae.putExtra("company_name", Intent_name);
				
	    startActivity(button_ae);
	    
	    }
	public void button_pr() {
		//Toast tbn= Toast.makeText(getApplicationContext(), "button news",Toast.LENGTH_LONG);
		//tbn.show();//go to news where you get a list view for news
		Intent button_pr = new Intent(this, PR_List.class);
		button_pr.putExtra("scrip", Intent_ticker.replace("^","%5E"));
		button_pr.putExtra("company_name", Intent_name);
				
	    startActivity(button_pr);
	    
	    }
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.button_buy: 
	    {button_buy(); break;}
		
		case R.id.button_sell: 
	    {button_sell(); break;}
	    
		case R.id.button_news: 
	    {button_news(); break;}
	    
		case R.id.button_ta: 
	    {button_ta(); break;}
	    
		case R.id.button_ao: 
	    {button_ao(); break;}
	    
		case R.id.button_ae: 
	    {button_ae(); break;}
	    
		case R.id.button_pr: 
	    {button_pr(); break;}
		
		}
		
	}	
	
}