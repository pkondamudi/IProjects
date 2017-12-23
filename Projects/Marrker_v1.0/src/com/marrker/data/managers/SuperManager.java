package com.marrker.data.managers;

import com.marrker.db.manager.DataBaseManager;

public class SuperManager {

	DataBaseManager dataBaseManager;
	SuperManager(){
		this.dataBaseManager = new DataBaseManager();
	}
}
