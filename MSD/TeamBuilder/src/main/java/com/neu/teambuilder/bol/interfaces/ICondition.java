package com.neu.teambuilder.bol.interfaces;

import com.neu.teambuilder.bol.businessObjects.FilterType;

/**
 * A simple interface for condition classes to implement. 
 * It can generally implemented by three types of condition class, 
 * FieldCondition, CombinedCondition, Filter. 
 * FieldCondition is used to hold search fields like Author, Conference, etc.
 * CombinedCondition is used to hold logical combine information like "AND, NOT, OR"
 * Filter is used to hold search criteria like "from 2010 to 2015"
 * @author team18
 *
 */
public interface ICondition {
	/**
	 * Add an extra condition to current condition
	 * @param condition
	 */
	public void set(FilterType type, String value);
}
