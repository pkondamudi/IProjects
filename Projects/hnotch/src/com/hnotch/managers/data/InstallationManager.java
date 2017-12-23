package com.hnotch.managers.data;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.Installation;
import com.hnotch.beans.data.User;
import com.hnotch.builders.data.ApplicationBuilder;
import com.hnotch.interfaces.data.InstallationManagerInterface;

public class InstallationManager implements InstallationManagerInterface {

	ResultSet results = null;
	ApplicationManager applicationManager;
	
	@Override
	public boolean createInstallation(Installation installation, Application application, User user) {
		
		InstallationManager.session.execute(BOUND_STATEMENT_CREATE_USER_INSTALLATION.bind(installation.getInstallationId(), application.getApplicationId(), installation.getUninstalled(), user.getUserId(), installation.getTimestamp()));
		return true;
	}
	
	@Override
	public boolean dropInstallation(Installation installation) {
		
		InstallationManager.session.execute(BOUND_STATEMENT_UNINSTALLATION.bind(installation.getInstallationId()));
		return true;
	}
	
	@Override
	public ArrayList<Application> getInstalledApplications(User User) {
		// TODO Auto-generated method stub
		applicationManager = new ApplicationManager();
		ArrayList<Application> applications = new ArrayList<Application>();
		this.results = InstallationManager.session.execute(BOUND_STATEMENT_GET_USER_INSTALLED_APPLICATIONS.bind(User.getUserId()));
		for (Row row : this.results){
				applications.add(new ApplicationBuilder()
						.setApplicationId(row.getString(Application.COLUMN_APPLICATION_ID))
						.build());
		}
		return applicationManager.getApplications(applications);
	}


	@Override
	public Installation getInstallation(Application application, User user) {
		// TODO Auto-generated method stub
		Installation installation = new Installation();
		this.results = InstallationManager.session.execute(BOUND_STATEMENT_GET_INSTALLATION.bind(application.getApplicationId(), user.getUserId()));
		for (Row row : this.results){
			installation.setInstallationId(row.getString("InstallationId"));
	}
		return installation;
	}
	
}
