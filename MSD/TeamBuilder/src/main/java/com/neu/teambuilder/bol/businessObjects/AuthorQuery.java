/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.resources.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author ideepakkrishnan
 *
 */
public class AuthorQuery implements IQuery<Person> {
	
	private Long queryId;
	private List<Filter> filters;
	private ObservableList<Person> results;
	private Person baseAuthor;
	private User owner;
	private boolean similarAuthor;

	public boolean isSimilarAuthor() {
		return similarAuthor;
	}

	public void setSimilarAuthor(boolean similarAuthor) {
		this.similarAuthor = similarAuthor;
	}

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
	 * @return the filters
	 */
	public List<Filter> getFilters() {
		return filters;
	}

	/**
	 * @param filters the filters to set
	 */
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * @return the results
	 */
	public ObservableList<Person> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(ObservableList<Person> results) {
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

	/* (non-Javadoc)
	 * @see com.neu.teambuilder.bol.interfaces.IQuery#exportToCSV()
	 */
	@Override
	public void exportToCSV() {
		// TODO Add logic to export the result set into a CSV file

	}
	
	@Override
	public ObservableList<Person> execute() {
		// TODO Add db query to fetch results for this query
		// 		and remove the temp data below

		
		
	
	
				
		List<String> aliases = new ArrayList<String>();
		aliases.add("Tip, Frank");
		aliases.add("Frank S Tip");
		aliases.add("Frank Steven Tip");
		
		List<String> notes = new ArrayList<String>();
		notes.add("Position: Professor");
		notes.add("Institution: Northeastern University, Boston, MA");
		notes.add("Email: franktip@neu.edu");
		
		Publisher acm = new Publisher("ACM", PublicationType.Journal, null, null);
		
		Publication acm2011 = new Publication(
				"jour/acm/2011", "Advancements in Software Design Patterns", 
				"", null, acm, "10", 2011, 5, null);
		
		Article a1 = new Article(
				"jour/acm/tip/1822", "Some title", null, "1232", 4, 2011,
				211, ArticleType.InCollection, acm2011);
		
		Article a2 = new Article(
				"jour/acm/tip/1222", "Some other title", null, "1019", 3, 2011,
				111, ArticleType.InCollection, acm2011);
		
		Article a3 = new Article(
				"jour/acm/tip/982", "One more title", null, "1928", 5, 2011,
				123, ArticleType.InCollection, acm2011);
		
		Article a4 = new Article(
				"jour/acm/tip/1233", "Another title", null, "1123", 3, 2011,
				23, ArticleType.InCollection, acm2011);
		
		Article a5 = new Article(
				"jour/acm/tip/342", "Yet another title", null, "3445", 5, 2011,
				43, ArticleType.InCollection, acm2011);
		
		Article a6 = new Article(
				"jour/acm/tip/2443", "Random title", null, "2342", 4, 2011,
				54, ArticleType.InCollection, acm2011);
		
		Article a7 = new Article(
				"jour/acm/tip/533", "Another random title", null, "3453", 5, 2011,
				334, ArticleType.InCollection, acm2011);
		
		Article a8 = new Article(
				"jour/acm/tip/564", "One more random title", null, "4533", 5, 2011,
				345, ArticleType.InCollection, acm2011);
		
		Article a9 = new Article(
				"jour/acm/tip/344", "Yet another random title", null, "1233", 2, 2011,
				122, ArticleType.InCollection, acm2011);
		
		Article a10 = new Article(
				"jour/acm/tip/533", "Third random title", null, "3452", 3, 2011,
				22, ArticleType.InCollection, acm2011);
		
		List<Article> articles = new ArrayList<Article>();
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
		articles.add(a4);
		articles.add(a5);
		articles.add(a6);
		articles.add(a7);
		articles.add(a8);
		articles.add(a9);
		articles.add(a10);
		
		ObservableList<Person> results = FXCollections.observableArrayList(
			new Person("as", aliases, "Aditya Sharma", "http://adityasharma.com", notes, null, articles, 55, 275, 4),
			new Person("ft", aliases, "Frank Tip", "http://franktip.com", notes, null, articles, 78, 345, 5),
			new Person("vs", aliases, "Vaisakh Srinivas", "http://vsrinivas.com", notes, null, articles, 18, 118, 1),
			new Person("mr", aliases, "Mohammed Rizwan", "http://mrizwan.com", notes, null, articles, 33, 224, 2),
			new Person("mar", aliases, "Manoj Ravindran", "http://mravindran.com", notes, null, articles, 21, 115, 1),
			new Person("ak", aliases, "Abhinav Kharbanda", "http://akharbanda.com", notes, null, articles, 52, 293, 4),
			new Person("jc", aliases, "Jingchao Cao", "http://jcao.com", notes, null, articles, 47, 310, 4),
			new Person("dk", aliases, "Deepak Krishnan", "http://dkrishnan.com", notes, null, articles, 41, 215, 3),
			new Person("tjj", aliases, "Tenny John Joseph", "http://tjjoseph.com", notes, null, articles, 15, 135, 1)
		);
		
		this.results = results;
		
		return this.results;
	}
	
	public ObservableList<Person> rankAuthors() {
		// TODO Add call to ranking algorithm
		return null;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof AuthorQuery) {
			AuthorQuery other = (AuthorQuery) obj;
			return this.queryId == other.queryId;
		}
		
		return false;
	}
	
	public AuthorQuery(User owner) {
		setQueryId(Util.generateRandomNumber());
		setFilters(new ArrayList<Filter>());
		setOwner(owner);
	}
	
	public AuthorQuery(User owner, Person baseAuthor) {
		setQueryId(Util.generateRandomNumber());
		setFilters(new ArrayList<Filter>());
		setBaseAuthor(baseAuthor);
		setOwner(owner);
	}
	
}
