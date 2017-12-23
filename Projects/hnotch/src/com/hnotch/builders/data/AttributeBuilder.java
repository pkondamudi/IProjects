package com.hnotch.builders.data;

import com.hnotch.beans.data.Attribute;

public class AttributeBuilder {

	private Attribute attribute = new Attribute();
	
	public AttributeBuilder setAttributeId(String attributeId) {
		this.attribute.setAttributeId(attributeId);
		return this;
	}
	public AttributeBuilder setAttributeDescription(String attributeDescription) {
		this.attribute.setAttributeDescription(attributeDescription);
		return this;
	}
	public AttributeBuilder setAttributeName(String attributeValue) {
		this.attribute.setAttributeName(attributeValue);
		return this;
	}
	public AttributeBuilder setTimestamp(String timestamp) {
		this.attribute.setTimestamp(timestamp);
		return this;
	}
	
	public AttributeBuilder setAttributeValue(String attributeValue) {
		this.attribute.setAttributeValue(attributeValue);
		return this;
	}
	public AttributeBuilder setApplicationAttributesId(String applicationAttributesId) {
		this.attribute.setApplicationAttributesId(applicationAttributesId);
		return this;
	}
	public AttributeBuilder setApplicationId(String applicationId) {
		this.attribute.setApplicationId(applicationId);
		return this;
	}
	public AttributeBuilder setXMLTagName(String xMLTagName) {
		this.attribute.setXMLTagName(xMLTagName);
		return this;
	}
	public Attribute build(){
		return this.attribute;
	}
}
