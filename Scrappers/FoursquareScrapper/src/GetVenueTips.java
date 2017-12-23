import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class GetVenueTips {

	public void getVenueTips(String at, WebDriver driver, UserAgent userAgent, int venueid) throws ResponseException, NotFound {
		// TODO Auto-generated method stub

		String queryString = "?tipsPage=INT_PAGE";
		
		driver.get(at);
		userAgent.openContent(driver.getPageSource());

		Elements tipdates = userAgent.doc.findEvery("<li class=\"tip tipWithLogging useTipUpvotes \">");

		getTips(tipdates, venueid);

		for (int i = 1;; i++) {
			System.out.println(at + queryString.replace("INT_PAGE", Integer.toString(i + 1)));
			driver.get(at + queryString.replace("INT_PAGE", Integer.toString(i)));
			userAgent.openContent(driver.getPageSource());
			tipdates = userAgent.doc.findEvery("<li class=\"tip tipWithLogging useTipUpvotes \">");
			if (tipdates.size() <= 0)
				break;
			getTips(tipdates, venueid);
		}
	}

	private void getTips(Elements tipdates, int venueid) throws NotFound {
		// TODO Auto-generated method stub

		Element tip, text;
		for (Element a : tipdates) {
			try{
				tip = a.findFirst("<span class=\"tipDate\">");
				text = a.findFirst("<div class=\"tipText\">");
				insertIntoDB(a.getAt("data-id"), tip.getText(), venueid, text.getText());
				System.out.println(a.getAt("data-id"));
				System.out.println(tip.getText());
				System.out.println(text.getText());
			}
			catch(Exception e){
				e.printStackTrace();
				e=null; System.gc();
			}
			
		}
	}

	private void insertIntoDB(String id, String date, int venueid, String text) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = FSS.conn.prepareStatement(
				"INSERT INTO `foursquare`.`tips` (`tfid`, `venueid`, `date`, `text`) VALUES(?, ?, ?, ?);",
				Statement.RETURN_GENERATED_KEYS);
		try {
			pst.setString(1, id);
			pst.setInt(2, venueid);
			pst.setString(3, date);
			pst.setString(4, text);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pst.setString(1, id);
			pst.setInt(2, venueid);
			pst.setString(3, date);
			pst.setString(4, "Special Character in there...");
			pst.executeUpdate();
		}

	}

}
