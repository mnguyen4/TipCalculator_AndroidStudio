package com.science.application;

import java.util.ArrayList;
import java.util.List;

import com.science.classes.Item;

import android.app.Application;

public class ApplicationCache extends Application {
	
	/*
	 * The total price of all items on the list
	 */
	private double subtotal = 0.0;
	
	/*
	 * The tax percentage. (Default 8.11%)
	 */
	private double tax = 8.0;
	
	/*
	 * The tax dollar amount.
	 */
	private double taxDollar = 0.0;
	
	/*
	 * The tip percentage. (Default 15%)
	 */
	private double tip = 15.0;
	
	/*
	 * The tip dollar amount.
	 */
	private double tipDollar = 0.0;
	
	/*
	 * The number of items.
	 */
	private int itemCount = 0;
	
	/*
	 * The total amount after tax and tip.
	 */
	private double total = 0.0;
	
	/*
	 * List of item names.
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * Set or get the subtotal.
	 * @param subtotal
	 * @return
	 */
	public Double setGetSubtotal(Double subtotal) {
		if (subtotal != null) {
			this.subtotal = subtotal;
		}
		return this.subtotal;
	}
	
	/**
	 * Set or get the tax.
	 * @param tax
	 * @return
	 */
	public Double setGetTax(Double tax) {
		if (tax != null) {
			this.tax = tax;
		}
		return this.tax;
	}
	
	/**
	 * Set or get the tip.
	 * @param tip
	 * @return
	 */
	public Double setGetTip(Double tip) {
		if (tip != null) {
			this.tip = tip;
		}
		
		return this.tip;
	}
	
	/**
	 * Add an item to the list.
	 * @param name
	 * @param price
	 */
	public void addItem(String name, double price) {
		Item item = new Item(name, price);
		items.add(item);
		itemCount++;
		subtotal += price;
	}
	
	/**
	 * Remove an item at the specified index.
	 * @param index
	 */
	public void removeItem(int index) {
		Item item = items.remove(index);
		itemCount--;
		subtotal -= item.getSetPrice(null);
	}
	
	/**
	 * Remove the specified object from the list.
	 * @param item
	 */
	public void removeItem(Item item) {
		items.remove(item);
		itemCount--;
		subtotal -= item.getSetPrice(null);
	}
	
	/**
	 * Get the item name at the specified index.
	 * @param index
	 * @return
	 */
	public String getNameAt(int index) {
		return items.get(index).getSetName(null);
	}
	
	/**
	 * Get the item price at the specified index.
	 * @param index
	 * @return
	 */
	public Double getPriceAt(int index) {
		return items.get(index).getSetPrice(null);
	}
	
	/**
	 * Get the number of items.
	 * @return
	 */
	public int getItemCount() {
		return itemCount;
	}
	
	/**
	 * Get the item list.
	 * @return
	 */
	public List<Item> getItemList() {
		return items;
	}
	
	/**
	 * Get the tax dollar amount.
	 * @return
	 */
	public double getTaxDollar() {
		return taxDollar;
	}
	
	/**
	 * Get the tip dollar amount.
	 * @return
	 */
	public double getTipDollar() {
		return tipDollar;
	}
	
	/**
	 * Get the bill total.
	 * @return
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * Calculate the tip, tax, and bill total.
	 */
	public void calculateTipAndTax() {
		taxDollar = subtotal * (tax/100.0);
		total = subtotal + taxDollar;
		tipDollar = total * (tip/100.0);
		total = total + tipDollar;
	}
}
