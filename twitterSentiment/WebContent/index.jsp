<%@page import="Twitter.*"%>
<%@page import="twitter4j.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.List"%>

<%@page import="javax.script.*"%>

<%@page import="java.awt.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.chart.ChartPanel"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.labels.PieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>

<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="org.jfree.data.general.PieDataset"%>
<%@page import="org.jfree.ui.ApplicationFrame"%>
<%@page import="org.jfree.ui.RefineryUtilities"%>

<%@page contentType="text/html; charset=iso-8859-1" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="twitter sentiment, analysis, twitter analysis" />
<meta name="description" content="Twitter sentiments" />

<title>Twitter Sentiments Analysis</title>
<style type="text/css">
 
 body {
	background-color: #CCC;
	height: 100%;
}
#wrapper {
	width: 1200px;
	margin: 0 auto;
	height: auto;
	
}
#header {
	height: 100px;
	width: 1200px;
	margin-right: auto;
	margin-left: auto;
	border-top-width: 2px;	
	border-right-width: 0px;
	border-bottom-width: 5px;
	padding-top:30px;
	border-left-width: 0px;
	border-style: solid;
	background-color: #143d55;
	font-family: hotpizza;
	src: hotpizza.ttf;
	font-size: 26px;
	font-weight: bold;
	color: #143d55;
	background-color: #2E9AFE;
	border-color: #CCC;
	border-bottom-color: #143d55;
}
#content {
	float: left;
	margin: 0 auto;
	width: 1160px;
	background-color: #CCFFCC;
	padding-top: 10px;
	padding-right: 10px;
	padding-bottom: 50px;
	padding-left: 30px;
	min-height: 75%;
}

#chart_center {
margin-left: auto;
margin-right: auto;
align: center;
}

input
{
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
}

#footer {
	float: left;
	height: auto;
	width: 1200px;
	margin-right: auto;
	margin-left: auto;
	border-top-width: 5px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: none;
	border-left-style: solid;
	background-color: #143d55;
	font-family: Arial, Helvetica, sans-serif;
	color: #e0e0e0;
	font-size: 12px;
	text-align: center;
	border-top-color: #2E9AFE;
	position: fixed; 
	bottom: 0;
}
p {
font-weight: bold;
}
.postweet {
padding: .5em;
	border: .5em;
	border-left-style: solid;
	border-color: #52E052;
	background: #E9FBE9;
	color: #003300;
}

.neutweet {
padding: .5em;
	border: .5em;
	border-left-style: solid;
	border-color: #13a8f0;
	background: #E9FBE9;
	color: #000099;
}

.negtweet {
padding: .5em;
	border: .5em;
	border-left-style: solid;
	border-color: #f01313;
	background: #E9FBE9;
	color: #990000;
}

h1 {
	padding-bottom: 4px;
	border-bottom-color: #CCC;
    font-weight: bold; 
	font-size: 30px;
	text-align: center;
	padding-bottom: 4px;
}
h3 {
	padding-bottom: 2px;
    font-weight: bold; 
	font-size: 16px;
	padding-bottom: 2px;
}
a:link {
	text-decoration:none;
	color:#000;
}
a:visited {
	text-decoration:none;
}
a:hover {
	text-decoration:none;
}
a:active {
	text-decoration:none;
}

.center 
{
text-align: center;
font-weight: bold; 
font-size: 16px;
font-family: Verdana, Geneva, sans-serif;
color: #143d55;
}

.link
 {
  color:#000;
  text-decoration:underline;
 }
#search-form {
	background: #e1e1e1;
	width: 365px;
	height: 112px;
	background: -webkit	-gradient( linear,left top, left bottom, color-stop(0, rgb(243,243,243)), color-stop(1, rgb(225,225,225)));
	background: -moz-linear-gradient( center top, rgb(243,243,243) 0%, rgb(225,225,225) 100%);

	border-radius: 17px; 
	-webkit-border-radius: 17px;
	-moz-border-radius: 17px;

	box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3); 
	-webkit-box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3);
	-moz-box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3);
}
input[type="text"]{
	background: #fafafa;

	background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(250,250,250)), color-stop(1, rgb(230,230,230)));
	background: -moz-linear-gradient( center top, rgb(250,250,250) 0%, rgb(230,230,230) 100%);
	
	border: 0;
	border-bottom: 1px solid #fff;
	border-right: 1px solid rgba(255,255,255,.8);
	font-size: 16px;
	margin: 4px;
	padding: 5px;
	width: 250px;

	border-radius: 17px; 
	-webkit-border-radius: 17px;
	-moz-border-radius: 17px;

	box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
	-webkit-box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
	-moz-box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
}

