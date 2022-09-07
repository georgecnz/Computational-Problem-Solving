import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.Locale; 
import java.util.Calendar;
import java.util.regex.*;

            /* 
            * COSC326 Etude 1-- Semester 1 2022.
            * @Author George Churton. 
            * This program takes user input to determine a dates validity.
            * Dates can be parsed in numerous ways in the conventional day month year order.
            */

public class date{

                /*The Main method takes an array from a standard text file passes it to the 
                * appropriate fucntions, where its validity can be computed.
                * @param args.
                */

   public static void main(String[] args) {
   
      System.out.println("");
      System.out.println("______________________________________________________________");
      System.out.println("Reading and analysing date input from file...");
      System.out.println("");

      try{
         BufferedReader reader = new BufferedReader(new FileReader("date-input.txt"));
         String date = reader.readLine();
         while(date != null){
                    
                    //Date case (d or dd)-(m or mm)-yyyy
            if(date.matches("[0-9]{1,2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{4}")){
               checkValid(date);
            
                    //Date case (d or dd-(M or MM)-yy
            }else if(date.matches("[0-9]{1,2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{2}")){
               checkValid1(date);
            
                    //Date case (d or dd)/ (M or MM)/ yyyy
            }else if(date.matches("[0-9]{1,2}[.*//.*]{1}[0-9]{1,2}[.*//.*]{1}[0-9]{4}")){
               checkValid2(date);
            
                    //Date case (d or dd)/ (M or MM)/ yy
            }else if(date.matches("[0-9]{1,2}[.*//.*]{1}[0-9]{1,2}[.*//.*]{1}[0-9]{2}")){
               checkValid3(date);
            
                    //Date case (d or dd)-(MMM)-(yyyy)
            }else if(date.matches("[0-9]{1,2}[-]{1}[a-zA-Z]{3}[-]{1}[0-9]{4}")){
               checkValid4(date);
            
                    //Date case (d or dd)-(MMM)-(yy)
            }else if(date.matches("[0-9]{1,2}[-]{1}[a-zA-Z]{3}[-]{1}[0-9]{2}")){
               checkValid5(date);
            
                    //Date case (d or dd)<space>(MMM Uppercase)<space>(yyyy)
            }else if(date.matches("[0-9]{1,2}[ ]{1}[A-Z]{3}[ ]{1}[0-9]{4}")){
               checkValid6(date);
                    
                    //Date case (d or dd)<space>(MMM Lowercase)<space>(yyyy)
            }else if(date.matches("[0-9]{1,2}[ ]{1}[A-Z]{1}[a-z]{2}[ ]{1}[0-9]{4}")){
               checkValid_6(date);
            
                    //Date case (d or dd)<space>(MMM Upper and Lowecase)<space>(yy)
            }else if(date.matches("[0-9]{1,2}[ ]{1}[a-zA-Z]{3}[ ]{1}[0-9]{2}")){
               checkValid7(date);
            
                    //Date case (d or dd)<space>(MM)<space>(yyyy)
            }else if(date.matches("[0-9]{1,2}[ ]{1}[0-9]{1,2}[ ]{1}[0-9]{4}")){
               checkValid8(date);
            
                    //Date case (d or dd)<space>(MM)<space>(yy)
            }else if(date.matches("[0-9]{1,2}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}")){
               checkValid9(date);
            
             //Date case (d or dd)<space>(M)<space>(yy)
                    }else if(date.matches("[0-9]{1,2}[ ]{1}[0-9]{1,2}[ ]{1}[0-9]{2}")){
                        checkValid10(date);
            
            }else{
               System.out.println(date + "- INVALID: Bad input in terms of date formatting ");
            }
            date = reader.readLine();
         }
         reader.close();
         System.out.println("______________________________________________________________");
      
      }catch(IOException e){
         e.printStackTrace();
      }
   }

      /* The static method Valid converts valid dates into the format set in the specifications..
                * @param storedDate- a date stored via the java date class.
                */

   public static void ValidOutput(Date storedDate){
    try{
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        String validDate = format.format(storedDate); 
        

        System.out.println(validDate + " - This Date is Valid");
    
     }catch(Exception e){
        e.printStackTrace();
     }   
  }

