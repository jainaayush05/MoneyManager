package com.aayush.mfm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class TA_Main extends Activity implements OnClickListener {

/*
 * Buttons and other things need implementation
 */
	WebView graph_ta;String url_list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ta_main);
		graph_ta=(WebView) findViewById(R.id.graph_ta);
		
		Intent intent=getIntent();
		url_list=intent.getStringExtra("url");
		graph_ta.loadUrl(url_list);

}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
