package com.hnotch.interfaces.data;

import java.util.ArrayList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.Installation;
import com.hnotch.beans.data.User;
import com.hnotch.interfaces.database.DataBase;

public interface InstallationManagerInterface extends DataBase {


	public static final String QUERY_CREATE_USER_INSTALLATION = "INSERT INTO \"IntallationLog\"(\"InstallationId\", \"ApplicationId\", \"Uninstalled\", \"UserId\", \"Timestamp\") VALUES(?, ?, ?, ?, ?)";
	public static final String QUERY_GET_USER_INSTALLED_APPLICATIONS = "SELECT * FROM \"IntallationLog\" WHERE \"UserId\" = ? AND \"Uninstalled\" = 'No' ALLOW FILTERING";
	public static final String QUERY_SELECT_INSTALL_ID = "SELECT * FROM \"IntallationLog\" WHERE \"ApplicationId\" = ? AND \"UserId\" = ? ALLOW FILTERING";
	public static final String QUERY_UNINSTALL = "UPDATE \"IntallationLog\" SET \"Uninstalled\" = 'Yes' WHERE \"InstallationId\" = ?";
	
	/* INSTALLED APPLICATIONS USER */
	public static final PreparedStatement STATEMENT_GET_USER_INSTALLED_APPLICATIONS = session.prepare(QUERY_GET_USER_INSTALLED_APPLICATIONS);
	public static final BoundStatement BOUND_STATEMENT_GET_USER_INSTALLED_APPLICATIONS = new BoundStatement(STATEMENT_GET_USER_INSTALLED_APPLICATIONS);
	/* INSTALLED APPLICATIONS USER */
	
	/* APPLICATIONS USER INSTALLATION */
	public static final PreparedStatement STATEMENT_CREATE_USER_INSTALLATION = session.prepare(QUERY_CREATE_USER_INSTALLATION);
	public static final BoundStatement BOUND_STATEMENT_CREATE_USER_INSTALLATION = new BoundStatement(STATEMENT_CREATE_USER_INSTALLATION);
	/* APPLICATIONS USER INSTALLATION */
	
	/* APPLICATIONS USER UNINSTALLATION */
	public static final PreparedStatement STATEMENT_UNINSTALLATION = session.prepare(QUERY_UNINSTALL);
	public static final BoundStatement BOUND_STATEMENT_UNINSTALLATION = new BoundStatement(STATEMENT_UNINSTALLATION);
	/* APPLICATIONS USER UNINSTALLATION */
	
	/* GET USER UNINSTALLATION */
	public static final PreparedStatement STATEMENT_GET_INSTALLATION = session.prepare(QUERY_SELECT_INSTALL_ID);
	public static final BoundStatement BOUND_STATEMENT_GET_INSTALLATION = new BoundStatement(STATEMENT_GET_INSTALLATION);
	/* GET USER UNINSTALLATION */
	
	public boolean createInstallation(Installation installation, Application application, User user);
	public boolean dropInstallation(Installation installation);
	public Installation getInstallation(Application application, User user);
	public ArrayList<Application> getInstalledApplications(User User);
}
