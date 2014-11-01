package Twitter;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AnalyzeTweet {
String Tweet;
double score = 0.0;
double intense = 1.0; // will help in calculating the intensifier
double neg = 1.0;
double intensifier[] = new double[10]; // an array that stores the intensifiers

	public double AnalyzeTweets(String tweet) {
		score = 0;
		this.Tweet = tweet;
		Tweet = Preprocess.replaceAbbv(Tweet);
		Tweet = Preprocess.removeURL(Tweet);
		Tweet = Preprocess.removeHash(Tweet);
		Tweet = Preprocess.removeDuplicates(Tweet);
		calculateEmoticons();
		Tweet = Preprocess.removeChars(Tweet);		
		calculateScore();
		return score;
	}

	public double calculateEmoticons() {
		int pos=0;
		int intens=0;
		String[] newtweet = new String[80];    // an array of strings where I will store the tweet
	//	after removing the negations or intensifiers that influence the emoticons
		int posit[] = new int[80];   // an array that will store at the position of the intensifier/negation
	//	1 for intensifiers and 2 for negations; assuming there cannot be more than 80 words in a tweet
		
		try {
			MapCreation maps = new MapCreation();		// create the maps that store the scores
			StringTokenizer word = new StringTokenizer(Tweet);			
			Arrays.fill(posit, 0);   // fills all the array with 0			
			
			while(word.hasMoreElements()) {
				String nextword = (String) word.nextElement();
				newtweet[pos] = nextword;
				pos+=1;

	//			System.out.println(nextword);   // for verification purposes
				
				if(maps.emoticons.get(nextword) != null){		
					for (int a = 0; a < intens; a++) {
						score = score + (maps.emoticons.get(nextword) * intensifier[a]);
						intensifier [a] = 0.0;
					}
					
					score = score + (neg * intense * maps.emoticons.get(nextword));
					for (int i=0; i < pos; i++) {
						if (posit[i] != 0) {
							newtweet[i]= ":";
							posit[i]=0;
						}
					}					
					neg = 1;
					intense = 1;
				}
			
			else if(maps.intensifiers.get(nextword) != null) {
				intensifier [intens++] = maps.intensifiers.get(nextword);   // storing each intensifier in the array
//				intense = intense * maps.intensifiers.get(nextword);
				posit[pos-1] = 1;
			}
			
			else if(maps.words.get(nextword) != null){ // if we find a word that retrieves an emotion, 
	//			we don't calculate it for the score, but we set the negation and intensifiers to 1 again,
	//			because they will influence that word, not the emoticon. If an emotion is found, all the 
	//			variables are set to 0/1 because the intensifiers or negations will influence the emotion,
	//			not the emoticon
					
					neg = 1;
					intense = 1;
	//				System.out.println(pos + Tweet);
					for (int i=0; i < pos; i++) {
	//					System.out.println("position: " + posit[i]);
						posit [i] = 0;
	//					System.out.println("position: " + posit[i]);
					}
			}
				
			else if(maps.negations.contains(nextword)) {
				neg = (-1.0) * neg;
				posit[pos-1] = 2;
		}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		Tweet = "";
		for (int i=0; i < pos; i++) {      // position will be nr of elements + 1 because increases after adding each word
			Tweet = Tweet + newtweet[i] + " ";
			
		}
		Tweet=Tweet.substring(0,Tweet.length()-1); // removing the last space
		return score;
	}
	
	public double calculateScore(){
		int intens = 0;
		try {
			MapCreation maps = new MapCreation();		// create the maps that store the scores
			StringTokenizer word = new StringTokenizer(Tweet);
			
			while(word.hasMoreElements()) {
			String nextword = (String) word.nextElement();
			
			if(maps.intensifiers.get(nextword) != null){
				intensifier [intens++] = maps.intensifiers.get(nextword); 
			}
			
			else if(maps.words.get(nextword) != null){

				for (int a = 0; a < intens; a++) {
					score = score + (maps.words.get(nextword) * intensifier[a]);
					intensifier [a] = 0.0;
				}
					score = score + (neg * maps.words.get(nextword));
					neg = 1;
					intense = 1;
					
	//			}
			}
			else if(maps.negations.contains(nextword)) {
				neg = (-1.0) * neg;
			}
		}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return score;
	}
}