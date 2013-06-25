package com.aayush.mfm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class Main extends Activity implements OnClickListener{
	
	
	public ImageButton refresh, search, settings, scores;
	public Button news_main,portfolio_main;
	public TextView investor, cash, assets, worth, player_rank,Tsensex,Tnifty,top_g,top_l;
	public WebView graph_main;
	public String cash_str, assets_str, worth_str; public int worth_int;
	ParseUser currentUser;
	String uname;
	StockModel stock_sensex,stock_nifty;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portfolio);
		
		//Oncreate objects
		refresh= (ImageButton) findViewById(R.id.refresh);
		search= (ImageButton) findViewById(R.id.search);
		settings= (ImageButton) findViewById(R.id.settings);
		scores= (ImageButton) findViewById(R.id.scores);
		news_main= (Button) findViewById(R.id.news_main);
		portfolio_main=(Button) findViewById(R.id.portfolio_main);
		top_g= (Button) findViewById(R.id.top_g);
		top_l= (Button) findViewById(R.id.top_l);
		graph_main=(WebView) findViewById(R.id.graph_main);
		
		top_g.setOnClickListener(this);
		top_l.setOnClickListener(this);
		refresh.setOnClickListener(this);
		search.setOnClickListener(this);
		settings.setOnClickListener(this);
		scores.setOnClickListener(this);
		news_main.setOnClickListener(this);
		portfolio_main.setOnClickListener(this);
		
		investor= (TextView) findViewById(R.id.username);
	
		cash= (TextView) findViewById(R.id.cash);
		assets= (TextView) findViewById(R.id.assets);
		worth= (TextView) findViewById(R.id.worth);
		player_rank= (TextView) findViewById(R.id.player_rank);
		Tnifty= (TextView) findViewById(R.id.Tnifty);
	    Tsensex= (TextView) findViewById(R.id.Tsensex);
	    
	    new LoadData().execute();
		
	}
	@Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
	
    
    //Async Task to get Stock Data
    class LoadData extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Main.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show

    	    cash_str="5678"; assets_str="1234";
    	    //Getting Data
    	    stock_sensex= StockAPI.getStockData("%5EBSESN");
    		stock_nifty= StockAPI.getStockData("%5ENSEI");

    		
    		
    		int worth_int=Integer.valueOf(cash_str)+Integer.valueOf(assets_str);
    		worth_str=Integer.toString(worth_int);
    		graph_main.loadUrl("http://ichart.finance.yahoo.com/t?s=^BSESN");
    		
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            Tsensex.setText(stock_sensex.getPrice());
    		Tnifty.setText(stock_nifty.getPrice());
    		cash.setText("Rs."+cash_str);
    		assets.setText("Rs."+assets_str);
    		worth.setText("Rs."+worth_str);
    		player_rank.setText("12");
    		
    		currentUser = ParseUser.getCurrentUser();
    		if (currentUser != null) {
    			investor.setText((uname=currentUser.getUsername()));
    			
    			
    		} else {
    			Toast.makeText(getApplicationContext(), "You are not logged in.Please login!", Toast.LENGTH_SHORT).show();
    		}
    		ParseQuery query = ParseUser.getQuery();
    		query.whereEqualTo("username", uname);
    		query.findInBackground(new FindCallback() {
    		  public void done(List<ParseObject> objects, ParseException e) {
    		    if (e == null) {
    		        currentUser= (ParseUser) objects.get(0);
    		        
    		    } else {
    		        // Something went wrong.
    		    }
    		  }
    		});
    			
    		cash.setText("Rs."+currentUser.getString("cash"));
    		progDailog.dismiss();
        }
    }
	
	
	
    
    
    //On click Listeners
	private void scores() {
		
		Intent scores = new Intent(this, Portfolio_list_index.class);
		startActivity(scores);
	  
	    }
	private void search() {
		
		Intent search = new Intent(this, Search.class);
		startActivity(search);
	  
	    }
	private void refresh() {
		
		Intent refresh = new Intent(this, Main.class);
	    startActivity(refresh);
	    
	    }
	private void settings() {
		
		Intent settings = new Intent(this, Settings.class);
		startActivity(settings);
	  
	    }
	private void news_main() {
		
		Intent news_main = new Intent(this, News.class);
	    startActivity(news_main);
	    
	    }
	private void portfolio_main() {
		
		Intent portfolio_main = new Intent(Main.this, Portfolio_list_equity.class);
	    startActivity(portfolio_main);
	   
	    }
	private void top_g() {
		
		Intent top_g = new Intent(Main.this,Top_GL.class);
		top_g.putExtra("code","gainers");
	    startActivity(top_g);
	   
	    }
	private void top_l() {
		
		Intent top_l = new Intent(Main.this, Top_GL.class);
		top_l.putExtra("code","losers");
	    startActivity(top_l);
	   
	    }
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.refresh: 
	    {refresh(); break;}
		case R.id.search: 
	    {search(); break;}
	    case R.id.scores: 
	    {scores(); break;}
		case R.id.settings: 
	    {settings(); break;}
		case R.id.news_main: 
	    {news_main(); break;}
		case R.id.portfolio_main: 
	    {portfolio_main(); break;}
		case R.id.top_g: 
	    {top_g(); break;}
		case R.id.top_l: 
	    {top_l(); break;}
	
		}
		
	}	
	
	

}
