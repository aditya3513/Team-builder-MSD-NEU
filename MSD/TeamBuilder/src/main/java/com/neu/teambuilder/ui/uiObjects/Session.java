/**
 * 
 */
package com.neu.teambuilder.ui.uiObjects;

import com.neu.teambuilder.bol.businessObjects.User;
import com.neu.teambuilder.resources.Util;
import com.neu.teambuilder.ui.interfaces.ISession;

/**
 * @author ideepakkrishnan
 *
 */
public class Session implements ISession {
	
	private Long sessionId;
	private User currentUser;

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public Session(User currentUser) {
		initialize(currentUser);
	}

	/* (non-Javadoc)
	 * @see com.neu.teambuilder.ui.interfaces.ISession#initialize(java.lang.String, java.lang.Object)
	 */
	@Override
	public void initialize(User user) {
		setSessionId(Util.generateRandomNumber());
		setCurrentUser(user);
	}

	/* (non-Javadoc)
	 * @see com.neu.teambuilder.ui.interfaces.ISession#invalidate()
	 */
	@Override
	public void invalidate() {
		setCurrentUser(null);
		setSessionId(null);
	}

}
