import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class NonPokeScrapper {

	static String pokeStopNearBy = "https://www.yelp.com/search?find_desc=Restaurants&find_loc={#}";
	// static String pokeStopNearBy =
	// "https://www.yelp.com/search?find_desc=Restaurants&find_loc={#}&attrs=PokestopNearby";

	static UserAgent userAgent = new UserAgent();

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// private static final String DB_URL =
	// "jdbc:mysql://localhost/data_science";
	private static final String DB_URL = "jdbc:mysql://localhost/Yelp_Pokemon";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "toor";

	private static Connection conn = null;

	static boolean proceedFlag = false;

	static HashMap<String, Object> prefs = new HashMap<String, Object>();

	static DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();

	static Dimension dim = new Dimension(10, 10);

	static {

		prefs.put("profile.managed_default_content_settings.images", 2);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("prefs", prefs);

		chromeCaps.setCapability(ChromeOptions.CAPABILITY, options);
		chromeCaps.setJavascriptEnabled(false);
	}

	public static void main(String[] args) throws NotFound, ResponseException, SQLException, ParseException, URISyntaxException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
		"E:\\Courses\\Others\\Jobs\\Yelp\\exes\\chromedriver.exe");
		
		init();
		PreparedStatement pst = conn.prepareStatement("select idresturents, URL from yelp_pokemon.resturents, yelp_pokemon.cities where yelp_pokemon.cities.idCities="+args[0],
				Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			getYelpReviews(processHRef(rs.getString("URL")), rs.getInt("idresturents"));
		}

		/*try {
			getYelpCities(args[0]);
			//getYelpResturentsByCities("naperville-il-us", 1);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	static void getYelpCities(String cityId) throws ResponseException, NotFound, SQLException, ParseException {

		ChromeDriver driverL;

		init();
		PreparedStatement pst = conn.prepareStatement("select * from yelp_pokemon.allcities where id = ?",
				Statement.RETURN_GENERATED_KEYS);

		pst.setString(1, cityId);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			driverL = new ChromeDriver(chromeCaps);
			driverL.manage().window().setSize(dim);
			driverL.manage().window().setSize(dim);
			driverL.get("https://www.yelp.com/locations");
			userAgent.openContent(driverL.getPageSource());
			driverL.close();
			driverL.quit();
			driverL = null;
			System.gc();
			Elements spans = userAgent.doc.findEvery("<ul class=\"cities\">"); // find
			Element anchor;
			String hRef = null;
			for (Element span : spans) { // iterate through Results
				anchor = span.findFirst("<a href>");
				hRef = anchor.getAt("href");
				// System.out.println(); //print the anchor's href attribute
				// System.out.println(hRef.substring(hRef.lastIndexOf('/') + 1,
				// hRef.length()).trim());
				if (hRef.substring(hRef.lastIndexOf('/') + 1, hRef.length()).trim()
						.equalsIgnoreCase(rs.getString("City"))) {
					break;
				}
			}
			getYelpResturentsByCities(hRef.substring(hRef.lastIndexOf('/') + 1, hRef.length()).trim(),
					insertCity(hRef.substring(hRef.lastIndexOf('/') + 1, hRef.length()).trim()));
		}
	}

	private static void getYelpResturentsByCities(String city, int autoId) throws SQLException, ParseException {
		// TODO Auto-generated method stub

		ChromeDriver driverL;

		int start = 10, i = 0;

		try {
			userAgent.visit(pokeStopNearBy.replace("{#}", city)); // open the

			for (i = 0; !userAgent.doc.innerHTML().contains("Try a larger search area."); i++) {
				driverL = new ChromeDriver(chromeCaps);
				driverL.manage().window().setSize(dim);
				System.out.println(pokeStopNearBy.replace("{#}", city) + "&start=" + (start * i));
				driverL.get(pokeStopNearBy.replace("{#}", city) + "&start=" + (start * i));
				userAgent.openContent(driverL.getPageSource());
				driverL.close();
				driverL.quit();
				driverL = null;
				System.gc();
				// userAgent.visit(pokeStopNearBy.replace("{#}", city) +
				// "&start=" + (start * i));

				if (userAgent.doc.innerHTML().contains("not a robot")) {
					JOptionPane.showInputDialog("Please do robot check, I cannot proceed...");
					i--;
					continue;
				}

				Elements spans = userAgent.doc.findEvery("<span class=\"indexed-biz-name\">"); // find
				System.out.println(spans.size());
				Element anchor;
				String hRef;
				if (spans.size() > 0) {
					for (Element span : spans) { // iterate through Results
						driverL = new ChromeDriver(chromeCaps);
						driverL.manage().window().setSize(dim);
						anchor = span.findFirst("<a href>");
						hRef = anchor.getAt("href");
						System.out.println(anchor.innerText());
						driverL.close();
						driverL.quit();
						driverL = null;
						System.gc();
						getYelpReviews(processHRef(hRef), insertResturent(anchor.innerText(), autoId, hRef));
					}
				} else {
					break;
				}
			}

		} catch (Exception e) {
			System.err.println(e);
			i--;
		}
		System.out.println();
	}

	private static void getYelpBInfo(Elements binfo, int autoId) throws NotFound, SQLException {
		// TODO Auto-generated method stub

		init();
		PreparedStatement pst = conn.prepareStatement("INSERT INTO `features`(`restid`,`Feature`,`value`) VALUES(?,?,?);",
				Statement.RETURN_GENERATED_KEYS);

		Element dt, dd;
		
		Elements dl = binfo.findEvery("<dl>");
		
		for (Element d: dl) { // iterate through Results
			dt = d.findFirst("dt");
			dd = d.findFirst("dd");
			pst.setInt(1, autoId);
			pst.setString(2, dt.getText().trim());
			pst.setString(3, dd.getText().trim());
			pst.executeUpdate();
		}
	}

	private static String processHRef(String hRef) throws URISyntaxException {
		// TODO Auto-generated method stub
		URI uri = null;
		try {
			uri = new URI(hRef);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new URI(uri.getScheme (),
                uri.getAuthority (),
                uri.getPath (),
                null, // ignore the query part of the input url
                uri.getFragment ()).toString();
	}

	

	private static void getYelpReviews(String hRef, int autoId) throws SQLException, ParseException {

		int start = 20, totalReviewPages = 0, i = 0;
		String URL;
		ChromeDriver driverL = new ChromeDriver(chromeCaps);

		driverL.manage().window().setSize(dim);

		driverL.manage().window().setSize(dim);

		try {
			driverL.get("https://www.yelp.com" + hRef);
			userAgent.openContent(driverL.getPageSource());
			// userAgent.visit(hRef); // open the HTML (or XML) from a file

			Element reviewCount = userAgent.doc.findFirst("<span class=\"review-count rating-qualifier\">");
			String reviews = reviewCount.getText().trim();
			Elements binfo = userAgent.doc.findEvery("<div class=\"short-def-list\">");
			System.out.println("***********************************************" + binfo.size()
					+ "***********************************************");
			getYelpBInfo(binfo, autoId);
			System.out.println(reviews);
			updateReviews(autoId, (Integer.parseInt((String) reviews.subSequence(0, reviews.indexOf(" reviews")))));
			totalReviewPages = (Integer.parseInt((String) reviews.subSequence(0, reviews.indexOf(" reviews")))) / 20;
			if (totalReviewPages < 20) {
				totalReviewPages = (Integer.parseInt((String) reviews.subSequence(0, reviews.indexOf(" reviews"))));
			}
			for (i = 0; i <= totalReviewPages && !userAgent.doc.innerHTML().contains("Whoops"); i++) {

				if ((start * i) > 100) {
					driverL.close();
					driverL.quit();
					driverL = null;
					System.gc();
					driverL = new ChromeDriver(chromeCaps);
					driverL.manage().window().setSize(dim);
				}

				if (i == 0) {
					URL = "https://www.yelp.com" + hRef;
					driverL.get(URL);
					userAgent.openContent(driverL.getPageSource());
					// userAgent.visit(hRef);
					System.out.println(URL);
				} else {
					URL = "https://www.yelp.com" + hRef + "?start=" + (start * i);
					driverL.get(URL);
					userAgent.openContent(driverL.getPageSource());
					System.out.println(hRef + "?start=" + (start * i));
				}
				if (userAgent.doc.innerHTML()
						.contains("Hey there! Before you continue, we just need to check that you're not a robot.")) {
					JOptionPane.showInputDialog("Please do robot check, I cannot proceed...");
					i--;
					continue;
				}
				Elements spans = userAgent.doc.findEvery("<div class=\"review-content\">"); // find
				Element p;
				Element date;
				Element img;
				if (spans.size() > 0) {
					insertURL(autoId, URL, spans.size());
					for (Element span : spans) { // iterate through Results
						System.out.println(span.getText().trim()); // print each
						date = span.findFirst("<span class=\"rating-qualifier\">");
						img = span.findFirst("<img class=\"offscreen\"");
						img.getAt("alt").substring(0, 2);
						p = span.findFirst("<p lang=\"en\">");
						insertReview(date.getText().trim(), autoId, p.getText(), img.getAt("alt").substring(0, 3));
					}
				} else {
					break;
				}
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			i--;
		}
		System.out.println();
		driverL.close();
		driverL.quit();
		driverL = null;
		System.gc();
	}

	private static void insertURL(int autoId, String uRL, int size) throws SQLException {
		// TODO Auto-generated method stub
		init();
		PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO `resturls` (`restid`,`URL`,`nReviews`) VALUES(? ,? ,?);", Statement.RETURN_GENERATED_KEYS);

		pst.setInt(1, autoId);
		pst.setString(2, uRL);
		pst.setInt(3, size);
		pst.executeUpdate();
	}

	private static void updateReviews(int autoId, int i) throws SQLException {
		// TODO Auto-generated method stub

		init();
		PreparedStatement pst = conn.prepareStatement("UPDATE `resturents` SET nReviews = ? where idresturents = ?;",
				Statement.RETURN_GENERATED_KEYS);

		pst.setInt(1, i);
		pst.setInt(2, autoId);
		pst.executeUpdate();
	}

	private static void init() {
		try {
			Class.forName(JDBC_DRIVER);
			// System.out.println("Connecting to database..."+DB_URL);
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			}
		} catch (Exception ex) {
			System.err.print(ex);
			ex.printStackTrace();
		}
	}

	private static int insertCity(String string) throws SQLException {
		// TODO Auto-generated method stub

		init();
		PreparedStatement pst = conn.prepareStatement("INSERT INTO `cities`(`City`) VALUES(?);",
				Statement.RETURN_GENERATED_KEYS);

		pst.setString(1, string);
		pst.executeUpdate();

		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);

	}

	private static int insertResturent(String string, int autoId, String hRef) {
		// TODO Auto-generated method stub

		init();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("INSERT INTO `resturents`(`cityid`, `name`, `URL`) VALUES(?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(2, string);
			pst.setInt(1, autoId);
			pst.setString(3, hRef);
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		return -1;
	}

	private static int insertReview(String string, int autoId, String review, String rating) throws SQLException, ParseException {
		// TODO Auto-generated method stub

		init();
		DateFormat df = new SimpleDateFormat("MM/dd/yyy");
		PreparedStatement pst = conn.prepareStatement("INSERT INTO `review_new`(`restid`, `reviewDate`, `text`, `rating`) VALUES(?, ?, ?, ?);",
				Statement.RETURN_GENERATED_KEYS);

		pst.setInt(1, autoId);
		pst.setDate(2, new Date(df.parse(string).getTime()));
		pst.setString(3, review);
		pst.setString(4, rating);
		pst.executeUpdate();

		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

}
