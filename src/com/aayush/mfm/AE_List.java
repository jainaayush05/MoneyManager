package com.aayush.mfm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import com.androidhive.jsonparsing.R;
public class AE_List extends ListActivity{

/*
 * Buttons and other things need implementation
 */ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
 ArrayList<String> s;
 String scrip;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ta_list);
		Intent intent=getIntent();
		scrip=intent.getStringExtra("scrip");
		
		
		
		try {
			s=scrapper();
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.single_list_item_view,
        		new String[] {"news"},
        		new int[] {R.id.product_label}
        		);
        populateList(s);
        setListAdapter(adapter);
}

    private void populateList(ArrayList<String> news) {
    	
    	
    	for(int i=0;i<news.size();i++){
    	HashMap<String,String> tempts1 = new HashMap<String,String>();
    	tempts1.put("news",news.get(i));
    	
    	list.add(tempts1);
    	}
    	
    	
    	
    	
    	
    }


   
    public ArrayList<String> scrapper() throws IOException{
    	Document doc = Jsoup.connect("http://finance.yahoo.com/q/ae?s="+scrip).get();
    	ArrayList<String> s=new ArrayList<String>();
        int i=0;
        for (Element e : doc.select("div > div > table > tbody > tr > td > table > tbody > tr > td > table > tbody > tr")) {
            
        	if(e.childNodeSize()>=2){
                s.add(e.child(0).text()+" : "+e.child(1).text()+" : "+e.child(2).text()+" : "+e.child(3).text()+" : "+e.child(4).text());
        	}  
            
        }
        
        s.add(0," ");
        s.add(6+1," ");
        s.add(13+2," ");
        s.add(18+3," ");
        s.add(24+4," ");
        s.add(29+5," ");
        
    	return s;
    	
    }

	
}





