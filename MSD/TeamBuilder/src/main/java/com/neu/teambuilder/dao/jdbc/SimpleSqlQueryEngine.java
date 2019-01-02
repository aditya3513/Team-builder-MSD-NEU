package com.neu.teambuilder.dao.jdbc;

import java.util.List;

import com.neu.teambuilder.bol.businessObjects.ArticleQuery;
import com.neu.teambuilder.bol.businessObjects.AuthorQuery;
import com.neu.teambuilder.bol.businessObjects.Entity;
import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.dao.core.IQueryBuilder;
import com.neu.teambuilder.dao.core.IQueryEngine;
import com.neu.teambuilder.dao.core.IQueryExecutor;
import com.neu.teambuilder.dao.core.IResultList;

import javafx.collections.ObservableList;
/**
 * A simple implementaion of QueryEngine to illustrate how the core engine works.
 * The query engine delegates QueryTransformer and QueryPerformer to transform a
 * query and perform it.
 * 
 * @author team18
 *
 */
public class SimpleSqlQueryEngine implements IQueryEngine{
	public IQuery execute(IQuery query) {
		// Init
		SqlExecutor performer = new SqlExecutor();
		SqlBuilder transformer = new SqlBuilder();
		
		// Build and execute sql
		if (query instanceof AuthorQuery) {
			AuthorQuery q = (AuthorQuery) query;
			if (q.getBaseAuthor() == null) {
				// Main page
				String sql = transformer.buildStatementForMainPage(q);
				performer.getMainPage(sql, q);
			} else {
				// Similar author
				String sql = transformer.buildStatementForSimilarAuthors(q);
				performer.getSimilarAuthors(sql, q);
			}
		} else if (query instanceof ArticleQuery) {
			ArticleQuery q = (ArticleQuery) query;
			// Author page
			String sqlAlias = transformer.buildStatementForAlias(q);
			String sqlArticle = transformer.buildStatementForArticle(q);
			performer.getAliasByAuthorName(sqlAlias, q);
			performer.getArticleByAuthorName(sqlArticle, q);
		}
		return query;
	}
}
