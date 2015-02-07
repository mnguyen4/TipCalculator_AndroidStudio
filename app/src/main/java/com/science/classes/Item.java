package com.science.classes;

import java.io.Serializable;

/**
 * The item class that represents each item in the bill.
 * @author MI-88H
 *
 */
public class Item implements Serializable {
	/**
	 * Auto generated UID.
	 */
	private static final long serialVersionUID = 7010853324097907804L;
	
	private String name = "";
	private double price = 0.0;
	
	/**
	 * The constructor for the item.
	 * @param name
	 * @param price
	 */
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Get or set the item name.
	 * @param name If this is null, the item name will not be set.
	 * @return
	 */
	public String getSetName(String name) {
		if (name != null) {
			this.name = name;
		}
		return this.name;
	}
	
	/**
	 * Get or set the item price.
	 * @param price If this is null, the item price will not be set.
	 * @return
	 */
	public double getSetPrice(Double price) {
		if (price != null) {
			this.price = price;
		}
		return this.price;
	}
}
