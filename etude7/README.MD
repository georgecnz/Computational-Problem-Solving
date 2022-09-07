## Etude 7-- Where in the World is CS?
COSC326- Otago University
@uthor George Churton.

This program is built upon four key files: (one of which is created)
These files include:
converter.java 
input.txt
feature_collection.geojson
whereintheworld.html

The converter.java is the largest of the collection of files.
The file logically focuses on cleansing and validating user input into the appropriate form that geojson points read.
User input is read from input.txt-- thus the addtion of coordinates can be added, removed and modifed here.
converter.java computationally converts units of geographical input into a standardised form.
Once the data has been validated and cleaned the output is written into a geojson file named: feature_collection.geojson 
This file can then be read by the javascript html file that is whereintheworld.html
whereintheworld.html uses leaflet, an interactive map for javascript. 
For simplicity, validated input can be viewed interactively on the map through the automated plotting of basic points.

# Note
The geojson file includes an empty feature at the end of the file-- however this does not affect the useability 
of the program and hence should be ignored. 

# Running the program
1. Enter georgaphical input into input.txt
2. Compile and run converter.java (javac converter.java) (java converter.java)
3. Run the whereintheworld.html file on local server and the data will be plotted on a leaflet map
   be sure to use a modern web broswer in doing this.

# Testing

Input:

-45.85647 170.5144 OtagoUni
°40 N 26.767′ N 79° 58.933′ W
-41.292,174.780 NewZealand
28.5206° S, 134.8860° E Australia
98.00° W, 37.000° N USA
57.36600,-102.304 Canada
54.99, -3.736 UnitedKingdom
38° 16.00′ N 140° 51.00′ W Japan
2000000.00, 500.000
location
45°52'01.6"S 170°31'04.1"E Owheo1
45 d 52 m 1.6s S, 170 d 31 m 4.1s E Owheo2
1.234567 -23.987654


Terminal Output:
_______________________________________________________________________________________
-------------------------------------Converter App-------------------------------------

Reading and converting input from into a GeoJSON file...

Unable to process: °40 N 26.767′ N 79° 58.933′ W

Unable to process: 2000000.00, 500.000

Unable to process: location


Number of valid geographical inputs = 10

Input that was able to be proccessed has been converted into a new geojson file.
_______________________________________________________________________________________

Leaflet Output: (on html localserver)

<img width="1382" alt="Revised Leaflet Output" src="https://user-images.githubusercontent.com/101151445/170394902-9c9ef820-0f35-4f2e-aee4-dfb63cc591e9.png">



# Resources Utilised
https://www.youtube.com/watch?v=C3dfjyft_m4&ab_channel=JonathanSoma --> leaflet javascrupt usage guidance
https://www.baeldung.com/java-round-decimal-number --> rounding numbers guidance
https://www.youtube.com/watch?v=ls_Eue1xUtY&t=2018s&ab_channel=GeoDev --> leaflet javascript usage guidance
https://leafletjs.com/examples/geojson/ --> leaflet usage and geojson featurecollection format guidance

