package br.com.k19.android.cap04_02;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button viewSiteButton = (Button) findViewById(R.id.view_site_button);
		Button sendEmailButton = (Button) findViewById(R.id.send_email_button);
		Button makeCallButton = (Button) findViewById(R.id.make_call_button);
	
		viewSiteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/renato.carvalhan?ref=tn_tnmn"));
				startActivity(i);
			}
		});
	
	
	sendEmailButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("plain/text");
			i.putExtra(Intent.EXTRA_EMAIL, new String[]{"carvalhan.r3cotia@gmail.com"});
			startActivity(Intent.createChooser(i, "Eviar email"));
		}
	});
  
	makeCallButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Intent.ACTION_DIAL, Uri
					.parse("tel: 99727-9375"));
			startActivity(i);
		}
	});
  }
}

