package com.hnotch.managers.data;

import java.util.ArrayList;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import com.hnotch.beans.data.SearchResult;
import com.hnotch.builders.data.SearchResultBuilder;

public class SearchResultsManager {

	public ArrayList<SearchResult> doSearch(String query) {

		Client client;
		Node node = null;
		ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();

		try {
			node = NodeBuilder.nodeBuilder()
					.settings(Settings.builder().put("path.home",
							"E:\\Downloads\\elasticsearch-2.1.1\\elasticsearch-2.1.1"))
					.clusterName("hnotch-search").node();
			client = node.client();

			QueryBuilder qb = QueryBuilders.functionScoreQuery()
					.add(QueryBuilders.matchQuery("title", query), ScoreFunctionBuilders.weightFactorFunction(10.0f))
					.add(QueryBuilders.matchQuery("text", query), ScoreFunctionBuilders.weightFactorFunction(1.0f))
					.add(QueryBuilders.matchQuery("url", query), ScoreFunctionBuilders.weightFactorFunction(5.0f));

			SearchResponse response = client.prepareSearch("hnotch").setTypes("search")
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(qb) // Query
					// .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
					// // Filter
					.addHighlightedField("text").setHighlighterPreTags("<b>").setHighlighterPostTags("</b>").setFrom(0)
					.setSize(60).setExplain(true).setScroll(new TimeValue(60000)).execute().actionGet();

			for (SearchHit hit : response.getHits().getHits()) {
				// Handle the hit...
				System.out.println("Title: " + hit.getSource().get("title").toString());
				System.out.println("URL: " + hit.getSource().get("url").toString());
				System.out.println("Text: " + hit.getSource().get("text").toString());
				System.out.println("Text: " + hit.getScore());
				searchResults.add(new SearchResultBuilder().setId(hit.id())
						.setTitle(hit.getSource().get("title").toString()).setUrl(hit.getSource().get("url").toString())
						.setText(hit.getSource().get("text").toString()).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
			node.close();
		} finally {
			node.close();
		}
		return searchResults;
	}

	public ArrayList<SearchResult> doSearch(String query, String queryFilter) {

		Client client;
		Node node = null;
		ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();

		try {
			node = NodeBuilder.nodeBuilder()
					.settings(Settings.builder().put("path.home",
							"E:\\Downloads\\elasticsearch-2.1.1\\elasticsearch-2.1.1"))
					.clusterName("hnotch-search").node();
			client = node.client();

			QueryBuilder qb = QueryBuilders.functionScoreQuery()
					.add(QueryBuilders.matchQuery("title", query), ScoreFunctionBuilders.weightFactorFunction(10.0f))
					.add(QueryBuilders.matchQuery("text", query), ScoreFunctionBuilders.weightFactorFunction(1.0f))
					.add(QueryBuilders.matchQuery("url", query), ScoreFunctionBuilders.weightFactorFunction(5.0f));

			SearchResponse response = client.prepareSearch("hnotch").setTypes("search")
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(qb) // Query
					// .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
					.setPostFilter(QueryBuilders.termQuery("filter_code", queryFilter))
					// // Filter
					.addHighlightedField("text").setHighlighterPreTags("<b>").setHighlighterPostTags("</b>").setFrom(0)
					.setSize(60).setExplain(true).setScroll(new TimeValue(60000)).execute().actionGet();

			for (SearchHit hit : response.getHits().getHits()) {
				// Handle the hit...
				searchResults.add(new SearchResultBuilder().setId(hit.id())
						.setTitle(hit.getSource().get("title").toString()).setUrl(hit.getSource().get("url").toString())
						.setText(hit.getSource().get("text").toString()).build());
			}

		} catch (Exception e) {
			e.printStackTrace();
			node.close();
		} finally {
			node.close();
		}
		return searchResults;
	}
}
