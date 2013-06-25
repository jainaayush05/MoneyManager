package com.aayush.mfm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class News extends Activity implements OnClickListener {

	/*
	 * Buttons and search bar need implementation.
	 * List view required.
	 */
	Button stock,mf,air,auto,bank,fmcg,oil,health,it,infra,tele;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_main);
		stock=(Button)findViewById(R.id.stock_news);
		mf=(Button)findViewById(R.id.mf_news);
		air=(Button)findViewById(R.id.airlines);
		auto=(Button)findViewById(R.id.auto);
		bank=(Button)findViewById(R.id.bank);
		fmcg=(Button)findViewById(R.id.fmcg);
		oil=(Button)findViewById(R.id.og);
		health=(Button)findViewById(R.id.health);
		it=(Button)findViewById(R.id.it);
		infra=(Button)findViewById(R.id.infra);
		tele=(Button)findViewById(R.id.tele);
		
		stock.setOnClickListener(this);
		mf.setOnClickListener(this);
		air.setOnClickListener(this);
		auto.setOnClickListener(this);
		bank.setOnClickListener(this);
		fmcg.setOnClickListener(this);
		oil.setOnClickListener(this);
		health.setOnClickListener(this);
		it.setOnClickListener(this);
		infra.setOnClickListener(this);
		tele.setOnClickListener(this);
		
		
		

}
	private void stock() {
		
		Intent i = new Intent(this, News_List.class);
		i.putExtra("code", "category-stocks");
		startActivity(i);
	   
	    }
private void mf() {
		
		Intent i = new Intent(this, News_List.class);
		i.putExtra("code", "personal-finance-mutual-funds");
		startActivity(i);
	   
	    }
private void air() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-airlines");
	startActivity(i);
   
    }
private void auto() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-automotive");
	startActivity(i);
   
    }
private void bank() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-banking");
	startActivity(i);
   
    }
private void fmcg() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-consumer-goods");
	startActivity(i);
   
    }
private void oil() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-energy");
	startActivity(i);
   
    }
private void health() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-healthcare");
	startActivity(i);
   
    }
private void it() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-infotech");
	startActivity(i);
   
    }
private void tele() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-telecom");
	startActivity(i);
   
    }
private void infra() {
	
	Intent i = new Intent(this, News_List.class);
	i.putExtra("code", "sector-infrastructure");
	startActivity(i);
   
    }

	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.stock_news: 
	    {stock(); break;}
		case R.id.mf_news: 
	    {mf(); break;}
	    case R.id.airlines: 
	    {air(); break;}
	    case R.id.auto: 
	    {auto(); break;}
	    case R.id.bank: 
	    {bank(); break;}
	    case R.id.fmcg: 
	    {fmcg(); break;}
	    case R.id.og: 
	    {oil(); break;}
	    case R.id.health: 
	    {health(); break;}
	    case R.id.it: 
	    {it(); break;}
	    case R.id.tele: 
	    {tele(); break;}
	    case R.id.infra: 
	    {infra(); break;}
	
		}
		
	}
}