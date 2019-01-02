package com.neu.teambuilder.bol.businessObjects;

import com.neu.teambuilder.bol.interfaces.ICondition;

/**
 * Filter interface
 * @author team18
 *
 */
public class Filter implements ICondition{
	private FilterType type;
	private String value;
	
	/**
	 * @return the type
	 */
	public FilterType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FilterType type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public Filter() {
		// Do nothing
	}
	
	public Filter(FilterType type, String value) {
		setType(type);
		setValue(value);
	}
	
	public void set(FilterType type, String value) {
		setType(type);
		setValue(value);
	}
	
}
