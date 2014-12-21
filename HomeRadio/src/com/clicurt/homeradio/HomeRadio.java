package com.clicurt.homeradio;


//import android.app.SearchManager;
//import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
//import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
//import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
//import android.widget.SearchView;

public class HomeRadio extends ActionBarActivity{
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
        
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

    }


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