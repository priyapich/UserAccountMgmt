package com.boeing.access.test1;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boeing.access.model.AccountSystemDAO;
import com.boeing.access.service.UAServiceImpl;
public class UserServiceTest {
@InjectMocks private com.boeing.access.dbutil.DBConnection dbConnection;
@Mock private Connection mockConnection;
@Mock private PreparedStatement mockStatement;

@Mock private com.boeing.access.model.AccountSystemDAO acctDao;
 @Before
  public void setUp() {
   MockitoAnnotations.initMocks(this);
  }
	  @Test
	  public void testMockDBConnection() throws Exception {

	    Mockito.when(mockConnection.prepareStatement(Mockito.any().toString())).thenReturn(mockStatement);

	    Mockito.when(mockConnection.prepareStatement(Mockito.any().toString()).executeUpdate()).thenReturn(1);

	    int value = dbConnection.dbConnection().createStatement().executeQuery("").getInt(1);
	    Assert.assertEquals(value, 1);
	    Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	  }
	 
	  @Test
	
	  	  public void testFindById() {
	
	  	    MockitoAnnotations.initMocks(this);
	
	  	    UAServiceImpl myService = new UAServiceImpl(acctDao);
	
	  	    myService.findByID(1L);
	
	  	    Mockito.verify(acctDao).findByID(1L);
	
	  	}
	  
	  @Test
	  
	  	  public void test() {
	  
	  	    UAServiceImpl myService = new UAServiceImpl(acctDao);
	  
	  	    Mockito.when(acctDao.findByID(1L)).thenReturn(createTestEntity());
	  
	  	    AccountSystemDAO actualDAO = myService.findByID(1L);
	  
	  	    Assert.assertEquals("a1", actualDAO.getAid());
	  
	  	    Assert.assertEquals("Jupiter", actualDAO.getSysName());
	  
	  	    Mockito.verify(acctDao).findByID(1L);
	  
	  	}
	  
	  private AccountSystemDAO createTestEntity() {
		  
		  AccountSystemDAO accountSystemDAO = new AccountSystemDAO();
		  
		  accountSystemDAO.setAid("a1");
		  
		  accountSystemDAO.setSysName("Jupiter");
		  
		  	    return accountSystemDAO;
		  	  }
	  
}
