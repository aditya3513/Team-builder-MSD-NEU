/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author ideepakkrishnan
 *
 */
public class Publisher {
	
	private StringProperty title;
	private PublicationType type;
	private ListProperty<String> areas;
	private ListProperty<Publication> editions;
	
	public Publisher(
			String title, PublicationType type, 
			List<String> areas, List<Publication> editions) {
		
		setTitle(title);
		setType(type);
		
		if (areas != null) {
			setAreas(areas);
		}
		
		if (editions != null) {
			setEditions(editions);
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
	 * @return the type
	 */
	public PublicationType getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(PublicationType type) {
		this.type = type;
	}
	
	/**
	 * @return the areas
	 */
	public ObservableList<String> getAreas() {
		return areas.get();
	}
	
	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<String> areas) {
		if (this.areas == null) {
			this.areas = new SimpleListProperty<String>(this, "areas");
		}
		
		this.areas.set(FXCollections.observableArrayList(areas));
	}
	
	/**
	 * @return the editions
	 */
	public ObservableList<Publication> getEditions() {
		return editions.get();
	}
	
	/**
	 * @param editions the editions to set
	 */
	public void setEditions(List<Publication> editions) {
		if (this.editions == null) {
			this.editions = new SimpleListProperty<Publication>(this, "editions");
		}
		
		this.editions.set(FXCollections.observableArrayList(editions));
	}

}
