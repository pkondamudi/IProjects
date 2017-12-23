package com.hnotch.managers.data;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.Attribute;
import com.hnotch.builders.data.AttributeBuilder;
import com.hnotch.interfaces.data.AttributeManagerInterface;

public class AttributeManager implements AttributeManagerInterface{

	ResultSet results = null;
	
	@Override
	public boolean createApplicationAttribute(Application application, Attribute applicationAttribute) {
		
		AttributeManager.session.execute(BOUND_STATEMENT_CREATE_APPLICATION_ATTRIBUTE.bind(applicationAttribute.getApplicationAttributesId(), application.getApplicationId(), applicationAttribute.getAttributeId(), applicationAttribute.getAttributeValue(), applicationAttribute.getTimestamp()));
		return true;
	}

	@Override
	public boolean updateApplicationComment(Attribute applicationAttribute) {
		
		AttributeManager.session.execute(BOUND_STATEMENT_UPDATE_APPLICATION_ATTRIBUTE.bind(applicationAttribute.getAttributeValue(), applicationAttribute.getApplicationAttributesId()));
		return true;
	}

	@Override
	public boolean dropApplicationComment(Attribute applicationAttribute) {
		
		AttributeManager.session.execute(BOUND_STATEMENT_DROP_APPLICATION_ATTRIBUTE.bind(applicationAttribute.getApplicationAttributesId()));
		return true;
	}

	@Override
	public ArrayList<Attribute> getApplicationAttributes(Application application) {
		// TODO Auto-generated method stub
		
		ArrayList<Attribute> applicationAttributes = new ArrayList<Attribute>();
		Attribute attribute;
		this.results = AttributeManager.session.execute(BOUND_STATEMENT_GET_APPLICATION_ATTRIBUTES.bind(application.getApplicationId()));
		for (Row row : results){
			attribute = new AttributeBuilder()
			.setAttributeId(row.getString(Attribute.COLUMN_ATTRIBUTEID))
			.setAttributeValue(row.getString(Attribute.COLUMN_ATTRIBUTEVALUE))
			.setApplicationAttributesId(row.getString(Attribute.COLUMN_APPLICATIONATTRIBUTESID))
			.setApplicationId(row.getString(Attribute.COLUMN_APPLICATIONID))
			.build();
			
			
			attribute.setAttributeName(getAttributeDetails(attribute).getAttributeName());
			attribute.setAttributeDescription(getAttributeDetails(attribute).getAttributeDescription());
			
			applicationAttributes.add(attribute);
		}
		return applicationAttributes;
	}

	@Override
	public Attribute getAttributeDetails(Attribute attribute) {
		// TODO Auto-generated method stub
		
		this.results = AttributeManager.session.execute(BOUND_STATEMENT_ATTRIBUTE.bind(attribute.getAttributeId()));
		for (Row row : results){
			attribute = new AttributeBuilder()
					.setAttributeId(row.getString(Attribute.COLUMN_ATTRIBUTEID))
					.setAttributeName(row.getString(Attribute.COLUMN_ATTRIBUTENAME))
					.setAttributeDescription(row.getString(Attribute.COLUMN_ATTRIBUTEDESCRIPTION))
					.build();
		}
		return attribute;
	}

	@Override
	public ArrayList<Attribute> getAllAttributes() {
		// TODO Auto-generated method stub
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		this.results = AttributeManager.session.execute(QUERY_GET_ALL_ATTRIBUTES);
		for (Row row : results){
			attributes.add(new AttributeBuilder()
					.setAttributeId(row.getString(Attribute.COLUMN_ATTRIBUTEID))
					.setAttributeName(row.getString(Attribute.COLUMN_ATTRIBUTENAME))
					.setAttributeDescription(row.getString(Attribute.COLUMN_ATTRIBUTEDESCRIPTION))
					.setXMLTagName(row.getString(Attribute.COLUMN_XMLTAGNAME))
					.build());
		}
		return attributes;
	}

	@Override
	public boolean addApplicationAttributes(Application application, ArrayList<Attribute> attributes) {
		// TODO Auto-generated method stub
		for(int index = 0; index < attributes.size(); index++){
			createApplicationAttribute(application, attributes.get(index));
		}
		return true;
	}

	@Override
	public Attribute getAttributeByXMLTagName(Attribute attribute) {
		// TODO Auto-generated method stub
		this.results = AttributeManager.session.execute(BOUND_STATEMENT_ATTRIBUTE_XMLTAG.bind(attribute.getXMLTagName()));
		for (Row row : results){
			attribute = new AttributeBuilder()
					.setAttributeId(row.getString(Attribute.COLUMN_ATTRIBUTEID))
					.setAttributeName(row.getString(Attribute.COLUMN_ATTRIBUTENAME))
					.setAttributeDescription(row.getString(Attribute.COLUMN_ATTRIBUTEDESCRIPTION))
					.build();
		}
		return attribute;
	}
	

}
