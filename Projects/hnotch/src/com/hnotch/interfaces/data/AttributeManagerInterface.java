package com.hnotch.interfaces.data;

import java.util.ArrayList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.Attribute;
import com.hnotch.interfaces.database.DataBase;

public interface AttributeManagerInterface extends DataBase {
	
	public static final String QUERY_CREATE_APPLICATION_ATTRIBUTE = "INSERT INTO \"ApplicationAttributes\"(\"ApplicationAttributesId\", \"ApplicationId\", \"AttributeId\", \"AttributeValue\", \"Timestamp\") VALUES(?, ?, ?, ?, ?)";
	public static final String QUERY_UPDATE_APPLICATION_ATTRIBUTE = "UPDATE \"ApplicationAttributes\" SET \"AttributeValue\" = ? WHERE \"ApplicationAttributesId\" = ?";
	public static final String QUERY_DROP_APPLICATION_ATTRIBUTE = "DELETE FROM \"ApplicationAttributes\" WHERE \"ApplicationAttributesId\" = ?";
	public static final String QUERY_GET_APPLICATION_ATTRIBUTES = "SELECT * FROM \"ApplicationAttributes\" WHERE \"ApplicationId\" = ? ALLOW FILTERING";
	public static final String QUERY_GET_ALL_ATTRIBUTES = "SELECT * FROM \"Attributes\" ALLOW FILTERING";
	public static final String QUERY_GET_ATTRIBUTE = "SELECT * FROM \"Attributes\" WHERE \"AttributeId\" = ? ALLOW FILTERING";
	public static final String QUERY_GET_ATTRIBUTE_WITH_XMLTAG = "SELECT * FROM \"Attributes\" WHERE \"XMLTagName\" = ? ALLOW FILTERING";
		
	
	/* CREATE ATTRIBUTE */
	public static final PreparedStatement STATEMENT_CREATE_APPLICATION_ATTRIBUTE = session.prepare(QUERY_CREATE_APPLICATION_ATTRIBUTE);
	public static final BoundStatement BOUND_STATEMENT_CREATE_APPLICATION_ATTRIBUTE = new BoundStatement(STATEMENT_CREATE_APPLICATION_ATTRIBUTE);
	/* CREATE ATTRIBUTE */
	
	/* UPDATE ATTRIBUTE */
	public static final PreparedStatement STATEMENT_UPDATE_APPLICATION_ATTRIBUTE = session.prepare(QUERY_UPDATE_APPLICATION_ATTRIBUTE);
	public static final BoundStatement BOUND_STATEMENT_UPDATE_APPLICATION_ATTRIBUTE = new BoundStatement(STATEMENT_UPDATE_APPLICATION_ATTRIBUTE);
	/* UPDATE ATTRIBUTE */
	
	/* DROP ATTRIBUTE */
	public static final PreparedStatement STATEMENT_DROP_APPLICATION_ATTRIBUTE = session.prepare(QUERY_DROP_APPLICATION_ATTRIBUTE);
	public static final BoundStatement BOUND_STATEMENT_DROP_APPLICATION_ATTRIBUTE = new BoundStatement(STATEMENT_DROP_APPLICATION_ATTRIBUTE);
	/* DROP ATTRIBUTE */
	
	/* GET APPLICATION ATTRIBUTES */
	public static final PreparedStatement STATEMENT_GET_APPLICATION_ATTRIBUTES = session.prepare(QUERY_GET_APPLICATION_ATTRIBUTES);
	public static final BoundStatement BOUND_STATEMENT_GET_APPLICATION_ATTRIBUTES= new BoundStatement(STATEMENT_GET_APPLICATION_ATTRIBUTES);
	/* GET APPLICATION ATTRIBUTES */
	
	/* GET ALL ATTRIBUTES */
	public static final PreparedStatement STATEMENT_ALL_ATTRIBUTES = session.prepare(QUERY_GET_ALL_ATTRIBUTES);
	public static final BoundStatement BOUND_STATEMENT_ALL_ATTRIBUTES= new BoundStatement(STATEMENT_ALL_ATTRIBUTES);
	/* GET ALL ATTRIBUTES */
	
	/* GET ATTRIBUTES DETAILS */
	public static final PreparedStatement STATEMENT_ATTRIBUTE = session.prepare(QUERY_GET_ATTRIBUTE);
	public static final BoundStatement BOUND_STATEMENT_ATTRIBUTE= new BoundStatement(STATEMENT_ATTRIBUTE);
	/* GET ATTRIBUTES DETAILS */
	
	/* GET ATTRIBUTES XMLTAG DETAILS */
	public static final PreparedStatement STATEMENT_ATTRIBUTE_XMLTAG = session.prepare(QUERY_GET_ATTRIBUTE_WITH_XMLTAG);
	public static final BoundStatement BOUND_STATEMENT_ATTRIBUTE_XMLTAG = new BoundStatement(STATEMENT_ATTRIBUTE_XMLTAG);
	/* GET ATTRIBUTES XMLTAG DETAILS */

	
	public boolean createApplicationAttribute(Application application, Attribute applicationAttribute);
	public boolean updateApplicationComment(Attribute applicationAttribute);
	public boolean dropApplicationComment(Attribute applicationAttribute);
	
	public ArrayList<Attribute> getApplicationAttributes(Application application);
	public Attribute getAttributeDetails(Attribute attribute);
	public ArrayList<Attribute> getAllAttributes();
	public boolean addApplicationAttributes(Application application, ArrayList<Attribute> attributes);
	public Attribute getAttributeByXMLTagName(Attribute attribute);
}
