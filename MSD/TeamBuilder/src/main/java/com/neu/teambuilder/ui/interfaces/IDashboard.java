package com.neu.teambuilder.ui.interfaces;

/**
 * UI interface including all the main actions that a user 
 * can perform
 * @author team18
 */
public interface IDashboard {
	
	/**
	 * Authenticates the credentials of a user who is
	 * trying to login
	 * @return true if action succeeds
	 */
	public boolean login(String username, String password);
		
	/**
	 * Invalidates the current user session by internally
	 * calling the helper function from the Session object
	 */
	public void logout(); 
	
}
