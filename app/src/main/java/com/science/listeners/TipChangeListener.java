package com.science.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import com.science.application.ApplicationCache;
import com.science.tipcalculator.Summary;
import com.science.utils.Constants;

import java.util.PriorityQueue;

/**
 * Created by MI-88H on 2/4/2015.
 */
public class TipChangeListener implements TextWatcher {

    private ApplicationCache cache;
    private Summary summary;

    public TipChangeListener(ApplicationCache cache, Summary summary) {
        this.cache = cache;
        this.summary = summary;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > Constants.ZERO_INT) {
            double tip = Double.parseDouble(s.toString());
            cache.setGetTip(tip);
            cache.calculateTipAndTax();
            summary.displayBill();
        }
        else {
            double tip = Constants.ZERO_DOUBLE;
            cache.setGetTip(tip);
            cache.calculateTipAndTax();
            summary.displayBill();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
