package com.hnotch.builders.data;

import com.hnotch.beans.data.Installation;

public class InstallationBuilder {

	private Installation installation = new Installation();
	
	public InstallationBuilder setInstallationId(String installationId) {
		this.installation.setInstallationId(installationId);
		return this;
	}
	public InstallationBuilder setApplicationId(String applicationId) {
		this.installation.setApplicationId(applicationId);
		return this;
	}
	public InstallationBuilder setUninstalled(String uninstalled) {
		this.installation.setUninstalled(uninstalled);
		return this;
	}
	public InstallationBuilder setUserId(String userId) {
		this.installation.setUserId(userId);
		return this;
	}
	public InstallationBuilder setTimestamp(String timestamp) {
		this.installation.setTimestamp(timestamp);
		return this;
	}
}
