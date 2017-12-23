package com.hnotch.beans.data;

public class SearchResult {

	private String Id;
	private String title;
	private String postDate;
	private String text;
	private String url;
	private String filter_code;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setFilter_code(String filter_code) {
		this.filter_code = filter_code;
	}
	public String getTitle() {
		return title;
	}
	public String getPostDate() {
		return postDate;
	}
	public String getText() {
		return text;
	}
	public String getUrl() {
		return url;
	}
	public String getFilter_code() {
		return filter_code;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	
}
