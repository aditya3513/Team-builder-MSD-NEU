package com.neu.teambuilder.bol.interfaces;

import javafx.collections.ObservableList;

public interface IQuery<T> {
	
	/**
	 * Export search results into CSV file
	 * @return
	 */
	public void exportToCSV();
	
	/**
	 * Executes the query using specified filters
	 * @return A list of authors
	 */
	public ObservableList<T> execute();
	
}
