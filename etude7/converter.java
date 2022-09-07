import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.math.MathContext;

/* 
* Etude 7- Where in the world is CS.
* COSC326- Semester 1.
* @author George Churton.
* This program takes various cordinates and formats them into a standardised form for the creation of a geojson file.
* Such geojson file can then be viewed interactively.
*/

public class converter {

//declarations and initalisations
   public static String co_int;
   public static String latitude = "";
   public static String longitude = "";
   public static String label = "";
   public static String coordinate = "";
   public static String featureCollection = "";
   public static int line = 0;
   public static int iteration = 0;
   public static int lastIteration = iteration -1;
   public static boolean nothing = false;
   public static boolean errorFlag = false;
   public static boolean terminalFlag = false;


   /*
   * main method where program is executed 
   * here functions are called appropriately. Regex aids the identification of valid inputs in this method.
   * after valid inputs have been identified they can then be modified for the creation of a geojson file. 
   * @param args
   */
   
   public static void main(String[] args) throws FileNotFoundException {
      System.out.println("_______________________________________________________________________________________");
      System.out.println("-------------------------------------Converter App-------------------------------------");
    
      System.out.println("\nReading and converting input from into a GeoJSON file...");
      System.out.println("");

   
      PrintStream o = new PrintStream(new File("feature_collection.geojson"));  
      PrintStream console = System.out;   

       try{    
        BufferedReader reader = new BufferedReader(new FileReader("input.txt")); 
        co_int = reader.readLine();

            while(co_int != null){
                coordinate = "";
                label = "";
                terminalFlag = false;
                System.setOut(o);

            if(co_int.matches("[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[.]{1}[0-9]{1,3}[′]{1}[ ]{0,1}[A-Z]{1}[ ]{0,1}[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[.]{1}[0-9]{1,3}[′]{1}[ ]{0,1}[A-Z][ ]{0,1}[a-zA-Z0-9_.-]{2,20}")){
                    degreeLabel(co_int);
                    iteration++;

            }else if(co_int.matches("[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[']{1}[0-9]{1,2}[.]{1}[0-9]{0,2}[\"]{1}[A-Za-z]{1}[ ]{1}[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[']{1}[0-9]{1,2}[.]{1}[0-9]{0,2}[\"]{1}[A-Za-z]{1}[ ]{1}[a-zA-Z0-9_.-]{2,20}")){ 
                    degreeLabel(co_int);
                    iteration++;

            }else if(co_int.matches("[0-9]{1,3}[ ]{1}[a-z]{1}[ ]{1}[0-9]{1,2}[ ]{1}[a-z]{1}[ ]{1}[0-9]{1,2}[.]{1}[0-9]{1,2}[a-z]{1}[ ]{1}[A-Z]{1}[,]{1}[ ]{1}[0-9]{1,3}[ ]{1}[a-z]{1}[ ]{1}[0-9]{1,2}[ ]{1}[a-z]{1}[ ]{1}[0-9]{1,3}[.]{1}[0-9]{1,3}[a-z]{1}[ ]{1}[A-Z]{1}[ ][a-zA-Z0-9_.-]{2,20}")){
                degreeMinSecond(co_int);
                iteration++;
        

            }else if(co_int.matches("[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[.]{1}[0-9]{3}[′]{1}[ ]{0,1}[A-Z]{1}[ ]{0,1}[0-9]{1,3}[°]{1}[ ]{0,1}[0-9]{1,3}[.]{1}[0-9]{3}[′]{1}[ ]{0,1}[a-zA-Z0-9_.-]{2,20}")){
                degreedecimal(co_int);
                iteration++;
            
            }else if((co_int.matches("[-]{0,1}[0-9]{1,3}[.]{1}[0-9]{0,6}[,]{1}[ ]{0,1}[-]{0,1}[0-9]{1,3}[.]{1}[0-9]{0,6}[ ]{0,1}[a-zA-Z0-9_.-]{2,20}") || 
            (co_int.matches("[0-9]{1,3}[.]{1}[0-9]{0,6}[°]{1}[ ]{0,1}[A-Z]{1}[,]{0,1}[ ]{0,1}[0-9]{1,3}[.]{1}[0-9]{0,6}[°]{1}[ ]{0,1}[A-Z]{1}[ ]{0,1}[a-zA-Z0-9_.-]{2,20}")) ||
            (co_int.matches("[-]{0,1}[0-9]{1,3}[.]{1}[0-9]{0,6}[,]{0,1}[ ]{0,1}[-]{0,1}[0-9]{1,3}[.]{1}[0-9]{0,6}[ ]{0,1}[a-zA-Z0-9_.-]{2,20}")))){
             processor(co_int);
             iteration++;

            }else{
                System.setOut(console);
                System.out.println("Unable to process: " + co_int + "\n"); 
            }

            co_int = reader.readLine();

            if(errorFlag == true){
                System.out.println("ERROR: unable to find file");
                errorFlag = false;
                }
                
            }
           
            System.setOut(console);
            System.out.println(coordinate);
        
            System.out.println("Number of valid geographical inputs = " +iteration);
            lastIteration++;
            System.out.println("\nInput that was able to be proccessed has been converted into a new geojson file.");
            System.out.println("_______________________________________________________________________________________");

            System.setOut(o);
            label = "";
            String feature = "{" + "\"type\":" + "\"Feature\"," + "\n" + "\"properties\":" + "{" + "\n" + "\"label\":" + "\"" + label + "\"" + "\n" + "},";
            String geometry = "\"geometry\":" + "{" + "\n" + "\"type\":" + "\"Point\"," + "\n" + "\"coordinates\":" + "[" + "\n" + coordinate + "]" + "}" + "}" + "";
            System.out.println(feature + geometry);
            System.out.println("]"  + "\n" + "}");
            reader.close();

        
            }catch(IOException e){
                errorFlag = true;
                }
            }


/* the static method processor is called upon if user input is decleared valid from the main method.
*  here user input is broken a format where latitude and longitude can be identified.
* @param co_int -- a string representing user input.
*/

public static void processor(String co_int){
    if(co_int.matches(".*[a-z].*") && (co_int.contains(",") && (!co_int.contains("°")))){ 
        label = co_int.substring(co_int.lastIndexOf(" ")+1);
        String noLabel = co_int.replace(label, "");
        String noLabelComp = noLabel.replace(" ", "");
        operationSplit(noLabelComp);
  
    }else if(co_int.matches(".*[a-z].*") && (!co_int.contains(",") && (!co_int.contains("°")))){
        label = co_int.substring(co_int.lastIndexOf(" ")+1);
        String noLabel = co_int.replace(label, "");
        operationSplit(noLabel);
    }
    
    else if(co_int.contains("°") || co_int.contains("\u00B0")){
          Pattern pattern = Pattern.compile("\\b\\w{2,20}\\b");
          Matcher matcher = pattern.matcher(co_int);
  
          while(matcher.find()) {
             label = matcher.group(0);
          }
  
         co_int = co_int.replace(label, "");
         String noLabelComp = co_int.replace(" ", "");
         operationSplit(noLabelComp);
  
      }else{
        operationSplit(co_int);
    }
  }


/* the static method operationSplit takes formatted user input and splits it into longitude and latitude.
* the method intuatively identifes latitude and longitude points based on the N, S, E, W orientation.
* @param co_int -- a string representing user input considered to be coordinates.
*/


