package com.hnotch.beans.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Attribute {

	private String AttributeId;
	private String ApplicationAttributesId;
	private String AttributeDescription;
	private String AttributeName;
	private String AttributeValue;
	private String ApplicationId;
	private String XMLTagName;
	private String Timestamp;
	
		
	/* CONSTANTS */
	public static final String COLUMN_ATTRIBUTEID = "AttributeId";
	public static final String COLUMN_APPLICATIONATTRIBUTESID = "ApplicationAttributesId";
	public static final String COLUMN_ATTRIBUTEDESCRIPTION = "AttributeDescription";
	public static final String COLUMN_ATTRIBUTENAME = "AttributeName";
	public static final String COLUMN_ATTRIBUTEVALUE = "AttributeValue";
	public static final String COLUMN_TIMESTAMP = "Timestamp";
	public static final String COLUMN_APPLICATIONID = "ApplicationId";
	public static final String COLUMN_XMLTAGNAME = "XMLTagName";
	/* CONSTANTS */
	
	public Attribute() {
		// TODO Auto-generated constructor stub
		this.AttributeId = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		this.Timestamp = new Timestamp(new Date().getTime()).toString();
	}
	
	public String getAttributeId() {
		return this.AttributeId;
	}
	public String getAttributeDescription() {
		return this.AttributeDescription;
	}
	public String getAttributeName() {
		return this.AttributeName;
	}
	public String getTimestamp() {
		return this.Timestamp;
	}
	
	public void setAttributeId(String attributeId) {
		this.AttributeId = attributeId;
	}
	public void setAttributeDescription(String attributeDescription) {
		this.AttributeDescription = attributeDescription;
	}
	public void setAttributeName(String attributeName) {
		this.AttributeName = attributeName;
	}
	public void setTimestamp(String timestamp) {
		this.Timestamp = timestamp;
	}
	public String getAttributeValue() {
		return this.AttributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.AttributeValue = attributeValue;
	}
	public String getApplicationAttributesId() {
		return ApplicationAttributesId;
	}
	public void setApplicationAttributesId(String applicationAttributesId) {
		ApplicationAttributesId = applicationAttributesId;
	}
	public String getApplicationId() {
		return ApplicationId;
	}
	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}
	public String getXMLTagName() {
		return XMLTagName;
	}
	public void setXMLTagName(String xMLTagName) {
		XMLTagName = xMLTagName;
	}
	
}
