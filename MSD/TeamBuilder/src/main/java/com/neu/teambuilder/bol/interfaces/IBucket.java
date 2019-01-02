package com.neu.teambuilder.bol.interfaces;

import com.neu.teambuilder.bol.businessObjects.Person;
import com.neu.teambuilder.bol.businessObjects.User;

/**
 * Bucket interface where the user can save, remove, finalize the
 * list of program committee.
 * @author team18
 */
public interface IBucket {
	
	/**
	 * Initializes a new bucket for the user who is current logged
	 * into this session.
	 * @param owner
	 * @return true iff the bucket is initialized
	 */
	public boolean initialize(User owner);
	
	/**
	 * Add a selected author to the bucket. The bucket is a place where 
	 * the user can add a selected author as part of the program committee.
	 * @param author The author to be added to the list
	 * @return true iff the author was successfully added
	 */
	public boolean addAuthor(Person author);
	
	/**
	 * Remove an author from bucket.
	 * @param key The key of author to be removed from the list
	 * @return true iff the author was successfully removed
	 */
	public boolean removeAuthor(String key);
	
	/**
	 * Export current authors in the bucket to a CSV file
	 */
	public boolean exportToCSV();
	
	/**
	 * Removes all authors in the bucket and updates this change
	 * in the database.
	 * @return true iff all members were removed
	 */
	public boolean clear();
	
}
