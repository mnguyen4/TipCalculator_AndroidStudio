package com.science.tipcalculator;

import com.science.application.ApplicationCache;
import com.science.classes.Item;
import com.science.classes.ItemListAdapter;
import com.science.utils.Constants;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * The activity class for the items activity.
 * @author MI-88H
 *
 */
public class Items extends Activity {
	
	private ItemListAdapter adapter;
	private ApplicationCache cache;
	
	/**
	 * On create event handler.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		init();
	}
	
	/**
	 * Displays the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

    /**
     * The callback to handle going back to the previous activity.
     * @param item
     * @return
     */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		Intent goBack = new Intent(getApplicationContext(), Summary.class);
        setResult(Constants.RESULT_CANCEL_CODE, goBack);
        finish();
		return super.onOptionsItemSelected(item);
	}

    /**
     * The callback for when the AddItem activity finishes.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode) {
            case Constants.RESULT_OK_CODE:
                finish();
                startActivity(getIntent());
                break;
            default:
                break;
        }
    }
	
	/**
	 * Initializes the view.
	 */
	private void init() {
		cache = (ApplicationCache) getApplicationContext();
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		Button addItem = (Button) findViewById(R.id.addItem);
		addItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent addDialog = new Intent(v.getContext(), AddItem.class);
				startActivityForResult(addDialog, 0);
			}
		});
		
		createItems();
	}
	
	/**
	 * Handle click remove item button.
	 * @param v
	 */
	public void removeItemClickHandler(View v) {
		Item itemToRemove = (Item) v.getTag();
		cache.removeItem(itemToRemove);
		adapter.remove(itemToRemove);
	}
	
	/**
	 * Populate the listview with data.
	 */
	private void createItems() {
		adapter = new ItemListAdapter(Items.this, R.layout.item_list_items, cache.getItemList());
		ListView itemSummary = (ListView) findViewById(R.id.itemSummary);
		itemSummary.setAdapter(adapter);
	}
}
