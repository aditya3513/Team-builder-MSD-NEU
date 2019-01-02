/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author ideepakkrishnan
 *
 */
public class Article extends Entity {
	
	private StringProperty title;
	private ListProperty<Person> authors;
	private StringProperty reviewId;
	private IntegerProperty rating;
	private IntegerProperty year;
	private IntegerProperty citations;
	private ArticleType type;
	private Publication publication;
	
	public Article () {
		
	}
	public Article(
			String key, String title, List<Person> authors, String reviewId,
			int rating, int year, int citations, ArticleType type, 
			Publication publication) {
		
		setKey(key);
		setTitle(title);
		
		if (authors != null) {
			setAuthors(authors);
		}
		
		setReviewId(reviewId);
		setRating(rating);
		setYear(year);
		setCitations(citations);
		setType(type);
		setPublication(publication);
		
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title.get();
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (this.title == null) {
			this.title = new SimpleStringProperty(this, "title");
		}
		
		this.title.set(title);
	}
	
	public StringProperty titleProperty() {
		if (this.title == null) {
			this.title = new SimpleStringProperty(this, "title");
		}
		
		return this.title;
	}
	
	/**
	 * @return the authors
	 */
	public ObservableList<Person> getAuthors() {
		return authors.get();
	}
	
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Person> authors) {
		if (this.authors == null) {
			this.authors = new SimpleListProperty<Person>(this, "authors");
		}
		
		this.authors.set(FXCollections.observableArrayList(authors));
	}
	
	/**
	 * @return the reviewId
	 */
	public String getReviewId() {
		return reviewId.get();
	}
	
	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(String reviewId) {
		if (this.reviewId == null) {
			this.reviewId = new SimpleStringProperty(this, "reviewId");
		}
		
		this.reviewId.set(reviewId);
	}
	
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating.get();
	}
	
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		if (this.rating == null) {
			this.rating = new SimpleIntegerProperty(this, "rating");
		}
		
		this.rating.set(rating);
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year.get();
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		if (this.year == null) {
			this.year = new SimpleIntegerProperty(this, "year");
		}
		
		this.year.set(year);
	}
	
	public IntegerProperty yearProperty() {
		if (this.year == null) {
			this.year = new SimpleIntegerProperty(this, "year");
		}
		
		return this.year;
	}
	
	/**
	 * @return the citations
	 */
	public int getCitations() {
		return citations.get();
	}

	/**
	 * @param citations the citations to set
	 */
	public void setCitations(int citations) {
		if (this.citations == null) {
			this.citations = new SimpleIntegerProperty(this, "citations");
		}
		
		this.citations.set(citations);
	}
	
	public IntegerProperty citationsProperty() {
		if (this.citations == null) {
			this.citations = new SimpleIntegerProperty(this, "citations");
		}
		
		return this.citations;
	}

	/**
	 * @return the type
	 */
	public ArticleType getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(ArticleType type) {
		this.type = type;
	}
	
	/**
	 * @return the publication
	 */
	public Publication getPublication() {
		return publication;
	}
	
	/**
	 * @param publication the publication to set
	 */
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	

}
