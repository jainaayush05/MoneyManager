package com.aayush.mfm;

import java.util.List;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
public class Buy_Screen extends Activity implements OnClickListener{
	
	
	Button button_submit, button_cancel;
	TextView Tticker,Tcompany_name,Tprice,Tcash,Tchange,Ttotal,Tmax,Tquantity_tv;
	String uname="";
	float cash;
	int max_i;
	float change_f,total_f;
	EditText ETquantity;
	SeekBar slider;
	String scrip,buy_price,quantity,company_name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_screen);
		//quantity="0";
		button_submit= (Button) findViewById(R.id.submit);
		button_cancel= (Button) findViewById(R.id.cancel);
		Tticker=(TextView) findViewById(R.id.ticker);
		Tcompany_name=(TextView) findViewById(R.id.company_name);
		Tprice= (TextView) findViewById(R.id.price);
		Tquantity_tv= (TextView) findViewById(R.id.quantity_tv);
		Tcash= (TextView) findViewById(R.id.cash);
		Tchange=(TextView) findViewById(R.id.change);
		Ttotal=(TextView) findViewById(R.id.total);
		Tmax=(TextView) findViewById(R.id.max);
		ETquantity=(EditText) findViewById(R.id.quantity_et);
		button_cancel.setOnClickListener(this);
		button_submit.setOnClickListener(this);
		slider=(SeekBar) findViewById(R.id.slider);
		
		Intent intent=getIntent();
		scrip=intent.getStringExtra("scrip");
		buy_price= intent.getStringExtra("price");
		company_name=intent.getStringExtra("company_name");
		quantity="1000";
		
		final ParseUser currentUser = ParseUser.getCurrentUser();
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
		        Tprice.setText("Rs."+buy_price);
				Tcompany_name.setText(company_name);
				Tticker.setText(scrip);
				change_f=Integer.parseInt(quantity)*Float.parseFloat(buy_price);
				Tchange.setText(Float.toString(change_f));
				total_f=cash-change_f;
				Ttotal.setText(Float.toString(total_f));
				max_i= (int) (cash/Float.parseFloat(buy_price));
				Tmax.setText(Integer.toString(max_i));
				
		    } else {
		        // Something went wrong.
		    }
		  }
		});
		
		
		//quantity=(String) ETquantity.getText().toString();
		
		
		slider.setMax(max_i);
		  slider.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
		  {
		  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		  {
		                                                                  // TODO Auto-generated method stub
			  																quantity=Integer.toString(progress);
		                                                                 // ETquantity.setText(Integer.toString(progress));
		                                                                  Tquantity_tv.setText(Integer.toString(progress));
		                                                  }

		                                                  public void onStartTrackingTouch(SeekBar seekBar)
		  {
		                                                                  // TODO Auto-generated method stub
		                                                  }

		                                                  public void onStopTrackingTouch(SeekBar seekBar)
		  {
		                                                                  // TODO Auto-generated method stub
		                                                  }
		  });
		
		
		// quantity=Integer.toString(progress);
		 }
	  		
			
	public void button_submit() {	
		final ParseUser currentUser = ParseUser.getCurrentUser();
		ParseQuery query = new ParseQuery("Portfolio");
		query.whereEqualTo("scrip", scrip);
		query.findInBackground(new FindCallback() {
		    public void done(List<ParseObject> portfolioList, ParseException e) {
		        if (e == null) {
		           if(portfolioList.size()==0){
		        	   if(cash>=(Float.parseFloat(buy_price)*Integer.parseInt(quantity))){
		        	   save(scrip,quantity,buy_price,uname);
		        	   updateuser(buy_price, quantity);
		        	   Toast.makeText(getApplicationContext(), "Successfully Bought : "+quantity+" Shares of "+scrip, Toast.LENGTH_SHORT).show();
			           
		        	   }
		        	   else{
		        		   Toast.makeText(getApplicationContext(), "Not Enough Cash!", Toast.LENGTH_SHORT).show();
				              
		        	   }
		           }
		           else{
		        	   if(Float.parseFloat(currentUser.getString("cash"))>=(Float.parseFloat(buy_price)*Integer.parseInt(quantity))){
		        		   final ParseObject portfolio= portfolioList.get(0);
		        		   portfolio.put("quantity",Integer.toString(Integer.parseInt(portfolio.getString("quantity"))+Integer.parseInt(quantity)));
		        		   portfolio.put("buy_cost",Float.toString(Float.parseFloat(portfolio.getString("buy_cost"))+(Float.parseFloat(buy_price)*Integer.parseInt(quantity))));
		        		   portfolio.saveInBackground(new SaveCallback() {
			        	   public void done(ParseException e) {
			        		    
			        		  }
			        		});
		        		   updateuser(buy_price, quantity);
		        	   Toast.makeText(getApplicationContext(), "Successfully Bought : "+quantity+" Shares of "+scrip, Toast.LENGTH_SHORT).show();
			           
	        	   }
	        	   else{
	        		   Toast.makeText(getApplicationContext(), "Not Enough Cash!", Toast.LENGTH_SHORT).show();
			              
	        	   }
		           
		           }}
		           
		         else {
		            
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
	
	

	
	public void save(String scrip,String quantity,String buy_price, String user){
		ParseObject portfolio =new ParseObject("Portfolio");
		portfolio.put("scrip", scrip);
		portfolio.put("quantity", quantity);
		portfolio.put("username", user);
		portfolio.put("buy_cost", Float.toString(Float.parseFloat(buy_price)*Integer.parseInt(quantity)));
		portfolio.saveInBackground();
		
	}
	public void updateuser(String buy_price,String quantity){
		ParseUser user = ParseUser.getCurrentUser();
		 //user.put("asset", Float.toString(Float.parseFloat(user.getString("asset"))+(buy_price*quantity)));
		 user.put("cash", Float.toString(Float.parseFloat(user.getString("cash"))-(Float.parseFloat(buy_price)*Integer.parseInt(quantity))));
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