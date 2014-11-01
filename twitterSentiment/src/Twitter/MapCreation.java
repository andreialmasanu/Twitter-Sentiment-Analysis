package Twitter;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapCreation {
	public Map<String, Integer> emoticons = new HashMap<String, Integer>();
	public Map<String, Double> intensifiers = new HashMap<String, Double>();
	public Map<String, Integer> words = new HashMap<String, Integer>();
	public Map<String, String> abbreviations = new HashMap<String, String>();
	public List<String> negations = new ArrayList<String>();
	public Map<String, String> countries = new HashMap<String, String>();
	public Map<String, String> cities = new HashMap<String, String>();
	
	public MapCreation() throws FileNotFoundException{
		try {
			emoticonMap();
			intensifierMap();
			emotionsMap();
			abbreviationsMap();
			negationList();
			countriesMap();
			citiesMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// creating the emoticons hashmap
	public void emoticonMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/emoticons.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + line.charAt(i);
			}
			emoticons.put(s, Integer.parseInt(line.substring(i + 1, line.length())));
		}
		reader.close();
	}

	// creating intensifiers hashmap
	public void intensifierMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/intensifiers.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + line.charAt(i);
			}
			intensifiers.put(s, Double.parseDouble(line.substring(i + 1, line.length())));
		}
		reader.close();
	}

	// creating the emotions map
	public void emotionsMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/emotions.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + line.charAt(i);
			}
			words.put(s, Integer.parseInt(line.substring(i + 1, line.length())));
		}
		reader.close();
	}

	// creating the abbreviations map
	public void abbreviationsMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/slangs.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			line = line.toLowerCase();
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + line.charAt(i);
			}
			abbreviations.put(s, (line.substring(i + 1, line.length())));
		}
		reader.close();
	}

	public void negationList() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/negations.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			negations.add(line);
		}
//		System.out.println();
		reader.close();
	}
	
//	creation of the countries hashmap
	public void countriesMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/countries.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + Character.toLowerCase(line.charAt(i));
			}
			countries.put(s, line.substring(i + 1, line.length()));
		}
		reader.close();
	}
	
//	creation of the cities hashmap
	public void citiesMap() throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(new File("C:/Twitterfiles/cities.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String s = "";
			for (i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '\t') {
					break;
				}
				s = s + line.charAt(i);
			}
			cities.put(s.toLowerCase(), line.substring(i + 1, line.length()));
		}
		reader.close();
	}
	
}
