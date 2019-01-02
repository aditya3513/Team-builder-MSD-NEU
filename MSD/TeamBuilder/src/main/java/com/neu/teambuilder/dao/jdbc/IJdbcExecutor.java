package com.neu.teambuilder.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.neu.teambuilder.bol.businessObjects.Entity;
import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.dao.core.IQueryExecutor;
import com.neu.teambuilder.dao.core.IResultList;

import javafx.collections.ObservableList;

/**
 * Interface that extends QueryExecutor to interact with the database 
 * through JDBC driver.
 * @author team18
 */
public interface IJdbcExecutor extends IQueryExecutor{
	/**
	 * Create a connection with database
	 */
	public Connection buildConnection();
	
	/**
	 * Close connection with database
	 * @return Database connection
	 */
	public void closeConnection(Connection c);
	
	/**
	 * Must be implemented to execute database queries. The query result 
	 * must be converted to Entity objects using any of the following 
	 * helper methods: convertToThesis(), convertToPerson(), convertToPublication(),
	 * convertToManuscript(), convertToPublisher() before wrapping them in a 
	 * ResultList object.
	 */
	@Override
	public IQuery executeQuery(String queryStatment, IQuery query);
}
