package com.aayush.mfm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
public class Scores extends Activity implements OnClickListener{
	
	Button my_score, top_scores;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		
		my_score=(Button) findViewById(R.id.my_score);
		top_scores=(Button) findViewById(R.id.top_scores);
		
		my_score.setOnClickListener(this);
		top_scores.setOnClickListener(this);
		
		
	}

	
	public void my_scores() {
		Toast tbs= Toast.makeText(getApplicationContext(), "my",Toast.LENGTH_LONG);
		tbs.show();//show list view of my score
		//Intent my_score = new Intent(this, ***.class);
	    //startActivity(my_score);
	    }
	public void top_scores() {
		Toast tbn= Toast.makeText(getApplicationContext(), "top",Toast.LENGTH_LONG);
		tbn.show();//show list view of top score
		//Intent top_score = new Intent(this, ***.class);
	    //startActivity(top_score);
	    }
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.my_score: 
	    {my_scores(); break;}
		
		case R.id.top_scores: 
	    {top_scores(); break;}
	    
		
		}
		
	}	

}
