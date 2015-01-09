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
	// action bar
    //private ActionBar actionBar;
 
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
                        showPopUp();
                        break;

                }
            }
        });
    }

    public void showPopUp(){

        // add your items, this can be done programatically
        // your items can be from a database
        ObjectItem[] ObjectItemData = new ObjectItem[40];

        ObjectItemData[0] = new ObjectItem(99.7, "Joy");
        ObjectItemData[1] = new ObjectItem(2, "Adom");
        ObjectItemData[2] = new ObjectItem(3, "Spice");
        ObjectItemData[3] = new ObjectItem(94, "Puregold");
        ObjectItemData[4] = new ObjectItem(95, "SM");
        ObjectItemData[5] = new ObjectItem(96, "7 Eleven");
        ObjectItemData[6] = new ObjectItem(97, "Ministop");
        ObjectItemData[7] = new ObjectItem(98, "Fat Chicken");
        ObjectItemData[8] = new ObjectItem(99, "Master Siomai");
        ObjectItemData[9] = new ObjectItem(100, "Mang Inasal");
        ObjectItemData[10] = new ObjectItem(101, "Mercury 2");
        ObjectItemData[11] = new ObjectItem(102, "Watson 2");
        ObjectItemData[12] = new ObjectItem(103, "Nissan 2");
        ObjectItemData[13] = new ObjectItem(104, "Puregold 2");
        ObjectItemData[14] = new ObjectItem(105, "SM 2");
        ObjectItemData[15] = new ObjectItem(106, "7 Eleven 2");
        ObjectItemData[16] = new ObjectItem(107, "Ministop 2");
        ObjectItemData[17] = new ObjectItem(108, "Fat Chicken 2");
        ObjectItemData[18] = new ObjectItem(109, "Master Siomai 2");
        ObjectItemData[19] = new ObjectItem(110, "Mang Inasal 2");

        ObjectItemData[20] = new ObjectItem(111, "Mercury 3");
        ObjectItemData[21] = new ObjectItem(112, "Watson 3");
        ObjectItemData[22] = new ObjectItem(113, "Nissan 3");
        ObjectItemData[23] = new ObjectItem(114, "Puregold 3");
        ObjectItemData[24] = new ObjectItem(115, "SM 3");
        ObjectItemData[25] = new ObjectItem(116, "7 Eleven 3");
        ObjectItemData[26] = new ObjectItem(117, "Ministop 3");
        ObjectItemData[27] = new ObjectItem(118, "Fat Chicken 3");
        ObjectItemData[28] = new ObjectItem(119, "Master Siomai 3");
        ObjectItemData[29] = new ObjectItem(120, "Mang Inasal 3");
        ObjectItemData[30] = new ObjectItem(121, "Mercury 4");
        ObjectItemData[31] = new ObjectItem(122, "Watson 4");
        ObjectItemData[32] = new ObjectItem(123, "Nissan 4");
        ObjectItemData[33] = new ObjectItem(124, "Puregold 4");
        ObjectItemData[34] = new ObjectItem(125, "SM 4");
        ObjectItemData[35] = new ObjectItem(126, "7 Eleven 4");
        ObjectItemData[36] = new ObjectItem(127, "Ministop 4");
        ObjectItemData[37] = new ObjectItem(128, "Fat Chicken 4");
        ObjectItemData[38] = new ObjectItem(129, "Master Siomai 4");
        ObjectItemData[39] = new ObjectItem(130, "Mang Inasal 4");

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
                .show();

    }

        
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // MenuInflater inflater = getMenuInflater();
    	getMenuInflater().inflate(R.menu.home_radio, menu);
        //return true;
    	// Associate searchable configuration with the SearchView
    /*	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            android.support.v7.widget.SearchView searchView = 
            		(android.support.v7.widget.SearchView) MenuItemCompat
            		.getActionView(searchItem);

            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
    	}
    	
    	// Associate searchable configuration with the SearchView
    			SearchManager searchManager = 
    					(SearchManager) getSystemService(Context.SEARCH_SERVICE);
    			MenuItem searchItem = menu.findItem(R.id.action_search);
    			SearchView searchView = 
    					(SearchView)MenuItemCompat.getActionView(searchItem);
    			searchView.setSearchableInfo(searchManager
    					.getSearchableInfo(getComponentName()));*/
    	
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
      /*  //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/
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