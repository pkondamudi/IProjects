package com.hnotch.beans.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Installation {

	private String InstallationId;
	private String ApplicationId;
	private String Uninstalled;
	private String UserId;
	private String Timestamp;
	
	public Installation() {
		// TODO Auto-generated constructor stub
		this.InstallationId = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		this.Timestamp = new Timestamp(new Date().getTime()).toString();
		this.Uninstalled = "No";
	}
	
	public void setInstallationId(String installationId) {
		InstallationId = installationId;
	}
	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}
	public void setUninstalled(String uninstalled) {
		Uninstalled = uninstalled;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public String getInstallationId() {
		return InstallationId;
	}
	public String getApplicationId() {
		return ApplicationId;
	}
	public String getUninstalled() {
		return Uninstalled;
	}
	public String getUserId() {
		return UserId;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	
	
}
