package com.aayush.mfm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aayush.mfm.parser.StockAPI;
import com.aayush.mfm.parser.StockModel;
import com.androidhive.jsonparsing.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
public class Register extends Activity implements OnClickListener {

/*
 * Buttons and other things need implementation
 */
	EditText username,pwd,confirm_pwd;
	Button submit_register;
	String uname;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		//Parse Initialization
		 Parse.initialize(this, "FQdSM0YZ5sEmEqJjAEpBNniDH5wY0U2ZHsVdylTQ", "Gu0o0AFsqZeMh86FLdHc0CCi8gKPu36VimVj8Iub"); 
		
		username=(EditText) findViewById(R.id.enter_username_register);
		pwd=(EditText) findViewById(R.id.enter_password_register);
		confirm_pwd=(EditText) findViewById(R.id.confirm_password_register);
		submit_register=(Button) findViewById(R.id.submit_register);
		
		
		submit_register.setOnClickListener(this);
		
		
		
		
		
}
	
class Load extends AsyncTask<String, String, String> {
	ProgressDialog progDailog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Register.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	if(pwd.getText().toString().equals(confirm_pwd.getText().toString())){
        		
        		uname= username.getText().toString();
        		ParseUser user = new ParseUser();
        		
        		user.setUsername(uname);
        		user.setPassword(pwd.getText().toString());
        		user.put("cash", "500000000");
        		user.put("asset","0");
        		user.put("rank","");
        		
        		user.signUpInBackground(new SignUpCallback() {
        			  public void done(ParseException e) {
        			    if (e == null) {
        			    	progDailog.dismiss();
        			    	Intent submit_register = new Intent(Register.this, Login.class);
        			    	startActivity(submit_register);
        				    finish();
        				    
        			    } else {
        			    	progDailog.dismiss();
        			      Toast.makeText(getApplicationContext(), e.getMessage().toUpperCase(), Toast.LENGTH_SHORT).show();
        			    }
        			  }
        			});
        		
        		
        		}
        		else{
        			progDailog.dismiss();
        			Toast.makeText(getApplicationContext(), "Password Didn't Match!", Toast.LENGTH_SHORT).show();
        		}
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            
        }
    }
	public void submit_register() {	
			new Load().execute();
		
		
	    }
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.submit_register: 
	    {submit_register(); break;}
		
		
		
		}
	}
	
}
