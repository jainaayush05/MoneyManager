package com.aayush.mfm;

import android.annotation.SuppressLint;
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

import com.androidhive.jsonparsing.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
public class Login extends Activity implements OnClickListener {

	EditText username_login,pwd_login;
	Button submit_login,register_login;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		//Parse Initialization
		 Parse.initialize(this, "FQdSM0YZ5sEmEqJjAEpBNniDH5wY0U2ZHsVdylTQ", "Gu0o0AFsqZeMh86FLdHc0CCi8gKPu36VimVj8Iub"); 
		 
		
		username_login=(EditText) findViewById(R.id.edit_username_login);
		pwd_login=(EditText) findViewById(R.id.edit_password_login);
		submit_login=(Button) findViewById(R.id.submit_login);
		register_login=(Button) findViewById(R.id.register_login);
		submit_login.setOnClickListener(this);
		register_login.setOnClickListener(this);
		
		//Check if alredy logged in
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
		  // do stuff with the user
			Intent submit_login = new Intent(Login.this, Main.class);
			startActivity(submit_login);
			finish();
			Toast.makeText(getApplicationContext(), "Welcome "+currentUser.getUsername()+"!.You are successfully logged in!", Toast.LENGTH_SHORT).show();
			finish();
			
		} else {
			Toast.makeText(getApplicationContext(), "Please login!", Toast.LENGTH_SHORT).show();
		}
		

}

	//Async Task for Loading....
class Load extends AsyncTask<String, String, String> {
        ProgressDialog progDailog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(Login.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            //do something while spinning circling show
        	if(!pwd_login.getText().toString().equals("")&&!username_login.getText().toString().equals("")){
        		ParseUser.logInInBackground(username_login.getText().toString(), pwd_login.getText().toString(), new LogInCallback() {
        			@SuppressLint("DefaultLocale")
					@Override
        			public void done(ParseUser user, ParseException e) {
        				
        				if (user != null) {
        					// Hooray! The user is logged in.
        						progDailog.dismiss();
        						Intent submit_login = new Intent(Login.this, Main.class);
        						startActivity(submit_login);
        						
        						Toast.makeText(getApplicationContext(), "Welcome "+user.getUsername()+"!", Toast.LENGTH_SHORT).show();
        						finish();
        						
        				      
        				    } else {
        				      // Signup failed. Look at the ParseException to see what happened.
        				    	progDailog.dismiss();
        				    	Toast.makeText(getApplicationContext(), e.getMessage().toUpperCase(), Toast.LENGTH_SHORT).show();
        				    }
        				
        			}
        			});
        		}else{
        			
        			Toast.makeText(getApplicationContext(), "Username or Password Empty!", Toast.LENGTH_SHORT).show();
        		}
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            
        }
    }
	
	
	public void register_login() {	
		//Toast tbb= Toast.makeText(getApplicationContext(), "button buy",Toast.LENGTH_LONG);
		//tbb.show();//go to buy screen for selecting no.of stocks to buy
		Intent register_login = new Intent(this, Register.class);
	    startActivity(register_login);
	    }
	public void submit_login() {
		new Load().execute();
		
		
	    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
		
		case R.id.submit_login: 
	    {submit_login(); break;}
		case R.id.register_login: 
	    {register_login(); break;}
		
		
		
		}
	}
}
