package com.neu.teambuilder.dao.core;

import com.neu.teambuilder.bol.businessObjects.Entity;
import com.neu.teambuilder.bol.interfaces.IQuery;

import javafx.collections.ObservableList;

/**
 * Core interface accessed by the business layer to execute a query. 
 * It delegates the task of transforming the incoming Query object 
 * into an equivalent native query statement to QueryBuilder, and 
 * the task of executing this native query to QueryExecutor.
 * @author team18
 */
public interface IQueryEngine {
	/**
	 * Executes a database query and returns an instance which 
	 * implements ResultList interface. Suitable implementations of 
	 * QueryExecutor and QueryBuilder must be specified inside this
	 * method to transform the incoming Query object into a native 
	 * query statement (ex. an SQL statement) and execute the 
	 * statement.
	 * Refer SimpleSqlQueryEngine in this package for a sample 
	 * implementation of this interface that clarifies how QueryEngine
	 * should work.
	 * @return A list of Entity objects
	 */
	public IQuery execute(IQuery query);
}
