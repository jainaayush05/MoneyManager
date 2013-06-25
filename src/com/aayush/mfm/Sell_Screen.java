package com.aayush.mfm;

import java.util.List;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class Sell_Screen extends Activity implements OnClickListener{
	
	
	Button button_submit, button_cancel;
	TextView Tticker,Tcompany_name,Tprice,Tcash;
	float cash;
	String uname="";
	String scrip,sell_price,quantity,company_name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sell_screen);

		button_submit= (Button) findViewById(R.id.submit);
		button_cancel= (Button) findViewById(R.id.cancel);
		Tticker=(TextView) findViewById(R.id.ticker);
		Tcompany_name=(TextView) findViewById(R.id.company_name);
		Tprice= (TextView) findViewById(R.id.price);
		Tcash= (TextView) findViewById(R.id.cash);
		button_cancel.setOnClickListener(this);
		button_submit.setOnClickListener(this);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
		  // do stuff with the user
			uname=currentUser.getUsername();
			
		} else {
			
		}
		ParseQuery query = ParseUser.getQuery();
		query.whereEqualTo("username", uname);
		query.findInBackground(new FindCallback() {
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        ParseObject u= (ParseUser) objects.get(0);
		        Tcash.setText(u.getString("cash"));
		        cash=Float.parseFloat(u.getString("cash"));
		    } else {
		        // Something went wrong.
		    }
		  }
		});
		
		Intent intent=getIntent();
		scrip=intent.getStringExtra("scrip");
		sell_price= intent.getStringExtra("price");
		company_name=intent.getStringExtra("company_name");
		quantity="1000";
		Tticker.setText(scrip);
		Tcompany_name.setText(company_name);
		Tprice.setText(sell_price);
		
		
		
	
		
	}
	
	public void button_submit() {
		ParseQuery query = new ParseQuery("Portfolio");
		query.whereEqualTo("scrip", scrip);
		query.findInBackground(new FindCallback() {
		    public void done(List<ParseObject> portfolioList, ParseException e) {
		        if (e == null) {
		           if(portfolioList.size()==0){
		        	   Toast.makeText(getApplicationContext(), "You dont own these shares", Toast.LENGTH_SHORT).show();
		           }
		           else{
		        	   final ParseObject portfolio= portfolioList.get(0);
			           if(Integer.parseInt(quantity)<=Integer.parseInt(portfolio.getString("quantity"))){
			           portfolio.put("quantity",Integer.toString(Integer.parseInt(portfolio.getString("quantity"))-Integer.parseInt(quantity)));
			           portfolio.put("buy_cost",Float.toString(Float.parseFloat(portfolio.getString("buy_cost"))-(Float.parseFloat(sell_price)*Integer.parseInt(quantity))));
			           portfolio.saveInBackground(new SaveCallback() {
			        	   public void done(ParseException e) {
			        		    
			        		  }
			        		});
			           updateuser(sell_price,quantity);
			           Toast.makeText(getApplicationContext(), "Successfully Sold "+quantity+" Shares of "+scrip, Toast.LENGTH_SHORT).show();
			           }
			           else{
			        	   Toast.makeText(getApplicationContext(), "You dont own enough shares", Toast.LENGTH_SHORT).show();
				           
			        	   
			           }
			           }
		           
		        } else {
		            
		        }
		    }
		});
		
		Intent button_buy = new Intent(this, Main.class);
	    startActivity(button_buy);
	    finish();
	    }
	public void button_cancel() {	
		
		Intent button_buy = new Intent(this, Ticker_Info.class);
	    startActivity(button_buy);
	    finish();
	    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.submit: 
	    {button_submit(); break;}
		
		case R.id.cancel: 
	    {button_cancel(); break;}
		
		}
		
	}
	public void updateuser(String sell_price,String quantity){
		ParseUser user = ParseUser.getCurrentUser();
		 //user.put("asset", Float.toString(Float.parseFloat(user.getString("asset"))+(buy_price*quantity)));
		 user.put("cash", Float.toString(cash+(Float.parseFloat(sell_price)*Integer.parseInt(quantity))));
		 user.saveInBackground(new SaveCallback() {
			 public void done(com.parse.ParseException e) {
				  if (e == null) {
				    // Save was successful!
				    
				  } else {
				    // Save failed. Inspect e for details.
				   
				  }
				}
		 });
	}
}