package com.boeing.access.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.boeing.access.Exception.UserAccessException;
import com.boeing.access.dbutil.DBConnection;
import com.boeing.access.model.AccountSystemDAO;
import com.boeing.access.model.ResultSetProcessor;
import com.boeing.access.util.UserAccessUtil;

public class UAServiceImpl {

	private AccountSystemDAO accountSystemDAO = null;
	public static String CLASSNAME = "UAServiceImpl";
	
	public UAServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public UAServiceImpl(AccountSystemDAO as) {
		// TODO Auto-generated constructor stub
		this.accountSystemDAO = as;
	}

	public AccountSystemDAO findByID(long id) {
		return accountSystemDAO.findByID(id);
	}

	/**
	 * addAccount() - Add new account to the specifed systemname
	 * 
	 * @param acctID
	 * @param sysNme
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int addAccount(String acctID, String sysNme) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		int status;
		String ADD_ACCOUNT = "INSERT INTO USER_ACCOUNT (ACCOUNTID,SYSTEMNAME,ENABLED) VALUES(?,?,'1')";
		try {

			status = update(getConnection(), ADD_ACCOUNT, acctID.toUpperCase(), sysNme.toUpperCase());

		} finally {
			closeDBConnection(con, stmt, null);
		}
		return status;

	}

	/**
	 * addSystem() - Add new system (specified system) to the sytemTable
	 * 
	 * @param sysNme
	 * @param sysDesc
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int addSystem(String sysNme, String sysDesc) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		int status;
		String ADD_SYSTEM = "INSERT INTO SYSTEM(NAME,DESCRIPTION) VALUES(?,?)";
		try {

			status = update(getConnection(), ADD_SYSTEM, sysNme.toUpperCase(), sysDesc.toUpperCase());

		} finally {
			closeDBConnection(con, stmt, null);
		}
		return status;

	}

	/**
	 * removeSystem() -Remove specified system from the system table
	 * 
	 * @param sysNme
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int removeSystem(String sysNme) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		String DELETE_SYSTEM = "DELETE FROM SYSTEM WHERE NAME=?";
		int status;
		try {

			status = update(getConnection(), DELETE_SYSTEM, sysNme.toUpperCase());

		} finally {
			closeDBConnection(con, stmt, null);
		}
		return status;

	}

	/**
	 * enabledAccount() - Check whether accountId is already enabled in any
	 * system or not.
	 * 
	 * @param acctID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * 
	 */
	private boolean enabledAccount(String acctID) throws SQLException,IOException {
		String ENABLE_ACCOUNT = "SELECT COUNT(ACCOUNTID)  FROM USER_ACCOUNT WHERE ENABLED='0' AND ACCOUNTID=?";
		boolean alreadyEnabled = false;
			Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
			select(getConnection(), ENABLE_ACCOUNT, (rs, cnt) -> {
				accountSystemDAO = new AccountSystemDAO();
				accountSystemDAO.setCount(rs.getInt(1));
				accountSystemDAOCol.add(accountSystemDAO);
			}, acctID.toUpperCase());
			if (accountSystemDAOCol.isEmpty()) {
				alreadyEnabled = false;
			}
			return alreadyEnabled;

	}

	/**
	 * disabledAccount() - Disable the accountid from the system which is
	 * specified by the user
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> disabledAccount()
			throws  SQLException, IOException{
		String DISABLE_ACCOUNT = "SELECT ACCOUNTID , SYSTEMNAME FROM USER_ACCOUNT WHERE ENABLED='0'";
			Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
			select(getConnection(), DISABLE_ACCOUNT, (rs, cnt) -> {
				accountSystemDAO = new AccountSystemDAO();
				accountSystemDAO.setAid(rs.getString(1));
				accountSystemDAO.setSysName(rs.getString(2));
				accountSystemDAOCol.add(accountSystemDAO);
			});
			return accountSystemDAOCol;

			}

	/**
	 * removeAccount() - disable the account from the system
	 * 
	 * @param acctName
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *             removeAccount just disable the account by updating enable
	 *             field to 'N'
	 * 
	 */

	private int removeAccount(String acctID, String sysNme) throws SQLException, IOException {
		String DISABLE_ACCOUNT = "update  USER_ACCOUNT SET ENABLED='0' WHERE UPPER(ACCOUNTID)=? AND SYSTEMNAME=?";
		Connection con = null;
		PreparedStatement stmt = null;
		int status;
		try {

			status = update(getConnection(), DISABLE_ACCOUNT, acctID.toUpperCase(), sysNme.toUpperCase());

		} finally {
			closeDBConnection(con, stmt, null);
		}
		return status;

	}

	public Collection<AccountSystemDAO> displayAllAccount() throws SQLException, IOException {
		String DISPLAY_ALL_ACCOUNT = "SELECT  ACCOUNTID,SYSTEMNAME from USER_ACCOUNT";
	
			Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
			select(getConnection(), DISPLAY_ALL_ACCOUNT, (rs, cnt) -> {
				accountSystemDAO = new AccountSystemDAO();
				accountSystemDAO.setAid(rs.getString(1));
				accountSystemDAO.setSysName(rs.getString(2));
				accountSystemDAOCol.add(accountSystemDAO);
			});
			return accountSystemDAOCol;
		}

