package com.splitmybill.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHelper {

	static Logger log = LoggerFactory.getLogger(DatabaseHelper.class);

	@Autowired
	DataSource ds;

	public Connection getConnection() throws Exception {
		Connection con = null;
		try {
			con = ds.getConnection();
			return con;
		} catch (SQLException e) {
			log.error("DB connection error");
			e.printStackTrace();
			throw e;
		}

	}

	public void closeResource(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null)
				pstmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}