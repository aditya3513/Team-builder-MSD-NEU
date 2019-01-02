/**
 * 
 */
package com.neu.teambuilder.ui.uiObjects;

import com.neu.teambuilder.bol.businessObjects.User;
import com.neu.teambuilder.ui.interfaces.IDashboard;

/**
 * @author ideepakkrishnan
 *
 */
public class Dashboard implements IDashboard {
	
	private Session currentSession;
	
	/**
	 * @return the currentSession
	 */
	public Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * @param currentSession the currentSession to set
	 */
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	/* (non-Javadoc)
	 * @see com.neu.teambuilder.ui.interfaces.IDashboard#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		User currentUser = new User();
		if (currentUser.validateSecurityResponse(username, password)) {
			setCurrentSession(new Session(currentUser));
			return true;
		}
		
		// TODO Add db query to fetch user details
		return false;
	}

	/* (non-Javadoc)
	 * @see com.neu.teambuilder.ui.interfaces.IDashboard#logout()
	 */
	@Override
	public void logout() {
		this.currentSession.invalidate();
	}

}
