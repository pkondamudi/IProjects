
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marrker.reg.db.manager.DataBaseManager;

/**
 * Servlet implementation class PutTopic
 */
@WebServlet("/PutTopic")
public class PutTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PUT_WEBTOPIC_USERID = "INSERT INTO `pavankon_marrker_reg`.`webtopics`"
			+ "(`SearchTerms`,`BoardName`,`BoardDescription`,`UserId`) " + "VALUES(?,?,?,?)";

	private static final String PUT_WEBTOPIC = "INSERT INTO `pavankon_marrker_reg`.`webtopics`"
			+ "(`SearchTerms`,`BoardName`,`BoardDescription`) " + "VALUES(?,?,?)";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PutTopic() {
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
		ResultSet rs;
		String userId = (String) request.getParameter("uid");
		try {
			if (userId.length() > 0) {
				pst = DataBaseManager.startDatabaseOperation(PUT_WEBTOPIC_USERID);
			} else {
				pst = DataBaseManager.startDatabaseOperation(PUT_WEBTOPIC);
			}

			pst.setString(1, request.getParameter("sterms"));
			pst.setString(2, request.getParameter("ctxname"));
			pst.setString(3, request.getParameter("ctxdessc"));
			
			if (userId.length() > 0) {
				pst.setString(4, (String)request.getParameter("uid"));
			}
			DataBaseManager.getDatabaseOprationResult(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
