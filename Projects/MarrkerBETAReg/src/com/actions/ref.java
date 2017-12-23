package com.actions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marrker.reg.db.manager.DataBaseManager;

/**
 * Servlet implementation class logRef
 */
@WebServlet("/ref/*")
public class ref extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String INSERT_REF = "INSERT INTO "
			+ "`pavankon_marrker_reg`.`reflog` (`refcode`,`IPLOC`) VALUES(?,?)";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ref() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PreparedStatement pst;
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		try {
			pst = DataBaseManager.startDatabaseOperation(INSERT_REF);
			pst.setString(1,
					request.getRequestURI()
							.subSequence(request.getRequestURI().lastIndexOf('/') + 1, request.getRequestURI().length())
							.toString());
			pst.setString(2, ipAddress);
			DataBaseManager.getDatabaseOprationResult(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doPost(request, response);
		}
		response.sendRedirect("http://www.marrker.com");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
