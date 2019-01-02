package com.neu.teambuilder.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
	public static void close(ResultSet rs, Statement ps) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
