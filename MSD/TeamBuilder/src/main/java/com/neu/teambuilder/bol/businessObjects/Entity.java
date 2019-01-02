package com.neu.teambuilder.bol.businessObjects;
import java.sql.Date;
/**
 * Abstract class to represent search results. 
 * @author team18
 *
 */
public abstract class Entity {
	private String key;
	private Date mDate;
	private String publType;
	private Date cDate;
	private Boolean isSelected;
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the mDate
	 */
	public Date getmDate() {
		return mDate;
	}
	/**
	 * @param mDate the mDate to set
	 */
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	/**
	 * @return the publType
	 */
	public String getPublType() {
		return publType;
	}
	/**
	 * @param publType the publType to set
	 */
	public void setPublType(String publType) {
		this.publType = publType;
	}
	/**
	 * @return the cDate
	 */
	public Date getcDate() {
		return cDate;
	}
	/**
	 * @param cDate the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	/**
	 * @return the isSelected
	 */
	public Boolean getIsSelected() {
		return isSelected;
	}
	/**
	 * @param isSelected the isSelected to set
	 */
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
}