input[type="text"]:focus{
	outline: none;
	background: #fff; 

	background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(255,255,255)), color-stop(1, rgb(235,235,235)));
	background: -moz-linear-gradient( center top, rgb(255,255,255) 0%, rgb(235,235,235) 100%);
}
input[type="submit"]{
	background: #143d55;	
	border: 0;
	color: #eee;
	cursor: pointer;
	float: center;
	font: 16px Arial, Helvetica, sans-serif;
	font-weight: bold;
	height: 30px;
	margin: 4px 4px 0;
	text-shadow: 0 -1px 0 rgba(0,0,0,.3);
	width: 84px;
	outline: none;
	text-align: center;
	border-radius: 30px; 
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;

	box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.4);
	-moz-box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.2);
	-webkit-box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.4);
}
input[type="submit"]:hover {
	background: #143d55; 
	background: -webkit-gradient( linear, left top, left bottom, color-stop(0, rgb(27,187,222)), color-stop(0.15, rgb(38,155,181)), color-stop(0.8, rgb(22,122,141)), color-stop(1, rgb(22,103,121)));
	background: -moz-linear-gradient( center top, rgb(27,187,222) 0%, rgb(38,155,181) 15%, rgb(22,122,141) 80%, rgb(22,103,121) 100%);
}
input[type="submit"]:active {
	background: #143d55; 
	background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(27,187,222)), color-stop(0.15, rgb(38,155,181)), color-stop(0.8, rgb(27,122,141)), color-stop(1, rgb(22,104,121)));
	background: -moz-linear-gradient( center bottom, rgb(27,187,222) 0%, rgb(38,155,181) 15%, rgb(27,122,141) 80%, rgb(22,104,121) 100%);
}
.centered {
  margin: auto;
}

.positive {
	color: 006600;
}

.negative {
	color: CC0000;
}
.neutral {
	color: 0066FF;
}
.buttonHolder { 
text-align: center; 
}

 </style>
</head>
<body>
<div id="wrapper">
	<div id="header"><h1>Twitter Sentiment Analysis</h1> 
