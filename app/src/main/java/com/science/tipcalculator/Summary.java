package com.science.tipcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.science.application.ApplicationCache;
import com.science.listeners.TaxChangeListener;
import com.science.listeners.TipChangeListener;
import com.science.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Summary extends Activity {
	
	private ApplicationCache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * The callback to handle returning from another activity.
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
     * Initialize the bill summary.
     */
    private void init() {
    	// get the application cache
    	cache = (ApplicationCache) getApplicationContext();

    	// add listeners
        EditText subtotalValue = (EditText) findViewById(R.id.subtotalValue);
        subtotalValue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openItems = new Intent(v.getContext(), Items.class);
				startActivityForResult(openItems, 0);
			}
		});
        subtotalValue.setFocusable(false);

        EditText taxEdit = (EditText) findViewById(R.id.taxEdit);
        taxEdit.addTextChangedListener(new TaxChangeListener(cache, this));
        taxEdit.setText(cache.setGetTax(null).toString());

        EditText tipEdit = (EditText) findViewById(R.id.tipEdit);
        tipEdit.addTextChangedListener(new TipChangeListener(cache, this));
        tipEdit.setText(cache.setGetTip(null).toString());
        
        cache.calculateTipAndTax();
        displayBill();
    }
    
    /**
     * Calculate the bill.
     */
    public void displayBill() {
    	// format and set the subtotal text
    	NumberFormat format = new DecimalFormat("#0.00");
    	TextView subtotalValue = (TextView) findViewById(R.id.subtotalValue);
        subtotalValue.setText(format.format(cache.setGetSubtotal(null)));
        
        // format and set the tax dollar amount
        TextView taxDollarValue = (TextView) findViewById(R.id.taxDollarValue);
        taxDollarValue.setText(format.format(cache.getTaxDollar()));
        
        // format and set the tip dollar amount
        TextView tipDollarValue = (TextView) findViewById(R.id.TipDollarValue);
        tipDollarValue.setText(format.format(cache.getTipDollar()));
        
        // format and set the total amount
        TextView totalValue = (TextView) findViewById(R.id.totalValue);
        totalValue.setText(format.format(cache.getTotal()));
    }
}
