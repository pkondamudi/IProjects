package com.hnotch.controllers.application;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.SearchResult;
import com.hnotch.beans.data.User;
import com.hnotch.managers.data.ApplicationManager;
import com.hnotch.managers.data.InstallationManager;
import com.hnotch.managers.data.SearchResultsManager;
import com.hnotch.values.constants.Constants;

@Controller
public class Main {

	@RequestMapping(value = { "/", "main" }, method = RequestMethod.GET)
	public String main(ModelMap model) {
		return "main";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "search" }, method = RequestMethod.GET)
	public String search(@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "filter", required = false) String queryFilter,
			@RequestParam(value = "page", required = false) String pageParam, HttpSession session, ModelMap model) {

		SearchResultsManager searchResultsManager = new SearchResultsManager();
		ArrayList<SearchResult> searchResults;
		PagedListHolder<SearchResult> searchResultsList;
		
		if (pageParam == null) {
			if (queryFilter != null) {
				searchResults = searchResultsManager.doSearch(query, queryFilter);
			} else {
				searchResults = searchResultsManager.doSearch(query);
			}
			searchResultsList = new PagedListHolder<SearchResult>(searchResults);
			searchResultsList.setPage(0);
			searchResultsList.setPageSize(1);
			model.addAttribute(Constants.SEARCH_RESULTS, searchResultsList.getPageList());
			session.setAttribute(Constants.SEARCH_RESULTS, searchResultsList);
		} else {
			String page = pageParam;
			searchResultsList = (PagedListHolder<SearchResult>) session.getAttribute(Constants.SEARCH_RESULTS);
			if ("next".equals(page))
				searchResultsList.nextPage();
			else if ("previous".equals(page))
				searchResultsList.previousPage();
			model.addAttribute(Constants.SEARCH_RESULTS, searchResultsList.getPageList());
		}
		return "search";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "appcenter" }, method = RequestMethod.GET)
	public String appCenterHome(HttpSession session, @RequestParam(value = "page", required = false) String pageParam,
			ModelMap model) {
		// model.addAttribute("User", new User());
		ApplicationManager applicationManager = new ApplicationManager();
		ArrayList<Application> applications;
		PagedListHolder<Application> appList;
		applications = applicationManager
				.getUserApplications((User) session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		PagedListHolder<Application> applicationsList = new PagedListHolder<Application>(applications);
		applicationsList.setPage(0);
		applicationsList.setPageSize(5);
		if (pageParam == null) {
			session.setAttribute("userApplications", applicationsList);
			model.addAttribute(Constants.MODEL_ATTRIBUTE_USER_APPLICATIONS, applicationsList.getPageList());
		} else {
			String page = pageParam;
			appList = (PagedListHolder<Application>) session.getAttribute("userApplications");
			if ("next".equals(page))
				appList.nextPage();
			else if ("previous".equals(page))
				appList.previousPage();
			model.addAttribute(Constants.MODEL_ATTRIBUTE_USER_APPLICATIONS, appList.getPageList());
		}
		model.addAttribute(Constants.MODEL_ATTRIBUTE_USER,
				(User) session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		return "appcenterhome";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpSession session, ModelMap model) {
		ArrayList<Application> userApplications;
		InstallationManager installationManager = new InstallationManager();
		userApplications = installationManager
				.getInstalledApplications((User) session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		for (int index = 0; index < userApplications.size(); index++) {
			System.out.println("Installed applications: " + userApplications.get(index).getApplicationName());
		}
		model.addAttribute(Constants.MODEL_ATTRIBUTE_USER_APPLICATIONS, userApplications);
		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/allapps", method = RequestMethod.GET)
	public String appCenterAllApps(HttpSession session,
			@RequestParam(value = "page", required = false) String pageParam, ModelMap model) {
		// model.addAttribute("User", new User());
		ApplicationManager applicationManager = new ApplicationManager();
		ArrayList<Application> applications;
		applications = applicationManager
				.getAllApplications((User) session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		PagedListHolder<Application> applicationsList = new PagedListHolder<Application>(applications);
		applicationsList.setPage(0);
		applicationsList.setPageSize(5);
		PagedListHolder<Application> appList;
		if (pageParam == null) {
			session.setAttribute("allApplications", applicationsList);
			model.addAttribute(Constants.MODEL_ATTRIBUTE_ALL_APPLICATIONS, applicationsList.getPageList());
		} else {
			String page = pageParam;
			appList = (PagedListHolder<Application>) session.getAttribute("allApplications");
			if ("next".equals(page))
				appList.nextPage();
			else if ("previous".equals(page))
				appList.previousPage();
			model.addAttribute(Constants.MODEL_ATTRIBUTE_ALL_APPLICATIONS, appList.getPageList());
		}
		model.addAttribute(Constants.MODEL_ATTRIBUTE_USER,
				(User) session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		return "appcenterallapps";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public View logout(HttpSession session, ModelMap model) {
		session.invalidate();
		return new RedirectView("");
	}
}
