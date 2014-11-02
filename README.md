Twitter-Sentiment-Analysis
==========================

This repository contains a Twitter Sentiment Analysis tool based on a Lexicon classifier developed for a software engineering project. It uses the Twitter4J library for retrieving the tweets based on a keyword and a location, the Lexicon classifier to analyze and classify the tweets, a MySQL database to store the results of a search, and a UI developed in JSP to interact with the user. 
The src folder contains the Twitter package with the classes for the search, tweet analysis and database connection. The TwitterAnalysis class requires the Twitter credentials necessary for the use of the API that retrieves the tweets. The DbConnection class connects with the database, inserts new search results into the database, and retrieves previous results. I have created an algorithm for sentiment analysis that takes into consideration the emoticons, the words that express emotions, the intensifiers, and the negations found in a tweet. These are stored in separate files, based on which I have created maps in the MapCreation class. The “cities” and “countries” files contain countries, capital cities and other major cities from English speaking countries and their latitude and longitude coordinates for geolocation purposes. The Preprocess class prepared the tweet for analysis. The AnalyzeTweet class gives a score based on the emoticons, and emotions expressed in a tweet and classifies it into a positive, neutral, or negative one.   
To start the Sentiment Analysis tool compile the index.jsp file located in the WebContent folder and access the local server. The tool uses Apache Tomcat for running the JSP program.
