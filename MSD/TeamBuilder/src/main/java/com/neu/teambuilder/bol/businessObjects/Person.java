/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.ArrayList;
import java.util.List;

import com.neu.teambuilder.resources.Util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * @author ideepakkrishnan
 *
 */
public class Person extends Entity {
	Long personId;
	ListProperty<String> aliases;
	StringProperty name;
	StringProperty homePageUrl;
	StringProperty imageUrl;
	ListProperty<String> notes;
	ListProperty<Person> duplicates;
	ListProperty<Article> articles;
	IntegerProperty citationCount;
	IntegerProperty publicationCount;
	IntegerProperty ranking;
	
	public Person() {
		
	}
	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the aliases
	 */
	public ListProperty<String> getAliases() {
		return this.aliases;
	}

	/**
	 * @param aliases the aliases to set
	 */
	public void setAliases(List<String> aliases) {
		if (this.aliases == null) {
			this.aliases = new SimpleListProperty<String>(this, "aliases");
		}
		
		this.aliases.set(FXCollections.observableArrayList(aliases));
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (this.name == null) {
			this.name = new SimpleStringProperty(this, "name");
		}
		
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		if (this.name == null) {
			this.name = new SimpleStringProperty(this, "name");
		}
		
		return this.name;
	}

	/**
	 * @return the homePageUrl
	 */
	public String getHomePageUrl() {
		return homePageUrl.get();
	}

	/**
	 * @param homePageUrl the homePageUrl to set
	 */
	public void setHomePageUrl(String homePageUrl) {
		if (this.homePageUrl == null) {
			this.homePageUrl = new SimpleStringProperty(this, "homePageUrl");
		}
		
		this.homePageUrl.set(homePageUrl);
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		if (imageUrl != null) {
			return imageUrl.get();
		} else {
			return null;
		}
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		if (this.imageUrl == null) {
			this.imageUrl = new SimpleStringProperty(this, "imageUrl");
		}
		
		this.imageUrl.set(imageUrl);
	}

	/**
	 * @return the notes
	 */
	public ListProperty<String> getNotes() {
		return this.notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<String> notes) {
		if (this.notes == null) {
			this.notes = new SimpleListProperty<String>(this, "notes");
		}
		
		this.notes.set(FXCollections.observableArrayList(notes));
	}

	/**
	 * @return the duplicates
	 */
	public ListProperty<Person> getDuplicates() {
		return this.duplicates;
	}

	/**
	 * @param duplicates the duplicates to set
	 */
	public void setDuplicates(List<Person> duplicates) {
		if (this.duplicates == null) {
			this.duplicates = new SimpleListProperty<Person>(this, "duplicates");
		}
		
		this.duplicates.set(FXCollections.observableArrayList(duplicates));
	}

	/**
	 * @return the articles
	 */
	public ListProperty<Article> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		if (this.articles == null) {
			this.articles = new SimpleListProperty<Article>(this, "articles");
		}
		
		this.articles.set(FXCollections.observableArrayList(articles));
	}

	/**
	 * @return the citationCount
	 */
	public int getCitationCount() {
		return citationCount.get();
	}

	/**
	 * @param citationCount the citationCount to set
	 */
	public void setCitationCount(int citationCount) {
		if (this.citationCount == null) {
			this.citationCount = new SimpleIntegerProperty(this, "citationCount");
		}
		
		this.citationCount.set(citationCount);
	}
	
	public IntegerProperty citationCountProperty() {
		if (citationCount == null) {
			citationCount = new SimpleIntegerProperty(this, "citationCount");
		}
		
		return citationCount;
	}

	/**
	 * @return the publicationCount
	 */
	public int getPublicationCount() {
		return publicationCount.get();
	}

	/**
	 * @param publicationCount the publicationCount to set
	 */
	public void setPublicationCount(int publicationCount) {
		if (this.publicationCount == null) {
			this.publicationCount = new SimpleIntegerProperty(this, "publicationCount");
		}
		
		this.publicationCount.set(publicationCount);
	}
	
	public IntegerProperty publicationCountProperty() {
		if (publicationCount == null) {
			publicationCount = new SimpleIntegerProperty(this, "publicationCount");
		}
		
		return publicationCount;
	}

	/**
	 * @return the ranking
	 */
	public int getRanking() {
		return ranking.get();
	}

	/**
	 * @param ranking the ranking to set
	 */
	public void setRanking(int ranking) {
		if (this.ranking == null) {
			this.ranking = new SimpleIntegerProperty(this, "ranking");
		}
		
		this.ranking.set(ranking);
	}
	
	public IntegerProperty rankingProperty() {
		if (ranking == null) {
			ranking = new SimpleIntegerProperty(this, "ranking");
		}
		
		return ranking;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return this.personId == other.personId;
		}
		
		return false;
	}

	public Person(
			String key,
			List<String> aliases, 
			String name, 
			String homePageUrl, 
			List<String> notes,
			List<Person> duplicates,
			List<Article> articles,
			int citationCount,
			int publicationCount,
			int ranking) {
		setKey(key);
		setPersonId(Util.generateRandomNumber());		
		setName(name);
		setHomePageUrl(homePageUrl);
		setCitationCount(citationCount);
		setPublicationCount(publicationCount);
		setRanking(ranking);
		
		if (aliases != null) {
			this.aliases = new SimpleListProperty<String>(
							FXCollections.observableArrayList(aliases));
		} else {
			this.aliases = new SimpleListProperty<String>();
		}
		
		if (notes != null) {
			this.notes = new SimpleListProperty<String>(
							FXCollections.observableArrayList(notes));
		} else {
			this.notes = new SimpleListProperty<String>();
		}
		
		if (duplicates != null) {
			this.duplicates = new SimpleListProperty<Person>(
					FXCollections.observableArrayList(duplicates));
		} else {
			this.duplicates = new SimpleListProperty<Person>();
		}
		
		if (articles != null) {
			this.articles = new SimpleListProperty<Article>(
					FXCollections.observableArrayList(articles));
		} else {
			this.articles = new SimpleListProperty<Article>();
		}
	}
	
	public int maxYearArticles()
	{
		int max = 0, temp;
		for(int i=0; i< articles.size(); i++)
		{ 
			temp = articles.get(i).getYear();
			if(temp > max)
			{
				max = temp;
			}
		}
		return max;
	}
	
	public int minYearArticles()
	{
		int min = 100000, temp;
		for(int i=0; i< articles.size(); i++)
		{ 
			temp = articles.get(i).getYear();
			if(temp < min)
			{
				min = temp;
			}
		}
		return min;
	}
	
	
	// function to get the domain from article
		public void getDomain()
		{
			// here i am using dummy values in a string arraylist
			// made a query and added the functions and 5 values and then generated the query as a whole
			ArrayList<String> keys = new ArrayList<String>();
			keys.add("journals/cad/LasemiXG10");
			keys.add("journals/cad/MehradXG14");
			keys.add("journals/cad/XueY04");
			keys.add("journals/cii/XueSN01");
			keys.add("journals/cor/LiNXT11");
			String[] str;
			for(int i=0; i<keys.size(); i++)
			{
				str = keys.get(i).split("/");
				System.out.println(str[1]);
			}
			// refer to break_keys.java in try_code
		}
		
		

}
