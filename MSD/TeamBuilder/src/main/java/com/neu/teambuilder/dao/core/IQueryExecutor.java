package com.neu.teambuilder.dao.core;

import com.neu.teambuilder.bol.businessObjects.Entity;
import com.neu.teambuilder.bol.interfaces.IQuery;

import javafx.collections.ObservableList;

/**
 * Interface which must be implemented to execute queries on the dataset 
 * @author team18
 */
public interface IQueryExecutor {
	/**
	 * Execute a query and return results to the caller. This method must
	 * internally call a helper method that converts the query result  
	 * into a ResultList object.
	 * @param query
	 * @return A list of Entity objects
	 */
	public IQuery executeQuery(String queryStatment, IQuery query);
}
