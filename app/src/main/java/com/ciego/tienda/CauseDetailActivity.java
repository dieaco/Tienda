package com.ciego.tienda;

import models.Cause;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CauseDetailActivity extends Activity {

    private ImageView btnFollow;
    private ImageView btnShare;
    private TextView tvDays;
    private TextView tvProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cause_detail);

        initViews();
	}
	
	private void initViews(){
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		Cause cause = (Cause)bundle.getSerializable("CAUSE");

        btnFollow = (ImageView)findViewById(R.id.ivFollow);
        btnShare = (ImageView)findViewById(R.id.ivShare);

        tvDays = (TextView)findViewById(R.id.tvDays);
        tvProgress = (TextView)findViewById(R.id.tvPorcentaje);
	}


}
