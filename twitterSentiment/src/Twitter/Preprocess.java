package Twitter;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Preprocess {
	
	// method that replaces the abbreviations with their actual meaning
	public static String replaceAbbv (String atweet) {
		String newtweet="";
		try {
			MapCreation maps = new MapCreation();
			StringTokenizer word = new StringTokenizer(atweet);
			while (word.hasMoreElements()) {
				String nextword = (String) word.nextElement();
				if (maps.abbreviations.get(nextword) != null) {
					newtweet = newtweet + maps.abbreviations.get(nextword).toLowerCase() + " ";
				}
				else {
					newtweet = newtweet + (String) nextword.toLowerCase() + " ";  // transformning the tweet to lower case, as this is the first transformation made
				}
			}		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newtweet;
	}
	
	
	
	// method removes links and usernames used in the tweet content from the tweet
	public static String removeURL(String atweet) { // atweet is the given tweet to be preprocessed
		String newtweet = "";
		String ht = "http://";
		String hts = "https://";
		String ww = "www.";
		String ftp = "ftp://";
		String ss = "ssh://";
		StringTokenizer word = new StringTokenizer(atweet);
		while (word.hasMoreElements()) {
			String nextword = (String) word.nextElement();
			if ((!nextword.startsWith(ht)) && (!nextword.startsWith(hts))
					&& (!nextword.startsWith(ww))
					&& (!nextword.startsWith(ftp))
					&& (!nextword.startsWith(ss))
					&& (!nextword.startsWith("@"))) {
				newtweet = newtweet + nextword + " ";
			}
		}
		return newtweet;
	}

	// removes the letters that appear 3 consecutive times
	public static String removeDuplicates(String atweet) {

		String tweet = ""+ atweet.charAt(0);
		int count=1;
		for (int i=1; i < atweet.length(); i++) {
			if (atweet.charAt(i) == atweet.charAt(i-1)) {
				count ++;
				if (count < 3) {
					tweet = tweet + atweet.charAt(i);
				} }
			else {
				tweet = tweet + atweet.charAt(i);
				count=1;	
			}			
		}
		return tweet;
	}

	// remove all the extra characters
	public static String removeChars(String atweet) {
		String newtweet = "";
		for (int i = 0; i < atweet.length(); i++) {
			char c = atweet.charAt(i);
			int a = (int) c;
			if ((a > 64 && a < 90) || (a > 96 && a < 123) || (a == 32))
				newtweet = newtweet + c;
		}
		return newtweet;
	}

	// removes hash from hashtags (leaving only the word)
	public static String removeHash(String atweet) {
		String newtweet = "";
		StringTokenizer word = new StringTokenizer(atweet);
		while (word.hasMoreElements()) {
			String nextword = (String) word.nextElement();
			if (nextword.startsWith("#")) {
				newtweet = newtweet	+ nextword.substring(1, (int) nextword.length()) + " ";
			} 
			else {
				newtweet = newtweet + nextword + " ";
			}
		}
		return newtweet;
	}

}
