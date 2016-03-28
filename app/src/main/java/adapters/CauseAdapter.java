package adapters;

import java.util.ArrayList;


import com.ciego.tienda.R;

import images.ImagesUtil;
import models.Cause;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CauseAdapter extends BaseAdapter{
	
	private ArrayList<Cause> listCause;
	private LayoutInflater inflater;
    private Context context;

    private CauseAdapter adapter;
	
	public CauseAdapter(Context context, ArrayList<Cause> listCauses){
		this.listCause = listCauses;
        this.context = context;

		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        adapter = this;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listCause.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listCause.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

    @SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		if(convertView ==  null){
			view = inflater.inflate(R.layout.cause_item, null);
		}
		
		TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
		TextView tvTime = (TextView)view.findViewById(R.id.tvTime);
		TextView tvPercentage = (TextView)view.findViewById(R.id.tvPercentage);
        ImageView ivItem = (ImageView)view.findViewById(R.id.imageItem);
		
		Cause cause = listCause.get(position);

        if(!ImagesUtil.existCauseImage(context, cause)){
            //Descargala
            new AsyncImage().execute(cause);
        }

        Bitmap bitmap = ImagesUtil.getCauseImage(context, cause);
        ivItem.setImageBitmap(bitmap);

        tvTitle.setText(cause.getTitle());
		tvTime.setText(cause.getDaysRemaining()+ " days");
		tvPercentage.setText(cause.getPercentage()+ " %");
		
		return view;
	}

    private class AsyncImage extends AsyncTask<Cause, Void, Void>{
        @Override
        protected Void doInBackground(Cause... params){

            Cause cause = params[0];
            ImagesUtil.saveCauseImage(context, cause);
            return null;
        }

        @Override
        protected void onPostExecute(Void param){
            try {
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
