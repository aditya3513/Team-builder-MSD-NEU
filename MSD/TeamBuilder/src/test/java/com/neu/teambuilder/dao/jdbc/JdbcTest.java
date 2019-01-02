package com.neu.teambuilder.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.neu.teambuilder.bol.businessObjects.Article;
import com.neu.teambuilder.bol.businessObjects.Publication;


public class JdbcTest {
	Connection connection = null;
	PreparedStatement pstmt = null;
	@Before
	public void setUp() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/5500Project?useSSL=false", "test", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test for getting journals
	 * Most journals tags are under article table
	 * @throws SQLException 
	 */
	@Test
	public void testForAuthorInfoJournals() throws SQLException {
		List<Article> articles = new ArrayList<>();
		String sql = "select c.title, c.year, c.booktitle from conferences c, article_author, author "
				+ "where article_author.writtenBy = author.id and article_author.writes = c.id and author.author = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "Bart Jacobs 0002");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setTitle(rs.getString(1));
				article.setYear(rs.getInt(2));
				Publication publication = new Publication();
				publication.setTitle(rs.getString(3));
				article.setPublication(publication);
				articles.add(article);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
		// assert count
		assertEquals(articles.size(), 5);
	}
	
	/**
	 * Test for getting conferences
	 * Most conf tags are under inproceedings table
	 * @throws SQLException
	 */
	@Test
	public void testForAuthorInfoInproceedings() throws SQLException {
		List<Article> articles = new ArrayList<>();
		String sql = "select j.title, j.year, j.journal from journals j, authors, author "
				+ "where author.author = ? and author.author = authors.author and authors.key = j.key";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "Bart Jacobs 0002");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setTitle(rs.getString(1));
				article.setYear(rs.getInt(2));
				Publication publication = new Publication();
				publication.setTitle(rs.getString(3));
				article.setPublication(publication);
				articles.add(article);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
		
		// assert
		assertEquals(articles.size(), 18);
	}
	
	/**
	 * Test for getting author alias information
	 * @throws SQLException
	 */
	@Test
	/*
	public void testForAuthorInfoAlias() throws SQLException {
		List<Article> articles = new ArrayList<>();
		String sql = "select w.author from adi_xml_author w where w.`key` in"
				+ "(select a.`key` from adi_xml_alias a where a.`key` in"
				+ "(select c.key  from adi_xml_author c where c.author = ?))";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "Sanjeev Saxena");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				articles.add(article);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	*/
	
	/**
	 * Test for getting publications count
	 * @throws SQLException
	 */
	/*
	@Test
	public void testForAuthorPublicationCount() throws SQLException {
		List<Article> articles = new ArrayList<>();
		String sql = "select w.author from adi_xml_author w where w.`key` in"
				+ "(select a.`key` from adi_xml_alias a where a.`key` in"
				+ "(select c.key  from adi_xml_author c where c.author = ?))";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "Sanjeev Saxena");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	*/
	
	
}
