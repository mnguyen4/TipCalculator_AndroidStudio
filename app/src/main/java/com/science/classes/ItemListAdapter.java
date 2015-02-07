package com.science.classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.science.tipcalculator.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Adapter for the item list view.
 * @author MI-88H
 *
 */
public class ItemListAdapter extends ArrayAdapter<Item> {
	private List<Item> items;
	private int layoutResourceId;
	private Context context;
	
	/**
	 * Constructor.
	 * @param context
	 * @param layoutResourceId
	 * @param items
	 */
	public ItemListAdapter(Context context, int layoutResourceId, List<Item> items) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.items = items;
	}
	
	/**
	 * Creates and return the list view item at the specified index.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);
		
		holder = new ItemHolder();
		holder.item = items.get(position);
		holder.removeItem = (ImageButton) row.findViewById(R.id.removeItem);
		holder.removeItem.setTag(holder.item);
		
		holder.nameText = (TextView) row.findViewById(R.id.nameText);
		holder.priceText = (TextView) row.findViewById(R.id.priceText);
		
		row.setTag(holder);
		setupItem(holder);
		
		return row;
	}
	
	/**
	 * Set the textview values.
	 * @param holder
	 */
	private void setupItem(ItemHolder holder) {
		holder.nameText.setText(holder.item.getSetName(null));
		NumberFormat format = new DecimalFormat("$#0.00");
		holder.priceText.setText(format.format(holder.item.getSetPrice(null)));
	}
	
	/**
	 * A simple class to represent the list view item.
	 * @author MI-88H
	 *
	 */
	public static class ItemHolder {
		Item item;
		TextView nameText;
		TextView priceText;
		ImageButton removeItem;
	}
}
