package com.ciego.tienda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ciego on 13/12/14
 */
public class ProfileFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
	}

}
