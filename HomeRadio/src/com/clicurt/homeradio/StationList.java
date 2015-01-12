package com.clicurt.homeradio;

import com.clicurt.homeradio.adater.CustomListAdapter;
import com.clicurt.homeradio.app.AppController;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.clicurt.homeradio.model.Station;

public class StationList extends ActionBarActivity {
	// Log tag
	private static final String TAG = StationList.class.getSimpleName();

	// stations json url
	private static final String url = "https://api.myjson.com/bins/18lfn";
	private ProgressDialog pDialog;
	private List<Station> stationList = new ArrayList<Station>();
	private ListView listView;
	private CustomListAdapter adapter;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stationlist);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, stationList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
	//	getActionBar().setBackgroundDrawable(
			//	new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest stationReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Station Station = new Station();
								Station.setTitle(obj.getString("title"));
								Station.setThumbnailUrl(obj.getString("image"));

                                Station.setFrequency(((Number) obj.get("frequency"))
                                        .doubleValue());

								// adding station to stations array
								stationList.add(Station);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(stationReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_radio, menu);
		return true;
	}

}
