package com.ciego.tienda;

import data.UserData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	private EditText etNombre;
	private EditText etPass;
	private Button btnEnter;

    private final int REQUEST_CODE = 12314;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		if(UserData.isLogged(this)){
			goToMainActivity();
		}
		
		initViews();
	}
	
	private void initViews(){
		etNombre = (EditText)findViewById(R.id.etNombre);
		etPass = (EditText)findViewById(R.id.etPass);
		btnEnter = (Button)findViewById(R.id.btnEntrar);
		
		btnEnter.setOnClickListener(this);
	}
	
	private boolean validate(){
		if(!etNombre.getText().toString().equals("Diego")){
			return false;
		}
		if(!etPass.getText().toString().equals("ciego"))
		{
			return false;
		}
		return true;
	}
	
	@Override
	public void onClick(View v) {

		if(validate()){
			//Registrar que ya inicio sesi�n
			UserData.setLogged(this, true);
			
			goToChooseStore();
		}
	}

    private void goToChooseStore(){
        Intent intent = new Intent(this, SelectStoreActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

	private void goToMainActivity(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

    private void launchBroadcast(String store){
        Intent intent = new Intent();
        intent.setAction("com.ciego.tienda.action.BROADCAST_STORE");
        intent.putExtra("STORE", store);
        sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                String store = data.getStringExtra("STORE");
                Toast.makeText(this, "Esta es la tienda :" + store,Toast.LENGTH_SHORT).show();
                goToMainActivity();
                launchBroadcast(store);

            }
        }
    }
	
	
}
