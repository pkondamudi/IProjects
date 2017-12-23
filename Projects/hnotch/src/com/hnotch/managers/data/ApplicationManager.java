package com.hnotch.managers.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.Attribute;
import com.hnotch.beans.data.User;
import com.hnotch.builders.data.ApplicationBuilder;
import com.hnotch.interfaces.data.ApplicationManagerInterface;

public class ApplicationManager implements ApplicationManagerInterface {


	ResultSet results = null;
	
	@Override
	public boolean createApplication(Application application) {
		
		ApplicationManager.session.execute(BOUND_STATEMENT_CREATE_APPLICATION.bind(application.getApplicationId(), application.getApplicationDescription(), application.getApplicationName(), application.getDeleted(), application.getTimestamp(), application.getUser().getUserId()));
		return true;
	}

	@Override
	public boolean updateApplication(Application application) {
		
		ApplicationManager.session.execute(BOUND_STATEMENT_UPDATE_APPLICATION.bind(application.getApplicationDescription(), application.getApplicationName()));
		return true;
	}

	@Override
	public boolean dropApplication(Application application) {
		
		ApplicationManager.session.execute(BOUND_STATEMENT_DROP_APPLICATION.bind(application.getApplicationId()));
		return true;
	}


	@Override
	public ArrayList<ApplicationComment> getApplicationComments(Application application) {
		
		return new ApplicationCommentsManager().getApplicationComments(application);
	}

	@Override
	public ArrayList<Attribute> getApplicationAttributes(Application application) {
		
		return new AttributeManager().getApplicationAttributes(application);
	}

	
	@Override
	public boolean addApplicationComments(ApplicationComment applicationComment) {
		
		return new ApplicationManager().addApplicationComments(applicationComment);
	}

	@Override
	public boolean addApplicationAttributes(Application application, ArrayList<Attribute> attributes) {

		return new AttributeManager().addApplicationAttributes(application, attributes);
	}

	@Override
	public boolean addApplicationUserInstallation(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Application> getUserApplications(User User) {
		
		ArrayList<Application> applications = new ArrayList<Application>();
		AttributeManager attributesManager = new AttributeManager();
		HashMap<String, String> attributes;
		ArrayList<Attribute> applicationAttributes;
		this.results = ApplicationManager.session.execute(BOUND_STATEMENT_GET_USER_APPLICATIONS.bind(User.getUserId()));
		for (Row row : results){
			applicationAttributes = attributesManager.getApplicationAttributes(new ApplicationBuilder().setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID)).build());
			attributes = new HashMap<String, String>();
			for(int currentIndex = 0; currentIndex < applicationAttributes.size(); currentIndex ++){
				System.out.println("Name: "+applicationAttributes.get(currentIndex).getAttributeName()+" Value:"+applicationAttributes.get(currentIndex).getAttributeValue());
				attributes.put(applicationAttributes.get(currentIndex).getAttributeName(), applicationAttributes.get(currentIndex).getAttributeValue());
			}
			applications.add(new ApplicationBuilder().setUser(User)
					.setApplicationDescription(row.getString(Application.COLUMN_APPLICATION_DESCRIPTION))
					.setApplicationName(row.getString(Application.COLUMN_APPLICATION_NAME))
					.setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID))
					.setComments(this.getApplicationComments(new ApplicationBuilder().setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID)).build()))
					.setAttributes(attributes)
					.build());
		}
		return applications;
	}

	@Override
	public ArrayList<Application> getApplications(ArrayList<Application> intalledApplications) {
		// TODO Auto-generated method stub
		
		ArrayList<Application> applications = new ArrayList<Application>();
		ArrayList<Attribute> applicationAttributes;
		AttributeManager attributesManager = new AttributeManager();
		HashMap<String, String> attributes;
		for(int index = 0; index < intalledApplications.size(); index++){
			this.results = ApplicationManager.session.execute(BOUND_STATEMENT_GET_APPLICATIONS.bind(intalledApplications.get(index).getApplicationId()));
			for (Row row : results){
				applicationAttributes = attributesManager.getApplicationAttributes(new ApplicationBuilder().setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID)).build());
				attributes = new HashMap<String, String>();
				for(int currentIndex = 0; currentIndex < applicationAttributes.size(); currentIndex ++){
					System.out.println("Name: "+applicationAttributes.get(currentIndex).getAttributeName()+" Value:"+applicationAttributes.get(currentIndex).getAttributeValue());
					attributes.put(applicationAttributes.get(currentIndex).getAttributeName(), applicationAttributes.get(currentIndex).getAttributeValue());
				}
				applications.add(new ApplicationBuilder()
						.setApplicationDescription(row.getString(Application.COLUMN_APPLICATION_DESCRIPTION))
						.setApplicationName(row.getString(Application.COLUMN_APPLICATION_NAME))
						.setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID))
						.setTimestamp(row.getString(Application.COLUMN_APPLICATION_TIMESTAMP))
						.setAttributes(attributes)
						.build());
			}
		}
		
		return applications;
	}

	@Override
	public boolean createApplication(Application application, ArrayList<Attribute> attributes) {
		// TODO Auto-generated method stub
		this.addApplicationAttributes(application, attributes);
		return true;
	}

	@Override
	public ArrayList<Application> getAllApplications(User User) {
		// TODO Auto-generated method stub
		InstallationManager installationManager = new InstallationManager();
		ArrayList<String> installedApplicationIdList = new ArrayList<String>();
		ArrayList<Application> installtedApplications;
		installtedApplications = installationManager.getInstalledApplications(User);
		for(int currentIndex = 0; currentIndex < installtedApplications.size(); currentIndex++){
			installedApplicationIdList.add(installtedApplications.get(currentIndex).getApplicationId());
		}
		
		ArrayList<Application> allApplications = new ArrayList<Application>();
		ArrayList<Attribute> applicationAttributes;
		AttributeManager attributesManager = new AttributeManager();
		HashMap<String, String> attributes;
		this.results = ApplicationManager.session.execute(BOUND_STATEMENT_GET_ALL_APPLICATIONS);
		for (Row row : results){
			if( ! installedApplicationIdList.contains(row.getString(Application.COLUMN_APPLICATION_ID))){
				applicationAttributes = attributesManager.getApplicationAttributes(new ApplicationBuilder().setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID)).build());
				attributes = new HashMap<String, String>();
				for(int currentIndex = 0; currentIndex < applicationAttributes.size(); currentIndex ++){
					System.out.println("Name: "+applicationAttributes.get(currentIndex).getAttributeName()+" Value:"+applicationAttributes.get(currentIndex).getAttributeValue());
					attributes.put(applicationAttributes.get(currentIndex).getAttributeName(), applicationAttributes.get(currentIndex).getAttributeValue());
				}
				allApplications.add(new ApplicationBuilder()
						.setApplicationDescription(row.getString(Application.COLUMN_APPLICATION_DESCRIPTION))
						.setApplicationName(row.getString(Application.COLUMN_APPLICATION_NAME))
						.setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID))
						.setComments(this.getApplicationComments(new ApplicationBuilder().setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID)).build()))
						.setAttributes(attributes)
						.build());
			}
		}
		return allApplications;
	}
	
}
