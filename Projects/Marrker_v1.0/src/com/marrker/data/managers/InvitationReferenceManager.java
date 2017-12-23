package com.marrker.data.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.marrker.data.beans.User;
import com.marrker.db.manager.RegDataBaseManager;
import com.marrker.util.EmailHelper;
import com.marrker.util.SessionIdentifierGenerator;

/**
 * Servlet implementation class getRefCode
 */
public class InvitationReferenceManager{

	public static final String INSERT_USER = "INSERT INTO "
			+ "`pavankon_marrker_reg`.`users` (`phno`, `RefCode`, `Name`) " + "VALUES (?,?,?)";

	public static final String SELECT_USER = "SELECT * FROM " + "`pavankon_marrker_reg`.`users` WHERE RefCode = ?";

	/*public static final String SELECT_REF_LOGS = "SELECT DISTINCT U.Name, COUNT(*) as VisitorCount FROM pavankon_marrker_reg.users U "
			+ "INNER JOIN pavankon_marrker_reg.reflog REF on U.refCode = REF.refCode "
			+ "GROUP BY U.phno, U.refCode, U.Name ORDER BY VisitorCount DESC LIMIT 5";*/

	public boolean isRefCodeExists(String refCode) {
		// TODO Auto-generated method stub
		// doGet(request, response);
		PreparedStatement pst;
		ResultSet rs;
		boolean exists = false;
		try {
			pst = RegDataBaseManager.startDatabaseOperation(SELECT_USER);
			pst.setString(1, refCode);
			rs = RegDataBaseManager.getDatabaseOprationResult(pst, SELECT_USER);
			if(rs.next()){
				exists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	
	
	public String putNewRefCodeAndSendInvitations(ArrayList<String> validEmails, User user) throws MessagingException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String randomId = SessionIdentifierGenerator.nextSessionId();
		PreparedStatement pst;
		EmailHelper emailHelper = new EmailHelper();
		try {
				for(int index = 0;index < validEmails.size(); index++){
					pst = RegDataBaseManager.startDatabaseOperation(INSERT_USER);
					pst.setString(1, randomId+"-"+validEmails.get(index));
					pst.setString(2, randomId);
					pst.setString(3, validEmails.get(index));
					RegDataBaseManager.getDatabaseOprationResult(pst);
					emailHelper.sendInvitationEmail(validEmails.get(index), randomId, user);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return randomId;
	}
}
