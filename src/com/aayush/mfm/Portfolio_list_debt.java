package com.aayush.mfm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SimpleAdapter;

public class Portfolio_list_debt extends ListActivity implements OnClickListener {
    /** Called when the activity is first created. */
	String uname;
	static ArrayList<String> scrips=new ArrayList<String>();
	static ArrayList<String> quantity=new ArrayList<String>();
	static ArrayList<String> buy_cost=new ArrayList<String>();
	static boolean flag=true;
	Button debt_pf,equity_pf,index_pf;
	SimpleAdapter adapter ;

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(); 
	HashMap<String, StockModel> hash= new HashMap<String, StockModel>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_list_equity);
      debt_pf=(Button) findViewById(R.id.debt_portfolio);
      equity_pf=(Button) findViewById(R.id.equity_portfolio);
      index_pf=(Button) findViewById(R.id.index_portfolio);
      debt_pf.setOnClickListener(this);
      equity_pf.setOnClickListener(this);
      index_pf.setOnClickListener(this);
     
		
		
				
        
      new Load().execute();
        
    }
    
    class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Portfolio_list_debt.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	//PARSE
            final ParseUser currentUser = ParseUser.getCurrentUser();
      		if (currentUser != null) {
      		  // do stuff with the user
      			uname=currentUser.getUsername();
      			
      			
      		} else {
      			
      		}
      		ParseQuery query = new ParseQuery("Index");
      		query.whereEqualTo("username", uname);
      		query.findInBackground(new FindCallback() {
      		    public void done(List<ParseObject> portfolioList, ParseException e) {
      		        if (e == null) {
      		         for(ParseObject p:portfolioList){
      		        	  
      		        	   if(Float.parseFloat(p.getString("quantity"))!=0.0f){
      		        		   scrips.add(p.getString("scrip"));
      		        		   quantity.add(p.getString("quantity"));
      		        		   buy_cost.add(p.getString("buy_cost"));
      		        		   
      		        	   }
      		        	   
      		           }
      		         
      	        	   }
      	        	 else {
      		            
      		        }
      		      if(scrips.size()!=0){
          			hash= StockAPI.getMultipleStockPrice(scrips);
          			}else{
          				Toast.makeText(getApplicationContext(), "No Item in Portfolio!", Toast.LENGTH_SHORT).show();
          			}
                  execulteatlast();
                  populateList(hash,scrips);
                  setListAdapter(adapter);
                 
              
                
                scrips=new ArrayList<String>();
                quantity=new ArrayList<String>();
                buy_cost=new ArrayList<String>();
      		      progDailog.dismiss();
      		    }
      		});
      	//Code to get multiple stock price
			
			
			
		
			
      		
      		
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            
        }
    }
    public void execulteatlast()
	{
		adapter = new SimpleAdapter(
	      		this,
	      		list,
	      		R.layout.portfolio_row,
	      		new String[] {"ticker","company_name","shares","price","value","change"},
	      		new int[] {R.id.ticker,R.id.company_name, R.id.shares, R.id.price, R.id.value, R.id.change}
	      		);
	}	
    private void populateList(HashMap<String, StockModel> hash,ArrayList<String> scrips) {
    	
    	
    	for(int i=0;i<scrips.size();i++){
    		float value=Integer.parseInt(quantity.get(i))*Float.parseFloat(hash.get(scrips.get(i)).getPrice());
    		float cost=Float.parseFloat(buy_cost.get(i));
    	HashMap<String,String> temp = new HashMap<String,String>();
    	temp.put("ticker",hash.get(scrips.get(i)).getTicker());
    	temp.put("company_name", hash.get(scrips.get(i)).getName());
    	temp.put("shares", quantity.get(i));
    	temp.put("price", hash.get(scrips.get(i)).getPrice());
    	temp.put("value", Float.toString(value));
    	temp.put("change",(Float.toString((value-cost)/cost*100)+"%").substring((Float.toString((value-cost)/cost*100)).indexOf("."),(Float.toString((value-cost)/cost*100)).indexOf(".")+3)+"%");
    	list.add(temp);
    	}
    	 
    	
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
    	HashMap<String,String> temp1 = new HashMap<String,String>();
    	temp1=(HashMap<String, String>) getListAdapter().getItem(position);
		String ticker_c=temp1.get("ticker");
		Toast.makeText(this, (ticker_c.substring(ticker_c.indexOf(":")+1)).trim().replace("^","%5E"), Toast.LENGTH_SHORT).show();
		Intent i=new Intent(Portfolio_list_debt.this,Ticker_Info.class);
		i.putExtra("scrip",(ticker_c.substring(ticker_c.indexOf(":")+1)).trim().replace("^","%5E") );
		startActivity(i);

	}
   private void debt_pf() {
		//Toast sco= Toast.makeText(getApplicationContext(), "scores",Toast.LENGTH_LONG);
		//sco.show();
		Intent debt_pf = new Intent(Portfolio_list_debt.this, Portfolio_list_debt.class);
	   startActivity(debt_pf);
	   finish();
	    }
    private void equity_pf() {
		//Toast sco= Toast.makeText(getApplicationContext(), "scores",Toast.LENGTH_LONG);
		//sco.show();
		Intent equity_pf = new Intent(Portfolio_list_debt.this, Portfolio_list_equity.class);
	   startActivity(equity_pf);
	   finish();
	    }
    private void index_pf() {
		//Toast sco= Toast.makeText(getApplicationContext(), "scores",Toast.LENGTH_LONG);
		//sco.show();
		Intent index_pf = new Intent(Portfolio_list_debt.this, Portfolio_list_index.class);
	   startActivity(index_pf);
	   finish();
	    }


	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
		
		case R.id.debt_portfolio: 
	    {debt_pf(); break;}
		case R.id.equity_portfolio: 
	    {equity_pf(); break;}
	    case R.id.index_portfolio: 
	    {index_pf(); break;}
		
	}




	}
	
	
}
