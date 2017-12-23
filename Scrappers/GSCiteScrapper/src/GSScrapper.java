import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class GSScrapper {

	private static Process startTOR() throws IOException {

		ProcessBuilder pb = new ProcessBuilder(".\\Tor Browser\\Browser\\firefox.exe");

		return pb.start();
	}

	public static void startFromDB() throws SQLException, ResponseException, InterruptedException, IOException {

		Process pb = startTOR();

		Thread.sleep(5000);

		String citeString = null;

		WebDriver browser = Util.getBrowser();

		UserAgent userAgent = new UserAgent();

		Connection con = DBManager.getDBConnection();

		Statement st = con.createStatement();

		PreparedStatement ps = con.prepareStatement("Update sp17680.sample set citations=? where id =?");

		ResultSet rs = st.executeQuery(
				"Select id, doc->\"$.citation.title\" as title FROM sp17680.sample where citations is null and doc->\"$.citation.title\" is not null");

		while (rs.next()) {

			System.out.println("Id:" + rs.getInt("id") + "\n");

			browser.navigate().to(
					Constants.GS_ROOT_URL.replace(Constants.qValue, URLEncoder.encode(rs.getString("title"), "UTF-8")));

			if (browser.getPageSource().contains(Constants.gs_roboString_1)
					|| browser.getPageSource().contains(Constants.gs_roboString_2)) {

				JOptionPane.showMessageDialog(null, Constants.app_roboDialog);
			}

			userAgent.openContent(browser.getPageSource());

			System.out.println("got page source...\n");

			Elements els = null;

			try {
				System.out.println("finding first link...\n");
				try {
					els = userAgent.doc.findFirst(Constants.gs_divString).findEach("<a>");
				} catch (NotFound nf) {
					if (browser.getPageSource().contains(Constants.gs_sorry)) {
						System.out.println("Says sorry...\n");
						pb.destroy();
						browser.close();
						System.exit(0);
					}
				}

				for (int i = 1; i <= els.size(); i++) {

					if (i == 3) {

						Element e = els.getElement(i);
						citeString = e.getText();

						if (citeString.contains(Constants.gs_citeBy)) {

							citeString = citeString.substring(Constants.gs_citeBy.length() + 1, citeString.length())
									.trim();

							if (citeString.matches("^-?\\d+$")) {

								ps.setInt(1, Integer.parseInt(citeString));

								System.out.println("Setting citations...\n");
							}

						} else {
							ps.setInt(1, 0);
						}
						break;
					}
				}
				ps.setInt(2, rs.getInt("id"));

				ps.executeUpdate();

				System.out.println("Updated...\n");
				System.out.println(citeString);
			} catch (Exception e) {

				e.printStackTrace();
			}

			System.out.println("Sleeping...\n");
			Thread.sleep(Configurations.requestDelay);
		}
		browser.close();
		pb.destroyForcibly();
	}

	public static void startFromCSV() throws IOException, ResponseException, InterruptedException {
		// TODO Auto-generated method stub

		String citeString = null, result = null;

		WebDriver browser = Util.getBrowser();

		UserAgent userAgent = new UserAgent();

		Reader in = new FileReader(Util.getCVS().getAbsolutePath());

		String timeStamp = new SimpleDateFormat(Constants.outputFilename).format(new Date());

		PrintWriter printWriter = new PrintWriter(timeStamp + Constants.outputFilenameExt);

		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

		File file;
		FileOutputStream fileOutputStream;

		for (CSVRecord record : records) {

			file = new File(timeStamp + ".csv");

			fileOutputStream = new FileOutputStream(file, true);

			printWriter = new PrintWriter(fileOutputStream);

			browser.navigate()
					.to(Constants.GS_ROOT_URL.replace(Constants.qValue, URLEncoder.encode(record.get(0), "UTF-8")));

			if (browser.getPageSource().contains(Constants.gs_roboString_1)
					|| browser.getPageSource().contains(Constants.gs_roboString_2)) {

				JOptionPane.showMessageDialog(null, Constants.app_roboDialog);
			}

			userAgent.openContent(browser.getPageSource());

			try {
				citeString = userAgent.doc.findFirst(Constants.gs_divString).findFirst("<a>").getText();

				if (citeString.contains(Constants.gs_citeBy)) {

					citeString = citeString.substring(Constants.gs_citeBy.length() + 1, citeString.length()).trim();

					if (citeString.matches("^-?\\d+$")) {

						result = record.get(0) + "," + citeString;
					}

					System.out.println(result.toString());

					printWriter.println(result.toString());
					printWriter.close();

				}
			} catch (NotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Thread.sleep(Configurations.requestDelay);
		}

		printWriter.close();
		browser.close();
	}

}
