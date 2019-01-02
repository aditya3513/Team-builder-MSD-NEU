/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.resources.Util;

import javafx.collections.ObservableList;

/**
 * @author ideepakkrishnan
 *
 */
public class ArticleQuery implements IQuery<Article> {
	
	private Long queryId;
	private ObservableList<Article> results;
	private Person baseAuthor;
	private User owner;
	
	/**
	 * @return the queryId
	 */
	public Long getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId the queryId to set
	 */
	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}

	/**
	 * @return the results
	 */
	public ObservableList<Article> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(ObservableList<Article> results) {
		this.results = results;
	}

	/**
	 * @return the baseAuthor
	 */
	public Person getBaseAuthor() {
		return baseAuthor;
	}

	/**
	 * @param baseAuthor the baseAuthor to set
	 */
	public void setBaseAuthor(Person baseAuthor) {
		this.baseAuthor = baseAuthor;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}	

	@Override
	public void exportToCSV() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObservableList<Article> execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArticleQuery(Person baseAuthor, User owner) {
		setQueryId(Util.generateRandomNumber());
		setBaseAuthor(baseAuthor);
		setOwner(owner);
	}

}
