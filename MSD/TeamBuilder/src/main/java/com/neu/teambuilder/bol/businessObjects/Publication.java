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
public class Publication extends Entity {
	
	private StringProperty title;
	private StringProperty bookTitle;
	private ListProperty<Person> editors;
	private Publisher publisher;
	private StringProperty volume;
	private IntegerProperty year;
	private IntegerProperty month;
	private ListProperty<Article> contents;
	
	public Publication () {
		
	}
	public Publication(
			String key, String title, String bookTitle, List<Person> editors,
			Publisher publisher, String volume, int year, int month, 
			List<Article> contents) {
		
		setKey(key);
		setTitle(title);
		setBookTitle(bookTitle);
		
		if (editors != null) {
			setEditors(editors);
		}
		
		setPublisher(publisher);
		setVolume(volume);
		setYear(year);
		setMonth(month);
		
		if (contents != null) {
			setContents(contents);
		}
		
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
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle.get();
	}
	
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		if (this.bookTitle == null) {
			this.bookTitle = new SimpleStringProperty(this, "bookTitle");
		}
		
		this.bookTitle.set(bookTitle);
	}
	
	/**
	 * @return the editors
	 */
	public ObservableList<Person> getEditors() {
		return editors.get();
	}
	
	/**
	 * @param editors the editors to set
	 */
	public void setEditors(List<Person> editors) {
		if (this.editors == null) {
			this.editors = new SimpleListProperty<Person>(this, "editors");
		}
		
		this.editors.set(FXCollections.observableArrayList(editors));
	}
	
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume.get();
	}
	
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		if (this.volume == null) {
			this.volume = new SimpleStringProperty(this, "volume");
		}
		
		this.volume.set(volume);
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
	
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month.get();
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		if (this.month == null) {
			this.month = new SimpleIntegerProperty(this, "month");
		}
		
		this.month.set(month);
	}
	
	/**
	 * @return the contents
	 */
	public ObservableList<Article> getContents() {
		return contents.get();
	}
	
	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<Article> contents) {
		if (this.contents == null) {
			this.contents = new SimpleListProperty<Article>(this, "contents");
		}
		
		this.contents.set(FXCollections.observableArrayList(contents));
	}

}
