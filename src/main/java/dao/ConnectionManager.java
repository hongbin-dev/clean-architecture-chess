package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionManager {

	String SERVER = "jdbc:hsqldb:mem:testcase;shutdown=true";
	String USER_NAME = "sa";
	String PASSWORD = "null";

	default String url() {
		return SERVER;
	}

	default String username() {
		return USER_NAME;
	}

	default String password() {
		return PASSWORD;
	}

	default Connection getConnection() {
		try {
			return DriverManager.getConnection(url(), username(), password());
		} catch (SQLException e) {
			throw new IllegalArgumentException("데이터베이스 연결 오류", e);
		}
	}
}
