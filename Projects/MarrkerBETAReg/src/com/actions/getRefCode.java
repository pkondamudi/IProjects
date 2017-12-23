package com.actions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marrker.reg.db.manager.DataBaseManager;
import com.marrker.reg.util.SessionIdentifierGenerator;

/**
 * Servlet implementation class getRefCode
 */
@WebServlet("/getRefCode")
public class getRefCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String INSERT_USER = "INSERT INTO "
			+ "`pavankon_marrker_reg`.`users` (`phno`, `RefCode`, `Name`) " + "VALUES (?,?,?)";

	public static final String SELECT_USER = "SELECT * FROM " + "`pavankon_marrker_reg`.`users` WHERE phno = ?";

	public static final String SELECT_REF_LOGS = "SELECT DISTINCT U.Name, COUNT(*) as VisitorCount FROM pavankon_marrker_reg.users U "
			+ "INNER JOIN pavankon_marrker_reg.reflog REF on U.refCode = REF.refCode "
			+ "GROUP BY U.phno, U.refCode, U.Name ORDER BY VisitorCount DESC LIMIT 5";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getRefCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setAttribute("fRunners", getFrontRunners());
		getServletContext().getRequestDispatcher("/getRefCode.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String randomId = SessionIdentifierGenerator.nextSessionId();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = DataBaseManager.startDatabaseOperation(SELECT_USER);
			pst.setString(1, request.getParameter("phno"));
			rs = DataBaseManager.getDatabaseOprationResult(pst, SELECT_USER);
			if(rs.next()){
				randomId = rs.getString("refCode");
			}else{
				pst = DataBaseManager.startDatabaseOperation(INSERT_USER);
				pst.setString(1, request.getParameter("phno"));
				pst.setString(2, randomId);
				pst.setString(3, request.getParameter("uname"));
				DataBaseManager.getDatabaseOprationResult(pst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("randid", randomId);
		request.setAttribute("fRunners", getFrontRunners());
		getServletContext().getRequestDispatcher("/getRefCode.jsp").forward(request, response);
	}

	private ArrayList<String> getFrontRunners() {
		PreparedStatement pst;
		ResultSet rs = null;
		ArrayList<String> values = new ArrayList<String>();
		try {
			pst = DataBaseManager.startDatabaseOperation(SELECT_REF_LOGS);
			rs = DataBaseManager.getDatabaseOprationResult(pst, SELECT_REF_LOGS);
			while(rs.next()){
				values.add(rs.getString("Name")+"^"+rs.getInt("VisitorCount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
}
