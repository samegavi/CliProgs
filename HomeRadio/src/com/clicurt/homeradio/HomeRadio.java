package com.clicurt.homeradio;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
//import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class HomeRadio extends ActionBarActivity{
	AlertDialog alertDialogStations;
 
    // Refresh menu item
    private MenuItem refreshMenuItem;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_radio);
       
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
              switch (position) {
                    case 0:
                        showStations();
                        break;

                }
            }
        });
    }

    public void showStations(){

        // add your items, this can be done programatically
        // your items can be from a database
        ObjectItem[] ObjectItemData = new ObjectItem[21];

        ObjectItemData[0] = new ObjectItem(99.7, "Joy");
        ObjectItemData[1] = new ObjectItem(2, "Adom");
        ObjectItemData[2] = new ObjectItem(3, "Spice");
        ObjectItemData[3] = new ObjectItem(94, "Atlantis");
        ObjectItemData[4] = new ObjectItem(95, "Y");
        ObjectItemData[5] = new ObjectItem(96, "AB Zion Radio");
        ObjectItemData[6] = new ObjectItem(97, "Amansan FM UK ");
        ObjectItemData[7] = new ObjectItem(98, "Evang. Bright Radio");
        ObjectItemData[8] = new ObjectItem(99, "Free Will Christ Radio");
        ObjectItemData[9] = new ObjectItem(100, "Ghana Waves");
        ObjectItemData[10] = new ObjectItem(101, "Highlife Radio");
        ObjectItemData[11] = new ObjectItem(102, "Mighty FM");
        ObjectItemData[12] = new ObjectItem(103, "Mighty God Radio");
        ObjectItemData[13] = new ObjectItem(104, "New Covenant Gospel");
        ObjectItemData[14] = new ObjectItem(105, "Osrane FM");
        ObjectItemData[15] = new ObjectItem(104.3, "Peace FM");
        ObjectItemData[16] = new ObjectItem(107, "Praises Radio");
        ObjectItemData[17] = new ObjectItem(108, "Rainbow Radio");
        ObjectItemData[18] = new ObjectItem(109, "Sankofa Radio");
        ObjectItemData[19] = new ObjectItem(110, "Starr Fm");

        ObjectItemData[20] = new ObjectItem(111, "Word Radio");

        // our adapter instance
        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.list_view_row_item, ObjectItemData);

        // create a new ListView, set the adapter and item click listener
        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(adapter);
        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());

        // put the ListView in the pop up
        alertDialogStations = new AlertDialog.Builder(HomeRadio.this)
                .setView(listViewItems)
                .setTitle("Radio Stations")
                .setIcon(R.drawable.radio_icon_1)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // MenuInflater inflater = getMenuInflater();
    	getMenuInflater().inflate(R.menu.home_radio, menu);   	
    			return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
	        case R.id.action_search:
	        	SearchResultsActivity();
	        	return true;
	        case R.id.action_share:
	        	return true;
	        case R.id.action_refresh:
	        	 refreshMenuItem = item;
	             // load the data from server
	             new SyncData().execute();
	        	return true;
	        case R.id.action_check_updates:
	        	return true;     
	    	default:
	    		return super.onOptionsItemSelected(item);
        }		
   }
    
    private void SearchResultsActivity() {
        Intent i = new Intent(HomeRadio.this, SearchResultsActivity.class);
        startActivity(i);
    }    
    
    private class SyncData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            // set the progress bar view
            //refreshMenuItem.setActionView(R.layout.action_progressbar);
        	MenuItemCompat.setActionView(refreshMenuItem, R.layout.action_progressbar);
        }
 
        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(String result) {
            MenuItemCompat.collapseActionView(refreshMenuItem);
            // remove the progress bar view
            MenuItemCompat.setActionView(refreshMenuItem,null);
        }
    };
}