package com.neu.teambuilder.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.neu.teambuilder.bol.businessObjects.Article;
import com.neu.teambuilder.bol.businessObjects.ArticleQuery;
import com.neu.teambuilder.bol.businessObjects.AuthorQuery;
import com.neu.teambuilder.bol.businessObjects.Filter;
import com.neu.teambuilder.bol.businessObjects.FilterType;
import com.neu.teambuilder.bol.interfaces.IQuery;
import com.neu.teambuilder.dao.core.IQueryBuilder;

public class SqlBuilder implements IQueryBuilder<String> {

	@Override
	public List<String> buildStatement(IQuery query) {
		List<String> result = new ArrayList<>();
		return null;
	}
	
	// Input: Author query from main page
	// Output: SQL query in the form of the string
	// 
	public String buildStatementForMainPage(AuthorQuery query) {
		
		List<Filter> filters = query.getFilters();
		StringBuilder stringBuilder = new StringBuilder();
		if (query.isSimilarAuthor()) {
			// No filters specified, return only similar authors
			
			stringBuilder.append("SELECT author_name FROM author, article_author, conferences where ");
			
		} else {
			stringBuilder.append("SELECT author_name FROM author, article_author, conferences where ");
			stringBuilder.append("author.id = article_author.writtenBy ");
			stringBuilder.append("AND ");
			stringBuilder.append("article_author.writes = conferences.id ");
			stringBuilder.append("AND ");
			stringBuilder.append("count.author = author.author");
			stringBuilder.append("AND ");
			
			for(int i=0;i<filters.size();i++){ 
				if (filters.get(i).getType() == FilterType.AuthorName){
					stringBuilder.append("author.author like '"+ filters.get(i).getValue()+"%' ");		
					}
				
				else if (filters.get(i).getType() == FilterType.FromDate){
					stringBuilder.append("conferences.year >= "+ filters.get(i).getValue()+" ");
						
					}
				else if (filters.get(i).getType() == FilterType.ToDate){
					stringBuilder.append("conferences.year <= "+ filters.get(i).getValue()+" ");
				}
				else if (filters.get(i).getType() == FilterType.Conference) {
					stringBuilder.append("conferences.title = "+ filters.get(i).getValue()+" ");
				}
				
				else if (filters.get(i).getType() == FilterType.MinPublicationCount) {
					stringBuilder.append("count.total_count >= "+ filters.get(i).getValue()+" ");
				}
				else if (filters.get(i).getType() == FilterType.MinPublicationCount) {
					stringBuilder.append("count.total_count > "+ filters.get(i).getValue()+" ");
				}
				
				if(i+1<filters.size()) {
					if(filters.get(i+1).getType()== FilterType.Or) {
						stringBuilder.append("OR ");
						i+=1;
					}
					else {
						stringBuilder.append("AND ");
						i+=1;
					}
					
				}
				
			}
		}
	
		return stringBuilder.toString();
	}
	
	public String buildStatementForAlias(ArticleQuery query) {
		return "select alias.name from alias, author "
				+ "where author.id = alias.partOf and author.author = ?";
	}
	
	public String buildStatementForArticle(ArticleQuery query) {
		return "select c.title, c.year, c.booktitle from conferences c, article_author, author "
				+ "where article_author.writtenBy = author.id and article_author.writes = c.id and author.author = ? "
				+ "union "
				+ "select j.title, j.year, j.journal from journals j, authors, author "
				+ "where author.author = ? and author.author = authors.author and authors.key = j.key;";
	}
	
	public String buildStatementForSimilarAuthors(AuthorQuery query) {
		return "select ";
	}
	
}
