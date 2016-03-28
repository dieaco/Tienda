package com.ciego.tienda;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	
	private String currentTitle;
	private boolean isMenuOpened;
	
	private TableRow btnHome;
	private TableRow btnFriends;
	private TableRow btnCommunity;
	private TableRow btnProfile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		isMenuOpened = false;
		initSlidingMenu();
		initViews();
		//Mostramos algo en la pantalla por primera vez
		showScreen(R.id.menuOpcion1);
	}
	
	private void initSlidingMenu(){
		
		final ActionBar actionBar = getSupportActionBar(); 

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFAA11")));
		currentTitle = "Home";
		actionBar.setTitle(currentTitle);
		
		drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		//Activar la navegaci�n
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		drawerToggle = new ActionBarDrawerToggle(this,
				drawerLayout, R.string.open, R.string.close){
			
			//Evento que sucede cuando se cierra el men�
			@Override
			public void onDrawerClosed(View drawerView){
				isMenuOpened = false;
				actionBar.setTitle(currentTitle);
				super.onDrawerClosed(drawerView);
			}
			
			//Evento que sucede cuando se abre el men�
			@Override
			public void onDrawerOpened(View drawerView){
				isMenuOpened = true;
				actionBar.setTitle("Tienda");
				//Tiene que volver a construir las opciones en el Action Bar
				supportInvalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}
			
		};
		
		drawerLayout.setDrawerListener(drawerToggle);
	}
	
	//Se inicilizan los objetos de la clase MenuListener
	private void initViews(){
		btnHome = (TableRow)findViewById(R.id.menuOpcion1);
		btnFriends = (TableRow)findViewById(R.id.menuOpcion2);
		btnCommunity = (TableRow)findViewById(R.id.menuOpcion3);
		btnProfile = (TableRow)findViewById(R.id.menuOpcion4);
		
		MenuListener listener = new MenuListener();
		
		btnHome.setOnClickListener(listener);
		btnFriends.setOnClickListener(listener);
		btnCommunity.setOnClickListener(listener);
		btnProfile.setOnClickListener(listener);
	}
	
	private void showScreen(int Screen){
		
		Fragment fragment = null;
		switch (Screen) {
		case R.id.menuOpcion1:
			fragment = new HomeFragment();
			currentTitle = "Home";
			break;
		case R.id.menuOpcion2:
			fragment = new FriendsFragment();
			currentTitle = "Friends";
			break;
		case R.id.menuOpcion3:
			fragment = new CommunityFragment();
			currentTitle = "Community";
			break;
		case R.id.menuOpcion4:
			fragment = new ProfileFragment();
			currentTitle = "Profile";
			break;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
			.replace(R.id.contentFrame, fragment).commit();
		drawerLayout.closeDrawers();
	}
	
	//Aqu� se crean los Men�s
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		if(isMenuOpened){
			getMenuInflater().inflate(R.menu.menu_main, menu);
		}else{
			getMenuInflater().inflate(R.menu.menu_principal, menu);
		}
		
		//retorna si se creo el men�
		return true;
	}
	
	//Aqu� se maneja que bot�n fue presionado
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.menuPerfil){
			//Presion� el bot�n perfil
			goToUserDetail();
		}else if(drawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		
		//Retorna si se manejo o no el evento
		return super.onOptionsItemSelected(item);
	}
	
	private void goToUserDetail(){
		Intent intent = new Intent(this, UserDetailActivity.class);
		startActivity(intent);
	}
	
	//Para sincronizar la acci�n cuando el dispositivo est� en Landscpae o Portrait
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	
	
	public class MenuListener implements View.OnClickListener{
		
		@Override
		public void onClick(View v){
			showScreen(v.getId());
		}
	}
	
}
