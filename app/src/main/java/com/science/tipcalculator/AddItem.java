package com.science.tipcalculator;

import com.science.application.ApplicationCache;
import com.science.utils.Constants;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		Intent goBack = new Intent(getApplicationContext(), Items.class);
        setResult(Constants.RESULT_CANCEL_CODE, goBack);
        finish();
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Initialize the display and button listeners.
	 */
	private void init() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Button itemAdd = (Button) findViewById(R.id.itemAddOk);
		itemAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addItemToList();
			}
		});
	}
	
	/**
	 * Add new item to list.
	 */
	private void addItemToList() {
		// get the application cache
    	ApplicationCache cache = (ApplicationCache) getApplicationContext();
    	
    	// get the item name
    	EditText itemName = (EditText) findViewById(R.id.itemName);
    	String name = (String) itemName.getText().toString();
    	
    	// get the item price
    	EditText itemPrice = (EditText) findViewById(R.id.itemPrice);
    	String price = (String)itemPrice.getText().toString();
        double priceDbl = 0;

        // Check for blank name field
        if (name.trim().length() < 1) {
            name = Constants.DEFAULT_ITEM;
        }
        // check for blank price field
        if (price.trim().length() < 1) {
            priceDbl = Constants.DEFAULT_PRICE;
        } else {
            priceDbl = Double.parseDouble(price);
        }
    	
    	cache.addItem(name, priceDbl);

    	Intent goBack = new Intent(getApplicationContext(), Items.class);
        setResult(Constants.RESULT_OK_CODE, goBack);
		finish();
	}
}
