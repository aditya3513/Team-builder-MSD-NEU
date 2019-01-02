package com.neu.teambuilder.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.neu.teambuilder.bol.businessObjects.Article;
import com.neu.teambuilder.bol.businessObjects.ArticleQuery;
import com.neu.teambuilder.bol.businessObjects.AuthorQuery;
import com.neu.teambuilder.bol.businessObjects.Entity;
import com.neu.teambuilder.bol.businessObjects.Person;
import com.neu.teambuilder.bol.businessObjects.Publication;
import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.dao.core.IResultList;

import javafx.collections.ObservableList;

public class SqlExecutor implements IJdbcExecutor{
	Connection connection = null;
	/**
	 * We must build connection first before we execute query
	 */
	@Override
	public Connection buildConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/5500Project?useSSL=false", "test", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IQuery executeQuery(String queryStatment, IQuery query) {
		return null;
	}
	
	// Get author page information by author name
	public void getArticleByAuthorName(String queryStatement, ArticleQuery query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();
		
		// Get journals and conferences article by author name
		try {
			pstmt = connection.prepareStatement(queryStatement);
			pstmt.setString(1, query.getBaseAuthor().getName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setTitle(rs.getString("title"));
				article.setYear(rs.getInt("year"));
				Publication publication = new Publication();
				publication.setTitle(rs.getString("booktitle"));
				article.setPublication(publication);
				articles.add(article);
			}
			query.getBaseAuthor().setArticles(articles);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs, pstmt);
		}
	}
	
	// Get a list of Person and return it to main page
	public void getMainPage(String queryStatement, AuthorQuery query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ObservableList<Person> personList = query.getResults();
		try {
			pstmt = connection.prepareStatement(queryStatement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Add a new person into the list
				Person person = new Person();
				person.setName(rs.getString("name"));
				person.setPublicationCount(rs.getInt("count"));
				personList.add(person);
			}
			query.setResults(personList);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs, pstmt);
		}
	}
	
	// Get a list of similar authors
	public void getSimilarAuthors(String queryStatement, AuthorQuery query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ObservableList<Person> personList = query.getResults();
		try {
			pstmt = connection.prepareStatement(queryStatement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Add a new person into the list
				Person person = new Person();
				person.setName(rs.getString("name"));
				person.setPublicationCount(rs.getInt("count"));
				personList.add(person);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs, pstmt);
		}
	}
	
	// Get a list of alias
	public void getAliasByAuthorName(String queryStatement, ArticleQuery query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> aliases = new ArrayList<>();
		Person person = query.getBaseAuthor();
		try {
			pstmt = connection.prepareStatement(queryStatement);
			pstmt.setString(1, query.getBaseAuthor().getName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Get list of alias
				aliases.add(rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs, pstmt);
		}
		person.setAliases(aliases);
	}
	
	
	
	
	
}
