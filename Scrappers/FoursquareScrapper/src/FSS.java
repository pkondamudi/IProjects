import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class FSS {

	public static Connection conn = null;

	public static void main(String[] args) throws InterruptedException, ResponseException, NotFound {
		// TODO Auto-generated method stub

		initBrowser(args[0]);

	}

	private static void initBrowser(String cityURL) throws InterruptedException, ResponseException, NotFound {
		// TODO Auto-generated method stub
		DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions options = new ChromeOptions();

		Dimension dim = new Dimension(10, 10);

		GetVenueTips gvt = new GetVenueTips();

		options.addArguments("chrome.switches", "--disable-extensions");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\PAVAN RAVIKANTH\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().setSize(dim);
		prefs.put("profile.managed_default_content_settings.images", 2);
		options.setExperimentalOption("prefs", prefs);

		chromeCaps.setCapability(ChromeOptions.CAPABILITY, options);
		UserAgent userAgent = new UserAgent();
		driver.get(cityURL);
		driver.findElement(By.linkText("See more popular places")).click();

		Thread.sleep(3000);

		while (driver.findElement(By.className("blueButton")).isDisplayed()) {
			driver.findElement(By.className("blueButton")).click();
			Thread.sleep(1000);
		}
		userAgent.openContent(driver.getPageSource());

		Elements anchors = userAgent.doc.findEvery("<a href>");
		for (Element a : anchors) {
			if (a.getAt("href").contains("https://foursquare.com/v/")) {
					driver.get(a.getAt("href").substring(0, a.getAt("href").lastIndexOf('/')));
					userAgent.openContent(driver.getPageSource());
				gvt.getVenueTips(a.getAt("href").substring(0, a.getAt("href").lastIndexOf('/')), driver, userAgent,
						insertVenue(insertCity(cityURL), a.getAt("href").toString(), a.getText(), userAgent.doc.findFirst("<span class=\"sectionCount\">").getText()));
			}
		}
		driver.close();
	}

	private static int insertCity(String city) {
		// TODO Auto-generated method stub

		initDB();
		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO `cities`(`City`) VALUES(?);",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, city);
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	private static void initDB() {

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/Foursquare?useUnicode=yes&characterEncoding=UTF-8";

		// Database credentials
		final String USER = "pavankon_app";
		final String PASS = "ppa_noknavap";

		try {
			Class.forName(JDBC_DRIVER);
			// System.out.println("Connecting to database..."+DB_URL);
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			}
		} catch (Exception ex) {
			System.out.print(ex);
			ex.printStackTrace();
		}
	}

	private static int insertVenue(int cityid, String string, String name, String tips) throws NotFound {
		// TODO Auto-generated method stub

		System.out.println("Current Venue:" + string);

		initDB();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("INSERT INTO `venues`(`vfid`, `cityid`, `name`, `tips`) VALUES(?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, string);
			pst.setInt(2, cityid);
			pst.setString(3, name);
			pst.setString(4, tips);
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