	/**
	 * displayAllAccount() - Display all the account for the system.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> displayAllAccountTest() throws SQLException, IOException {
		String DISPLAY_ALL_ACCOUNT = "SELECT  ACCOUNTID,SYSTEMNAME from USER_ACCOUNT";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), DISPLAY_ALL_ACCOUNT, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		});
		return accountSystemDAOCol;

	}

	/**
	 * displayGvnUserAcct()- Display account system information for the given
	 * user
	 * 
	 * @param acctID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> displayGvnUserAcct(String acctID) throws SQLException, IOException {
		String USR_ACCOUNT_DISPLY = "SELECT ACCOUNTID,SYSTEMNAME FROM USER_ACCOUNT WHERE UPPER(ACCOUNTID)=?";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), USR_ACCOUNT_DISPLY, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		}, acctID.toUpperCase());
		return accountSystemDAOCol;

	}

	/**
	 * displayGvnUserAcctSortBy() - Display account system information for the
	 * given user sort by systemname and account id
	 * 
	 * @param acctID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> displayGvnUserAcctSortBy(String acctID) throws SQLException, IOException {
		String USER_ACCOUNTS = "SELECT ACCOUNTID, SYSTEMNAME FROM USER_ACCOUNT WHERE UPPER(ACCOUNTID)=? ORDER BY SYSTEMNAME,ACCOUNTID";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), USER_ACCOUNTS, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		}, acctID.toUpperCase());
		return accountSystemDAOCol;
	}

	/**
	 * displayGvnUserIndivAcct() - Display account system information for the
	 * given user indiviually
	 * 
	 * @param acctID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> displayGvnUserIndivAcct(String acctID) throws SQLException, IOException {
		String USER_ACCOUNTS = "SELECT ACCOUNTID, SYSTEMNAME FROM USER_ACCOUNT WHERE UPPER(ACCOUNTID)=? ";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), USER_ACCOUNTS, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		}, acctID.toUpperCase());
		return accountSystemDAOCol;

	}

	/**
	 * displayGvnUserEnabledAcct () - Display account ,system information which
	 * are enabled for the given user
	 * 
	 * @param acctID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> displayGvnUserEnabledAcct(String acctID) throws SQLException, IOException {

		String USER_ACCOUNTS = "SELECT ACCOUNTID, SYSTEMNAME FROM USER_ACCOUNT WHERE UPPER(ACCOUNTID)=? AND ENABLED=1";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), USER_ACCOUNTS, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		}, acctID.toUpperCase());
		return accountSystemDAOCol;
	}

	/**
	 * displayAccount() - Display account system information for the given
	 * systemname.
	 * 
	 * @param acctID
	 * @param sysName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	private Collection<AccountSystemDAO> displayAccount(String sysName) throws SQLException, IOException {
		String DISPLAY_ACCOUNTS_SYS = "SELECT ACCOUNTID,SYSTEMNAME FROM USER_ACCOUNT WHERE  UPPER(SYSTEMNAME)=?";
		Collection<AccountSystemDAO> accountSystemDAOCol = new ArrayList<AccountSystemDAO>();
		select(getConnection(), DISPLAY_ACCOUNTS_SYS, (rs, cnt) -> {
			accountSystemDAO = new AccountSystemDAO();
			accountSystemDAO.setAid(rs.getString(1));
			accountSystemDAO.setSysName(rs.getString(2));
			accountSystemDAOCol.add(accountSystemDAO);
		}, sysName.toUpperCase());
		return accountSystemDAOCol;

	}

	/**
	 * createAndDisplayAcct() - Create account for the specified acctID and
	 * Display account information for the given system name
	 * 
	 * @param acctID
	 * @param sysName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */

	public Collection<AccountSystemDAO> createAndDisplayAcct(String acctID, String sysName)
			throws SQLException, IOException, UserAccessException {
		Collection<AccountSystemDAO> accountSystemDAOCol = null;
		try {
			
			boolean enabledFlag = enabledAccount(acctID);
			if (enabledFlag) {
				addAccount(acctID, sysName);
				accountSystemDAOCol = (Collection<AccountSystemDAO>) displayAccount(sysName);
			} else {

				throw new UserAccessException(
						CLASSNAME.toUpperCase() + ":: createAndDisplayAcct() :: "+UserAccessUtil.getPropertyValue("ER_1002"));
			}
		} finally {

		}
		return accountSystemDAOCol;
	}

	/**
	 * disableAndDisplayAcct() - Disable account id which is specified and
	 * Display account information for the given SystemName
	 * 
	 * @param acctNme
	 * @param sysNme
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Collection<AccountSystemDAO> disableAndDisplayAcct(String acctNme, String sysNme)
			throws SQLException, IOException {
		removeAccount(acctNme, sysNme);
		Collection<AccountSystemDAO> accountSystemDAOCol = ((Collection<AccountSystemDAO>) displayAccount(sysNme));
		return accountSystemDAOCol;

	}

	/**
	 * getConnection() - Get the Database connection
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Connection getConnection() throws SQLException, IOException {
		Connection con = new DBConnection().dbConnection();
		return con;

	}

	/**
	 * closeDBConnection() - Close all the db connection statment and resultset
	 * 
	 * @param con
	 * @param stmt
	 * @param rs
	 * @throws SQLException
	 */
	public static void closeDBConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			throw e;
		}

	}

	private static void select(Connection connection, String sql, ResultSetProcessor processor, Object... params)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			ps = connection.prepareStatement(sql);
			for (Object param : params) {
				ps.setObject(++cnt, param);
			}
			rs = ps.executeQuery();
			long rowCnt = 0;
			while (rs.next()) {
				processor.process(rs, rowCnt++);
			}

		} catch (SQLException e) {
			throw e;
		} finally {

			closeDBConnection(connection, ps, rs);

		}
	}

	private static int update(Connection connection, String sql, Object... params) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		int cnt = 0;
		try {
			ps = connection.prepareStatement(sql);
			for (Object param : params) {
				ps.setObject(++cnt, param);
			}
			result = ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {

			closeDBConnection(connection, ps, null);
		}
		return result;
	}
}
