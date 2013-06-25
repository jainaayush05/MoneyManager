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
public class Search extends Activity implements OnClickListener {

	/*
	 * Buttons and search bar need implementation.
	 * List view required.
	 */
	Button debt,equity,index;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		debt=(Button)findViewById(R.id.debt_market);
		equity=(Button)findViewById(R.id.equity_market);
		index=(Button)findViewById(R.id.index_market);
		debt.setOnClickListener(this);
		equity.setOnClickListener(this);
		index.setOnClickListener(this);
		
		

}
	private void debt() {
		
		Intent debt = new Intent(this, Debt_Search.class);
		startActivity(debt);
	   
	    }
	private void equity() {
		
		Intent equity = new Intent(this, Equity_Search.class);
	   startActivity(equity);
	   
	    }
	private void index() {
		
		Intent index = new Intent(this, Index_Search.class);
	   startActivity(index);
	   
	    }
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.debt_market: 
	    {debt(); break;}
		case R.id.equity_market: 
	    {equity(); break;}
	    case R.id.index_market: 
	    {index(); break;}
	
		}
		
	}
}