/**
 * 
 */
package com.neu.teambuilder.bol.businessObjects;

import java.util.ArrayList;
import java.util.List;

import com.neu.teambuilder.resources.Util;

/**
 * @author ideepakkrishnan
 *
 */
public class User {
	
	private Long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String securityQuestion;
	private String securityResponse;
	private List<AuthorQuery> searchHistory;
	private Bucket bucket;
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
		//TODO: Add db query to update the userid for this user
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
		//TODO: Add db query to update the username for this user
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		//TODO: Add db query to update the password for this user
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		//TODO: Add db query to update the first name for this user
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		//TODO: Add db query to update the last name for this user
	}
	
	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	
	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
		//TODO: Add db query to update the security question for this user
	}
	
	/**
	 * @return the securityResponse
	 */
	public String getSecurityResponse() {
		return securityResponse;
	}
	
	/**
	 * @param securityResponse the securityResponse to set
	 */
	public void setSecurityResponse(String securityResponse) {
		this.securityResponse = securityResponse;
		//TODO: Add db query to update the security response for this user
	}
	/**
	 * @return the searchHistory
	 */
	public List<AuthorQuery> getSearchHistory() {
		return searchHistory;
	}
	
	/**
	 * @param searchHistory the searchHistory to set
	 */
	public void setSearchHistory(List<AuthorQuery> searchHistory) {
		this.searchHistory = searchHistory;
		//TODO: Add db query to update the search history for this user
	}
	
	/**
	 * @return the bucket
	 */
	public Bucket getBucket() {
		return bucket;
	}
	
	/**
	 * @param bucket the bucket to set
	 */
	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
		//TODO: Add db query to update the bucket for this user
	}
	
	public User() {
		// Do nothing
	}
	
	public User(
			String username,
			String password,
			String firstName, 
			String lastName, 
			String securityQuestion,
			String securityResponse) {
		setUserId(Util.generateRandomNumber());
		setUserName(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setSecurityQuestion(securityQuestion);
		setSecurityResponse(securityResponse);
		setSearchHistory(new ArrayList<AuthorQuery>());
		setBucket(new Bucket(this));
		//TODO: Add db query to save user details
	}
	
	public String fetchSecurityQuestion(String userName) {
		if (userName != null && userName != "" && userName.length() > 0) {
			// TODO Add db query to fetch the security question
			//		for specified username
		}
		
		return null;
	}
	
	public boolean validateSecurityResponse(String userName, String response) {
		if (response != null && response != "" && response.length() > 0) {
			//TODO: Add db query to fetch the security response for
			//		specified username. Also initialize this object
			//		details with the result of this query
			return this.securityResponse.equals(response);
		}
		
		return false;
	}
	
	public boolean authenticateUser(String userName, String password) {
		//TODO: Add db query to check the userName and password
		// 		and load the details into this object.
		return false;
	}
	
	public boolean addQuery(AuthorQuery query) {
		if (this.searchHistory != null) {
			this.searchHistory.add(query);
			//TODO: Add db query to add this query
			return true;
		}
		
		return false;
	}
	
	public boolean removeQuery(AuthorQuery query) {
		if (this.searchHistory != null && this.searchHistory.size() > 0) {
			this.searchHistory.remove(query);
			//TODO: Add db query to remove this query
			return true;
		}
		
		return false;
	}

}
