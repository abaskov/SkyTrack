package com.reconinstruments.reconsdkapplication;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.reconinstruments.ReconSDK.IReconEventListener;
import com.reconinstruments.ReconSDK.ReconEvent;
import com.reconinstruments.ReconSDK.ReconSDKManager;

public class ReconSDKApplicationActivity extends Activity implements IReconEventListener {
	
	private static final String TAG = ReconSDKApplicationActivity.class.getSimpleName();
	public static final String RECON_DATA_BUNDLE = "RECON_DATA_BUNDLE";
	ReconSDKManager sdkManager = ReconSDKManager.Initialize(this);

	private int eventsLogged = 0;

	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		Button startLogging = (Button) findViewById(R.id.button1);

		startLogging.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLogging();
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public void startLogging() 
	{
		try {
			sdkManager.registerListener(this, ReconEvent.TYPE_ALTITUDE
					| ReconEvent.TYPE_DISTANCE | ReconEvent.TYPE_JUMP
					| ReconEvent.TYPE_LOCATION | ReconEvent.TYPE_RUN
					| ReconEvent.TYPE_SPEED | ReconEvent.TYPE_TEMPERATURE
					| ReconEvent.TYPE_TIME | ReconEvent.TYPE_VERTICAL);
		} catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
	}

	// event change notifier callback
	public void onDataChanged(ReconEvent event, Method m) {
		Toast.makeText(this, event.getType(), Toast.LENGTH_LONG).show();
		eventsLogged++;
		
		TextView textView = (TextView)findViewById(R.id.textView1);
		textView.setText("Events Logged: " + eventsLogged);
	}

}
