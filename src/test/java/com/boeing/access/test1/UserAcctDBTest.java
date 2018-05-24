package com.boeing.access.test1;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class) 
public class UserAcctDBTest {

	@Mock
	private DataSource ds;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;
	
	//UAService uas;

	@Before
	// Set the Mock for all dependencies for UA Service
	public void setUp() throws Exception {

		assertNotNull(ds);
		
		//uas = new UAService();
		
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);

		when(ds.getConnection()).thenReturn(c);

		
		when(rs.first()).thenReturn(true);

		when(rs.getString(1)).thenReturn("a1");

		when(rs.getString(2)).thenReturn("Jupiter");

		when(stmt.execute()).thenReturn(true);
		when(stmt.executeQuery()).thenReturn(rs);

	}

	// Unit Test Cases for 1. addAccount

	// Test Case - Null arguments throws NullpOinterException

	@Test(expected = NullPointerException.class)
	public void nulladdAccountThrowsException() throws ClassNotFoundException,
			SQLException, IOException {
		
		// UAService uas = new UAService();
	//	 uas.addAccount(null,null);
		//Assert.assertEquals(true, true);

	}

	// Test Case - New System added Successfully
	@Test
	public void addAccount() throws Exception {
		//UAService uas = new UAService();
		//uas.addAccount(asd.getAid(), asd.getSysName());

	}

	// Test Case - Attempt to add already existing System - Throws Exception
	@Test
	public void addAccountException() throws Exception {

		// Not yet implemented

		Assert.assertEquals(true, true);
	}

	// Test Case - Display user account for given user.
	@Test
	public void displayForGvnUserAccount() throws Exception {

		// UAService dao = new UAService(ds);

		// dao.addAccount(null,null);

		// AccountSystemDAO r =
		// (AccountSystemDAO)uas.displayGvnUserAcct("a1").toArray()[0];
		// Assert.assertEquals("a1",r.getAid());
		// Assert.assertEquals("Jupiter",r.getSysName());
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - adding new system Successfully.
	public void addSystem() throws Exception {

		// Not yet implemented
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - remove new system Successfully.
	public void removeSystem() throws Exception {

		// Not yet impletemted
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - display all disabled account Successfully.
	public void disabledAccount() throws Exception {

		// Not yet implemented
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - remove account for the give acctId and systemname
	public void removeAccount() throws Exception {

		// Not yet implemented
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - display all account Successfully
	public void displayAllAccount() throws Exception {

		// Not yet implemented
		Assert.assertEquals(true, true);
	}

	@Test
	// Test Case - display accountid /system name for the given userid sortby
	// systemname and accountid Successfully
	public void displayGvnUserAcctSortBy() throws Exception {

		// Not yet implemented
		Assert.assertEquals(true, true);
	}

}