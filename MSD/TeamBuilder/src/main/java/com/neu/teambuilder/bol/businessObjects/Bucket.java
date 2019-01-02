/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.ArrayList;
import java.util.List;

import com.neu.teambuilder.bol.interfaces.IBucket;
import com.neu.teambuilder.resources.Util;

/**
 * @author ideepakkrishnan
 *
 */
public class Bucket implements IBucket {
	
	private Long bucketId;
	private User owner;
	private List<Person> selectedAuthors;
	private boolean isLocked;
	
	/**
	 * @return the bucketId
	 */
	public Long getBucketId() {
		return bucketId;
	}
	/**
	 * @param bucketId the bucketId to set
	 */
	public void setBucketId(Long bucketId) {
		this.bucketId = bucketId;
		//TODO: Add db query to update the bucket ID
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
		//TODO: Add db query to update the owner of this bucket
	}
	
	/**
	 * @return the selectedAuthors
	 */
	public List<Person> getSelectedAuthors() {
		return selectedAuthors;
	}
	/**
	 * @param selectedAuthors the selectedAuthors to set
	 */
	public void setSelectedAuthors(List<Person> selectedAuthors) {
		this.selectedAuthors = selectedAuthors;
		//TODO: Add db query to update members
	}
	
	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}
	
	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
		//TODO: Add db query to update isLocked
	}
	
	public Bucket(User owner) {
		setBucketId(Util.generateRandomNumber());
		setOwner(owner);
		setSelectedAuthors(new ArrayList<Person>());
		setLocked(false);
		
		//TODO: Add db query to add this bucket
	}
	
	public boolean initialize(User owner) {
		if (this.owner == null) {
			this.owner = owner;
			//TODO: Add db query to update the owner of this bucket
			return true;
		}
		
		return false;
	}
	
	public boolean addAuthor(Person author) {
		if (this.selectedAuthors == null) {
			this.selectedAuthors = new ArrayList<Person>();
		}
		
		if (!this.isLocked) {
			this.selectedAuthors.add(author);
			//TODO: Add db query to add this author
			return true;
		}
		
		return false;
	}
	
	public boolean removeAuthor(String key) {
		if (this.selectedAuthors != null && !this.isLocked) {
			String authKey = key.substring(6);
			for (Person p : this.selectedAuthors) {				
				if (p.getKey().equals(authKey)) {
					this.selectedAuthors.remove(p);
					//TODO: Add db query to remove this author
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean exportToCSV() {
		//TODO: Add code to export the members to a CSV file
		return false;
	}
	
	public boolean clear() {
		if (this.selectedAuthors != null && this.selectedAuthors.size() > 0) {
			this.selectedAuthors.clear();
			
			//TODO: Add db query to clear all members
			
			return true;
		}
		
		return false;
	}

}
