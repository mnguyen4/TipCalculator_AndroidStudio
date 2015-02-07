package com.science.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import com.science.application.ApplicationCache;
import com.science.tipcalculator.Summary;
import com.science.utils.Constants;

/**
 * Created by MI-88H on 2/4/2015.
 */
public class TaxChangeListener implements TextWatcher {

    private ApplicationCache cache;
    private Summary summary;

    public TaxChangeListener(ApplicationCache cache, Summary summary) {
        this.cache = cache;
        this.summary = summary;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > Constants.ZERO_INT) {
            double tax = Double.parseDouble(s.toString());
            cache.setGetTax(tax);
            cache.calculateTipAndTax();
            summary.displayBill();
        }
        else {
            double tax = Constants.ZERO_DOUBLE;
            cache.setGetTax(tax);
            cache.calculateTipAndTax();
            summary.displayBill();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
