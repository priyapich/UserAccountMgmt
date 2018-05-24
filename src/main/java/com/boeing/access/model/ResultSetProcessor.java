package com.boeing.access.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetProcessor {

    public void process(ResultSet resultSet, 
                        long currentRow) 
                        throws SQLException;
}
