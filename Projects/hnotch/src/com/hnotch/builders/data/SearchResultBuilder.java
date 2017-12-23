package com.hnotch.builders.data;

import com.hnotch.beans.data.SearchResult;

public class SearchResultBuilder {

	private SearchResult searchResult = new SearchResult();
	
	public SearchResultBuilder setTitle(String title) {
		this.searchResult.setTitle(title);
		return this;
	}
	public SearchResultBuilder setPostDate(String postDate) {
		this.searchResult.setPostDate(postDate);
		return this;
	}
	public SearchResultBuilder setText(String text) {
		this.searchResult.setText(text);
		return this;
	}
	public SearchResultBuilder setUrl(String url) {
		this.searchResult.setUrl(url);
		return this;
	}
	public SearchResultBuilder setFilter_code(String filter_code) {
		this.searchResult.setFilter_code(filter_code);
		return this;
	}
	public SearchResultBuilder setId(String id) {
		this.searchResult.setId(id);
		return this;
	}
	public SearchResult build(){
		return this.searchResult;
	}
	
}
