package com.neu.teambuilder.dao.core;
import java.util.List;

/**
 * A wrapper interface used to hold the result returned from a query
 * executed by the query engine
 * @author team18
 */
public interface IResultList {
	
	/**
	 * Returns the number of records in the result list
	 * @return
	 */
	public int getSize();
	
	/**
	 * Returns an element stored at a particular list in the list
	 * @param index
	 * @return
	 */
	public Object getElement(int index);
	
	/**
	 * Returns a subset of elements starting at the index
	 * specified by start and ending at the index specified
	 * by end.
	 * @param start The starting index
	 * @param end The ending index
	 * @return
	 */
	public List<Object> getElements(int start, int end);
	
}
