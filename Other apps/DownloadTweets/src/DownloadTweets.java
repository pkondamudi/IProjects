import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class DownloadTweets {

	static PrintWriter printWriter;
	private static long totalReplies = 0;
	private static long totalNonReplies = 0;
	static HashMap<String, String> languages = new HashMap<String, String>();
	private static HashMap<String, String> sources = new HashMap<String, String>();
	private static String INSERT_TWEET = "INSERT INTO `pokemongotweets`.`pokemongo_tweets`"
			+ "(`inReplyToUserId`,`userMentionEntities`,`tweetscol`,"
			+ "`retweeted`,`currentUserRetweetId`,`createdAt`,"
			+ "`hashtagEntities`,`mediaEntities`,`inReplyToStatusId`,"
			+ "`extendedMediaEntities`,`id`,`text`,`lang`,`favorited`,"
			+ "`retweet`,`accessLevel`,`URLEntities`,`truncated`,`quotedStatusId`,"
			+ "`possiblySensitive`,`contributors`,`retweetedByMe`,`symbolEntities`,"
			+ "`retweetCount`,`favoriteCount`) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static PreparedStatement prepareInsert;
	
	static enum Switches {
		inReplyToUserId, source, inReplyToStatusId, text, lang, truncated, possiblySensitive, retweetCount, favoriteCount;
	}

	public static void main(String[] args) throws IOException, TwitterException, JSONException {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(date.getTime());
		File file = new File("tweets_" + date.getTime() + ".txt");
		file.createNewFile();
		if (!file.exists()) {
			System.exit(1);
		}
		Twitter twitter = TwitterFactory.getSingleton();
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		JOptionPane.showInputDialog(null,
				"Open the following URL and grant access to your account and enter the PIN in next dialog",
				"Open the following URL and grant access to your account and enter the PIN in next dialog: ",
				JOptionPane.QUESTION_MESSAGE, null, null, requestToken.getAuthorizationURL());
		while (null == accessToken) {
			String pin = JOptionPane.showInputDialog("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			try {
				if (pin.length() > 0) {
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		Query query = new Query(JOptionPane.showInputDialog(null,
				"Enter search trems/hashtag with hash symbol (E.g. #PokemonGo)", "#PokemonGo"));
		query.setCount(100);
		QueryResult result;
		printWriter = new PrintWriter(file);
		int i = 0;
		JOptionPane.showMessageDialog(null, "Downloading... Click Ok and wait for download complete dialog...");
		DataBaseManager dataBaseManager = new DataBaseManager();
		Connection conn = dataBaseManager.getNewDataBaseConnection();
		while (query != null) {
			result = twitter.search(query);
			for (Status status : result.getTweets()) {
				try {
					prepareInsert = conn.prepareStatement(INSERT_TWEET);
					printJSONObject(status, false);
					prepareInsert.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						printJSONObject(status, true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						printWriter.println(e.toString());
					}
					e.printStackTrace();
					printWriter.println(e.toString());
				}
				System.out.println(status);
				printWriter.println("EOF");
				i++;
			}
			query = result.nextQuery();
		}
		JOptionPane.showMessageDialog(null, "Download complete !!! Total Tweets downloaded:" + i);
		System.exit(0);
	}

	private static void printJSONObject(Object object, boolean isNested) throws SQLException {
		// TODO Auto-generated method stub
		JSONObject jObject = new JSONObject(object);
		Iterator<?> keys = jObject.keys();
		int index = 1;

		while (keys.hasNext()) {
			String key = (String) keys.next();
			try {
				if (jObject.get(key) instanceof JSONObject) {
					printJSONObject(jObject.get(key), true);
				} else {
					printWriter.println("Key:" + key + "|Value:" + jObject.get(key) + "| Missing in DB:" + isNested);
					prepareInsert.setString(index, jObject.get(key).toString());
					index++;
					//processKey(key, jObject.get(key).toString());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*private static void processKey(String key, String value) {
		// TODO Auto-generated method stub
		System.out.println(Switches.valueOf(key));
		switch (Switches.valueOf(key)) {
		case inReplyToUserId:
			processInReplyToUserId(value);
			break;
		case source:
			processSource(value);
			break;
		case inReplyToStatusId:
			processInReplyToStatusId(value);
			break;
		case text:
			processText(value);
			break;
		case lang:
			processLang(value);
			break;
		case truncated:
			processTrunctated(value);
			break;
		case possiblySensitive:
			processPossiblySensitive(value);
			break;
		case retweetCount:
			processRetweetCount(value);
			break;
		case favoriteCount:
			processFavoriteCount();
			break;
		}
	}

	private static void processSource(String value) {
		// TODO Auto-generated method stub
		sources.put(value.substring(value.indexOf(">")+1, value.indexOf("</a>")), value.substring(value.indexOf(">")+1, value.indexOf("</a>")));
	}

	private static void processLang(String value) {
		// TODO Auto-generated method stub
		languages.put(value, value);
	}

	private static void processInReplyToUserId(String value) {
		// TODO Auto-generated method stub
		if(value.equalsIgnoreCase("-1")){
			totalReplies++;
		}else{
			totalNonReplies++;
		}
	}*/

}
