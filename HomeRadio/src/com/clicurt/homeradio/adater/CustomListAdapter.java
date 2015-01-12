package com.clicurt.homeradio.adater;

import com.clicurt.homeradio.R;
import com.clicurt.homeradio.app.AppController;
import com.clicurt.homeradio.model.Station;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Station> stationItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Station> stationItems) {
		this.activity = activity;
		this.stationItems = stationItems;
	}

	@Override
	public int getCount() {
		return stationItems.size();
	}

	@Override
	public Object getItem(int location) {
		return stationItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, parent,false);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView frequency = (TextView) convertView.findViewById(R.id.frequency);

		// getting station data for the row
		Station s = stationItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(s.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(s.getTitle());
		
		// frequency
		frequency.setText("Frequency: " + String.valueOf(s.getFrequency()));

		return convertView;
	}

}