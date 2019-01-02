package com.neu.teambuilder.dao.core;

import java.util.List;

import com.neu.teambuilder.bol.interfaces.IQuery;

/**
 * Interface to generate native query statements. 
 * Provides methods that can transform a Query object into a native 
 * statement which the corresponding QueryExecutor can understand.
 * For example, a SQL query builder would transform the Query object
 * into a SQL statement that would in turn be passed on to a SQL 
 * executor.
 * @author team18
 * @param <T>
 */
public interface IQueryBuilder<T> {
	/**
	 * Builds a native query statement from the incoming Query object
	 * @return The native query statement as String
	 */
	public List<T> buildStatement(IQuery query);
}