   public static void operationSplit(String co_int){
    int j = 0;
    int i = 0;

    if(co_int.contains(",")){

        String splitCord[] = co_int.split(",", 0);
        latitude = splitCord[0];
        longitude = splitCord[1];
    
        if(latitude.contains("W") || latitude.contains("E")){
            latitude = splitCord[1];
            longitude = splitCord[0];

        }else if(longitude.contains("N") || longitude.contains("S")){
                latitude = splitCord[1];
                longitude = splitCord[0];

        }else{
            latitude = splitCord[0];
            longitude = splitCord[1];
        }
    }

     if(co_int.contains(" ")){
        String splitCord[] = co_int.split(" ", 0);
        latitude = splitCord[0];
        longitude = splitCord[1];
       }

    if(latitude.contains("°") || longitude.contains("°")){
           latitude = latitude.replace("°", "");
           longitude = longitude.replace("°", "");
       }

     if(longitude.contains("E")){
        longitude = longitude.replace("E", "");

     }
      if(latitude.contains("S")){
        StringBuilder sb = new StringBuilder(latitude);   
        sb.insert(0, "-");
        latitude = sb.toString();
        latitude = latitude.replace("S", "");
        latitude = latitude.replace(" ", "");
      }
    
     if(latitude.contains("N")){
        latitude = latitude.replace("N", "");
     }

     if(co_int.contains("W")){
        StringBuilder sb = new StringBuilder(longitude);   
        sb.insert(0, "-");
        longitude = sb.toString();
        longitude = longitude.replace("W", "");
        longitude = longitude.replace(" ", "");

    }
    //if points are not to 6 sigfig.
    if(!latitude.matches("[0-9]{1,3}[.]{1}[0-9]{6}") || (!longitude.matches("[0-9]{1,3}[.]{1}[0-9]{6}"))){
      latitude = latitude.replace(",","");
      longitude = longitude.replace(",", "");
      int latfigures = latitude.length() - latitude.indexOf(".")-1;
      int latpadding = 6 - latfigures;
      int lonfigures = longitude.length() - longitude.indexOf(".")-1;
      int lonpadding = 6 - lonfigures;
      
      while(i < latpadding){
         latitude += "0";
         i++;
      }

      while(j < lonpadding){
         longitude += "0";
         j++;
      }
    }
    pointCreator(latitude, longitude);
}

/* the method degreeMinSecond converters such input that uses d, m and s instead of °, ' and " respectively.
* this method intuitively enables code to be re-used. For instance once converted the degreedecimal method can be involked.
* @param co_int -- string representing input.
*/

public static void degreeMinSecond(String co_int){
    String minSplit[] = new String[50];
    Pattern pattern = Pattern.compile("[a-zA-Z0-9_.-]{2,20}");
    Matcher matcher = pattern.matcher(co_int);
    while(matcher.find()){
        int i = 0;
        minSplit[i] = matcher.group(0);
        i++;
    }
        label = minSplit[0];
       co_int = co_int.replace(label, "");

    String min = "'";
    String modifer = "";
    modifer = co_int.replace("d", "°");
    modifer = modifer.replace("m", min);
    modifer = modifer.replace("s", "\"");
    modifer = modifer.replace(" ", "");
    modifer = modifer.replace(",", " ");
    
   degreedecimal(modifer);

}

/* the method degreeLabel logically collects any labels included in user input that is in degree form
* again, regex aids the identification of such labels.
* @param co_int 
*/

public static void degreeLabel(String co_int){
    String minSplit[] = new String[50];
    Pattern pattern = Pattern.compile("[a-zA-Z0-9_.-]{1,20}");
    Matcher matcher = pattern.matcher(co_int);
    while(matcher.find()){
        int i = 0;
        minSplit[i] = matcher.group(0);
        i++;
    }
    label = minSplit[0];
        co_int = co_int.replace(label, "");
        degreedecimal(co_int);
}


/* the method degreedecimal is simular to the proccessor and operation split methods however
* it is specific to user input is in a degree format.
* @param co_int
*/

public static void degreedecimal(String co_int){
//declarations and initalisations
String latdegree = "";
String longdegree = "";
String latmin = "";
String longmin = "";
String latSec = "";
String longSec = "";
int middle = co_int.length()/2;
String secondseg = co_int.substring(middle);
String firstseg = co_int.substring(0, middle);

// north or south (N or S) indicates latitude 
if(secondseg.contains("N") || secondseg.contains("S")){
String subSecond = secondseg.substring(4);
String minSplit[] = subSecond.split("′", 0);
latmin = minSplit[0];

String degreeSplit[] = secondseg.split("°",0);
latdegree = degreeSplit[0];

if(co_int.contains("\"")){
    String sec = secondseg.substring(6);
    String secSplit3[] = sec.split("\"",0);
    latSec = secSplit3[0];
    String subFirst1 = secondseg.substring(3);
    String minSplit4[] = subFirst1.split("'", 0);
    
    latmin = minSplit4[0];
    latmin = latmin.replace(" ", "");

    //south is considered negative geographically.
    if(secondseg.contains("S")){
        StringBuilder sb = new StringBuilder(latdegree);   
        sb.insert(0, "-");
        latdegree = sb.toString();
    }


}

// west or east (W or E) indicates longitude 
}else if(secondseg.contains("W") || secondseg.contains("E")){
    String subSecond = secondseg.substring(8);
    String minSplit1[] = subSecond.split("′", 0);
    longmin = minSplit1[0];
    longmin = longmin.replace(" ", "");

    String degreeSplit1[] = secondseg.split("°",0);
    longdegree = degreeSplit1[0];
    longdegree = longdegree.replace(" ", "");

    if(co_int.contains("\"")){
        String sec = secondseg.substring(7);
        String secSplit3[] = sec.split("\"",0);
        longSec = secSplit3[0];

        String subFirst1 = secondseg.substring(4);
        String minSplit4[] = subFirst1.split("'", 0);
        
        longmin = minSplit4[0];
        longmin = longmin.replace(" ", "");

        //west is considered negative geographically.
        if(secondseg.contains("W")){
            StringBuilder sb = new StringBuilder(longdegree);   
            sb.insert(0, "-");
            longdegree = sb.toString();
        }
    }
}


// north or south (N or S) indicates latitude 
if(firstseg.contains("N") || firstseg.contains("S")){
    String degreeSplit2[] = firstseg.split("°",0);
    latdegree = degreeSplit2[0];

    String subFirst = firstseg.substring(4);
    String minSplit2[] = subFirst.split("′", 0);
    latmin = minSplit2[0];

    if(co_int.contains("\"")){
        String sec = firstseg.substring(6);
        String secSplit3[] = sec.split("\"",0);
        latSec = secSplit3[0];
        String subFirst1 = firstseg.substring(3);

        String minSplit4[] = subFirst1.split("'", 0);
        
        latmin = minSplit4[0];
        latmin = latmin.replace(" ", ""); 
        
        //south is considered negative geographically.
        if(firstseg.contains("S")){
            StringBuilder sb = new StringBuilder(latdegree);   
            sb.insert(0, "-");
            latdegree = sb.toString();
        }
    }

    // west or east (W or E) indicates longitude 
    }else if(firstseg.contains("W") || firstseg.contains("E")){
        String degreeSplit3[] = firstseg.split("°",0);
        longdegree = degreeSplit3[0];
        longdegree = longdegree.replace(" ", "");

        String subFirst = firstseg.substring(4);
        String minSplit3[] = subFirst.split("′", 0);
        
        longmin = minSplit3[0];
        longmin = longmin.replace(" ", "");

        if(co_int.contains("\"")){
        String sec = firstseg.substring(7);
        String secSplit3[] = sec.split("\"",0);
        longSec = secSplit3[0];

        String subFirst1 = firstseg.substring(3);
        String minSplit4[] = subFirst1.split("'", 0);
        
        longmin = minSplit4[0];
        longmin = longmin.replace(" ", "");

        //west is considered negative geographically.
        if(firstseg.contains("W")){
            StringBuilder sb = new StringBuilder(longdegree);   
            sb.insert(0, "-");
            longdegree = sb.toString();
             }

        }
     
    }

    if(co_int.contains("\"")){
        minSecParser(latdegree, longdegree, latmin, longmin, latSec, longSec);

    }else{
        degreeParser(latdegree, longdegree, latmin, longmin);  
  }
}


/* the method degreeParser ensures input that is in degree form is padded in terms of significant figures.
* it also calculates the cordinates for input in minutes form.
* @param latdegree, longdegree, latmin, longmin --- strings representing the degree and minutes for lat and long.
*/

public static void degreeParser(String latdegree, String longdegree, String latmin, String longmin){
        //conversions
        Integer lat_degree = Integer.parseInt(latdegree);
        Integer long_degree = Integer.parseInt(longdegree);
        double lat_min = Double.parseDouble(latmin);
        double long_min = Double.parseDouble(longmin);
        double lat_decimal_degree = 0.00;
        double long_decimal_degree = 0.00;

        //calculations
        lat_decimal_degree = lat_degree + (lat_min/60);
        long_decimal_degree = long_degree + (long_min/60);

        BigDecimal bdLat = new BigDecimal(lat_decimal_degree);
        BigDecimal bdLon = new BigDecimal(long_decimal_degree);
        bdLat = bdLat.round(new MathContext(8));
        bdLon = bdLon.round(new MathContext(8));
        lat_decimal_degree = bdLat.doubleValue();
        long_decimal_degree = bdLon.doubleValue();

        BigDecimal bd = new BigDecimal(Double.toString(long_decimal_degree));
        bd = bd.setScale(6);
        String lon = bd.toString();
        
        BigDecimal bd1 = new BigDecimal(Double.toString(lat_decimal_degree));
        bd1 = bd1.setScale(6);
        String lat = bd1.toString();
        
        latitude = lat;
        longitude = lon;

        pointCreator(latitude, longitude);
}

/* the method minSecParser ensures input that includes minutes and seconds is padded in terms of significant figures.
* it also calculates the degree input cordinate.
* @param latdegree, longdegree, latmin, longmin, latSec, longSec --- strings representing the degree and minutes and seconds for lat and long.
*/

public static void minSecParser(String latdegree, String longdegree, String latmin, String longmin, String latSec, String longSec){
    Boolean negativeLat = false;
    Boolean negativeLon = false;
   
    //identifiying negative coordinates-- removing sign for computation.
    if(latdegree.contains("-")){
        negativeLat = true;
        latdegree = latdegree.replace("-", "");
    }

    if(longdegree.contains("-")){
        negativeLon = true;
        longdegree = longdegree.replace("-", "");
    }

    //conversions
    Integer lat_degree = Integer.parseInt(latdegree);
    Integer long_degree = Integer.parseInt(longdegree);
    double lat_min = Double.parseDouble(latmin);
    double long_min = Double.parseDouble(longmin);
    double lat_decimal_degree = 0.00;
    double long_decimal_degree = 0.00;
    double lat_Sec = Double.parseDouble(latSec);
    double lon_Sec = Double.parseDouble(longSec);

    //calculations
    lat_decimal_degree = lat_degree + (lat_min/60) + (lat_Sec/3600);
    long_decimal_degree = long_degree + (long_min/60) + (lon_Sec/3600);
    
    BigDecimal bdLat = new BigDecimal(lat_decimal_degree);
    BigDecimal bdLon = new BigDecimal(long_decimal_degree);
    bdLat = bdLat.round(new MathContext(8));
    bdLon = bdLon.round(new MathContext(8));
    lat_decimal_degree = bdLat.doubleValue();
    long_decimal_degree = bdLon.doubleValue();

    BigDecimal bd = new BigDecimal(Double.toString(long_decimal_degree));
    bd = bd.setScale(6);
    String lon = bd.toString();
    
    BigDecimal bd1 = new BigDecimal(Double.toString(lat_decimal_degree));
    bd1 = bd1.setScale(6);
    String lat = bd1.toString();

    //if latitude should be a negative figure.
    if(negativeLat == true){
        StringBuilder sb = new StringBuilder(lat);   
        sb.insert(0, "-");
        lat = sb.toString();
    }

    //if longitude should be a negative figure.
    if(negativeLon == true){
        StringBuilder sb = new StringBuilder(lon);   
        sb.insert(0, "-");
        lon = sb.toString();
    }
    
    negativeLat = false;
    negativeLon = false;

    latitude = lat;
    longitude = lon;

    pointCreator(latitude, longitude);
}


/* 
* the method pointCreator takes formatted coordinates to form a feature collection file.
* the file created is saved as a gesojson file, thus this method ensures the syntax for a geojson file is considered.
* @param latitude, longitude-- strings representing latitude and longitude.
*/

public static void pointCreator(String latitude, String longitude){
    String feature;
    String geometry;

    latitude = latitude.replace(" ","");
    longitude = longitude.replace(" ", "");

     String coordinate = longitude + "," + latitude;
    

    if(line < 1){
    System.out.println("\n{" + "\n\"type\":" + "\"FeatureCollection\"" + "," + "\n" + "\"features\":" + "[\n");
    }
    line++;


        feature = "{\n" + "\"type\":" + "\"Feature\"," + "\n" + "\"properties\":" + "{" + "\n" + "\"label\":" + "\"" + label + "\"," + "\n" + "\"show_on_map\":" + " true" + "},\n";
        geometry = "\"geometry\":" + "{" + "\n" + "\"type\":" + "\"Point\"," + "\n" + "\"coordinates\":" + "[" + coordinate + "]\n" + "}\n" + "}" + ",\n";
        System.out.println(feature + geometry);
    }
}