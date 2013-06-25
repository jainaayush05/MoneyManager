package com.aayush.mfm;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class TA_List extends ListActivity implements OnClickListener {

/*
 * Buttons and other things need implementation
 */ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	String scrip,company_name;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ta_list);
		Intent intent=getIntent();
		scrip=intent.getStringExtra("scrip");
		company_name=intent.getStringExtra("company_name");
		Toast.makeText(getApplicationContext(), scrip+":"+company_name, Toast.LENGTH_SHORT).show();

		
		
		
		
		
		
		SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.single_list_item_view,
        		new String[] {"graph_type"},
        		new int[] {R.id.product_label}
        		);
        populateList(scrip);
        setListAdapter(adapter);
}

    private void populateList(String scrips) {
    	
    	
    	
    	HashMap<String,String> tempts1 = new HashMap<String,String>();
    	tempts1.put("graph_type","Time Span: 5 Days");
    	tempts1.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=5d" );
    	list.add(tempts1);
    	HashMap<String,String> tempts2 = new HashMap<String,String>();
    	tempts2.put("graph_type","Time Span: 3 Months");
    	tempts2.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=3m" );
    	list.add(tempts2);
    	HashMap<String,String> tempts3 = new HashMap<String,String>();
    	tempts3.put("graph_type","Time Span: 6 Months");
    	tempts3.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m" );
    	list.add(tempts3);
    	HashMap<String,String> tempts4 = new HashMap<String,String>();
    	tempts4.put("graph_type","Time Span: 1 Year");
    	tempts4.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=1y" );
    	list.add(tempts4);
    	HashMap<String,String> tempts5 = new HashMap<String,String>();
    	tempts5.put("graph_type","Time Span: Max");
    	tempts5.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=my" );
    	list.add(tempts5);
    	
    	HashMap<String,String> tempt1 = new HashMap<String,String>();
    	tempt1.put("graph_type","Type: Line");
    	tempt1.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l" );
    	list.add(tempt1);
    	HashMap<String,String> tempt2 = new HashMap<String,String>();
    	tempt2.put("graph_type","Type: Bar");
    	tempt2.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=b" );
    	list.add(tempt2);
    	HashMap<String,String> tempt3 = new HashMap<String,String>();
    	tempt3.put("graph_type","Type: Candle");
    	tempt3.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=c" );
    	list.add(tempt3);
    	
    	HashMap<String,String> temp2 = new HashMap<String,String>();
    	temp2.put("graph_type","Log Scale");
    	temp2.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on" );
    	list.add(temp2);
    	
    	HashMap<String,String> temp3 = new HashMap<String,String>();
    	temp3.put("graph_type","Size: Medium");
    	temp3.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=m" );
    	list.add(temp3);
    	HashMap<String,String> temps3 = new HashMap<String,String>();
    	temp3.put("graph_type","Size: Large");
    	temp3.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l" );
    	list.add(temps3);
    	
    	
    	HashMap<String,String> temp4 = new HashMap<String,String>();
    	temp4.put("graph_type","Moving Average Indicator (Interval 50, 200)");
    	temp4.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=m50,m200" );
    	list.add(temp4);
    	
    	HashMap<String,String> temp5 = new HashMap<String,String>();
    	temp5.put("graph_type","Exp. Moving Average Indicator");
    	temp5.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=e50,e200" );
    	list.add(temp5);
    	
    	HashMap<String,String> temp6 = new HashMap<String,String>();
    	temp6.put("graph_type","Volume/ Bollinger Bands");
    	temp6.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=v,b" );
    	list.add(temp6);
    	
    	HashMap<String,String> temp7 = new HashMap<String,String>();
    	temp7.put("graph_type","Rate of Change");
    	temp7.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=m50,e200,v&a=p12,p" );
    	list.add(temp7);
    	
    	HashMap<String,String> tempv7 = new HashMap<String,String>();
    	tempv7.put("graph_type","William Percent Range");
    	tempv7.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=m50,e200,v&a=w14,p" );
    	list.add(tempv7);
    	
    	HashMap<String,String> tempa7 = new HashMap<String,String>();
    	tempa7.put("graph_type","Relative Strength Index");
    	tempa7.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=m50,e200,v&a=r14,p" );
    	list.add(tempa7);
    	
    	HashMap<String,String> tempaw7 = new HashMap<String,String>();
    	tempaw7.put("graph_type","Money Flow Index");
    	tempaw7.put("url","http://chart.finance.yahoo.com/z?s="+scrips+"&t=6m&q=l&l=on&z=l&p=m50,e200,v&a=f14,p" );
    	list.add(tempaw7);
    	
    	
    	
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
    	HashMap<String,String> temp1 = new HashMap<String,String>();
    	temp1=(HashMap<String, String>) getListAdapter().getItem(position);
		String ta_type_url=temp1.get("url");
		Toast.makeText(this, ta_type_url, Toast.LENGTH_SHORT).show();
		Intent ta = new Intent(this, TA_Main.class);
		ta.putExtra("url",ta_type_url );
		
				
	   startActivity(ta);
	    

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
