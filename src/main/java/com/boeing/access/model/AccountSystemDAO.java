/**
 * 
 */
package com.boeing.access.model;



/**
 * @author Priya Pichamuthu
 *
 */
public class AccountSystemDAO {

	
	public AccountSystemDAO findByID(long id) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 
	 */
	
	public AccountSystemDAO() {
		// TODO Auto-generated constructor stub
	}
	public int count;
	public String aid= null;
	public String sysName = null;

	/**
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	
	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the aid
	 */
	public String getAid() {
		return aid;
	}

	/**
	 * @param aid the aid to set
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}
	
}