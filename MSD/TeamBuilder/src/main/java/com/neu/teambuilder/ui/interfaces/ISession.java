/**
 * 
 */
package com.neu.teambuilder.ui.interfaces;

import com.neu.teambuilder.bol.businessObjects.User;

/**
 * Handles the current user session
 * @author team18
 */
public interface ISession {
	
	/**
	 * Initializes the current user session
	 * @param user The current user who has logged into this
	 * session
	 */
	public void initialize(User user);
	
	/**
	 * Removes the details of the current user from memory
	 * and invalidates the session. At this point, the
	 * method redirects the user to login screen.
	 */
	public void invalidate();

}