</div>
<div id="content">
		<form id="search-form" class="centered" method="get" action="">
		<div class="buttonholder">
			Insert a place*    <input type="text" class="tftextinput" name="place" size="60"
				maxlength="100"> <br>
			Insert keyword	<input type="text" class="tftextinput" name="key" size="60"
				maxlength="100">
				<input type="submit" value="search">  
		</div>
		</form><br><p>*If a place is not specified or not found on our lists, the default one is set for Ireland <br>
		</p>
		<%  
			if (request.getParameter("key") != null) { 
				String location = "ireland";
				String k = request.getParameter("key").toString();
				if (request.getParameter("place") != null) {
					location = request.getParameter("place").toString().toLowerCase();
					MapCreation maps = new MapCreation();
	    			if ((maps.countries.get(location) == null) && (maps.cities.get(location) == null)) 
	    					location = "ireland";
	    				} 
				String keyword = request.getParameter("key").toString().toLowerCase();
				String searchfor = keyword + " -RT";  // to remove retweets
				TwitterAnalysis twe = new TwitterAnalysis();
				AnalyzeTweet sent = new AnalyzeTweet();
				
				List<Status> tweets = twe.TweetsRetrival(searchfor, location);
								
				try {	
					int neu = 0, pos = 0, neg = 0;
					if (tweets.size() > 0) {
						int set = 20, total=0;
						for(Status tweet : tweets)  {
							double score = sent.AnalyzeTweets(tweet.getText());
							
							if (score >= 1) { 
								pos++;
								if (set > 0) {
									%>
									<p class="postweet">
									<%
									out.print("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
									set--;
								}								
							}							
							else if (score <= -1) {
								neg++;
								if (set > 0) {
									%>
									</p>
									<p class="negtweet">
									<%
									out.print("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
									set--;
								}								
							}	
							else if ((score < 1) && (score > -1)) { 
								neu++;
								if (set>0) {
									%>
									</p>
									<p class="neutweet">
									
									<%
									out.print("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
									set--; 
								}								
							}  
						}
						%>
						
						 <!--Load the AJAX API-->
   <script type="text/javascript" src="https://www.google.com/jsapi"></script>
   <script type="text/javascript">

     // Load the Visualization API and the piechart package.
     google.load('visualization', '1.0', {'packages':['corechart']});

     // Set a callback to run when the Google Visualization API is loaded.
     google.setOnLoadCallback(drawChart);

     // Callback that creates and populates a data table,
     // instantiates the pie chart, passes in the data and
     // draws it.
     function drawChart() {

       // Create the data table.
       var data = new google.visualization.DataTable();
       
       data.addColumn('string', 'Sentiment');
       data.addColumn('number', 'Tweets');
       data.addRows([
         ['Positive', <%=pos%>],
         ['Neutral', <%=neu%>],
         ['Negative', <%=neg%>],
       ]);

       // Set chart options
       var options = {'title':'Sentiments of tweets for ' + '<%=k%>',
                      'width':400,
                      'height':300,
                      colors: ['#259C37', '#42E3E0', '#D90B15'],};

       // Instantiate and draw our chart, passing in some options.
       var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
       chart.draw(data, options);
     }
   </script>
 		<div id="chart_center">
		<div id="chart_div"></div>
			</div>
					
				<% 
						total = pos + neg + neu; 
				//	PieCharts res = new PieCharts("The search results for "+ keyword, "Sentiment Analysis", pos ,neu ,neg );
				//	res.pack();
				//	res.setVisible(true);	    
				  %>
				  <p>
					<%
						out.print("Your search based on " + keyword + " has returned " + total + " results, out of which, " + pos + 
						" represent positive tweets, " +  neg + " represent negative tweets, and " + neu + " represent neutral tweets."+"<br><br>");						
						DbConnection conn = new DbConnection();
						conn.insertkey(location, keyword, pos, neu, neg);
						
						String previous[] = conn.getPrevious(location, keyword);
						int j, x;
						
						// I want to display in the chart only the results of the last 7 searches for the given specifications; to do that,
						// I need to know how many elements does the previous string contain and start the loop from the (nr of elements - 5) elem
						
						for (j=0; j<1000; j++)   
							if (previous[j] == null)
								break;
						if (j <= 28)
							x = 0;
						else 
							x = j - 28;
						
						if (previous[6] != null) {    // checking if there is at least a previous search with same specifications, so I can 
							// create the bar chart
						
						%>
						</p>
						<script type="text/javascript" src="https://www.google.com/jsapi"> </script>
					    <script type="text/javascript">
					      google.load("visualization", "1", {packages:["corechart"]});
					google.setOnLoadCallback(drawChart);
					function drawChart() {
						var dataArray = [['Time of search', 'Positive', 'Neutral' , 'Negative']];
						<%
						for (int i=x; i<1000; i=i+4) {
							if (previous[i]!= null) {
								if ((i%4 == 0) || (i==0)) 
									
								%>
									dataArray.push (['<%=previous[i]%>', parseInt(<%=previous[i+1]%>), parseInt(<%=previous[i+2]%>), parseInt(<%=previous[i+3]%>)]);
								<%
							}
						}
						%>
							
						var data = new google.visualization.arrayToDataTable(dataArray);
						
					  var options = {
					    title: 'Previous sentiments results for ' + '<%=k%>' + '(only a maximum of 7 most recent results are displayed)',
					    colors: ['#259C37', '#42E3E0', '#D90B15'],
					    hAxis: {title: 'Time of search', titleTextStyle: {color: 'black'}}
					  };

					  var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));

					  chart.draw(data, options);

					}
					    </script>

					    <div id="chart_div2" style="width: 900px; height: 500px;"></div>
											
											
			<%			
						} else
							out.print("There are no results of previous searches for " + k + ".");
					}
					else
					{	
						out.print("<br><p>" + "Your search has not retrieved any results.</p>");
					}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}			
		 %>		
		   </div> <!--Closing the content div -->
  <div id="footer">Copyright &copy; Andrei Almasanu 2014 <br>
  This website has been created as part of the Software Engineer Project by Andrei Almasanu, University College Dublin
  </div>
</div> <!--Closing the wrapper div -->
</body>
</html>
