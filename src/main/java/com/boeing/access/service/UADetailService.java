/**
 * 
 */
package com.boeing.access.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.boeing.access.Exception.UserAccessException;
import com.boeing.access.model.AccountSystemDAO;

/**
 * @author Priya Pichamuthu
 * 
 */
// Class implementing End User console interface 
public class UADetailService {

	
	/**
	 * 
	 */
	public UADetailService() {
		// TODO Auto-generated constructor stub
	}

	final static Logger logger = Logger.getLogger(UADetailService.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		logging(" **** Welcome to User Account Details **** ", false);

		new UADetailService().userDetails(args);
	}
/**
 * Print log in console as well as log file
 * @param logMsg
 * @param flag
 */
	public static void logging(String logMsg, boolean flag) {
		if (flag)
		logger.info(logMsg);
		System.out.println(logMsg);

	}

	/**
	 * @param args Get user input 1. Choice , 2 - AccountId , 3  SystemName .. Based upon user choice each case get exceuted
	 */
	public void userDetails(String[] args) {

		Collection<AccountSystemDAO> accountSystemDAOCol = null;
		try {
			boolean logOutput = false;
			logging(" Choice 1  ALL ACCOUNTS IN THE SYSTEM  ", logOutput);
			logging(" Choice 2 :  ALL ACCOUNTS FOR A GIVEN USER/ACCTID ON ALL SYSTEMS ",
					logOutput);
			logging(" Choice 3 :  ALL ACCOUNTS FOR A GIVEN USER/ACCTID ON ALL SYSTEMS  AND SORT BY SYSTEM NAME AND THEN ACCOUNT ID  ",
					logOutput);
			logging(" Choice 4:  ALL ACCOUNTS FOR A GIVEN USER FOR EACH SYSTEMS INDIVIDUALLY  ",
					logOutput);
			logging(" Choice 5:  ALL ACCOUNTS FOR A GIVEN USER ON ALL SYSTEMS  WHICH ARE ENABLED",
					logOutput);
			logging(" Choice 6:  CREATE A NEW ACCOUNT IN ONE OF THE SYSTEM AND DISPLAY ALL OF THE ACCOUNTS IN THAT SYSTEM \n DISABLE THE SAME ACCOUNT AND DISPLAY ALL THE ACCOUNTS IN THAT SYSTEM",
					logOutput);
			logging(" Choice 7:  ALL ACCOUNTS FOR A GIVEN USER ON ALL SYSTEMS WHICH ARE DISABLED ",
					logOutput);
			logging(" Choice 8:  CREATE NEW SYSTEM ", logOutput);
			logging(" Choice 9:  CREATE REMOVE SYSTEM ", logOutput);
			logging("ENTER YOUR CHOICE:  1 - 9 ; 0 - EXIT ", logOutput);

			// prompt for the user's input

			InputStreamReader str = new InputStreamReader(System.in);
			BufferedReader uinp = new BufferedReader(str);
			String val;
			val = uinp.readLine();
			UAServiceImpl uAservice = new UAServiceImpl();
			while (!val.equals("0")) {

				String acctID = null;
				String sysName = null;
				int status = 0;
				
				switch (Integer.parseInt(val)) {
				
				//Retrieve all Accounts in the All Systems
				case 1:

					accountSystemDAOCol = (Collection<AccountSystemDAO>) uAservice.displayAllAccount();
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					
					break;
				// Retrieve all Accounts for a Given User (AccountId)
				case 2:
					logging("Enter AccountID:  ",logOutput);
					acctID = uinp.readLine();
					accountSystemDAOCol = uAservice.displayGvnUserAcct(acctID);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				 //Retrieve All Accounts for a given User/AccountID on All Systems Sort by Systems Name and then with Account ID
				case 3:
					logging("Enter AccountID:  ",logOutput);
					acctID = uinp.readLine();
					accountSystemDAOCol = uAservice.displayGvnUserAcctSortBy(acctID);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				//Retrieve All Accounts for a Given User for each Systems Individually
				case 4:
					logging("Enter AccountID:  ",logOutput);
					acctID = uinp.readLine();
					accountSystemDAOCol = uAservice.displayGvnUserIndivAcct(acctID);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				//Retrieve All Accounts for a given User/AccountID on all Systems which are enabled
				case 5:
					logging("Enter AccountID:  ",logOutput);
					acctID = uinp.readLine();
					accountSystemDAOCol = uAservice.displayGvnUserEnabledAcct(acctID);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				//Create a New AccountID/User in in the given system and display all accounts in the systems
			    //Disabling the Account and then displaying Again
				case 6:
					logging("Enter AccountID:  ",logOutput);
					acctID = uinp.readLine();
					logging("Enter SystemName:  ",logOutput);
					sysName = uinp.readLine();
					accountSystemDAOCol = uAservice.createAndDisplayAcct(acctID, sysName);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					logging("Disabling the same account and display all the accounts for that System  ",logOutput);
					accountSystemDAOCol = uAservice.disableAndDisplayAcct(acctID, sysName);
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				//Display accountid/user disabled in all systems
				case 7:
					accountSystemDAOCol = uAservice.disabledAccount();
					accountSystemDAOCol.forEach(accountSystemDAO -> logging("AccountID :"+" "+accountSystemDAO.getAid() + " " +"SystemName :" +" "+ accountSystemDAO.getSysName(),logOutput));
					break;
				//Add a Systems
				case 8:
					logging("Enter SystemName :  ", logOutput);
					sysName = uinp.readLine();
					logging("Enter SystemDesc :  ", logOutput);
					String sysDesc = uinp.readLine();
					status = uAservice.addSystem(sysName, sysDesc);
					if (status != 0)
						logging("Sucessfully Created System :  ", logOutput);
				
					break;
				//Remove the System
				case 9:
					logging("Enter SystemName :  ", logOutput);
					sysName = uinp.readLine();
					status = uAservice.removeSystem(sysName);
					if (status != 0)
						logging("Sucessfully removed System :  ",logOutput);
					else 
						logging("System does not exits to remove :  ",logOutput);
					break;
				
				default :
					logging("Please enter valid option  ",logOutput);
						break;

				}
				logging("ENTER YOUR CHOICE:  1 - 9 ; 0 - EXIT ", logOutput);
				val = uinp.readLine();
			}
		} catch (UserAccessException e) {
			logger.error("Error Code "+ e.getLocalizedMessage());
			
		}
		 catch (SQLException e) {
				logger.error("Error Code"+e.getMessage()+"SQLState :: "+ e.getSQLState());
			
			}
		 catch (IOException e) {
				logger.error("Error Code"+e.getMessage()+e.getCause());
			
			}
	
		 
	}

}