      /* The logical, static method boundChecker checks if dates are within the range expected.
                * @param compareHigh- an integer representing the upper bound
                * @param compareLow- an integer representing the lower bound.
                * @param storedDate- a date stored via the java date class.
                * @param date- a string representing the 'date' or sequence entered in by users in date-input.txt
                */

   public static void boundChecker(int compareHigh, int compareLow, Date storedDate, String date){
      if (compareLow == 0) { 
        ValidOutput(storedDate);
      
      }else if(compareHigh == 0){
        ValidOutput(storedDate);
      
      } else if ((compareLow >= 0) & (compareHigh <= 0)) {
        ValidOutput(storedDate);
      
      }else{
          System.out.println(date + " - INVALID: This date is out of range.");
            }
   }

                /* The logical, static method checkValid that checks the validity of the date case (d or dd)-(m or mm)-yyyy 
                * and is called upon from the main function or method when the date case occurs 
                * @param date.
                */

   public static void checkValid(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
      
         Date lowerBound = format.parse("01-01-1753");
         Date upperBound = format.parse("01-01-3000");
      
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
      
         if(date.matches("[29]{1,2}[-]{1}[02|2]{1,2}[-]{1}[0-9]{4}")){
            leapYear(storedDate, date);
         
         }else{
            boundChecker(compareHigh, compareLow, storedDate, date);
         }
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      }   
   }

            /* The static method checkValid1 checks the validity of the date case (d or dd)- (M or MM)- yy
            *   and is called upon from the main function or method when the date case occurs 
            * @param date.
            */

   public static void checkValid1(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
         format.set2DigitYearStart(format.parse("01-01-1950"));
         format.setLenient(false);
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = '0';
         char leap3 = '2';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && (date.charAt(3)== leap2) && (date.charAt(4)== leap3)){
            leapYearTwoYears(storedDate, date);
         }else{
            ValidOutput(storedDate);
         }
      
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      } 
   }

            /* The static method checkValid2 checks the validity of the date case (d or dd)/ (M or MM)/ yyyy
            *   and is called upon from the main function or method when the date case occurs 
            * @param date.
            */

   public static void checkValid2(String date){
      try{
                        
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
                
         Date lowerBound = format.parse("01/01/1753");
         Date upperBound = format.parse("01/01/3000");
                
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
      
         if(date.matches("[29]{1,2}[.*//.*]{1}[02|2]{1,2}[.*//.*]{1}[0-9]{4}")){
            leapYear(storedDate, date);
         
         }else{
            boundChecker(compareHigh, compareLow, storedDate, date);
         }
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");  
   }
}

                /* The static method checkValid3 checks the validity of the date case (d or dd)/ (M or MM)/ yy
                *   and is called upon from the main function or method when the date case occurs 
                * @param date.
                */

   public static void checkValid3(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
         format.setLenient(false);
         format.set2DigitYearStart(format.parse("01/01/50"));
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = '0';
         char leap3 = '2';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && ((date.charAt(3)== leap2) && (date.charAt(4)== leap3) || (date.charAt(3)== leap3))){
            leapYearTwoYears(storedDate, date);
         }else{
            ValidOutput(storedDate);
         }
      
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range.");
      } 
   }
                    
                /* The static method checkValid4 checks the validity of the date case dd-MMM-yyyy
                *   and is called upon from the main function or method when the date case occurs 
                * @param date.
                */
                    
   public static void checkValid4(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
                        
         Date lowerBound = format.parse("01-Jan-1753");
         Date upperBound = format.parse("01-Jan-3000");
                        
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
                    
         if(date.matches("[29]{2}[-]{1}[a-zA-Z]{3}[-]{1}[0-9]{4}")){
            leapYear(storedDate, date);
                                
         }else{
            boundChecker(compareHigh, compareLow, storedDate, date);  
         }       
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      }
    }   
   
                            
                            /* The static method checkValid5 checks the validity of the date case dd-MMM-yy
                            *   and is called upon from the main function or method when the date case occurs 
                            * @param date.
                            */
                        
   public static void checkValid5(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
         format.set2DigitYearStart(format.parse("01-JAN-1950"));
         format.setLenient(false);
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = 'F';
         char leap3 = 'e';
         char leap4 = 'b';
      
         char leap_2 = 'F';
         char leap_3 = 'E';
         char leap_4 = 'B';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && ((date.charAt(3)== leap2) && (date.charAt(4)== leap3) && (date.charAt(5)== leap4)
                                        || (date.charAt(3)== leap_2) && (date.charAt(4)== leap_3) && (date.charAt(5)== leap_4))){
            leapYear(storedDate, date);
         
         }else{
           ValidOutput(storedDate);
         }
      
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range.");
      } 
   }
                            
                        /* The static method checkValid6 checks the validity of the date case dd MMM (upercase) yyyy.
                        *   It is called upon from the main function or method when the date case occurs. 
                        * @param date.
                        */
                            
   public static void checkValid6(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
                                
         Date lowerBound = format.parse("01 JAN 1753");
         Date upperBound = format.parse("01 JAN 3000");
                                
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = 'F';
         char leap3 = 'e';
         char leap4 = 'b';
      
         char leap_2 = 'F';
         char leap_3 = 'E';
         char leap_4 = 'B';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && ((date.charAt(3)== leap2) && (date.charAt(4)== leap3) && (date.charAt(5)== leap4)
                                        || (date.charAt(3)== leap_2) && (date.charAt(4)== leap_3) && (date.charAt(5)== leap_4))){
            leapYear(storedDate, date);
                                
        }else{
            boundChecker(compareHigh, compareLow, storedDate, date);      
        }   
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      }   
   }


                                        /* The static method checkValid_6 checks the validity of the date case dd MMM (lowercase) yyyy.
                                            *   It is called upon from the main function or method when the date case occurs. 
                                        *   @param date.
                                            */

   public static void checkValid_6(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
                                    
         Date lowerBound = format.parse("01 Jan 1753");
         Date upperBound = format.parse("01 Jan 3000");
                                    
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = 'F';
         char leap3 = 'e';
         char leap4 = 'b';
      
         char leap_2 = 'F';
         char leap_3 = 'E';
         char leap_4 = 'B';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && ((date.charAt(3)== leap2) && (date.charAt(4)== leap3) && (date.charAt(5)== leap4)
                                                || (date.charAt(3)== leap_2) && (date.charAt(4)== leap_3) && (date.charAt(5)== leap_4))){
            leapYear(storedDate, date);
                                        
         }else{
            boundChecker(compareHigh, compareLow, storedDate, date);
         }
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      }   
   }

                                            /* The static method checkValid7 checks the validity of the date case dd MMM yy.
                                            *   It is called upon from the main function or method when the date case occurs. 
                                            * @param date.
                                            */

   public static void checkValid7(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd MMM yy");
         format.set2DigitYearStart(format.parse("01 JAN 1950"));
         format.setLenient(true);
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = 'F';
         char leap3 = 'e';
         char leap4 = 'b';
      
         char leap_2 = 'F';
         char leap_3 = 'E';
         char leap_4 = 'B';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && ((date.charAt(3)== leap2) && (date.charAt(4)== leap3) && (date.charAt(5)== leap4)
                                            || (date.charAt(3)== leap_2) && (date.charAt(4)== leap_3) && (date.charAt(5)== leap_4))){
            leapYearTwoYears(storedDate, date);
         
         }else{
            ValidOutput(storedDate);
        }
                    
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range.");
      }   
   }

                                
                                            /* The static method checkValid8 checks the validity of the date case dd MMM yyyy.
                                            *   It is called upon from the main function or method when the date case occurs. 
                                            * @param date.
                                            */
   public static void checkValid8(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
         format.setLenient(false);
         Date storedDate = format.parse(date);
                                    
         Date lowerBound = format.parse("01 01 1753");
         Date upperBound = format.parse("01 01 3000");
                                    
         int compareLow = storedDate.compareTo(lowerBound);
         int compareHigh = storedDate.compareTo(upperBound);
                                
         if(date.matches("[29]{1,2}[ ]{1}[0-2]{1,2}[ ]{1}[0-9]{4}")){
            leapYear(storedDate, date);
         
         }else{
            boundChecker(compareHigh, compareLow, storedDate, date);
         }
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range or doesn't exist.");
      }   
   }



                                            /* The static method checkValid9 checks the validity of the date case (d or dd)<space>(MM)<space>(yy)
                                            *   It is called upon from the main function or method when the date case occurs. 
                                            * @param date.
                                                */
   public static void checkValid9(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd MM yy");
         format.set2DigitYearStart(format.parse("01 01 1950"));
         format.setLenient(true);
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = '0';
         char leap3 = '2';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && (date.charAt(3)== leap2) && (date.charAt(4)== leap3)){
            leapYearTwoYears(storedDate, date);
         }else{
            ValidOutput(storedDate);
        }
      
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range.");
      } 
   }

                                            /* The static method checkValid9 checks the validity of the date case (d or dd)<space>(M)<space>(yy)
                                            *   It is called upon from the main function or method when the date case occurs. 
                                        * @param date.
                                            */
   public static void checkValid10(String date){
      try{
         SimpleDateFormat format = new SimpleDateFormat("dd M yy");
         format.set2DigitYearStart(format.parse("01 01 50"));
         format.setLenient(true);
         Date storedDate = format.parse(date);
      
         char leap = '2';
         char leap1 = '9';
         char leap2 = '2';
      
         if((date.charAt(0)== leap) && (date.charAt(1)== leap1) && (date.charAt(3)== leap2)){
            leapYearTwoYears(storedDate, date);
         }else{
            ValidOutput(storedDate);
        }
      
      }catch(Exception e){
         System.err.println(date + " - INVALID: Year out of range.");
      } 
   }
                                    
                                    
                                    /* The static method leapYears calculates if a four digit year has leap properties.
                                    *   It is called upon from other methods as appropriate.
                                    * The method converts arg input into char and integer values for computation.
                                    * @param date.
                                    */

   public static void leapYear(Date storedDate, String date){

      char last = date.charAt(date.length() - 1);
      char secondLast = date.charAt(date.length() - 2);
      char thirdLast = date.charAt(date.length() - 3);
      char forthLast = date.charAt(date.length() - 4);
   
      StringBuilder sb = new StringBuilder();
      sb.append(forthLast);
      sb.append(thirdLast);
      sb.append(secondLast);
      sb.append(last);
      String yearString = sb.toString();
   
      int year = Integer.parseInt(yearString);
   
      if((year % 4 == 0) && (year <= 3000) && (year >= 1753)){
         ValidOutput(storedDate);
      }else{
         System.out.println(date + " - INVALID: Either the date is not a leap year or it is outside of the specified year paramater");
      }
   }
                            
                                /* The static method leapYearTwoYears calculates if a two digit year has leap properties.
                                    *   It is called upon from other methods as appropriate.
                                    * The method converts arg input into char and integer values for computation.
                                    * @param date.
                                    */


   public static void leapYearTwoYears(Date storedDate, String date){
    
      char last = date.charAt(date.length() - 1);
      char secondLast = date.charAt(date.length() - 2);
      char century1 = '1';
      char century_1 = '9';
      char century2 = '2';
      char century_2 = '0';
   
      StringBuilder sb = new StringBuilder();
      StringBuilder sb1 = new StringBuilder();
   
      sb.append(secondLast);
      sb.append(last);
      String yearInput = sb.toString();
      int year_Input = Integer.parseInt(yearInput);
                                        
   
      if(year_Input >= 50 ){
         sb1.append(century1);
         sb1.append(century_1);
         sb1.append(secondLast);
         sb1.append(last);
                                        
      
         String yearString = sb1.toString();
         int year = Integer.parseInt(yearString);
                                            
         if(year % 4 == 0){
           ValidOutput(storedDate);
         }else{
            System.out.println(storedDate + " - INVALID: The date is not a leap year");
         }
                                            
      }else{
         sb1.append(century2);
         sb1.append(century_2);
         sb1.append(secondLast);
         sb1.append(last);
      
         String yearString = sb1.toString();
         int year = Integer.parseInt(yearString);
      
      
         if(year % 4 == 0){
            ValidOutput(storedDate);
         }else{
            System.out.println(storedDate + " - INVALID: The date is not a leap year");
         }
      
      }
   }
}
