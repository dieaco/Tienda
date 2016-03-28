package com.ciego.tienda;

import java.util.ArrayList;

import cache.AppCache;
import models.Cause;
import adapters.CauseAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Created by Ciego on 13/12/14
 */
public class HomeFragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		
		return inflater.inflate(R.layout.fragment_home, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		
		initViews();
	}
	
	private void initViews(){
		listView = (ListView)getActivity().findViewById(R.id.listViewHome);
		listView.setOnItemClickListener(this);
		
		ArrayList<Cause> listCauses = AppCache.getListCauses();
		//listCauses.add(new Cause("Support Amy", 10, 8));
		//listCauses.add(new Cause("Support Diego", 90, 91));
		//listCauses.add(new Cause("Support Carlos", 30, 4));
		//listCauses.add(new Cause("Support Martina", 5, 1));
		
		CauseAdapter adapter = new CauseAdapter(getActivity(), listCauses);
		
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Cause cause = (Cause)parent.getItemAtPosition(position);
		
		Intent intent = new Intent(getActivity(), CauseDetailActivity.class);
		intent.putExtra("Cause", cause);
		startActivity(intent);		
	}

}
